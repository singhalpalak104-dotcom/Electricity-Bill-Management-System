import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login() {

        setTitle("Electricity Bill Management System - Login");
        setSize(420, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ================= LOGO AT TOP =================

        add(LogoUtil.getLogoLabel(), BorderLayout.NORTH);

        // ================= FORM =================

        JPanel formPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);

        JLabel titleLabel = new JLabel("User Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        formPanel.add(titleLabel, gbc);

        // Username
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;

        formPanel.add(new JLabel("Username:"), gbc);

        usernameField = new JTextField(15);

        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 2;

        formPanel.add(new JLabel("Password:"), gbc);

        passwordField = new JPasswordField(15);

        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        // Buttons
        JButton loginButton = new JButton("Login");
        JButton forgotButton = new JButton("Forgot Password?");

        gbc.gridx = 0;
        gbc.gridy = 3;

        formPanel.add(loginButton, gbc);

        gbc.gridx = 1;

        formPanel.add(forgotButton, gbc);

        add(formPanel, BorderLayout.CENTER);

        // ================= LOGIN =================

        loginButton.addActionListener(e -> {

            String username = usernameField.getText().trim();

            String password =
                    new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter username and password."
                );

                return;
            }

            // GUI DEMO LOGIN
            if (username.equals("admin")
                    && password.equals("1234")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Login Successful!"
                );

                new Dashboard().setVisible(true);
                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid username or password.\n\n"
                                + "Demo Login:\n"
                                + "Username: admin\n"
                                + "Password: 1234"
                );
            }
        });

        // ================= FORGOT PASSWORD =================

        forgotButton.addActionListener(e -> {

            new ForgotPassword().setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
}