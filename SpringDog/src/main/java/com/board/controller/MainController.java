package com.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping(value = "/main.do")
	public String home() {
		System.out.println("main컨트롤러 실행");
		return "main";
	}
	
	
	
}
