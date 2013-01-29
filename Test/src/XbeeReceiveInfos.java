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

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.rapplogic.xbee.api.ApiId;
import com.rapplogic.xbee.api.ErrorResponse;
import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeResponse;
import com.rapplogic.xbee.api.wpan.RxResponse16;
import com.rapplogic.xbee.api.wpan.RxResponse64;

/**
 * Receive a TX Request containing Msg and waits for TX status packet.
 * @author edh
 * 
 */
public class XbeeReceiveInfos {

	private final static Logger log = Logger.getLogger(XbeeReceiveInfos.class);
	
	public static final int RESP_INFOS = 0x01;

	public XbeeReceiveInfos(
			int Function,
			int SpeedMotorRight,
			int SpeedMotorLeft,
			int TickRight,
			int TickLeft,
			int direction,
			int distance										
			) throws Exception {
		
    log.addAppender(new ConsoleAppender(new PatternLayout("%-6r [%p] %c - %m%n")));
    
    log.debug("Start");
	
		XBee xbee = new XBee();	
		log.info("start XBee");

		int resp = 0;

		try {			
			xbee.open("COM14", 9600);
			log.info("COM14");
	
				try {
					XBeeResponse response = xbee.getResponse();
					log.info("xbee.getResponse");
														
					if (response.isError()) {
						log.info("response contains errors", ((ErrorResponse)response).getException());
						
					} else if (response.getApiId() == ApiId.RX_16_RESPONSE) {
						log.info("Received RX 16 packet " + ((RxResponse16)response));
						
 					} else if (response.getApiId() == ApiId.RX_64_RESPONSE) {
 						log.info("Received RX 64 packet " + ((RxResponse64)response));
 						
 						// byte 0: response code, must be = RESP_INFOS
 						resp = ((RxResponse64) response).getData()[0];
 						log.info("resp: " + resp);
 						if(resp != RESP_INFOS)
 						{
 							log.info("bad resp: " + resp);
 						}
 						else
 						{	
 							// byte 1: Function
 							log.info("Function:" + ((RxResponse64) response).getData()[1]);
 							Function = ((RxResponse64) response).getData()[1];
 							// byte 2: SpeedMotorRight
 							log.info("SpeedMotorRight:" + ((RxResponse64) response).getData()[2]);
 							SpeedMotorRight = ((RxResponse64) response).getData()[2];
 							// byte 3: SpeedMotorLeft
 							log.info("SpeedMotorLeft:" + ((RxResponse64) response).getData()[3]);
 							SpeedMotorLeft = ((RxResponse64) response).getData()[3];							
 							// byte 4: TickRight
 							log.info("TickRight:" + ((RxResponse64) response).getData()[4]);
 							TickRight = ((RxResponse64) response).getData()[4];	
 							// byte 5: TickLeft
 							log.info("TickLeft:" + ((RxResponse64) response).getData()[5]);
 							TickLeft = ((RxResponse64) response).getData()[5];	
 							// byte 6: Direction
 							log.info("Direction:" + ((RxResponse64) response).getData()[6]);
 							direction = ((RxResponse64) response).getData()[6];
 							// byte 7: Distance
 							log.info("Distance:" + ((RxResponse64) response).getData()[7]);
 							distance = ((RxResponse64) response).getData()[7];
 						}
					} else {
						log.info("Ignoring mystery packet " + response.toString());
					}
	
				} catch (Exception e) {
					log.error(e);
				}
				
			log.debug("Receive OK");
			 
		} finally {
			xbee.close();
			log.debug("End Receive");
			return;
		}
	}		
	
}
