package nbose.edu.sbmkdemo;

import java.net.InetAddress;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson ;

@RestController
public class WelcomeController {
	Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	
    @RequestMapping( value="/app", produces = "application/json")
    public String index(HttpServletResponse  response) {
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	String json = new Gson().toJson(new ResponseVO(), ResponseVO.class) ;
    	System.out.println(json);
    	logger.info(json);
        return json ;
    }
}

class ResponseVO{
	private String msg ; 
	private String version = "(V1)"; 
	private String hostname ;
	private String status = "up" ;
	private String hostip ;
	private String color = "blue" ; //blue
	//private String color = "green" ; //green
	
	ResponseVO(){
		try { 
			Thread.sleep(4000L) ;
			hostname = InetAddress.getLocalHost().getHostName();
			hostip   = InetAddress.getLocalHost().getHostAddress();
			msg = "Greetings " + version + " from Spring Boot Running Inside Docker in Minikube Pod !! [" + LocalDateTime.now() + "]";
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