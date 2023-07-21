package com.board.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.dao.BoardDao;
import com.board.domain.BoardCommand;
import com.board.util.PagingUtil;

@Controller
public class ListController {
	//로그객체 생성(로그를 출력시키기 위해서는 매개변수(적용대상자)
	//getLogger(ListController.class) 이렇게 들어가는걸 getClass()로 써주면 클래스명변경돼도코드수정할필요x
	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private BoardDao boardDao;
	
	@RequestMapping("/board/list.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
											   @RequestParam(value="keyField",defaultValue="") String keyField,
											   @RequestParam(value="keyWord",defaultValue="") String keyWord) {
		//pageNum전달 못받았다면 default는 1   / 정상적으로 받으면 currentPage로 반환 받는다. (int 형으로 변환해서)
		//keyField(검색분야)전달 못받았다면 default는 "" (빈문자열) / 정상적으로 받으면 currentPage로 반환
		//keyField(검색분야)전달 못받았다면 default는 "" (빈문자열)
		if(log.isDebugEnabled()) {//로그객체가 디버깅모드상태라면
			System.out.println("/board/list.do 요청중...");
			log.debug("currentPage=>"+currentPage); //   =>   ? 도 출력
			log.debug("keyField=>"+keyField); 
			log.debug("keyWord=>"+keyWord); 
		}
		//검색분야,검색어 ->parameterType="map"(Map객체)
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("keyField",keyField); //#{keyField}로 받음
		map.put("keyWord",keyWord); //#{keyWord}로 받음
		//<->map.get("키명("keyField")) 똑같음=>   #{keyField} 는 getter꺼내오는것과 똑같은 효과
		
		//총 레코드 수
		int count=boardDao.getRowCount(map);
		System.out.println("ListController의 count=>"+ count);
		//페이징처리(1.현재페이지 2.총레코드수 3.페이지당 게시물수 4.블럭당 페이지수 5.요청명령어 지정
		PagingUtil page=new PagingUtil(currentPage,count,3,3,"list.do");
		//start=>페이지당 맨 첫번째 나오는 게시물번호
		//end->마지막 게시물 번호
		map.put("start", page.getStartCount());
		//<->map.get("start") =>#{start}
		map.put("end", page.getEndCount());
		
		List<BoardCommand> list=null;
		if(count > 0) {
			list=boardDao.list(map);//keyField,keyWord,start,end
		}else {//검색결과없을경우
			list=Collections.EMPTY_LIST;//0 적용. 빈 List
		}
		//페이지 이동=>데이터 공유해서 전달    이동할 페이지명
		ModelAndView mav=new ModelAndView("boardList");
		mav.addObject("count",count);//총 레코드수
		mav.addObject("list",list);//검색된 레코드 리스트
		mav.addObject("pagingHtml",page.getPagingHtml());//<a href="~"> 
		
		return mav;//boardList.jsp로 이동( 영역이름 = 페이지 이름)
	}
}
