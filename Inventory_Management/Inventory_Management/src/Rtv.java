import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Rtv extends JFrame implements ActionListener {
	JFrame r;
	JTextArea textarea1;
	JLabel label1, label2, label3, label4, label5, label6;
	JButton button1, button2, button3;
	JTextField textfield1, textfield2, textfield3, textfield4;

	Rtv() {
		r = new JFrame("Inventry Management");
		r.setSize(700, 600);
		r.setLayout(null);// using no layout managers
		r.setVisible(true);// making the frame visible

		label1 = new JLabel("WELCOME TO THE RETURN TO VENDER PAGE");
		label1.setBounds(220, 20, 300, 40);

		label2 = new JLabel("GRN Number :");
		label2.setBounds(130, 90, 100, 30);

		textfield1 = new JTextField();
		textfield1.setBounds(230, 90, 100, 30);

		label3 = new JLabel("Supplier Name :");
		label3.setBounds(120, 150, 100, 30);

		textfield2 = new JTextField();
		textfield2.setBounds(230, 150, 100, 30);

		label4 = new JLabel("Part Number :");
		label4.setBounds(130, 200, 100, 30);

		textfield3 = new JTextField();
		textfield3.setBounds(230, 200, 100, 30);

		label5 = new JLabel("Quantity :");
		label5.setBounds(150, 250, 100, 30);

		textfield4 = new JTextField();
		textfield4.setBounds(230, 250, 100, 30);

		label6 = new JLabel("Reason :");
		label6.setBounds(150, 300, 100, 30);

		textarea1 = new JTextArea();
		textarea1.setBounds(230, 300, 220, 70);

		button1 = new JButton("Submit");
		button1.setBounds(150, 400, 100, 30);
		button1.addActionListener(this);

		button2 = new JButton("Back");
		button2.setBounds(550, 100, 100, 30);
		button2.addActionListener(this);

		JPanel panel = new JPanel();
		panel.setBackground(Color.green);
		panel.setBounds(0, 0, 700, 60);

		r.add(label1);
		r.add(label2);
		r.add(label3);
		r.add(label4);
		r.add(label5);
		r.add(label6);
		r.add(textarea1);
		r.add(panel);
		r.add(button1);
		r.add(button2);
		r.add(textfield1);
		r.add(textfield2);
		r.add(textfield3);
		r.add(textfield4);
		r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		r.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			if (textfield1.getText().equals("") || textfield2.getText().equals("") || textfield3.getText().equals("")
					|| textfield4.getText().equals("") || textarea1.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Wrong Inputs", "Title", JOptionPane.ERROR_MESSAGE);
			} else {
				int ans = JOptionPane.showConfirmDialog(null, "Are you sure ?", "Title",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (ans == 0) {
					insertIntoRtvTable(textfield1.getText(), textfield2.getText(), textfield3.getText(),
							textfield4.getText(), textarea1.getText());
					r.setVisible(false);
					new Rtv();
				}
			}
		}
		if (e.getSource() == button2) {
			int ans = JOptionPane.showConfirmDialog(null, "Are you sure ? You may loss your records.", "Title",
					JOptionPane.YES_NO_CANCEL_OPTION);
			if (ans == 0) {
				r.setVisible(false);
				new Mainpage();
			}
		}
	}

	private void insertIntoRtvTable(String Grn, String Supplier_Name, String Part_number, String Quantity,
			String Reason) {
		try {
			String url = "jdbc:mysql://localhost:3306/inventory";
			String uname = "root";
			String pass = "Wireless@26";

			String query = "INSERT INTO rtv (Grn, Supplier_Name,Part_number ,Quantity, Reason) VALUES (?, ?, ?, ?, ?)";

			// Corrected class name for MySQL Connector/J 8+
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(url, uname, pass);
			PreparedStatement st = con.prepareStatement(query);

			st.setString(1, Grn);
			st.setString(2, Supplier_Name);
			st.setInt(3, Integer.parseInt(Part_number));
			st.setInt(4, Integer.parseInt(Quantity));
			st.setString(5, Reason);

			int count = st.executeUpdate();
			System.out.println(count + " row/s affected");

			st.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Rtv();
	}
}
