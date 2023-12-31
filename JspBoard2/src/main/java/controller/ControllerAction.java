package controller;

import java.io.*;//FileInputStream
import java.util.*;//Map,Properties
import javax.servlet.*;
import javax.servlet.http.*;
//추가->다른 패키지의 클래스나 인터페이스를 참조
import action.CommandAction;

public class ControllerAction extends HttpServlet {
	
    //명령어와 명령어 처리클래스를 쌍으로 저장
    private Map commandMap = new HashMap();
    
	//서블릿을 실행시 서블릿의 초기화 작업->생성자
    public void init(ServletConfig config) throws ServletException {
    	
  //경로에 맞는 CommandPro.properties파일을 불러옴
    String props = config.getInitParameter("propertyConfig");
    System.out.println("불러온경로="+props);
    
  //명령어와 처리클래스의 매핑정보를 저장할 Properties객체 생성
    Properties pr = new Properties();
    FileInputStream f = null;//파일불러올때 
    
        try {
           //CommandPro.properties파일의 내용을 읽어옴
        	f=new FileInputStream(props);
           
        	//파일의 정보를 Properties에 저장
        	pr.load(f);
        	
        }catch(IOException e){
          throw new ServletException(e);
        }finally{
        if(f!=null) try{f.close();}catch(IOException ex){}	
        }
        	
     //객체를 하나씩 꺼내서 그 객체명으로 Properties
     //객체에 저장된 객체를 접근 => list.do=action.ListAction
     Iterator keyiter = pr.keySet().iterator();
     
     while(keyiter.hasNext()){
       //요청한 명령어를 구하기위해
       String command = (String)keyiter.next(); //메모리에 올리는 것은 전부 Object형이기 때문에 형변환을 시켜야 한다.
       System.out.println("command="+command); // list.do가 찍히는지 확인해야 한다.
       //요청한 명령어(키)에 해당하는 클래스명을 구함
       String className=pr.getProperty(command);
       System.out.println("className="+className);
       // action.ListActon이 콘솔창에 찍히는지 확인하기
       try{
       //그 클래스의 객체를 얻어오기위해 메모리에 로드
       Class commandClass = Class.forName(className);
       System.out.println("commandClass="+commandClass);
       Object commandInstance = commandClass.newInstance();
       System.out.println
              ("commandInstance="+commandInstance);//주소값(저장된 위치)이 출력되는지 확인
      
       //Map객체 commandMap에 저장
       commandMap.put(command, commandInstance);
       System.out.println("commandMap="+commandMap);
       
            } catch (ClassNotFoundException e) {
                throw new ServletException(e);
            } catch (InstantiationException e) {
                throw new ServletException(e);
            } catch (IllegalAccessException e) {
                throw new ServletException(e);
            }
        }//while
    }

    public void doGet(//get방식의 서비스 메소드
                     HttpServletRequest request, 
                     HttpServletResponse response)
    throws ServletException, IOException {
    	    process(request,response);
    }

    protected void doPost(//post방식의 서비스 메소드
                     HttpServletRequest request, 
                     HttpServletResponse response)
    throws ServletException, IOException {
    	    process(request,response);
    }

    //사용자의 요청을 분석해서 해당 작업을 처리
    private void process(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException {
    	String view=null;//요청명령어에 따라서 최종적으로 이동할 페이지 저장
    	//  /list.do=action.ListAction
    	//  /writeForm.do=action.WriteFormAction ,,,
    	//ListAction com=new ListAction();
    	//WriteFromAction com=new WriteFromAction() ,,, ->모델1으로 코딩할 경우 이렇게 계속해서 객체를 만들어야 한다.
    	CommandAction com=null;
    	//부모-> 어떠한 자식클래스의 객체라도 자동으로 부모형으로 형변환 된다.
    	//CommandAction com=new ListAction();
    	//CommandAction com=new WriteFormAction()
    	try {
    		//1.요청명령어 분리( /JspBoard2/list.do)
    		String command=request.getRequestURI();
    		System.out.println("request.getRequestURI()=>"+request.getRequestURI());
    		System.out.println("request.getContextPath()=>"+request.getContextPath());
    	    // /JspBoard2/list.do
    	    // /JspBoard2 
    		//=> 문자열을 서로 비교했을 때 일치하는 위치가 몇 번째부터인지 아래에 숫자로 적기
    	if(command.indexOf(request.getContextPath())==0) {
    		command=command.substring(request.getContextPath().length());
    		System.out.println("실질적인 command=>"+command); // /list.do가 찍히면 성공
    	}
    	//요청명령어를 알아냈으면 -> /list.do->action.ListAction객체 -> requestPro()를 호출하기 위해서 필요하다
    	com=(CommandAction)commandMap.get(command); // /list.do
    	System.out.println("com=>"+com);//action.ListAction@주소값 -> 얻으면 성공
    	view=com.requestPro(request, response);
    	System.out.println("view=>"+view); // /list.jsp

    	}catch(Throwable e) {
    		throw new ServletException(e);//서블릿 예외처리
    	}
    	//위에서 분류한 요청명령어를 해당하는 view로 데이터를 공유시키면서 이동시키기 -> forward()필요하다.
    	RequestDispatcher dispatcher=request.getRequestDispatcher(view);// "/list.jsp"
    	dispatcher.forward(request, response);
    	
    	
    }
}







