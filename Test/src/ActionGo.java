import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;


public class ActionGo extends AbstractAction {
	private RobotWindow window;
	
	public ActionGo(RobotWindow window, String texte){
		super(texte);
		
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent e) { 
	    System.out.println("Go");
			
	} 
}