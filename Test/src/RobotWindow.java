

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RobotWindow extends JFrame{
	private JTextField field1;
	private JTextField field2;
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
	
	public RobotWindow(){
		super();
		
		build();//On initialise notre fenêtre
	}
	
	private void build(){
		setTitle("Robot"); //On donne un titre à l'application
		setSize(800,600); //On donne une taille à notre fenêtre
		setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
		setResizable(false); //On interdit la redimensionnement de la fenêtre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
		setContentPane(buildContentPane());
	}

	private JPanel buildContentPane(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.lightGray);
		
		field1 = new JTextField();
		field1.setColumns(10);	
		field1.addActionListener(new GetAction(this));
		
		panel.add(field1);
		field2 = new JTextField();
		field2.setColumns(10);
		
		
		panel.add(field2);

		JButton boutonStart = new JButton(new ActionStart(this, "Start"));
		JButton boutonStop = new JButton(new ActionStop(this, "Stop"));
		JButton boutonInfos = new JButton(new ActionInfos(this, "Get infos"));
		JButton boutonTakePicture = new JButton(new ActionTakePicture(this, "Take picture"));


		panel.add(boutonStart);
		panel.add(boutonStop);
		panel.add(boutonInfos);
		panel.add(boutonTakePicture);
		
		labelSpeedMotorRight = new JLabel("SpeedMotorRight: " + SpeedMotorRight);
		labelSpeedMotorLeft  = new JLabel("labelSpeedMotorLeft: " + labelSpeedMotorLeft);
		labelTickRight  = new JLabel("labelTickRight: " + labelTickRight);
		labelTickLeft  = new JLabel("labelTickLeft: " + labelTickLeft);
		labeldirection  = new JLabel("labeldirection: " + labeldirection);
		labeldistance  = new JLabel("labeldistance: " + labeldistance);
		
		panel.add(labelSpeedMotorRight);
		panel.add(labelSpeedMotorLeft);
		panel.add(labelTickRight);
		panel.add(labelTickLeft);
		panel.add(labeldirection);
		panel.add(labeldistance);
		

		labelSpeedMotorRight.setText("SpeedMotorRight: " + SpeedMotorRight);
		
		return panel;
	}
	
		
	public JTextField getField1(){
		return field1;
	}
	
	public JTextField getField2(){
		return field2;
	}
	
	public JLabel getLabel(){
		return label;
	}
}