
import java.io.IOException;

import javax.swing.SwingUtilities;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.SimpleLayout;

public class Robot {

	private final static Logger log = Logger.getLogger(Robot.class);
 
	 
	
	public static void main(String[] args) throws IOException{
		//log.addAppender(new ConsoleAppender(new PatternLayout("%-6r [%p] %c - %m%n")));
		PropertyConfigurator.configure("log4j.properties");
		
			SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				log.info("Start Robot" );
				RobotWindow window = new RobotWindow();
				window.setVisible(true);
			}
		});
	}
}