package com.dogwalker.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dogwalker.domain.MemberVO;
import com.dogwalker.service.MemberService;
import com.dogwalker.util.FileUtil;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService member;
	
	@GetMapping("/login")//로그인 페이지로 이동
	public String loginPage(HttpSession session) {
		if(session.getAttribute("id")!=null) {return "redirect:/main";}//로그인이 이미 되어있다면 메인페이지로 이동
		return "/member/login";
		
	}

	@PostMapping("/login")//로그인 아이디 비밀번호 제출 
	public String login(@RequestParam(value="id") String id, //로그인페이지 아이디 입력칸으로 부터 입력받은값
						@RequestParam(value="pw") String pw, //로그인페이지 암호 입력칸으로 부터 입력받은값		
						HttpSession session,
						Model model)throws Exception{
		System.out.println("id=>"+id);
		System.out.println("pw=>"+pw);
		System.out.println(member.login(id));
		if(!pw.equals(member.login(id))) {//비밀번호 일치하지않을경우 로그인창으로 이동
			//model.addAttribute("isloginSuccess", "fail");//로그인 실패 String 전달
			return "redirect:/login?isloginSuccess=fail";
		}

		session.setAttribute("id", id);//로그인 성공한 경우
		return "redirect:/main";
	}
	
	@GetMapping("/logout") //로그아웃기능
	public String logout() {
		return "/member/logout";
	}
	
	@GetMapping("/signup") //회원가입창으로 이동 
	public String goSignupPage() {
		return "/member/signup";
	}
	
	@PostMapping("/signup") //회원가입창에서 입력후 제출
	public String signup(MemberVO vo) {
		System.out.println(vo);
		try {
			String NewfileName="";
			if(!vo.getUpload().isEmpty()) {
			NewfileName=FileUtil.rename(vo.getUpload().getOriginalFilename());
			System.out.println("NewfileName=>"+NewfileName);
			vo.setPhoto(NewfileName);
			System.out.println(vo);
			}
			
			member.signup(vo);//회원가입
			
			if(!vo.getUpload().isEmpty()) {
				File file=new File(FileUtil.UPLOAD_PATH+"/"+NewfileName);
				vo.getUpload().transferTo(file);//실제 파일 전송
			}

		}catch(IOException e1) {
			e1.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		
		return "redirect:/login";
	}
}
