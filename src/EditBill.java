import javax.swing.*;
import java.awt.*;

public class EditBill extends JFrame {

    private JTextField billIdField;
    private JTextField consumerField;
    private JTextField billDateField;
    private JTextField dueDateField;
    private JTextField unitsField;
    private JTextField amountField;

    private JComboBox<String> monthBox;
    private JComboBox<String> statusBox;

    private BillData.Bill currentBill;

    public EditBill() {

        setTitle("Electricity Bill Management System - Edit Bill");
        setSize(480, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel =
                new JPanel(new GridBagLayout());

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);

        int row = 0;

        // Bill ID
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Bill ID:"), gbc);

        billIdField = new JTextField(15);

        gbc.gridx = 1;
        panel.add(billIdField, gbc);
        row++;

        JButton searchButton =
                new JButton("Search Bill");

        gbc.gridx = 1;
        gbc.gridy = row;
        panel.add(searchButton, gbc);
        row++;

        // Month
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Month:"), gbc);

        String[] months = {
                "January", "February", "March",
                "April", "May", "June",
                "July", "August", "September",
                "October", "November", "December"
        };

        monthBox = new JComboBox<>(months);

        gbc.gridx = 1;
        panel.add(monthBox, gbc);
        row++;

        // Consumer
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Consumer Number:"), gbc);

        consumerField = new JTextField(15);

        gbc.gridx = 1;
        panel.add(consumerField, gbc);
        row++;

        // Bill Date
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Bill Date:"), gbc);

        billDateField = new JTextField(15);

        gbc.gridx = 1;
        panel.add(billDateField, gbc);
        row++;

        // Due Date
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Due Date:"), gbc);

        dueDateField = new JTextField(15);

        gbc.gridx = 1;
        panel.add(dueDateField, gbc);
        row++;

        // Units
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Units:"), gbc);

        unitsField = new JTextField(15);

        gbc.gridx = 1;
        panel.add(unitsField, gbc);
        row++;

        // Amount
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Amount:"), gbc);

        amountField = new JTextField(15);

        gbc.gridx = 1;
        panel.add(amountField, gbc);
        row++;

        // Status
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Status:"), gbc);

        statusBox =
                new JComboBox<>(
                        new String[]{"Unpaid", "Paid"}
                );

        gbc.gridx = 1;
        panel.add(statusBox, gbc);
        row++;

        JButton updateButton =
                new JButton("Update Bill");

        JButton backButton =
                new JButton("Back to Dashboard");

        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(updateButton, gbc);

        gbc.gridx = 1;
        panel.add(backButton, gbc);

        add(panel, BorderLayout.CENTER);
        add(LogoUtil.getLogoLabel(), BorderLayout.NORTH);

        searchButton.addActionListener(e -> searchBill());

        updateButton.addActionListener(e -> updateBill());

        backButton.addActionListener(e -> {
            new Dashboard().setVisible(true);
            dispose();
        });
    }

    private void searchBill() {

        String id =
                billIdField.getText().trim();

        currentBill = null;

        for (BillData.Bill bill : BillData.bills) {

            if (bill.billId.equalsIgnoreCase(id)) {

                currentBill = bill;
                break;
            }
        }

        if (currentBill == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Bill not found."
            );

            return;
        }

        monthBox.setSelectedItem(currentBill.month);
        consumerField.setText(currentBill.consumerNumber);
        billDateField.setText(currentBill.billDate);
        dueDateField.setText(currentBill.dueDate);
        unitsField.setText(String.valueOf(currentBill.units));
        amountField.setText(String.valueOf(currentBill.amount));
        statusBox.setSelectedItem(currentBill.status);

        JOptionPane.showMessageDialog(
                this,
                "Bill loaded successfully!"
        );
    }

    private void updateBill() {

        if (currentBill == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Search for a bill first."
            );

            return;
        }

        try {

            currentBill.month =
                    (String) monthBox.getSelectedItem();

            currentBill.consumerNumber =
                    consumerField.getText().trim();

            currentBill.billDate =
                    billDateField.getText().trim();

            currentBill.dueDate =
                    dueDateField.getText().trim();

            currentBill.units =
                    Double.parseDouble(
                            unitsField.getText().trim()
                    );

            currentBill.amount =
                    Double.parseDouble(
                            amountField.getText().trim()
                    );

            currentBill.status =
                    (String) statusBox.getSelectedItem();

            JOptionPane.showMessageDialog(
                    this,
                    "Bill updated successfully!"
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Units and Amount must be numeric."
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new EditBill().setVisible(true);
        });
    }
}