import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JPanel;



public class ActionTakePicture extends AbstractAction {
	private RobotWindow window;

	
	public ActionTakePicture(RobotWindow window, String texte){
		super(texte);
		
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent e) { 
	    System.out.println("Take Picture");
	    
	    FramePicture windowPicture = new FramePicture() ;
	    windowPicture.setVisible(true) ;

		
	} 
}