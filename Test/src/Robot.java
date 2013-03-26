import java.io.IOException;
import javax.swing.SwingUtilities;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.RollingFileAppender;

public class Robot {
	
	public static final int XBEECOM = 1;
	public static final int HTPPCOM = 2;
	public static int COMTYPE = HTPPCOM;
	public static String Xbeecom = "COM14";
	public static String logfilename;
	
	public static String Httppath = "/Robot";
	
    private static Logger log = Logger.getLogger(Robot.class);
  

    public static void main(String[] args) throws IOException{

            PropertyConfigurator.configure("/log4j.properties");
            RollingFileAppender r =(RollingFileAppender) Logger.getRootLogger().getAppender("main-appender");
            logfilename = r.getFile();
            
            SwingUtilities.invokeLater(new Runnable(){

                  public void run(){
                        log.info("Start Robot" );
                        RobotWindow window = new RobotWindow();
                        window.setVisible(true);

                  }

            });

      }

}


