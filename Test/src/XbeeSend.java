import org.apache.log4j.Logger;

import com.rapplogic.xbee.api.ApiId;
import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeAddress64;
import com.rapplogic.xbee.api.XBeeResponse;
import com.rapplogic.xbee.api.wpan.TxRequest64;
import com.rapplogic.xbee.api.wpan.TxStatusResponse;
;

/**
 * Sends a TX Request containing Msg and waits for TX status packet.
 * @author edh
 * 
 */
public class XbeeSend {
	
	private final static Logger log = Logger.getLogger(XbeeSend.class);
	
	public static final int CMD_START = 0x01;
	public static final int CMD_STOP = 0x02;
	public static final int CMD_INFOS = 0x03;
	public static final int CMD_PICTURE = 0x04;
	
	public XbeeSend(int cmd[]) throws Exception {
		
	    System.out.println("XbeeSend");
	    System.out.println(cmd.length);
	    System.out.println(cmd[0]);

	    XBee xbee = new XBee();

		final int sleep = 5000;

		int errors = 0;
		int ackErrors = 0;
		int ccaErrors = 0;
		int purgeErrors = 0;
		
		try {
			    
			    xbee.open("COM14", 9600);
			    XBeeAddress64 destination = new XBeeAddress64(0, 0x13, 0xa2, 0, 0x40, 0x7b, 0xea, 0x23);
			    int frameId = 0x12;
			    
				TxRequest64 tx = new TxRequest64(destination, frameId, cmd);
						
			
			    for (int i=0; i <cmd.length; i++) {
					log.debug("Send : " + (char)cmd[i]);
				}
			    
				xbee.sendSynchronous(tx, 5000);

				XBeeResponse response = null;

				while (true) {
					// blocks until we get response
					response = xbee.getResponse();

					if (response.getApiId() != ApiId.TX_STATUS_RESPONSE) {
						log.debug("expected tx status but received " + response);
					} else {
						if (((TxStatusResponse) response).getFrameId() != frameId) {
							throw new RuntimeException("frame id does not match");
						}

						if (((TxStatusResponse) response).getStatus() != TxStatusResponse.Status.SUCCESS) {
							errors++;

							if (((TxStatusResponse) response).isAckError()) {
								ackErrors++;
							} else if (((TxStatusResponse) response).isCcaError()) {
								ccaErrors++;
							} else if (((TxStatusResponse) response).isPurged()) {
								purgeErrors++;
							}

							log.debug("Tx status failure with status: " + ((TxStatusResponse) response).getStatus());
						} else {
							// success
							log.debug("Success, errors is " + errors + ", in "  + ", ack errors "
									+ ackErrors + ", ccaErrors " + ccaErrors + ", purge errors " + purgeErrors);
						}

						break;
					}
				}

				//Thread.sleep(sleep);
				log.debug("Send OK");
		
		} finally {
			xbee.close();
			log.debug("End Send");
		}
	}



}
