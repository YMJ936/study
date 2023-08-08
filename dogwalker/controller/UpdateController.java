package com.dogwalker.controller;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dogwalker.domain.BoardVO;
import com.dogwalker.service.BoardService;
import com.dogwalker.util.FileUtil;

@Component
@Controller
public class UpdateController {

	//로그객체 생성문
	private static final Logger log = LoggerFactory.getLogger(DetailController.class);//현재클래스명을 불러와서 지정
	
	@Autowired
	private BoardService boardService;//자동적으로 Setter Methdo호출X (의존성객체 넣어줌)
	
	//1.글수정 폼으로 이동(GET방식)->반환값(단순 페이지 이동=>String)
	//페이지이동->데이터 출력->ModelAndView
	@RequestMapping(value="update",method=RequestMethod.GET)
	public ModelAndView form(@RequestParam("seq") int seq) { //메서드명은 임의로 작성
		System.out.println("다시 처음부터 값을 입력을 받기위해서(초기화) form()호출됨");
		BoardVO boardVO=boardService.selectBoard(seq);
		//1.이동할페이지명(확장자생략),2.키명.3.전달할값
		return new ModelAndView("board/boardModify","VO",boardVO);
	}
	
	//2.입력해서 유효성검사->에러발생
	//BindingResult->유효성검사때문에 필요=>에러정보객체를 저장
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String submit(@ModelAttribute("VO") BoardVO vo) {
	  
		  if(log.isDebugEnabled()) {//로그객체가 디버깅모드상태인지 아닌지를 체크
			  System.out.println("/update.do 요청중(post)");//? 을 출력X
			  log.debug("BoardVO:"+vo);//? 을 출력 가능(select ~ where num=?)
	          //로그객체명.debug(출력대상자)
		  }
		  //변경전의 데이터를 불러오기->board(비밀번호)=웹상에서의 입력비밀번호
		  BoardVO board=null;
		  String oldFileName="";//변경전 파일명
		  board=boardService.selectBoard(vo.getSeq());
		  //비밀번호체크(DB상의 암호!=웹상에서의 입력한 암호)
		  if(!board.getPwd().contentEquals(vo.getPwd())) {
			  //비밀번호가 틀렸을 때 프론트에서 보여줄 것이 필요하다.
			  return "board/boardModify";//이동해서 다시입력받게 한다.
		  }else {//비밀번호가 맞다면
	        /*
	         * 기본파일명->업로드된파일이 존재->기존파일 삭제->새로운파일 세팅 업로드돼야한다.
	         */
			  oldFileName=board.getFilename();//DB상의 원래 기존파일명
			  //업로드되어있다면
			  if(!vo.getUpload().isEmpty()) {
				 try {
				  vo.setFilename(FileUtil.rename(vo.getUpload().getOriginalFilename()));
				 }catch(Exception e) {e.printStackTrace();}
			  }else {//새로운 파일로 업로드 하지 않은경우(기존파일은 덮어쓰기)
				  vo.setFilename(oldFileName);
			  }
			 
			  //글수정 호출
			  boardService.update(vo);//DB상에 반영(<insert>~</insert>)
			  //실제로 upload폴더로 업로드한 파일을 전송(복사)
			  if(!vo.getUpload().isEmpty()) {
				  try {
				  File file=new File(FileUtil.UPLOAD_PATH+"/"+vo.getFilename());
				  //물리적으로 데이터전송(파일전송)
				  vo.getUpload().transferTo(file);//파일업로드위치로 전송
				  }catch(IOException e) {
					  e.printStackTrace();
				  }catch(Exception e2) {
					  e2.printStackTrace();
				  }
		       //기존파일은 삭제하는 구문이 필요
				if(oldFileName!=null) {
					FileUtil.removeFile(oldFileName);
				}
		     }// if(!com.getUpload().isEmpty()) {
		 }//else 암호가 맞다면
		return  "redirect:list";
	}	
}
