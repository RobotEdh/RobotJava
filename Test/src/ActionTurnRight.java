import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;


public class ActionTurnRight extends AbstractAction {
	
	private final static Logger log = Logger.getLogger(ActionTurnRight.class);
	
	private RobotWindow window;
	private int cmd[] =  {XbeeSend.CMD_TURN_RIGHT, 0};
	
	public ActionTurnRight(RobotWindow window, String texte){
		super(texte);
		
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent e) { 
	    
		log.addAppender(new ConsoleAppender(new PatternLayout("%d{HH:mm:ss,SSS} [%p] %c.%L - %m%n")));
		log.debug("Start");
		
		try {
			cmd[1] = Integer.parseInt(RobotWindow.textAlpha.getText());
			log.debug(cmd[1]);
		} catch (Exception e0) {
			log.error("Invalid number: "+ RobotWindow.textAlpha.getText());
		}
	    if (cmd[1] < 0 || cmd[1] > 90) log.error("angle must be between 0 and 90 : "+ RobotWindow.textAlpha.getText());
    
		try {
	    	XbeeSend a = new XbeeSend(cmd);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			
	} 
}