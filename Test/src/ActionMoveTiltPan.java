import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;


public class ActionMoveTiltPan extends AbstractAction {
	
	private final static Logger log = Logger.getLogger(ActionMoveTiltPan.class);
	
	private RobotWindow window;
	private int cmd[] =  {XbeeSend.CMD_MOVE_TILT_PAN, 0, 0};
	
	public ActionMoveTiltPan(RobotWindow window, String texte){
		super(texte);
		
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent e) {  
		log.debug("Start");

		cmd[1] = RobotWindow.sliderHorizontal.getValue() + 90;
		cmd[2] = RobotWindow.sliderVertical.getValue() + 90;
		log.debug(cmd[1]+"/"+ cmd[2]);

	    try {
	    	XbeeSend a = new XbeeSend(cmd);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			
	} 
}