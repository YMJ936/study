package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.dao.BoardDAO;
import com.board.domain.BoardVO;

@Controller
public class ListController {

		@Autowired
		private BoardDAO boardDAO;//자동적으로 Setter Methdo호출X (의존성객체 넣어줌)
		
		@RequestMapping("/list.do")
		public ModelAndView process() {
			System.out.println("List컨트롤러 실행");
			 List<BoardVO> list=boardDAO.list();
			
			ModelAndView mav=new ModelAndView("list");//boardList.jsp
			mav.addObject("list",list);
			
			return mav;
		}	
}
