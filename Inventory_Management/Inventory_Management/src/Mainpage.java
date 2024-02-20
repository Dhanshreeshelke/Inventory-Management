import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mainpage extends JFrame implements ActionListener {
	JFrame g;
	JLabel label, label1;
	JButton button1, button2, button3, button4,
			button5;

	Mainpage() {
		g = new JFrame("Inventry Management");
		g.setSize(700, 600);
		g.setLayout(null);// using no layout managers
		g.setVisible(true);// making the frame visible

		label = new JLabel("WELCOME TO THE HOMEPAGE");
		label.setBounds(220, 20, 300, 40);

		JPanel panel = new JPanel();
		panel.setBackground(Color.green);
		panel.setBounds(0, 0, 700, 60);

		button1 = new JButton("Recieving");
		button1.setBounds(20, 80, 200, 30);
		button1.addActionListener(this);

		button3 = new JButton("Dispatch Details");
		button3.setBounds(20, 180, 200, 30);
		button3.addActionListener(this);

		button4 = new JButton("Return To Vender(RTV)");
		button4.setBounds(20, 230, 200, 30);
		button4.addActionListener(this);

		button2 = new JButton("View Material Transaction");
		button2.setBounds(20, 130, 200, 30);
		button2.addActionListener(this);

		label1 = new JLabel(); // JLabel Creation
		label1.setIcon(new ImageIcon("Inventory_Management\\src\\inventory1.jpg"));
		label1.setBounds(250, 80, 400, 400);

		g.add(label);
		g.add(label1);
		g.add(button1);
		g.add(button2);
		g.add(button3);
		g.add(button4);
		g.add(panel);

		g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		g.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			new Recieving();
			g.dispose(); // Dispose the current frame
		}

		if (e.getSource() == button3) {
			new Dispatch();
			g.setVisible(false);
		}

		if (e.getSource() == button4) {
			g.setVisible(false);
			new Rtv();
		}

		if (e.getSource() == button2) {
			new ViewTransaction().setVisible(true); // Set visibility to true for the new frame
			g.dispose(); // Dispose the current frame
		}
	}

	public static void main(String[] args) {

		new Mainpage();

	}

}
