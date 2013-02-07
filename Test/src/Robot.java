import java.io.IOException;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Robot {
      private static Logger log = Logger.getLogger(Robot.class);
  

      public static void main(String[] args) throws IOException{

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


