import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;


public class ActionStop extends AbstractAction {
	
private final static Logger log = Logger.getLogger(ActionStop.class);
	
	private RobotWindow window;
	private String texte;
	private int cmd[] 		= {XbeeSend.CMD_STOP};
	private String szcmd[]	= {HttpSend.CMD_STOP};	
	
	
	public ActionStop(RobotWindow window, String texte){
		super(texte);
		
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent e) { 
		log.debug("Start");
		
	    try {
	    	if(Robot.COMTYPE == Robot.XBEECOM)
	    	{
	    		XbeeSend a = new XbeeSend(cmd);
	    	}
	    	else
	    	{
	    		HttpSend a = new HttpSend(szcmd);
	    	}
		} catch (Exception e1) {
			e1.printStackTrace();
		}	
	} 
}