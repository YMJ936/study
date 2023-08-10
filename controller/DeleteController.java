package com.dogwalker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dogwalker.domain.BoardVO;
import com.dogwalker.service.BoardService;
import com.dogwalker.util.FileUtil;


@Controller
public class DeleteController {

	//로그객체 생성문
	private static final Logger log = LoggerFactory.getLogger(DetailController.class);//현재클래스명을 불러와서 지정
	
	@Autowired
	private BoardService boardService;

	
	@RequestMapping(value="delete",method=RequestMethod.GET)
	public ModelAndView form(@RequestParam("seq") int seq) { //메서드명은 임의로 작성
		//1.이동할페이지명(확장자생략),2.키명.3.전달할값
		return new ModelAndView("board/boardDelete","seq",seq);
	}
	
	//2.입력해서 유효성검사->에러발생
	@RequestMapping(value="delete",method=RequestMethod.POST)
	//public String submit(@ModelAttribute("command") BoardCommand com,@RequestParam("pwd") String pwd) {
	public String submit(@RequestParam("seq") int seq,@RequestParam("pwd") String pwd) {
		  if(log.isDebugEnabled()) {//로그객체가 디버깅모드상태인지 아닌지를 체크
			  System.out.println("/delete 요청중(post)");//? 을 출력X
			  log.debug("seq=>"+seq);//? 을 출력 가능(select ~ where num=?)
			  log.debug("pwd=>",pwd);
	          //로그객체명.debug(출력대상자)
		  }

		  //변경전의 데이터를 불러오기->board(비밀번호)=웹상에서의 입력비밀번호
		  BoardVO board=null;
		  board=boardService.selectBoard(seq);
		  //비밀번호체크(DB상의 암호!=웹상에서의 입력한 암호)
		  if(!board.getPwd().contentEquals(pwd)) {
			  return "board/boardDelete?seq=seq";//이동해서 다시입력받게 한다.
		  }else {//비밀번호가 맞다면
			  //글삭제 호출
			  boardService.delete(seq);//DB상에 반영(<delete>~</delete>)
			  //실제로 upload폴더로 업로드한 파일을 전송(복사)
			 // if(!com.getUpload().isEmpty()) {
		       //업로드한 파일까지 삭제
				if(board.getFilename()!=null) {
					FileUtil.removeFile(board.getFilename());
				}
		    // }// if(!com.getUpload().isEmpty()) {
		 }//else 암호가 맞다면 
		return  "redirect:list";
	}	
}