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

import org.apache.log4j.Logger;

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
public class XbeeReceiveFile {

	private final static Logger log = Logger.getLogger(XbeeReceiveFile.class);

	public XbeeReceiveFile(String filename) throws Exception {
	
	log.info("start XbeeReceiveFile, open:" + filename);
		  
	OutputStream out = null;
	out = new BufferedOutputStream(new FileOutputStream(filename));
		
		XBee xbee = new XBee();	
		log.info("start XBee");

		long Endofdata = 0;

		try {			

			xbee.open("COM14", 9600);
			log.info("COM14");

		
			while (Endofdata == 0) {

				try {
					XBeeResponse response = xbee.getResponse();
					log.info("xbee.getResponse");
														
					if (response.isError()) {
						log.info("response contains errors", ((ErrorResponse)response).getException());
					}
				
 					if (response.getApiId() == ApiId.RX_16_RESPONSE) {
						log.info("Received RX 16 packet " + ((RxResponse16)response));
 					} else if (response.getApiId() == ApiId.RX_64_RESPONSE) {
 						log.info("Received RX 64 packet " + ((RxResponse64)response));
 						Endofdata = ((RxResponse64) response).getData()[0];
 						log.info("Endofdata: " + Endofdata);
 						for (int i = 1; i < ((RxResponse64) response).getData().length; i++) // start at 1 to ignore first byte
 						{
 							log.info("::" + ((RxResponse64) response).getData()[i]);
 							out.write(((RxResponse64) response).getData()[i]);
 						
 						}
					} else {
						log.info("Ignoring mystery packet " + response.toString());
					}

				
					
				} catch (Exception e) {
					log.error(e);
				}
				
			}
			log.debug("Receive OK");
			out.close();
			log.debug("Close file OK");
			 
		} finally {
			xbee.close();
			log.debug("End Receive");
			return;
		}
		
	}


	
	public Boolean DataToRead() {
		return false;
	}
	
	
}
