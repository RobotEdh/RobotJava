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
public class XbeeReceiveInfos {

	private final static Logger log = Logger.getLogger(XbeeReceiveInfos.class);
	
	private static int State;
	private static int SpeedMotorRight;
	private static int SpeedMotorLeft;
	private static int TickRight;
	private static int TickLeft;
	private static int direction;
	private static int distance;

	public XbeeReceiveInfos(
										
			) throws Exception {
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
 						
 						// byte 0: State
 						log.info("State:" + ((RxResponse64) response).getData()[0]);
 						State = ((RxResponse64) response).getData()[0];
 						// byte 1: SpeedMotorRight
 						log.info("SpeedMotorRight:" + ((RxResponse64) response).getData()[1]);
 						SpeedMotorRight = ((RxResponse64) response).getData()[1];
 						// byte 2: SpeedMotorLeft
 						log.info("SpeedMotorLeft:" + ((RxResponse64) response).getData()[2]);
 						SpeedMotorLeft = ((RxResponse64) response).getData()[2];							
 						// byte 3: TickRight
 						log.info("TickRight:" + ((RxResponse64) response).getData()[3]);
 						TickRight = ((RxResponse64) response).getData()[3];	
 						// byte 4: TickLeft
 						log.info("TickLeft:" + ((RxResponse64) response).getData()[4]);
 						TickLeft = ((RxResponse64) response).getData()[4];	
 						// byte 5: Direction
 						log.info("Direction:" + ((RxResponse64) response).getData()[5]);
 						direction = ((RxResponse64) response).getData()[5];
 						log.debug("Direction2:"  + direction);
 						// byte 6: Distance
 						log.info("Distance:" + ((RxResponse64) response).getData()[6]);
 						distance = ((RxResponse64) response).getData()[6];
 						
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
	
	public static int get_State(){
		return State;
    }
	public static int get_SpeedMotorRight(){
		return SpeedMotorRight;
	}
	public static int get_SpeedMotorLeft(){
		return SpeedMotorLeft;
	}
	public static int get_TickRight(){
		return TickRight;
	}
	public static int get_TickLeft(){
		return TickLeft;
	}
	public static int get_direction(){
		return direction;
	}
	public static int get_distance(){
			return distance;
	}
	
	
}
