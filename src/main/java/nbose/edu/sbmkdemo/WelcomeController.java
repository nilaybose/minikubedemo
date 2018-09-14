package nbose.edu.sbmkdemo;

import java.net.InetAddress;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson ;

@RestController
public class WelcomeController {
    @RequestMapping( value="/app", produces = "application/json")
    public String index() {
        return new Gson().toJson(new ResponseVO(), ResponseVO.class) ;
    }
}

class ResponseVO{
	private String msg = "Greetings from Spring Boot Running Inside Docker in Minikube Pod !!" ;
	private String hostname ;
	private String status = "up" ;
	private String hostip ;
	
	ResponseVO(){
		try { 
			hostname = InetAddress.getLocalHost().getHostName();
			hostip   = InetAddress.getLocalHost().getHostAddress();
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
}