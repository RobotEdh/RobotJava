import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

class FramePicture extends JFrame
{
	FramePicture(String filename) {
		setTitle ("PICTURE") ;
		setSize (800, 500) ;
		panpic = new PanelPicture(filename) ;
		getContentPane().add(panpic) ;
	}
	private JPanel panpic ;
}

public class PanelPicture extends JPanel{
	
	 private Image photo = null;
	
	public PanelPicture(String filename){
		photo = getToolkit().getImage(filename) ;
    }
    public void paintComponent(Graphics g){
    	super.paintComponent(g) ;
    	int x=10, y=10 ;
    	if(photo != null){
    		g.drawImage (photo, x, y, this);
    	}

    }    	
 
}

