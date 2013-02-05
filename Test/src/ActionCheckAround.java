import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextField;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;


public class ActionCheckAround extends AbstractAction {
	
	private final static Logger log = Logger.getLogger(ActionCheckAround.class);
	
	private RobotWindow window;
	private int cmd[] =  {XbeeSend.CMD_CHECK_AROUND};
	private int direction_to_go;

	
	public ActionCheckAround(RobotWindow window, String texte){
		super(texte);
		
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent e) { 
		log.addAppender(new ConsoleAppender(new PatternLayout("%d{HH:mm:ss,SSS} [%p] %c.%L - %m%n")));
		log.debug("Start");
	    
	    try {
	    	XbeeSend a = new XbeeSend(cmd);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	    
		 try {
			 XbeeReceiveDirection b = new XbeeReceiveDirection (direction_to_go);
		} catch (Exception e1) {
			e1.printStackTrace();
		}	
	} 
	
	public int getdirection_to_go(){
		return direction_to_go;
	}
	
	
}