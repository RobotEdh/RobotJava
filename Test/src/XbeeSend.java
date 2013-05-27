import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.rapplogic.xbee.api.ApiId;
import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeAddress64;
import com.rapplogic.xbee.api.XBeeResponse;
import com.rapplogic.xbee.api.XBeeTimeoutException;
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

	public static final int CMD_START =         0x01;
	public static final int CMD_STOP =          0x02;
	public static final int CMD_INFOS =         0x03;
	public static final int CMD_PICTURE =       0x04;
	public static final int CMD_TURN_RIGHT =    0x05;
	public static final int CMD_TURN_LEFT =     0x06;
	public static final int CMD_CHECK_AROUND =  0x07;
	public static final int CMD_MOVE_TILT_PAN = 0x08;
	public static final int CMD_GO =            0x09;
	
	public XbeeSend(int cmd[]) throws Exception {
	    log.debug("Start");
	
	    int frameId = 0x12;
		XBee xbee = new XBee();	
		
		try {		    
				xbee.open(Robot.Xbeecom, 9600);

			    XBeeAddress64 destination = new XBeeAddress64(0, 0x13, 0xa2, 0, 0x40, 0x7b, 0xea, 0x23);
			    
				TxRequest64 tx = new TxRequest64(destination, frameId, cmd);
		
			    for (int i=0; i <cmd.length; i++) {
					log.debug("cmd [" + i +"]= "+ cmd[i]);
				}

				XBeeResponse response = xbee.sendSynchronous(tx, 5*1000);  // send a request and wait up to 5 seconds for the response
	
				log.debug("getResponse");
				
				if (response.getApiId() != ApiId.TX_STATUS_RESPONSE) {
						log.error("expected tx status but received: " + response);
				} else if (((TxStatusResponse) response).getFrameId() != frameId) {
					    log.error("Bad frame Id");
				} else if (((TxStatusResponse) response).getStatus() != TxStatusResponse.Status.SUCCESS) {
                        // error
						if (((TxStatusResponse) response).isAckError()) {
							log.error("Tx status failure: AckError" );
						} else if (((TxStatusResponse) response).isCcaError()) {
							log.error("Tx status failure: CcaError" );
						} else if (((TxStatusResponse) response).isPurged()) {
							log.error("Tx status failure: purged" );
						}else {
							log.error("Tx status failure with status: " + ((TxStatusResponse) response).getStatus());
						}
				} else {
						// success
						log.debug("Tx status Success");
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
}
