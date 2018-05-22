import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame
{
    private JButton login; private JTextField userField;
    private JButton cancel;private JPasswordField passwordField;
    String user ; String password; String admin = "admin";

    public LoginFrame()
    {
        JPanel panelTop = new JPanel(new GridLayout(2, 2));

        JLabel username_label = new JLabel("Përdoruesi:");
        userField = new JTextField();

        JLabel password_label = new JLabel("Fjalëkalimi:");
        passwordField = new JPasswordField();

        panelTop.add(username_label);
        panelTop.add(userField);
        panelTop.add(password_label);
        panelTop.add(passwordField);
        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        login = new JButton("Login");
        login.addActionListener(new LoginFrameListener());
        cancel = new JButton("Cancel");
        cancel.addActionListener(new LoginFrameListener());
        panelBottom.add(cancel);
        panelBottom.add(login);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(panelTop, BorderLayout.NORTH);
        mainPanel.add(panelBottom, BorderLayout.SOUTH);
        getContentPane().add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,300);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    class LoginFrameListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == login)
            {
                user = userField.getText();   password = passwordField.getText();

                if ((user.equals(admin)) && (password.equals(admin)))
                {
                    new MainFrame();
                }
                else
                    {
                        JOptionPane.showMessageDialog(null, "Incorrect info");
                    }
            }
            else if (ae.getSource() == cancel)
            {
                System.exit(0);
            }
        }
    }
    public static void main(String [] args)
    {
        new LoginFrame();
    }
}