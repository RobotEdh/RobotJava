

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
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class RobotWindow extends JFrame{

	private JLabel labelSpeedMotorRight;
	private int SpeedMotorRight = 0;
	private JLabel labelSpeedMotorLeft;
	private int SpeedMotorLeft = 0;
	private JLabel labelTickRight;
	private int TickRight = 0;
	private JLabel labelTickLeft;
	private int TickLeft = 0;
	private JLabel labeldirection;
	private int direction = 0;
	private JLabel labeldistance;
	private int distance = 0;
	public static JTextField textAlpha;
	public static JSlider sliderHorizontal;
	public static JSlider sliderVertical;
	
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
					
		JButton boutonStart = new JButton(new ActionStart(this, "Start"));
		JButton boutonStop = new JButton(new ActionStop(this, "Stop"));
		JButton boutonInfos = new JButton(new ActionInfos(this, "Get infos"));
		
		JButton boutonTurnLeft = new JButton(new ActionTurnLeft(this, "Turn Left"));
		textAlpha = new JTextField("0");
		textAlpha.setHorizontalAlignment(JTextField.CENTER);
		textAlpha.setForeground(Color.red);
		textAlpha.setOpaque(true);
		textAlpha.setBackground(Color.WHITE);
		textAlpha.setFont(font);
		
		JButton boutonTurnRight = new JButton(new ActionTurnRight(this, "Turn Right"));
		
		JButton boutonMoveTiltPan = new JButton(new ActionMoveTiltPan (this, "Move Tilt&Pan"));
		
		
		sliderHorizontal = new JSlider(JSlider.HORIZONTAL, -90, 90, 0);
		sliderHorizontal.setMajorTickSpacing(45);
		sliderHorizontal.setPaintTicks(true);
		sliderHorizontal.setPaintLabels(true);
		sliderHorizontal.setFont(font);
		
		sliderVertical = new JSlider(JSlider.VERTICAL, -90, 90, 0);
		sliderVertical.setMajorTickSpacing(45);
		sliderVertical.setPaintTicks(true);
		sliderVertical.setPaintLabels(true);
		sliderVertical.setFont(font);
		
		JButton boutonCheckAround = new JButton(new ActionCheckAround(this, "Check Around"));
		
		JButton boutonTakePicture = new JButton(new ActionTakePicture(this, "Take picture"));
		JButton boutonDummy = new JButton();
		
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
		panel.add(boutonTakePicture);
		panel.add(boutonDummy);

		return panel;
	}
	
	private JPanel buildContentPanel2(){
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2, 0));
		panel2.setBackground(Color.lightGray);
		panel2.setBorder(BorderFactory.createLineBorder(Color.black));
		
		Font font = new Font("Arial", Font.BOLD, 12);
					
		labelSpeedMotorRight = new JLabel("Speed Motor Right: " + SpeedMotorRight);
		labelSpeedMotorLeft  = new JLabel("Speed Motor Left: " + SpeedMotorLeft);
		
		labelSpeedMotorRight.setForeground(Color.red);
		labelSpeedMotorRight.setOpaque(true);
		labelSpeedMotorRight.setBackground(Color.WHITE);
		labelSpeedMotorRight.setFont(font);
		labelSpeedMotorLeft.setForeground(Color.red);
		labelSpeedMotorLeft.setOpaque(true);
		labelSpeedMotorLeft.setBackground(Color.WHITE);
		labelSpeedMotorLeft.setFont(font);
	
		panel2.add(labelSpeedMotorRight);
		panel2.add(labelSpeedMotorLeft);

		return panel2;
	}
	
	private JPanel buildContentPanel3(){
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(2, 0));
		panel3.setBackground(Color.lightGray);
		
		Font font = new Font("Arial", Font.BOLD, 12);
					
		labelTickRight  = new JLabel("Tick Right: " + TickRight);
		labelTickLeft  = new JLabel("Tick Left: " + TickLeft);
		
		labelTickRight.setForeground(Color.red);
		labelTickRight.setOpaque(true);
		labelTickRight.setBackground(Color.WHITE);
		labelTickRight.setFont(font);
		labelTickLeft.setForeground(Color.red);
		labelTickLeft.setOpaque(true);
		labelTickLeft.setBackground(Color.WHITE);
		labelTickLeft.setFont(font);

		panel3.add(labelTickRight);
		panel3.add(labelTickLeft);

		return panel3;
	}
	
	private JPanel buildContentPanel4(){
		JPanel panel4 = new JPanel();
		panel4.setLayout(new GridLayout(2, 0));
		panel4.setBackground(Color.lightGray);
		
		Font font = new Font("Arial", Font.BOLD, 12);
					
		labeldirection  = new JLabel("Direction: " + direction);
		labeldistance  = new JLabel("Distance: " + distance);
		
		labeldirection.setForeground(Color.red);
		labeldirection.setOpaque(true);
		labeldirection.setBackground(Color.WHITE);
		labeldirection.setFont(font);
		labeldistance.setForeground(Color.red);
		labeldistance.setOpaque(true);
		labeldistance.setBackground(Color.WHITE);
		labeldistance.setFont(font);
	
		panel4.add(labeldirection);
		panel4.add(labeldistance);

		return panel4;
	}
}	
	
