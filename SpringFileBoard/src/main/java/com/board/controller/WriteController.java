package com.board.controller;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.dao.BoardDao;
import com.board.domain.BoardCommand;
import com.board.util.FileUtil;
import com.board.validator.BoardValidator;

@Controller
public class WriteController {
	//로그객체 선언
	private Logger log=Logger.getLogger(getClass());
	
	@Autowired
	private BoardDao boardDao;
	
	/*
	 * 하나의 요청명령어당 하나의 컨트롤러만 사용하는것이 아니다!!
	 * 하나의 컨트롤러=>여러개의 요청명령어를 등록해서 사용이 가능
	 * ex ) writeForm.do  write.do   
	 * 같은 요청명령어를 GET or POST로 전송할지를 결정
	 * 
	 */
	//1.글쓰기 폼으로 이동(Get방식)
	@RequestMapping(value="/board/write.do",method=RequestMethod.GET)
	public String form() {//메서드명은 마음대로 작성
		System.out.println("다시 처음부터 값을 입력받기위해서");
		return "boardWrite";//return "이동할 페이지명"
	}
	//2.에러메세지 출력->다시 초기화가 가능하게 설정
	//@ModelAttribute("커맨드객체 별칭")		왜 별칭을 굳이?=>나중에 boardWrite.jsp에서 사용
	@ModelAttribute("command")
	public BoardCommand forBacking() {
		//형식) 반환형(DTO or VO형) 임의의 메서드명
		System.out.println("forBacking()호출됨");
		return new BoardCommand();//return new UserVO()
	}
	//3.입력해서 유효성 검사 -> 에러발생      명령어가 "/board/write.do"로 같을때 GET과 POST로 구분
	@RequestMapping(value="/board/write.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") BoardCommand com,
									BindingResult result) {
		if(log.isDebugEnabled()) {
			System.out.println("/board/write.do (post) 요청중");
			log.debug("BoardCommand=>"+com);//com.toString
			//로그객체명.debug(출력대상자)
		}
		//유효성검사->jQuery
		new BoardValidator().validate(com,result);
		//에러정보가 있다면
		if(result.hasErrors()) {
			return form();//"boardWrite"; // return "이동할 페이지명"
		}
		try{
			String newName="";//업로드한 파일의 변경된 파일명저장
			//업로드 되어있다면
			if(!com.getUpload().isEmpty()) {
				//탐색기->aaa.txt ->getOriginalFileName()
				newName=FileUtil.rename(com.getUpload().getOriginalFilename());
				System.out.println("newName=>"+newName);
				//VO에 변경한값을 넣어야 테이블에도 반영될수있음
				com.setFilename(newName);//수동변경->수동저장
			}
			
			//최댓값+1=>수동으로 계산,저장
			int newSeq=boardDao.getNewSeq()+1;
			System.out.println("newSeq=>"+newSeq);
			com.setSeq(newSeq);//수동으로 저장->입력 x
			//글쓰기 메서드 호출
			boardDao.insert(com);//#{seq} ->com.getSeq()
			//업로드하는 경우
			if(!com.getUpload().isEmpty()) {
				File file=new File(FileUtil.UPLOAD_PATH+"/"+newName);
				//물리적으로 데이터전송(파일전송)
				com.getUpload().transferTo(file);//파일업로드로 전송
			}
		}catch(IOException e) {
			e.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		//response.sendRedirect("/list.do"); 이렇게했던 jsp와 달리 
		//return "redirect:/요청명령어";      ->  return 이동할페이지명
		return "redirect:/board/list.do";
	}
}