package com.example.demo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@RestController
public class SmController {

	@Autowired
	private ParseSMResponse parseResponse;

	@RequestMapping(value="/smlatest",method = RequestMethod.GET)
	public Response getData() {
		return parseResponse.getAllParsedResponses();
	}
	
	
	@RequestMapping(value="/hello",method = RequestMethod.GET)
	    public String sayHi() {
	        return "hi"; 
	    }

}
