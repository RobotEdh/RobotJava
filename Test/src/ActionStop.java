import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;


public class ActionStop extends AbstractAction {
	
private final static Logger log = Logger.getLogger(ActionStart.class);
	
	private RobotWindow window;
	private int cmd[] =  {XbeeSend.CMD_STOP};
	
	
	
	public ActionStop(RobotWindow window, String texte){
		super(texte);
		
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent e) { 
		
		log.addAppender(new ConsoleAppender(new PatternLayout("%-6r [%p] %c - %m%n")));
		
		log.debug("Start");
		
	    try {
	    	XbeeSend a = new XbeeSend(cmd);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
	} 
}