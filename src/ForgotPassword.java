import javax.swing.*;
import java.awt.*;

public class ForgotPassword extends JFrame {

    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField newPasswordField;

    public ForgotPassword() {

        setTitle("Electricity Bill Management System - Forgot Password");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel =
                new JPanel(new GridBagLayout());

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);

        JLabel title =
                new JLabel("Reset Password");

        title.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        panel.add(title, gbc);

        // Username
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;

        panel.add(
                new JLabel("Username:"),
                gbc
        );

        usernameField =
                new JTextField(15);

        gbc.gridx = 1;

        panel.add(
                usernameField,
                gbc
        );

        // Email
        gbc.gridx = 0;
        gbc.gridy = 2;

        panel.add(
                new JLabel("Email:"),
                gbc
        );

        emailField =
                new JTextField(15);

        gbc.gridx = 1;

        panel.add(
                emailField,
                gbc
        );

        // New password
        gbc.gridx = 0;
        gbc.gridy = 3;

        panel.add(
                new JLabel("New Password:"),
                gbc
        );

        newPasswordField =
                new JPasswordField(15);

        gbc.gridx = 1;

        panel.add(
                newPasswordField,
                gbc
        );

        JButton resetButton =
                new JButton("Reset Password");

        JButton backButton =
                new JButton("Back to Login");

        gbc.gridx = 0;
        gbc.gridy = 4;

        panel.add(resetButton, gbc);

        gbc.gridx = 1;

        panel.add(backButton, gbc);

        add(
                panel,
                BorderLayout.CENTER
        );

        add(
                LogoUtil.getLogoLabel(),
                BorderLayout.NORTH
        );

        // RESET
        resetButton.addActionListener(e -> {

            String username =
                    usernameField.getText().trim();

            String email =
                    emailField.getText().trim();

            String password =
                    new String(
                            newPasswordField.getPassword()
                    );

            if (username.isEmpty()
                    || email.isEmpty()
                    || password.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all fields."
                );

                return;
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Password reset successfully!"
            );

            usernameField.setText("");
            emailField.setText("");
            newPasswordField.setText("");
        });

        // BACK
        backButton.addActionListener(e -> {

            new Login().setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new ForgotPassword().setVisible(true);
        });
    }
}