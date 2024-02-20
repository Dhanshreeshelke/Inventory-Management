import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Dispatch extends JFrame implements ActionListener {
	JFrame r;
	JTextArea textarea1;
	JLabel label1, label2, label3, label4, label5, label6, label7;
	JButton button1, button2, button3;
	JTextField textfield1, textfield2, textfield3, textfield4, textfield5;

	Dispatch() {
		r = new JFrame("Inventry Management");
		r.setSize(700, 600);
		r.setLayout(null);// using no layout managers
		r.setVisible(true);// making the frame visible

		label1 = new JLabel("WELCOME TO THE DISPATCH PAGE");
		label1.setBounds(220, 20, 300, 40);

		label2 = new JLabel("Customer Name :");
		label2.setBounds(100, 90, 100, 30);

		textfield1 = new JTextField();
		textfield1.setBounds(230, 90, 100, 30);

		label3 = new JLabel("Product code:");
		label3.setBounds(120, 150, 100, 30);

		textfield2 = new JTextField();
		textfield2.setBounds(230, 150, 100, 30);

		label4 = new JLabel("Quantity :");
		label4.setBounds(140, 210, 100, 30);

		textfield3 = new JTextField();
		textfield3.setBounds(230, 210, 100, 30);

		label5 = new JLabel("Material cost :");
		label5.setBounds(120, 270, 100, 30);

		textfield4 = new JTextField();
		textfield4.setBounds(230, 270, 100, 30);

		button1 = new JButton("Submit");
		button1.setBounds(150, 330, 100, 30);
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
			if (textfield1.getText().equals("") || textfield2.getText().equals("") || textfield3.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Wrong Inputs", "Title", JOptionPane.ERROR_MESSAGE);

			} else {
				int ans = JOptionPane.showConfirmDialog(null, "Are you sure ?", "Title",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (ans == 0) {
					insertIntoDispatchTable(textfield1.getText(), textfield2.getText(), textfield3.getText(),
							textfield4.getText());
					r.setVisible(false);
					new Dispatch();
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

	private void insertIntoDispatchTable(String Customer_Name, String Product_Code, String Quantity,
			String Material_Cost) {
		try {
			String url = "jdbc:mysql://localhost:3306/inventory";
			String uname = "root";
			String pass = "Wireless@26";

			String query = "INSERT INTO Dispatch (Customer_Name, Product_Code, Quantity, Material_Cost) VALUES (?, ?, ?, ?)";

			// Corrected class name for MySQL Connector/J 8+
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(url, uname, pass);
			PreparedStatement st = con.prepareStatement(query);

			st.setString(1, Customer_Name);
			st.setInt(2, Integer.parseInt(Product_Code));
			st.setInt(3, Integer.parseInt(Quantity));
			st.setInt(4, Integer.parseInt(Material_Cost));

			int count = st.executeUpdate();
			System.out.println(count + " row/s affected");

			st.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Dispatch();
	}
}
