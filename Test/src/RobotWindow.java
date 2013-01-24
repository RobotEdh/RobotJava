

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
	private JLabel label;
	private JComboBox liste;
	
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

		JButton boutonTakePicture = new JButton(new ActionTakePicture(this, "Take picture"));
		JButton boutonGo = new JButton(new ActionGo(this, "Go"));
		JButton boutonStop = new JButton(new ActionStop(this, "Stop"));
		
		panel.add(boutonTakePicture);
		panel.add(boutonGo);
		panel.add(boutonStop);
		
		label = new JLabel("Résultat : Pas encore calculé");
		
		panel.add(label);
		
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