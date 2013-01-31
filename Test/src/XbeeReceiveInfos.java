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
	
	public static final int RESP_INFOS = 0x01;

	public XbeeReceiveInfos(
			int State,
			int SpeedMotorRight,
			int SpeedMotorLeft,
			int nb_go,
			int nb_obstacle,
			int direction,
			int distance										
			) throws Exception {
		
	    log.addAppender(new ConsoleAppender(new PatternLayout("%d{HH:mm:ss,SSS} [%p] %c.%L - %m%n")));
	    log.debug("Start");

		int resp = 0;
		XBee xbee = new XBee();
		
		try {	
			xbee.open("COM14", 9600);
	
			XBeeResponse response = xbee.getResponse(5*1000); // wait 5 seconds
			log.debug("getResponse");
						
			if (response.getApiId() != ApiId.RX_64_RESPONSE) {
				log.error("expected RxResponse64 but received: " + response);
			} else if (response.isError()) {
					log.error("response contains errors", ((ErrorResponse)response).getException());				
 			} else {
 						log.debug("Received RX 64 packet " + ((RxResponse64)response));
 						
 						// byte 0: response code, must be = RESP_INFOS
 						resp = ((RxResponse64) response).getData()[0];
 						log.debug("resp: " + resp);
 						if(resp != RESP_INFOS)
 						{
 							log.info("bad resp: " + resp);
 						}
 						else
 						{	
 							// byte 1: State
 							log.info("State:" + ((RxResponse64) response).getData()[1]);
 							State = ((RxResponse64) response).getData()[1];
 							// byte 2: SpeedMotorRight
 							log.info("SpeedMotorRight:" + ((RxResponse64) response).getData()[2]);
 							SpeedMotorRight = ((RxResponse64) response).getData()[2];
 							// byte 3: SpeedMotorLeft
 							log.info("SpeedMotorLeft:" + ((RxResponse64) response).getData()[3]);
 							SpeedMotorLeft = ((RxResponse64) response).getData()[3];							
 							// byte 4: nb_go
 							log.info("nb_go:" + ((RxResponse64) response).getData()[4]);
 							nb_go = ((RxResponse64) response).getData()[4];	
 							// byte 5: nb_obstacle
 							log.info("nb_obstacle:" + ((RxResponse64) response).getData()[5]);
 							nb_obstacle = ((RxResponse64) response).getData()[5];	
 							// byte 6: Direction
 							log.info("Direction:" + ((RxResponse64) response).getData()[6]);
 							direction = ((RxResponse64) response).getData()[6];
 							// byte 7: Distance
 							log.info("Distance:" + ((RxResponse64) response).getData()[7]);
 							distance = ((RxResponse64) response).getData()[7];
 						}
		    }
		}
		catch (Exception e) {
			log.error("exception: " + e.getMessage()+ e.toString());
		} 
		finally {
			xbee.close();
			Thread.sleep(1*1000); //sleep for 1 second
			log.debug("End");
		}
	}		
	
}
