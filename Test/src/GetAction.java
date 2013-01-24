import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class GetAction extends AbstractAction {
	private RobotWindow window;
	
	public GetAction(RobotWindow fenetre){
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent e) { 
		System.out.println("GetAction");
	} 
}