package com.dogwalker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String main() {
		logger.info("========== MainController main::START ==========");
		
		System.out.println("TEST 01");
		
		logger.info("========== MainController main::END ==========");
		return "main/main";//폴더명/이동할 페이지명
	}
	
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		logger.info("========== MainController index::START ==========");
		
		System.out.println("TEST 01");
		
		logger.info("========== MainController index::END ==========");
		return "redirect:main";
	}
	
	
	
}
