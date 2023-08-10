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

import com.dogwalker.domain.BoardVO;
import com.dogwalker.service.BoardService;
import com.dogwalker.util.FileUtil;


@Controller
public class WriteController {

	//로그객체 생성문
	//private Logger log=Logger.getLogger(WriteController.class);//로그를 처리할 클래스명
	private static final Logger log = LoggerFactory.getLogger(DetailController.class);//현재클래스명을 불러와서 지정
	
	@Autowired
	private BoardService boardService;//자동적으로 Setter Methdo호출X (의존성객체 넣어줌)
	
	/*
	 * 하나의 요청명령어=>하나의 컨트롤러만 사용X
	 * 하나의 컨트롤러=>여러개의 요청명령어를 등록해서 사용이 가능(ex writeForm.do,write.do)
	 * 같은 요청명령어를 GET or POST으로 전송할지를 결정하는 속성
	 *        write.do(페이지 이동)                write.do
	 * method=RequestMethod.GET        method=RequestMethod.POST
	 */
	//1.글쓰기 폼으로 이동(GET방식)
	@RequestMapping(value="write",method=RequestMethod.GET)
	public String form() { //메서드명은 임의로 작성
		System.out.println("다시 처음부터 값을 입력을 받기위해서(초기화) form()호출됨");
		return "board/boardWrite"; //return "이동할 페이지명" //definition name과 동일
	}
	
	//3.입력해서 유효성검사->에러발생
	//BindingResult->유효성검사때문에 필요=>에러정보객체를 저장
	@RequestMapping(value="write",method=RequestMethod.POST)
	public String submit(@ModelAttribute("VO") BoardVO vo) {
	  
		  if(log.isDebugEnabled()) {//로그객체가 디버깅모드상태인지 아닌지를 체크
			  System.out.println("/write 요청중(post)");//? 을 출력X
			  log.debug("BoardCommand:"+vo);//? 을 출력 가능(select ~ where num=?)
	          //로그객체명.debug(출력대상자)
		  }
		  
		  //글쓰기 및 업로드=>입출력=>예외처리
		  try {
			  String newName="";//업로드한 파일의 변경된 파일명을 저장
			  //업로드되어있다면
			  if(!vo.getUpload().isEmpty()) {
				  //탐색기에서 선택한 파일->getOriginalFileName() aaaa.txt->1234444
				  newName=FileUtil.rename(vo.getUpload().getOriginalFilename());//a.txt
				  System.out.println("newName=>"+newName);
				  //DTO에 변경=>테이블에서도 변경저장
				  vo.setFilename(newName);//springboard2->filename필드명 123ddd.txt
			  }
			  //최대값+1
			  int newSeq=boardService.getNewSeq()+1;//primary key때문에 유효성검사에러유발
			  System.out.println("newSeq=>"+newSeq);
			  //게시물번호=>계산->저장
			  vo.setSeq(newSeq);//1->2
			  //글쓰기 호출
			  boardService.insert(vo);//DB상에 반영(<insert>~</insert>)
			  //실제로 upload폴더로 업로드한 파일을 전송(복사)
			  if(!vo.getUpload().isEmpty()) {
				  File file=new File(FileUtil.UPLOAD_PATH+"/"+newName);
				  //물리적으로 데이터전송(파일전송)
				  vo.getUpload().transferTo(file);//파일업로드위치로 전송
			  }
		  }catch(IOException e) {
			  e.printStackTrace();
		  }catch(Exception e2) {
			  e2.printStackTrace();
		  }
	
		//return "redirect:/요청명령어"=>return "이동할 페이지"
		return  "redirect:list";
	}	
}
