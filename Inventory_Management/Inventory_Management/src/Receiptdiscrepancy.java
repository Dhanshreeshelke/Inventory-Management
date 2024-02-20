import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Receiptdiscrepancy extends JFrame implements ActionListener {
	JFrame l;
	JLabel label1, label2, label3, label4;
	JButton button1, button2, button3;
	JTextField textfield1, textfield2, textfield3;

	Receiptdiscrepancy() {
		l = new JFrame("Inventry Management");
		l.setSize(700, 600);
		l.setLayout(null);// using no layout managers
		l.setVisible(true);// making the frame visible

		label1 = new JLabel("WELCOME TO THE RECEIPT DISCREPANCY PAGE");
		label1.setBounds(220, 20, 300, 40);

		label2 = new JLabel("GRN Number :");
		label2.setBounds(120, 90, 100, 30);

		textfield1 = new JTextField();
		textfield1.setBounds(230, 90, 100, 30);

		label3 = new JLabel("Part Number :");
		label3.setBounds(120, 150, 100, 30);

		textfield2 = new JTextField();
		textfield2.setBounds(230, 150, 100, 30);

		label4 = new JLabel("Quantity :");
		label4.setBounds(140, 200, 100, 30);

		textfield3 = new JTextField();
		textfield3.setBounds(230, 200, 100, 30);

		button1 = new JButton("Submit");
		button1.setBounds(150, 270, 100, 30);
		button1.addActionListener(this);

		button2 = new JButton("Home Page");
		button2.setBounds(550, 150, 100, 30);
		button2.addActionListener(this);

		button3 = new JButton("Back");
		button3.setBounds(580, 100, 70, 30);
		button3.addActionListener(this);

		JPanel panel = new JPanel();
		panel.setBackground(Color.green);
		panel.setBounds(0, 0, 700, 60);

		l.add(label1);
		l.add(label2);
		l.add(label3);
		l.add(label4);
		l.add(panel);
		l.add(button1);
		l.add(textfield1);
		l.add(textfield2);
		l.add(textfield3);
		l.add(button2);
		l.add(button3);
		l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		l.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			if (textfield1.getText().equals("") || textfield2.getText().equals("") || textfield3.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Wrong Inputs", "Title", JOptionPane.ERROR_MESSAGE);
			} else {
				int ans = JOptionPane.showConfirmDialog(null, "Are you sure ?", "Title",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (ans == 0) {
					insertIntoReceiptdiscrepancyTable(textfield1.getText(), textfield2.getText(), textfield3.getText());
					l.setVisible(false);
					new Receiptdiscrepancy();
				}
			}
		}
		if (e.getSource() == button2) {
			int ans = JOptionPane.showConfirmDialog(null, "Are you sure ? You may loss your records.", "Title",
					JOptionPane.YES_NO_CANCEL_OPTION);
			if (ans == 0) {
				l.setVisible(false);
				new Mainpage();
			}
		}
		if (e.getSource() == button3) {
			int ans = JOptionPane.showConfirmDialog(null, "Are you sure ? You may loss your records.", "Title",
					JOptionPane.YES_NO_CANCEL_OPTION);
			if (ans == 0) {
				l.setVisible(false);
				new Recieving();
			}
		}
	}

	private void insertIntoReceiptdiscrepancyTable(String Grn, String Part_number, String Quantity) {
		try {
			String url = "jdbc:mysql://localhost:3306/inventory";
			String uname = "root";
			String pass = "Wireless@26";

			String query = "INSERT INTO Receiptdiscrepancy (Grn, Part_number, Quantity) VALUES (?, ?, ?)";

			// Corrected class name for MySQL Connector/J 8+
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(url, uname, pass);
			PreparedStatement st = con.prepareStatement(query);

			st.setString(1, Grn);
			st.setInt(2, Integer.parseInt(Part_number));
			st.setInt(3, Integer.parseInt(Quantity));

			int count = st.executeUpdate();
			System.out.println(count + " row/s affected");

			st.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Receiptdiscrepancy();
	}
}
