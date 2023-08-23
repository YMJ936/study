package com.dogwalker.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.dogwalker.domain.PointVO;
import com.dogwalker.service.PointService;
import com.dogwalker.util.PagingUtil;

@Controller
public class PointController {
	
	@Autowired
	private PointService pointService;
	
	//멍코인 내역 페이지로 이동
		@RequestMapping("pointcheck")
		public ModelAndView pointcheck(
		   @RequestParam(value="pageNum",defaultValue="1") int currentPage, //페이지번호
		   @SessionAttribute("id") String id
				) {
			  
			 Map<String,Object> map=new HashMap<String,Object>();
			  
			  System.out.println("id=>"+id);
			  //총레코드수 또는 검색된 글의 총레코드수
			 int count=pointService.pointCount(id);
			 System.out.println("PointController클래스의 count=>"+count);
			 //페이징 처리(1.현재페이지 2.총레코드수 3.페이지당 게시물수 4.블럭당 페이지수 5.요청명령어지정
			 PagingUtil page=new PagingUtil(currentPage,count,10,5,"pointcheck");
			 //start=>페이지당 맨 첫번째 나오는 게시물번호 ,end->마지막 게시물번호
			 map.put("id", id);
			 map.put("start", page.getStartCount());//<->map.get("start")=>#{start}
			 map.put("end",  page.getEndCount());//<->map.get("end")=>#{end}
			 
			 List<PointVO> list=null;
			 if(count > 0) {
				 list=pointService.pointlist(map);//keyField,keyWord,start,end
			 }else {
				 list=Collections.EMPTY_LIST;//0 적용
			 }
			
			int nowbalance=pointService.getnowbalance(id);
			ModelAndView mav=new ModelAndView("member/mypage_dogcoin_uh");//boardList.jsp
			mav.addObject("count",count);//총레코드수
			mav.addObject("list",list);//검색된 레코드리스트
			mav.addObject("nowbalance", nowbalance);
			mav.addObject("pagingHtml",page.getPagingHtml());//<a href=" ~?>링크문자열*/
			 		
			return mav;
		}
		
		//코인충전 페이지로 가기
		@RequestMapping("chargeform")
		public String chargeform(@SessionAttribute("id") String id,Model model) {
			
			int nowbalance=pointService.getnowbalance(id);
			model.addAttribute("nowbalance", nowbalance);
			return "member/mypage_dogcoin_fee";
		}
		
}
