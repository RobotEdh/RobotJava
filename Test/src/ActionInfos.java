import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextField;


public class ActionInfos extends AbstractAction {
	
	private RobotWindow window;
	private int cmd[] =  {XbeeSend.CMD_INFOS};
	private int Function;
	private int SpeedMotorRight;
	private int SpeedMotorLeft;
	private int TickRight;
	private int TickLeft;
	private int direction;
	private int distance;	
	
	public ActionInfos(RobotWindow window, String texte){
		super(texte);
		
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent e) { 
	    System.out.println("Infos");
	    
	    try {
	    	XbeeSend a = new XbeeSend(cmd);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
		 try {
			XbeeReceiveInfos b = new XbeeReceiveInfos (
					 Function,
					 SpeedMotorRight,
					 SpeedMotorLeft,
					 TickRight,
					 TickLeft,
					 direction,
					 distance);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	} 
	
	public int getSpeedMotorRight(){
		return SpeedMotorRight;
	}
	public int getSpeedMotorLeft(){
		return SpeedMotorLeft;
	}
	public int getTickRight(){
		return TickRight;
	}
	public int getTickLeft(){
		return TickLeft;
	}
	public int getdirection(){
		return direction;
	}
	public int getdistance(){
		return distance;
	}
	
	
}