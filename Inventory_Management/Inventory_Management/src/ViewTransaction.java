import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewTransaction extends JFrame {

    public ViewTransaction() {
        super("Inventory Management");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        // Create a layout manager for organizing tables
        GridLayout mainLayout = new GridLayout(1, 1);
        setLayout(mainLayout);

        // Create a tabbed pane to hold tables
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add tables to the tabbed pane
        tabbedPane.addTab("Dispatch",
                createTable("dispatch", "Customer_Name", "Product_Code", "Quantity", "Material_Cost"));
        tabbedPane.addTab("GRN",
                createTable("grn", "Supplier_Name", "Part_number", "Quantity", "Grn"));
        tabbedPane.addTab("Receiptdiscrepancy", createTable("receiptdiscrepancy", "Grn", "Part_number", "Quantity"));
        tabbedPane.addTab("Return To Vender(RTV)",
                createTable("rtv", "Grn", "Supplier_Name", "Part_number", "Quantity", "Reason"));

        add(tabbedPane);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new Mainpage();

                dispose();
            }
        });

        // Create a panel for the button
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);

        // Add the button panel to the main frame
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JScrollPane createTable(String tableName, String... columns) {
        // Create a panel with a layout manager for organizing the table and its label
        JPanel panel = new JPanel(new GridLayout(2, 1));

        // Add a label for the table name
        JLabel tableNameLabel = new JLabel(tableName);
        panel.add(tableNameLabel);

        // Create a table model
        DefaultTableModel tableModel = new DefaultTableModel();
        for (String column : columns) {
            tableModel.addColumn(column);
        }
        JTable dataTable = new JTable(tableModel);

        // Add the table to the panel
        panel.add(new JScrollPane(dataTable));

        // Connect to the MySQL database and retrieve data
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Replace the URL, username, and password with your MySQL database connection
            // details
            String url = "jdbc:mysql://localhost:3306/inventory";
            String username = "root";
            String password = "Wireless@26";

            // Create a connection
            connection = DriverManager.getConnection(url, username, password);

            // Create a statement
            statement = connection.createStatement();

            // Construct the query using the provided table name
            String query = "SELECT * FROM " + tableName;
            resultSet = statement.executeQuery(query);

            // Populate the table model with data
            while (resultSet.next()) {
                Object[] rowData = new Object[columns.length];
                for (int i = 0; i < columns.length; i++) {
                    rowData[i] = resultSet.getObject(columns[i]);
                }
                tableModel.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the resources in a finally block
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Return the panel containing the table and its label
        return new JScrollPane(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ViewTransaction().setVisible(true);
        });
    }
}
