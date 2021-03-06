import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextField;

import org.apache.http.HttpStatus;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;


public class ActionInfos extends AbstractAction {
	
	private final static Logger log = Logger.getLogger(ActionInfos.class);
	
	public static final int STATE_STOP = 0x00;
	public static final int STATE_GO   = 0x01;
	
	private RobotWindow window;
	private int cmd[] 		= {XbeeSend.CMD_INFOS};
	private String szcmd[]	= {HttpSend.CMD_INFOS};	
	
	private int State;

	public ActionInfos(RobotWindow window, String texte){
		super(texte);
		
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent e) { 
		log.debug("Start");
	    
		if(Robot.COMTYPE == Robot.XBEECOM) {
			try {
				XbeeSend a = new XbeeSend(cmd);
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	    
			try {
				XbeeReceiveInfos b = new XbeeReceiveInfos ();
				State = XbeeReceiveInfos.get_State();
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}	
		}
		else
	    {
	    	try {
				HttpSend a = new HttpSend(szcmd);
					
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	    	if (HttpSend.get_Status() == HttpStatus.SC_OK)
	    		State = Integer.parseInt(HttpSend.get_State());
	    	else
	    		State = -1;
	    }
		
		log.debug("State: " + State);		
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
		if (HttpSend.get_Status() == HttpStatus.SC_OK) {
			if(Robot.COMTYPE == Robot.XBEECOM) {		
				RobotWindow.labelSpeedMotorRight.setText("Speed Motor Right: " + Integer.toString(XbeeReceiveInfos.get_SpeedMotorRight()));
				RobotWindow.labelSpeedMotorLeft.setText("Speed Motor Left: " + Integer.toString(XbeeReceiveInfos.get_SpeedMotorLeft()));
				RobotWindow.labelTickRight.setText("nb Ticks Right: " + Integer.toString(XbeeReceiveInfos.get_TickRight()));
				RobotWindow.labelTickLeft.setText("nb Ticks Left: " + Integer.toString(XbeeReceiveInfos.get_TickLeft()));
				RobotWindow.labeldirection.setText("direction: " + Integer.toString(XbeeReceiveInfos.get_direction()));
				RobotWindow.labeldistance.setText("distance: " + Integer.toString(XbeeReceiveInfos.get_distance()));
				RobotWindow.labeltemperature.setText("temperature: " + Integer.toString(XbeeReceiveInfos.get_temperature()));
			
			}
			else
			{
				RobotWindow.labelSpeedMotorRight.setText("Speed Motor Right: " + HttpSend.get_SpeedMotorRight());
				RobotWindow.labelSpeedMotorLeft.setText("Speed Motor Left: " + HttpSend.get_SpeedMotorLeft());
				RobotWindow.labelTickRight.setText("nb Tick Right: " + HttpSend.get_TickRight());
				RobotWindow.labelTickLeft.setText("nb Tick Left: " + HttpSend.get_TickLeft());
				RobotWindow.labeldirection.setText("direction: " + HttpSend.get_direction());
				if (HttpSend.get_distance() != "65535") {
				      RobotWindow.labeldistance.setText("distance: " + HttpSend.get_distance());
				}
				else
				{
					   RobotWindow.labeldistance.setText("distance: ?" );
				}					
				RobotWindow.labeltemperature.setText("temperature: " + HttpSend.get_temperature());				
		    }
		  }
	} 
}