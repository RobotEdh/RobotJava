import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;


public class ActionStart extends AbstractAction {
	
	private RobotWindow window;
	private int cmd[] =  {XbeeSend.CMD_START};
	
	public ActionStart(RobotWindow window, String texte){
		super(texte);
		
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent e) { 
	    System.out.println("Start");
	    
	    try {
	    	XbeeSend a = new XbeeSend(cmd);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
	} 
}