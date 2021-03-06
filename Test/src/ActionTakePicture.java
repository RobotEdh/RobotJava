import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JPanel;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;


public class ActionTakePicture extends AbstractAction {
	
	private final static Logger log = Logger.getLogger(ActionTakePicture.class);
	
	private RobotWindow window;
	private int cmd[] 		= {XbeeSend.CMD_PICTURE};
	private String szcmd[]	= {HttpPicture.CMD_PICTURE};	
	
	private String filename;
	private int pictno = 0;
	
	public ActionTakePicture(RobotWindow window, String texte){
		super(texte);
		
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent e) { 
		log.debug("Start");
	    
	    pictno++;
	    filename = "PICT" + pictno + ".jpg";
	    
	    try {
	    	if(Robot.COMTYPE == Robot.XBEECOM)
	    	{
	    		XbeeSend a = new XbeeSend(cmd);
	    		
				XbeeReceiveFile b = new XbeeReceiveFile(filename);
	    	}
	    	else
	    	{
	    		HttpPicture a = new HttpPicture(szcmd, filename);
	    	}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    
	    FramePicture windowPicture = new FramePicture(filename) ;
	    windowPicture.setVisible(true) ;

		
	} 
}