package com.board.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

//다운로드를 따로 처리해줄 수 있는 전용 뷰 클래스
public class DownloadView extends AbstractView {

	public DownloadView() {
		//다운로드 받는 화면으로 자동으로 전환
		setContentType("application/download"); //text/html 이면 다운로드가 안됨 
	}
	
	//모델값(화면에 출력할 대상자)을 매개변수로 전달받아서 처리해주는 메서드
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

			
		/*
		 * return new ModelAndView
		 * ("downloadView","downloadFile",downloadFile);
		 */
		//1.다운로드 받을 파일의 정보 얻어오기
			File file=(File)model.get("downloadFile");
			System.out.println("file=>"+file);
		//2.다운받을 파일의 타입,크기를 지정
			response.setContentType(getContentType());//위에서 이미("application/download")으로 설정해줬기때문에
			response.setContentLength((int)file.length()); //파일의 길이(크기) 
		//브라우저별로 한글처리->User-Agent(브라우저정보가 저장된 head값)
			String userAgent=request.getHeader("User-Agent");
			System.out.println("userAgent=>"+userAgent);//MSIE
			boolean ie=userAgent.indexOf("MSIE") > -1;//못찾으면 -1 ,찾았으면 -1보다 클것이므로 TRUE
			String fileName=null;
			if(ie) {							//1.파일명 2.캐릭터셋(utf-8)
				fileName=URLEncoder.encode(file.getName(),"utf-8");
			}else {//영문
				fileName=new String(file.getName().getBytes("utf-8"),"iso8859-1");
			}
			//대화상자에서 원하는 위치를 다운로드 대화상자에서 설정
			//Content-Disposition   -> 다운로드 받는 위치
			//attachment;filename=다운로드받을 파일명
			//exe,bat=>이진파일    =>Content-Transfer-Encoding
			//									binary
			response.setHeader("Content-Disposition","attachment;filename=\""+fileName+"\";");//다운받을 위치  탈출문자사용
			response.setHeader("Content-Transfer-Encoding", "binary");//원래는 보안때문에 안되지만 이진파일도 받을수있게 설정
			//입출력객체를 만들어서 전송
			OutputStream out=response.getOutputStream();
			FileInputStream fis=null;
			try {
				fis=new FileInputStream(file);//다운로드 받는 파일정보
				//서버로부터 파일을 읽어들여서 다운로드 받음->복사 
				//1.다운로드받는쪽 입력객체명   2.보내주는 서버의 출력객체
				FileCopyUtils.copy(fis, out);//다운로드 받는쪽
			}catch(IOException e) {
				e.printStackTrace();
			}finally {//예외처리와 상관없이 반드시 처리해야할 구문
				if(fis!=null) {
					try {
						fis.close();
					}catch(IOException e) {e.printStackTrace();}
				}
				out.flush();//입출력->버퍼에 저장해서 꽉차면 보내는 방식이 아니라 바로 보낼것 =>flush() 바로출력
			}
	}

}
