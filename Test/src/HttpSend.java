import java.awt.Color;
import java.net.URI;

import org.apache.log4j.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpSend {
	
	private final static Logger log = Logger.getLogger(HttpSend.class);
	
	public static final String CMD_START =         "START";
	public static final String CMD_STOP =          "STOP";
	public static final String CMD_INFOS =         "INFOS";
	public static final String CMD_TURN_RIGHT =    "TURN_RIGHT";
	public static final String CMD_TURN_LEFT =     "TURN_LEFT";
	public static final String CMD_CHECK_AROUND =  "CHECK_AROUND";
	public static final String CMD_MOVE_TILT_PAN = "MOVE_TILT_PAN";
		
    private static String szparam = "";
    private static String[] szFieldnb;
    private static String[] szFieldvalue;
    
    private static int Status = 0;
	
public HttpSend(String szcmd[]) throws Exception {
    log.debug("Start");   

    URIBuilder builder = new URIBuilder();
    builder.setScheme("http").setHost(RobotWindow.textIP.getText()).setPort(Integer.parseInt(RobotWindow.textPort.getText())).setPath(Robot.Httppath);
    builder.addParameter("CMD",szcmd[0]);
    szparam= ""; 
    if (szcmd.length > 1) {
    	for (int i=1; i <szcmd.length; i++) {
	    	log.debug("szcmd [" + i +"]= "+ szcmd[i]);
	    	szparam = szparam + szcmd[i] + ";";
	    	log.debug(szparam);
	    }
	    log.debug(szparam);
	    builder.addParameter("PARAM",szparam);	    	
    }
    URI uri = builder.build();
    log.debug(uri); 
    //GET /Robot?CMD=MOVE_TILT_PAN&PARAM=122%3B118%3B HTTP/1.1[\r][\n]

    try {       
	    HttpClient httpclient = new DefaultHttpClient(); 
	    

	    HttpGet httpget = new HttpGet(uri);
	    HttpResponse response = httpclient.execute(httpget);
	    log.debug("response:"+ response);
	    
	    szFieldnb = new String[10];
	    szFieldvalue = new String[10];

	    String s = response.toString();
	    //String s = "jhlflfmlflmField587:123;Field588:456;";
	    log.debug(response.getStatusLine());
	    Status = response.getStatusLine().getStatusCode();
	    if (Status == HttpStatus.SC_OK)
	    {
			RobotWindow.labelHttpStatus.setText("HttpStatus: OK");
			RobotWindow.labelHttpStatus.setForeground(Color.green);
	    	int Field = s.indexOf("Field");
		    int column;
		    int semicolumn;
		    for(int i =0; Field != -1; i++) {	    
		    	column = s.indexOf(":",Field);
		    	szFieldnb[i]= s.substring(Field+5, column);
		    	semicolumn = s.indexOf(";",column);
		    	szFieldvalue[i]= s.substring(column+1, semicolumn);
		    	log.debug("Field" + szFieldnb[i] + ":" + szFieldvalue[i]);
		    	Field = s.indexOf("Field", semicolumn);
		    }
	    }
	    else
	    {	
	    	RobotWindow.labelHttpStatus.setText("HttpStatus:"+response.getStatusLine());
			RobotWindow.labelHttpStatus.setForeground(Color.red);
	    }
	}
	catch (Exception e) {
		log.error("exception: " + e.getMessage()+ e.toString());
	   	RobotWindow.labelHttpStatus.setText("HttpStatus: KO");
		RobotWindow.labelHttpStatus.setForeground(Color.red);
	} 
	finally {
		log.debug("End");
	}
}

public static int get_Status(){
	return  Status;
}

public static String get_State(){
	return  szFieldvalue[0];
}
public static String get_SpeedMotorRight(){
	return  szFieldvalue[1];
}
public static String get_SpeedMotorLeft(){
	return  szFieldvalue[2];
}
public static String get_TickRight(){
	return  szFieldvalue[3];
}
public static String get_TickLeft(){
	return  szFieldvalue[4];
}
public static String get_direction(){
	return  szFieldvalue[5];
}
public static String get_distance(){
	return  szFieldvalue[6];
}
public static String get_temperature(){
	return  szFieldvalue[7];
}

public static String get_direction_to_go(){
	return  szFieldvalue[0];
}

}
