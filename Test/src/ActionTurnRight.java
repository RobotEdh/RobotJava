import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;


public class ActionTurnRight extends AbstractAction {
	
	private final static Logger log = Logger.getLogger(ActionTurnRight.class);
	
	private RobotWindow window;
	private String texte;
	private int cmd[] 		= {XbeeSend.CMD_TURN_RIGHT, 0};
	private String szcmd[]	= {HttpSend.CMD_TURN_RIGHT, ""};
	
	public ActionTurnRight(RobotWindow window, String texte){
		super(texte);
		
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent e) { 
		log.debug("Start");
		
		try {
			cmd[1] = Integer.parseInt(RobotWindow.textAlpha.getText());
			szcmd[1] = Integer.toString(cmd[1]);
		} catch (Exception e0) {
			log.error("Invalid number: "+ RobotWindow.textAlpha.getText());
		}
	    if (cmd[1] < 0 || cmd[1] > 90) log.error("angle must be between 0 and 90 : "+ RobotWindow.textAlpha.getText());
    
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
	    
		ActionInfos infos = new ActionInfos( window,  texte);
	    infos.actionPerformed(e);			
	} 
}