import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard() {

        setTitle("Electricity Bill Management - Dashboard");
        setSize(500, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ================= TOP SECTION =================

        JPanel topPanel = new JPanel(new BorderLayout());

        topPanel.add(
                LogoUtil.getLogoLabel(),
                BorderLayout.NORTH
        );

        JLabel title = new JLabel(
                "Electricity Bill Management System",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        5, 5, 10, 5
                )
        );

        topPanel.add(title, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // ================= BUTTONS =================

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                3, 2, 15, 15
                        )
                );

        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 40, 20, 40
                )
        );

        JButton add = new JButton("Add Bill");
        JButton view = new JButton("View Bills");
        JButton edit = new JButton("Edit Bill");
        JButton delete = new JButton("Delete Bill");
        JButton payment = new JButton("Payment");
        JButton logout = new JButton("Logout");

        buttonPanel.add(add);
        buttonPanel.add(view);
        buttonPanel.add(edit);
        buttonPanel.add(delete);
        buttonPanel.add(payment);
        buttonPanel.add(logout);

        add(buttonPanel, BorderLayout.CENTER);

        // ================= CONNECTIONS =================

        add.addActionListener(e -> {
            new AddBill().setVisible(true);
            dispose();
        });

        view.addActionListener(e -> {
            new ViewBills().setVisible(true);
            dispose();
        });

        edit.addActionListener(e -> {
            new EditBill().setVisible(true);
            dispose();
        });

        delete.addActionListener(e -> {
            new DeleteBill().setVisible(true);
            dispose();
        });

        payment.addActionListener(e -> {
            new Payment().setVisible(true);
            dispose();
        });

        logout.addActionListener(e -> {
            new Login().setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Dashboard().setVisible(true);
        });
    }
}