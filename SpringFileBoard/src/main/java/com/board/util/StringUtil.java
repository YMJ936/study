package com.board.util;
//아주옛날에 썼던 방식 (그냥 참고만)
//<pre></pre> 가 있어서 이제는 이 방식이 필요없음
public class StringUtil {
	public static String parseBr(String msg){
		
		if(msg == null) return null;
		//여러줄 입력한 문자열의 엔터를 구분하는 메서드
		return msg.replace("\r\n", "<br>")
                  .replace("\n", "<br>");
	}
}
