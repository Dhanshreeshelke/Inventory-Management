import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Recieving extends JFrame implements ActionListener {
	JFrame v;
	JLabel label1, label2;
	JButton button1, button2, button3, button4;

	Recieving() {
		v = new JFrame("Inventry Management");
		v.setSize(700, 600);
		v.setLayout(null);// using no layout managers
		v.setVisible(true);// making the frame visible

		label1 = new JLabel("WELCOME TO THE RECIEVING PAGE");
		label1.setBounds(220, 20, 300, 40);

		label2 = new JLabel();
		label2.setIcon(new ImageIcon("Inventory_Management\\src\\Recieving.jpg")); // Sets the image to be displayed as
		label2.setBounds(0, 60, 700, 540);

		button1 = new JButton("GRN(Goods Reciept Number)");
		button1.setBounds(20, 90, 200, 30);
		button1.addActionListener(this);

		button2 = new JButton("Receipt Discrepancy");
		button2.setBounds(20, 155, 200, 30);
		button2.addActionListener(this);

		button3 = new JButton("Back");
		button3.setBounds(600, 100, 80, 30);
		button3.addActionListener(this);

		JPanel panel = new JPanel();
		panel.setBackground(Color.green);
		panel.setBounds(0, 0, 700, 60);

		v.add(label1);
		v.add(panel);
		v.add(button1);
		v.add(button2);
		v.add(button3);
		v.add(label2);
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			v.setVisible(false);
			new Grn();
		}
		if (e.getSource() == button2) {
			v.setVisible(false);
			new Receiptdiscrepancy();
		}
		if (e.getSource() == button3) {
			v.setVisible(false);
			new Mainpage();
		}

	}

	public static void main(String[] args) {
		new Recieving();
	}
}
