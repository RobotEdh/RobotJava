import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;


public class ActionGo extends AbstractAction {
	
	private final static Logger log = Logger.getLogger(ActionGo.class);
	
	private RobotWindow window;
	private String texte;
	private int cmd[] 		= {XbeeSend.CMD_GO, 0, 0};
	private String szcmd[]	= {HttpSend.CMD_GO, "", ""};
	
	public ActionGo(RobotWindow window, String texte){
		super(texte);
		
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent e) { 
		log.debug("Start");
		
		try {
			cmd[1] = Integer.parseInt(RobotWindow.textNbSecs.getText());
			cmd[2] = Integer.parseInt(RobotWindow.textPID.getText());
			szcmd[1] = Integer.toString(cmd[1]);
			szcmd[2] = Integer.toString(cmd[2]);

		} catch (Exception e0) {
			log.error("Invalid number: "+ RobotWindow.textNbSecs.getText() + RobotWindow.textPID.getText());
		}
	    if (cmd[1] < 0 || cmd[1] > 999999) log.error("Nb secs between 0 and 999999 : "+ RobotWindow.textNbSecs.getText());
	    if (cmd[2] < 0 || cmd[1] > 1) log.error("PID between 0 and 1 : "+ RobotWindow.textPID.getText());
	    
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
	    log.debug("End");
	} 
	
	
}