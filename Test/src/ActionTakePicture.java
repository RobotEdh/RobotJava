import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JPanel;



public class ActionTakePicture extends AbstractAction {
	private RobotWindow window;
	private int cmd[] =  {XbeeSend.CMD_PICTURE};
	
	private String filename;
	private int pictno = 0;
	

	
	public ActionTakePicture(RobotWindow window, String texte){
		super(texte);
		
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent e) { 
	    System.out.println("Take Picture");
	    
	    try {
	    	XbeeSend a = new XbeeSend(cmd);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
    
	    pictno++;
	    filename = "PICT" + pictno + ".jpg";
	    System.out.println(filename);
	    	
	    try {
			XbeeReceiveFile b = new XbeeReceiveFile(filename);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    FramePicture windowPicture = new FramePicture(filename) ;
	    windowPicture.setVisible(true) ;

		
	} 
}