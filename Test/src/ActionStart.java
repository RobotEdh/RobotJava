import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;


public class ActionStart extends AbstractAction {
	
	private final static Logger log = Logger.getLogger(ActionStart.class);
	
	private RobotWindow window;
	private int cmd[] =  {XbeeSend.CMD_START,0};
	
	public ActionStart(RobotWindow window, String texte){
		super(texte);
		
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent e) { 
		log.debug("Start");
		
		try {
			cmd[1] = Integer.parseInt(RobotWindow.textMotorNum.getText());
			log.debug("cmd[1]: " + cmd[1]);
		} catch (Exception e0) {
			log.error("Invalid number: "+ RobotWindow.textMotorNum.getText());
		}
	    if (cmd[1] < 0 || cmd[1] > 4) log.error("motor number between 0 and 4 : "+ RobotWindow.textMotorNum.getText());
	    
	    try {
	    	XbeeSend a = new XbeeSend(cmd);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			
	} 
}