import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextField;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;


public class ActionInfos extends AbstractAction {
	
	private final static Logger log = Logger.getLogger(ActionInfos.class);
	
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
		log.addAppender(new ConsoleAppender(new PatternLayout("%-6r [%p] %c - %m%n")));
		 
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
	} 
	
	public int getState(){
		return State;
	}
	public int getSpeedMotorRight(){
		return SpeedMotorRight;
	}
	public int getSpeedMotorLeft(){
		return SpeedMotorLeft;
	}
	public int getnb_go(){
		return nb_go;
	}
	public int getnb_obstacle(){
		return nb_obstacle;
	}
	public int getdirection(){
		return direction;
	}
	public int getdistance(){
		return distance;
	}
	
	
}