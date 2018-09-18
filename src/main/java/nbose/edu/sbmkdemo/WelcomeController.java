package nbose.edu.sbmkdemo;

import java.net.InetAddress;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson ;

@RestController
public class WelcomeController {
    @RequestMapping( value="/app", produces = "application/json")
    public String index(HttpServletResponse  response) {
    	response.setHeader("Access-Control-Allow-Origin", "*");
        return new Gson().toJson(new ResponseVO(), ResponseVO.class) ;
    }
}

class ResponseVO{
	private String msg ; 
	private String version = "(V1)"; 
	private String hostname ;
	private String status = "up" ;
	private String hostip ;
	private String color = "#33B8FF" ; //blue
	//private String color = "#80FF33" ; //green
	
	ResponseVO(){
		try { 
			hostname = InetAddress.getLocalHost().getHostName();
			hostip   = InetAddress.getLocalHost().getHostAddress();
			msg = "Greetings " + version + " from Spring Boot Running Inside Docker in Minikube Pod !!<br/>" + LocalDateTime.now() ;
		}
		catch(Exception _ignore) {
		}
	}

	public String getMsg() {
		return msg;
	}

	public String getHostname() {
		return hostname;
	}

	public String getStatus() {
		return status;
	}

	public String getHostip() {
		return hostip;
	}

	public String getColor() {
		return color ;
	}
}