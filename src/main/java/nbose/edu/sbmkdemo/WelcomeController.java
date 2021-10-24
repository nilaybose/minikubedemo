package nbose.edu.sbmkdemo;

import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson ;

@RestController
public class WelcomeController {
	Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	
    @RequestMapping( value="/app", produces = "application/json")
    public String index(HttpServletResponse  response) {
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	ResponseVO resp = new ResponseVO() ;
    	String json = new Gson().toJson(resp, ResponseVO.class) ;
    	logger.info(resp.getDataForLogging());
        return json ;
    }

	@RequestMapping( value="/sap/**")
	public ResponseEntity<String>dump(HttpServletRequest request) {
		String result = "";
		try {
			result = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
		}
		catch (Exception ex){
			result = "Error" ;
		}
		System.out.println( "Request Received: " + request.getRemoteHost()
				+ ", path - " + request.getRequestURI() + ", body - " + result);
		return new ResponseEntity<>(HttpStatus.OK) ;
	}

	@RequestMapping( value="/health")
	public ResponseEntity<String>health(HttpServletRequest request) {
		return new ResponseEntity<>(HttpStatus.OK) ;
	}
}

class ResponseVO{
	private String traceid = "" + ThreadLocalRandom.current().nextInt(0, 1000000);;
	private String msg ; 
	private String version = "(V2)"; 
	private String hostname ;
	private String status = "up" ;
	private String hostip ;
	//private String color = "blue" ; //blue
	private String color = "green" ; //green
	
	public String getDataForLogging() {
		return "'{\"event\":\"" + "version=" + version + ", hostname=" + hostname + ", color=" + color + ", msg=" + msg + ", traceid=" + traceid +", hostip=" 
				+ hostip + "\"}'" ;
	}
	
	ResponseVO(){
		try { 
			Thread.sleep(3000L) ;
			hostname = InetAddress.getLocalHost().getHostName();
			hostip   = InetAddress.getLocalHost().getHostAddress();
			msg = "Greetings " + version + " from Spring Boot Running Inside Docker in GKE Pod !! [" + LocalDateTime.now() + "]";
		}
		catch(Exception _ignore) {
		}
	}
	
	public String getTraceid() {
		return traceid;
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