import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextField;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;


public class ActionInfos extends AbstractAction {
	
	private final static Logger log = Logger.getLogger(ActionInfos.class);
	
	public static final int STATE_STOP = 0x00;
	public static final int STATE_GO   = 0x01;
	
	private RobotWindow window;
	private int cmd[] =  {XbeeSend.CMD_INFOS};
	private int State;
	private int SpeedMotorRight;
	private int SpeedMotorLeft;
	private int nb_go;
	private int nb_obstacle;
	private int direction;
	private int distance;	
	
	public ActionInfos(RobotWindow window, String texte){
		super(texte);
		
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent e) { 
		log.debug("Start");
	    
	    try {
	    	XbeeSend a = new XbeeSend(cmd);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	    
		 try {
			XbeeReceiveInfos b = new XbeeReceiveInfos (
					 State,
					 SpeedMotorRight,
					 SpeedMotorLeft,
					 nb_go,
					 nb_obstacle,
					 direction,
					 distance);
		} catch (Exception e1) {
			e1.printStackTrace();
		}	
		
		 switch (State)
		 {
		 case STATE_STOP:
			 RobotWindow.labelState.setText("State: Stopped");
			 RobotWindow.labelState.setForeground(Color.red);
			 break;
		 case STATE_GO:
			 RobotWindow.labelState.setText("State: Go");
			 RobotWindow.labelState.setForeground(Color.green);
			 break;
		 default:
			 RobotWindow.labelState.setText("State: Unknown");
		 }
		 RobotWindow.labelSpeedMotorRight.setText("Speed Motor Right: " + Integer.toString(SpeedMotorRight));
		 RobotWindow.labelSpeedMotorLeft.setText("Speed Motor Left: " + Integer.toString(SpeedMotorLeft));
		 RobotWindow.labelnb_go.setText("nb go: " + Integer.toString(nb_go));
		 RobotWindow.labelnb_obstacle.setText("nb obstacle: " + Integer.toString(nb_obstacle));
		 RobotWindow.labeldirection.setText("direction: " + Integer.toString(direction));
		 RobotWindow.labeldistance.setText("distance: " + Integer.toString(distance));

	
	} 
}