import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;

import org.apache.log4j.Logger;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.rapplogic.xbee.api.wpan.RxResponse64;

public class HttpPicture {
	
	private final static Logger log = Logger.getLogger(HttpPicture.class);
	
	public static final String CMD_PICTURE =       "PICTURE";

    private static int Status = 0;
	
public HttpPicture(String szcmd[], String filename) throws Exception {
	log.debug("Start");  
	log.info("filename:" + filename);

	OutputStream out = null;
	try {
		out = new BufferedOutputStream(new FileOutputStream(filename));
	   }
    catch (Exception e) {
    	log.error("exception: " + e.getMessage()+ e.toString());
    }
	
    URIBuilder builder = new URIBuilder();
    builder.setScheme("http").setHost(RobotWindow.textIP.getText()).setPort(Integer.parseInt(RobotWindow.textPort.getText())).setPath(Robot.Httppath);
    builder.addParameter("CMD",szcmd[0]);
    URI uri = builder.build();
    log.debug(uri); 
    
    try {       
	    HttpClient httpclient = new DefaultHttpClient(); 
	    HttpGet httpget = new HttpGet(uri);
	    
	    HttpResponse response = httpclient.execute(httpget);
	    log.debug("response:"+ response);
	    
	    Header contentType = response.getFirstHeader("Content-Type");
	    String mimeType = contentType.getValue().split(";")[0].trim();
	    log.debug("mimeType:"+ mimeType);

	    log.debug(response.getStatusLine());
	    Status = response.getStatusLine().getStatusCode();
	    if (Status == HttpStatus.SC_OK)
	    {
	       int ibuf = 0;
	        while ((ibuf = response.getEntity().getContent().read()) != -1 ) {
	          out.write(ibuf);	
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
    	out.close();
    	log.debug("End");
    }
		
}

}
