/**
 * Copyright (c) 2008 Andrew Rapp. All rights reserved.
 *  
 * This file is part of XBee-API.
 *  
 * XBee-API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *  
 * XBee-API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *  
 * You should have received a copy of the GNU General Public License
 * along with XBee-API.  If not, see <http://www.gnu.org/licenses/>.
 */

import org.apache.log4j.ConsoleAppender;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.rapplogic.xbee.api.ApiId;
import com.rapplogic.xbee.api.ErrorResponse;
import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeResponse;
import com.rapplogic.xbee.api.wpan.RxResponse64;
import com.rapplogic.xbee.api.wpan.TxStatusResponse;

/**
 * Receive a TX Request containing Msg and waits for TX status packet.
 * @author edh
 * 
 */
public class XbeeReceiveDirection {

	private final static Logger log = Logger.getLogger(XbeeReceiveDirection.class);
	
	private static int direction_to_go;	

	public XbeeReceiveDirection() throws Exception {
	    log.debug("Start");

		XBee xbee = new XBee();
		
		try {	
			xbee.open(Robot.Xbeecom, 9600);
	
			XBeeResponse response = xbee.getResponse(5*1000); // wait 5 seconds
			log.debug("getResponse");
						
			if (response.getApiId() != ApiId.RX_64_RESPONSE) {
				log.error("expected RxResponse64 but received: " + response);
			} else if (response.isError()) {
					log.error("response contains errors", ((ErrorResponse)response).getException());				
 			} else {
 						log.debug("Received RX 64 packet " + ((RxResponse64)response));
						// byte 0: direction_to_go
						log.info("direction_to_go:" + ((RxResponse64) response).getData()[0]);
						direction_to_go = ((RxResponse64) response).getData()[0];					
		    }
		}
		catch (Exception e) {
			log.error("exception: " + e.getMessage()+ e.toString());
		} 
		finally {
			xbee.close();
			Thread.sleep(1*1000); //sleep for 1 second time to close the xbee threads
			log.debug("End");
		}
	}		
	
	public static int get_direction_to_go(){
		return direction_to_go;
}	
	
}
