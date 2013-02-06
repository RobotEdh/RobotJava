import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextField;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;


public class ActionCheckAround extends AbstractAction {
	
	private final static Logger log = Logger.getLogger(ActionCheckAround.class);
	public static final int OBSTACLE = -2;
	public static final int LEFT_DIRECTION = 1;
	public static final int RIGHT_DIRECTION = 2;
	
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
		
		 switch (direction_to_go)
		 {
		 case LEFT_DIRECTION:
			 RobotWindow.labelCheckAround.setText("Left");
		 break;
		 case RIGHT_DIRECTION:
			 RobotWindow.labelCheckAround.setText("Right");
		 break;
		 case OBSTACLE:
			 RobotWindow.labelCheckAround.setText("Obstacle");
		 break;
		 default:
			 RobotWindow.labelCheckAround.setText("Unknown");
		 }
	} 

		
}