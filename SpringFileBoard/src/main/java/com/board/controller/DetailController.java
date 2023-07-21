package com.board.controller;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.dao.BoardDao;
import com.board.domain.BoardCommand;
import com.board.util.FileUtil;
import com.board.util.StringUtil;

@Controller
public class DetailController {
	//자기클래스 내부의 처리과정을 출력하기위해서 필요
	private Logger log=Logger.getLogger(getClass());
	
	@Autowired
	private BoardDao boardDao;//byType을 이용해서 자동 주입 (Injection)
	
	//   /board/detail.do?seq=4
	@RequestMapping("/board/detail.do")
	public ModelAndView process(@RequestParam("seq") int seq) { //seq를 int로 형변환해서 받은거임
		//int seq=Integer.parseInt(request.getParameter("seq")와 똑같음 =>RequestParam 어노테이션
		if(log.isDebugEnabled()) {//로그객체가 작동중이라면(=디버깅상태라면)  (=모니터링상태라고 함)
			log.debug("seq=>"+seq);
		}
		//1.조회수 증가
		boardDao.updateHit(seq);
		BoardCommand board=boardDao.selectBoard(seq);
		//글내용   \r\n(줄바꿈)   aaa  \r\n   ->  <br>옛날방식			<pre></pre> 요즘방식
		board.setContent(StringUtil.parseBr(board.getContent()));
		//   \r\n 줄바꿈을 <br>로 변경하는 메서드   (옛날방식이라 지금은 사용  x)
		
		/* 이전 방식
		ModelAndView mav=new ModelAndView("boardView");
		mav.addObject("board",board);//${board}사용하기위해 key,value 같게 했음 
														( ${abc}이런식이면 무슨값인지못알아봄)
		return mav;
		*/
		// 새로운 방식  ( 1.이동할페이지명  2.전달할키명  3.전달할값  )
		return new ModelAndView("boardView","board",board);

	}
	//글상세보기와 연관(다운로드)
	@RequestMapping("/board/file.do")
	public ModelAndView download(@RequestParam("filename") String filename) {
		//1.다운로드 받을 파일의 위치와 이름을 알아야된다.
		File downloadFile=new File(FileUtil.UPLOAD_PATH+"/"+filename);
		//2.스프링에서 다운로드 받는 뷰 따로 작성 -> AbstractView 상속
		//1)다운로드 받을 뷰객체   2)모델객체명(키명)   3)전달할값(다운로드 받을 파일명)
		//1)이동할 페이지 x (jsp 페이지 x)
		return new ModelAndView("downloadView","downloadFile",downloadFile);
	}
	
}
