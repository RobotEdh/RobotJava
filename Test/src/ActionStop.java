import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;


public class ActionStop extends AbstractAction {
	private RobotWindow window;
	
	public ActionStop(RobotWindow window, String texte){
		super(texte);
		
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent e) { 
	    System.out.println("Stop");
			
	} 
}