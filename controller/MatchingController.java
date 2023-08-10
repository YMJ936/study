package com.dogwalker.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.dogwalker.domain.MatchingVO;
import com.dogwalker.service.MatchingService;
import com.dogwalker.util.PagingUtil2;

@Controller
public class MatchingController {

	private static final Logger log = LoggerFactory.getLogger(MatchingController.class);
	
	@Autowired
	private MatchingService matchingService;
	
	@RequestMapping("/rescheck")
	public ModelAndView rescheck(
	   @RequestParam(value="pageNum",defaultValue="1") int currentPage, //페이지번호
	   @RequestParam(value="viewType",defaultValue="all") String viewType, //검색분야 -> 좀 더 고민해보기
	   @SessionAttribute("id") String id
			) {
		  if(log.isDebugEnabled()) {//로그객체가 디버깅모드상태인지 아닌지를 체크
			  System.out.println("matching 요청중");//? 을 출력X
			  log.debug("currentPage:"+currentPage);//? 을 출력 가능(select ~ where num=?)
			  log.debug("viewType=>"+viewType);
		  }
		  
		  Map<String,Object> map=new HashMap<String,Object>();
		  int dog_id=matchingService.idtodog_id(id);
		  map.put("viewType", viewType);
		  map.put("id", id);
		  map.put("dog_id", dog_id);
		  //총레코드수 또는 검색된 글의 총레코드수
		 int count=matchingService.getRowCount(map);
		 System.out.println("MatchingController클래스의 count=>"+count);
		 //페이징 처리(1.현재페이지 2.총레코드수 3.페이지당 게시물수 4.블럭당 페이지수 5.요청명령어지정
		 PagingUtil2 page=new PagingUtil2(currentPage,count,10,5,"matching");
		 //start=>페이지당 맨 첫번째 나오는 게시물번호 ,end->마지막 게시물번호
		 map.put("start", page.getStartCount());//<->map.get("start")=>#{start}
		 map.put("end",  page.getEndCount());//<->map.get("end")=>#{end}
		 
		 List<MatchingVO> list=null;
		 if(count > 0) {
			 list=matchingService.mList(map);//keyField,keyWord,start,end
		 }else {
			 list=Collections.EMPTY_LIST;//0 적용
		 }
		
		ModelAndView mav=new ModelAndView("matching/ReservationCheck");//boardList.jsp
		mav.addObject("count",count);//총레코드수
		mav.addObject("list",list);//검색된 레코드리스트
		mav.addObject("pagingHtml",page.getPagingHtml());//<a href=" ~?>링크문자열
		 		
		return mav;
	}	
}
