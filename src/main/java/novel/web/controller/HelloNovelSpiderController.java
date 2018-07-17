package novel.web.controller;

import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloNovelSpiderController {
	
	@RequestMapping(value="hello.do" ,method=RequestMethod.GET)
	@ResponseBody  //json
	public A sayHello() {
		A a=new A();
		a.setMessage("Hello,中文");
		return a;
	}
}
class A{
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
