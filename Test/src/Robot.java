
import javax.swing.SwingUtilities;

public class Robot {
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				RobotWindow window = new RobotWindow();
				window.setVisible(true);
			}
		});
	}
}