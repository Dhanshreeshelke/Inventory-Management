import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class FirstGUI extends JFrame implements ActionListener {
    JButton button1; // declaring instance variables
    JFrame f;
    JPasswordField passwordfield1;
    JLabel label, label1, labels, label2;
    JPanel panel;
    JTextField textfield1, textfield2;

    FirstGUI() {
        f = new JFrame("Inventory Management");
        panel = new JPanel();
        panel.setBackground(new Color(0, 255, 0));
        panel.setBounds(0, 0, 700, 60); // 0,0,700,60

        label1 = new JLabel("WELCOME TO THE LOGIN PAGE");
        label1.setFont(new Font("Arial", Font.PLAIN, 18));
        label1.setForeground(Color.RED);

        label1.setBounds(200, 20, 300, 40); // 200,20,300,40

        button1 = new JButton("LOGIN");
        button1.setBounds(90, 350, 100, 40); // 120,350,100,40
        button1.addActionListener(this);

        passwordfield1 = new JPasswordField();
        passwordfield1.setBounds(120, 250, 100, 30); // 120,250,100,30

        labels = new JLabel("Name:");
        // labels.setForeground(Color.RED);
        labels.setBounds(50, 100, 100, 30); // 50,100,100,30

        label2 = new JLabel("User ID :");
        // label2.setForeground(Color.RED);
        label2.setBounds(40, 170, 100, 30);// 40,170,100,30

        label = new JLabel("Password:");

        label.setBounds(30, 250, 100, 30); // 30,250,100,30

        textfield1 = new JTextField("");
        textfield1.setBounds(120, 100, 100, 30); // x=120,100,100,30

        textfield2 = new JTextField("");
        textfield2.setBounds(120, 170, 100, 30); // x=120,170,100,30

        // -----

        JLabel label3 = new JLabel();
        label3.setIcon(
                new ImageIcon(
                        "C:\\Users\\sst\\Downloads\\Inventory_Management\\Inventory_Management\\src\\Login.jpg"));
        label3.setBounds(280, 10, 400, 425);

        f.add(button1);
        f.add(passwordfield1);
        f.add(label);
        f.add(label1);
        f.add(label2);
        f.add(label3);
        f.add(labels);
        f.add(panel);
        f.add(textfield1);
        f.add(textfield2);
        f.setSize(700, 600);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            if (textfield1.getText().equals("") || textfield2.getText().equals("")
                    || !passwordfield1.getText().equals("Inventory123")) {
                JOptionPane.showMessageDialog(null, "Wrong Inputs", "Title", JOptionPane.ERROR_MESSAGE);
            } else {
                int ans = JOptionPane.showConfirmDialog(null, "Are you sure ?", "Title",
                        JOptionPane.YES_NO_CANCEL_OPTION);
                if (ans == 0) {
                    // Insert data into the login table
                    insertIntoLoginTable(textfield2.getText(), textfield1.getText(), passwordfield1.getText());

                    f.setVisible(false);
                    new Mainpage();
                }
            }
        }
    }

    private void insertIntoLoginTable(String userId, String userName, String password) {
        try {
            String url = "jdbc:mysql://localhost:3306/inventory";
            String uname = "root";
            String pass = "Wireless@26";

            String query = "INSERT INTO login (Name, User_ID, password) VALUES (?, ?, ?)";

            // Corrected class name for MySQL Connector/J 8+
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, uname, pass);
            PreparedStatement st = con.prepareStatement(query);

            st.setString(1, userId);
            st.setString(2, userName);
            st.setString(3, password);

            int count = st.executeUpdate();
            System.out.println(count + " row/s affected");

            st.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FirstGUI();
    }
}
