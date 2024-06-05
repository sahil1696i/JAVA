import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Frontend extends Frame implements ActionListener {
    TextField tfUsername, tfPassword;

    Frontend() {
        Label u = new Label("Username:");
        Label p = new Label("Password:");
        tfUsername = new TextField();
        tfPassword = new TextField();
        tfPassword.setEchoChar('*');
        Button b = new Button("Login");
        
        add(u);
        add(tfUsername);
        add(p);
        add(tfPassword);
        add(b);

        u.setBounds(30, 100, 80, 30);
        tfUsername.setBounds(140, 100, 180, 30);
        p.setBounds(30, 180, 80, 30);
        tfPassword.setBounds(140, 180, 180, 30);
        b.setBounds(200, 230, 100, 60);

        b.addActionListener(this);
        setSize(400, 400);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM Credential WHERE Username=? AND Password=?");
            pst.setString(1, username); // Setting username
            pst.setString(2, password); // Setting password
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("Login Successful");
            } else {
                System.out.println("Login Unsuccessful");
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println("Database Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Frontend();
    }
}
