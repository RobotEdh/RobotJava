

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class RobotWindow extends JFrame{

	public static JTextField textAlpha;
	public static JSlider sliderHorizontal;
	public static JSlider sliderVertical;
	public static JLabel labelCheckAround;
	
	public static JLabel labelState;
	public static JLabel labelSpeedMotorRight;
	public static JLabel labelSpeedMotorLeft;
	public static JLabel labelnb_go;
	public static JLabel labelnb_obstacle;
	public static JLabel labeldirection;
	public static JLabel labeldistance;
	
	public static JLabel labelCOMTYPE;
	public static JLabel labelHttpStatus;
	public static JLabel labelLogfilename;
	
	public static JTextField textMotorNum;
	public static JTextField textIP;
	public static JTextField textPort;
	

	public RobotWindow(){
		super();
		
		build();
	}
	
	private void build(){
		setTitle("Robot"); 
		setSize(500,700); 
		setLocationRelativeTo(null); 
		setResizable(true); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		setContentPane(buildContentPanel());
		getContentPane().add(buildContentPanel2());
		getContentPane().add(buildContentPanel3());
		getContentPane().add(buildContentPanel4());
	}

	private JPanel buildContentPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 3));
		panel.setBackground(Color.lightGray);
		
		Font font = new Font("Arial", Font.BOLD, 12);
		TitledBorder title;
					
		JButton boutonStart = new JButton(new ActionStart(this, "Start"));
		JButton boutonStop = new JButton(new ActionStop(this, "Stop"));
		JButton boutonInfos = new JButton(new ActionInfos(this, "Infos"));
		
		JButton boutonTurnLeft = new JButton(new ActionTurnLeft(this, "Turn Left"));
		textAlpha = new JTextField("00");
		textAlpha.setHorizontalAlignment(JTextField.CENTER);
		textAlpha.setForeground(Color.BLACK);
		textAlpha.setOpaque(true);
		textAlpha.setBackground(Color.WHITE);
		textAlpha.setFont(font);
		title = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "0 < Angle to turn < 90");
        title.setTitleJustification(TitledBorder.CENTER);
        textAlpha.setBorder(title);
		
		
		JButton boutonTurnRight = new JButton(new ActionTurnRight(this, "Turn Right"));
				
		JButton boutonMoveTiltPan = new JButton(new ActionMoveTiltPan (this, "Move Tilt&Pan"));
		
		
		sliderHorizontal = new JSlider(JSlider.HORIZONTAL, -90, 90, 0);
		sliderHorizontal.setMajorTickSpacing(45);
		sliderHorizontal.setPaintTicks(true);
		sliderHorizontal.setPaintLabels(true);
		sliderHorizontal.setFont(font);
		
		sliderVertical = new JSlider(JSlider.VERTICAL, -45, 90, 0);
		sliderVertical.setMajorTickSpacing(45);
		sliderVertical.setPaintTicks(true);
		sliderVertical.setPaintLabels(true);
		sliderVertical.setFont(font);
		
		JButton boutonCheckAround = new JButton(new ActionCheckAround(this, "Check Around"));
		labelCheckAround = new JLabel("unknown");
		labelCheckAround.setForeground(Color.blue);
		labelCheckAround.setOpaque(true);
		labelCheckAround.setFont(font);
		labelCheckAround.setHorizontalAlignment(JLabel.CENTER);	
		title = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "Direction to go");
        title.setTitleJustification(TitledBorder.CENTER);
        labelCheckAround.setBorder(title);
		
		JButton boutonTakePicture = new JButton(new ActionTakePicture(this, "Take picture"));
		
		panel.add(boutonStart);
		panel.add(boutonStop);
		panel.add(boutonInfos);
		
		panel.add(boutonTurnLeft);
		panel.add(textAlpha);
		panel.add(boutonTurnRight);
		

		panel.add(sliderHorizontal);
		panel.add(boutonMoveTiltPan);
		panel.add(sliderVertical);
		
		panel.add(boutonCheckAround);
		panel.add(labelCheckAround);
		
		panel.add(boutonTakePicture);

		return panel;
	}
	
	private JPanel buildContentPanel2(){
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		TitledBorder title = BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Infos");
        title.setTitleJustification(TitledBorder.CENTER);
        panel2.setBorder(title);
        
		
		Font font = new Font("Arial", Font.BOLD, 12);
		
		labelState = new JLabel("State: ?");
		labelState.setForeground(Color.blue);
		labelState.setOpaque(true);
		labelState.setFont(font);
		labelState.setHorizontalAlignment(JLabel.LEFT);
		
		panel2.add(labelState);
		
		labelSpeedMotorRight = new JLabel("Speed Motor Right: ?");
		labelSpeedMotorLeft  = new JLabel("Speed Motor Left: ?");
		labelSpeedMotorRight.setForeground(Color.blue);
		labelSpeedMotorRight.setOpaque(true);
		labelSpeedMotorRight.setFont(font);
		labelSpeedMotorRight.setHorizontalAlignment(JLabel.LEFT);
		labelSpeedMotorLeft.setForeground(Color.blue);
		labelSpeedMotorLeft.setOpaque(true);
		labelSpeedMotorLeft.setFont(font);
		labelSpeedMotorLeft.setHorizontalAlignment(JLabel.LEFT);
	
		panel2.add(labelSpeedMotorRight);
		panel2.add(labelSpeedMotorLeft);
		
			
		labelnb_go = new JLabel("Nb go: ?");
		labelnb_obstacle = new JLabel("Nb obstacle: ?");
		labelnb_go.setForeground(Color.blue);
		labelnb_go.setOpaque(true);
		labelnb_go.setFont(font);
		labelnb_go.setHorizontalAlignment(JLabel.LEFT);
		labelnb_obstacle.setForeground(Color.blue);
		labelnb_obstacle.setOpaque(true);
		labelnb_obstacle.setFont(font);
		labelnb_obstacle.setHorizontalAlignment(JLabel.LEFT);
		
		panel2.add(labelnb_go);
		panel2.add(labelnb_obstacle);
		
		labeldirection  = new JLabel("Direction: ?");
		labeldistance  = new JLabel("Distance: ?");
		labeldirection.setForeground(Color.blue);
		labeldirection.setOpaque(true);
		labeldirection.setFont(font);
		labeldirection.setHorizontalAlignment(JLabel.LEFT);
		labeldistance.setForeground(Color.blue);
		labeldistance.setOpaque(true);
		labeldistance.setFont(font);
		labeldistance.setHorizontalAlignment(JLabel.LEFT);
	
		panel2.add(labeldirection);
		panel2.add(labeldistance);
      
		return panel2;
	}
	
	private JPanel buildContentPanel3(){
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());
		       
		TitledBorder title = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "1 < Motor to run < 4");
        title.setTitleJustification(TitledBorder.CENTER);
        panel3.setBorder(title);
		
        Font font = new Font("Arial", Font.BOLD, 12);

		textMotorNum = new JTextField("0");
		textMotorNum.setHorizontalAlignment(JTextField.CENTER);
		textMotorNum.setForeground(Color.BLACK);
		textMotorNum.setOpaque(true);
		textMotorNum.setBackground(Color.WHITE);
		textMotorNum.setFont(font);
		textMotorNum.setBorder(null);

		panel3.add(textMotorNum);
				
		return panel3;
	}
	
	private JPanel buildContentPanel4(){
		JPanel panel4 = new JPanel();
		panel4.setLayout(new FlowLayout());
		       
		TitledBorder title = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "Configuration");
        title.setTitleJustification(TitledBorder.CENTER);
        panel4.setBorder(title);
		
        Font font = new Font("Arial", Font.BOLD, 12);

        labelCOMTYPE = new JLabel("Communication: "+ (Robot.COMTYPE == Robot.XBEECOM?"XBee":"WIFI"));
		labelCOMTYPE.setForeground(Color.blue);
		labelCOMTYPE.setOpaque(true);
		labelCOMTYPE.setFont(font);
		labelCOMTYPE.setHorizontalAlignment(JLabel.LEFT); 
		
		panel4.add(labelCOMTYPE);
		
		textIP = new JTextField("192.168.0.15");
		textIP.setHorizontalAlignment(JTextField.LEFT);
		textIP.setForeground(Color.BLACK);
		textIP.setOpaque(true);
		textIP.setBackground(Color.WHITE);
		textIP.setFont(font);
		textIP.setBorder(null);
		
		textPort = new JTextField("44300");
		textPort.setHorizontalAlignment(JTextField.LEFT);
		textPort.setForeground(Color.BLACK);
		textPort.setOpaque(true);
		textPort.setBackground(Color.WHITE);
		textPort.setFont(font);
		textPort.setBorder(null);

		panel4.add(textIP);
		panel4.add(textPort);
		
		labelHttpStatus = new JLabel("HttpStatus: ?");
		labelHttpStatus.setForeground(Color.blue);
		labelHttpStatus.setOpaque(true);
		labelHttpStatus.setFont(font);
		labelHttpStatus.setHorizontalAlignment(JLabel.LEFT); 
		
		panel4.add(labelHttpStatus);
		
		labelLogfilename = new JLabel("Log: "+ Robot.logfilename);
		labelLogfilename.setForeground(Color.blue);
		labelLogfilename.setOpaque(true);
		labelLogfilename.setFont(font);
		labelLogfilename.setHorizontalAlignment(JLabel.LEFT);
	
		panel4.add(labelLogfilename);
		return panel4;
	}
}	
	
