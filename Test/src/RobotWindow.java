

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class RobotWindow extends JFrame{
	private JTextField field1;

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
		setSize(500,300); //On donne une taille à notre fenêtre
		setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
		setResizable(false); //On interdit la redimensionnement de la fenêtre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
		setContentPane(buildContentPane());
	}

	private JPanel buildContentPane(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));
		panel.setBackground(Color.lightGray);
			
		JButton boutonStart = new JButton(new ActionStart(this, "Start"));
		JButton boutonStop = new JButton(new ActionStop(this, "Stop"));
		//JButton boutonInfos = new JButton(new ActionInfos(this, "Get infos"));
		JButton boutonInfos = new JButton("Get infos");
		JButton boutonTakePicture = new JButton(new ActionTakePicture(this, "Take picture"));

		
		panel.add(boutonStart);
		panel.add(boutonStop);
		panel.add(boutonInfos);
		panel.add(boutonTakePicture);
		
		labelSpeedMotorRight = new JLabel("SpeedMotorRight: " + SpeedMotorRight);
		labelSpeedMotorLeft  = new JLabel("SpeedMotorLeft: " + SpeedMotorLeft);
		labelTickRight  = new JLabel("TickRight: " + TickRight);
		labelTickLeft  = new JLabel("TickLeft: " + TickLeft);
		labeldirection  = new JLabel("direction: " + direction);
		labeldistance  = new JLabel("distance: " + distance);
		
		labelSpeedMotorRight.setForeground(Color.red);
		labelSpeedMotorRight.setOpaque(true);
		labelSpeedMotorRight.setBackground(Color.WHITE);
		labelSpeedMotorLeft.setForeground(Color.red);
		labelSpeedMotorLeft.setOpaque(true);
		labelSpeedMotorLeft.setBackground(Color.WHITE);
		labelTickRight.setForeground(Color.red);
		labelTickRight.setOpaque(true);
		labelTickRight.setBackground(Color.WHITE);
		labelTickLeft.setForeground(Color.red);
		labelTickLeft.setOpaque(true);
		labelTickLeft.setBackground(Color.WHITE);
		labeldirection.setForeground(Color.red);
		labeldirection.setOpaque(true);
		labeldirection.setBackground(Color.WHITE);
		labeldistance.setForeground(Color.red);
		labeldistance.setOpaque(true);
		labeldistance.setBackground(Color.WHITE);
	
		panel.add(new JSeparator(), BorderLayout.CENTER);
		panel.add(new JSeparator(), BorderLayout.CENTER);
		panel.add(labelSpeedMotorRight);
		panel.add(labelSpeedMotorLeft);
		panel.add(labelTickRight);
		panel.add(labelTickLeft);
		panel.add(labeldirection);
		panel.add(labeldistance);

		return panel;
	}
	
}	
	
