import javax.swing.*;
import java.awt.*;

public class AddBill extends JFrame {

    private JTextField billIdField;
    private JTextField consumerNumberField;
    private JTextField billDateField;
    private JTextField dueDateField;
    private JTextField unitsField;
    private JTextField amountField;

    private JComboBox<String> monthBox;
    private JComboBox<String> statusBox;

    public AddBill() {

        setTitle("Electricity Bill Management System - Add Bill");
        setSize(480, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel =
                new JPanel(new GridBagLayout());

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;

        // Bill ID
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Bill ID:"), gbc);

        billIdField = new JTextField(15);

        gbc.gridx = 1;
        formPanel.add(billIdField, gbc);
        row++;

        // Month
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Month:"), gbc);

        String[] months = {
                "January", "February", "March",
                "April", "May", "June",
                "July", "August", "September",
                "October", "November", "December"
        };

        monthBox = new JComboBox<>(months);

        gbc.gridx = 1;
        formPanel.add(monthBox, gbc);
        row++;

        // Consumer
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Consumer Number:"), gbc);

        consumerNumberField = new JTextField(15);

        gbc.gridx = 1;
        formPanel.add(consumerNumberField, gbc);
        row++;

        // Bill Date
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Bill Date:"), gbc);

        billDateField = new JTextField(15);

        gbc.gridx = 1;
        formPanel.add(billDateField, gbc);
        row++;

        // Due Date
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Due Date:"), gbc);

        dueDateField = new JTextField(15);

        gbc.gridx = 1;
        formPanel.add(dueDateField, gbc);
        row++;

        // Units
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Units Consumed:"), gbc);

        unitsField = new JTextField(15);

        gbc.gridx = 1;
        formPanel.add(unitsField, gbc);
        row++;

        // Amount
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Bill Amount:"), gbc);

        amountField = new JTextField(15);

        gbc.gridx = 1;
        formPanel.add(amountField, gbc);
        row++;

        // Status
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Payment Status:"), gbc);

        statusBox =
                new JComboBox<>(new String[]{"Unpaid", "Paid"});

        gbc.gridx = 1;
        formPanel.add(statusBox, gbc);
        row++;

        JButton saveButton = new JButton("Save Bill");
        JButton backButton = new JButton("Back to Dashboard");

        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(saveButton, gbc);

        gbc.gridx = 1;
        formPanel.add(backButton, gbc);

        add(formPanel, BorderLayout.CENTER);
        add(LogoUtil.getLogoLabel(), BorderLayout.NORTH);

        // SAVE
        saveButton.addActionListener(e -> saveBill());

        // BACK
        backButton.addActionListener(e -> {
            new Dashboard().setVisible(true);
            dispose();
        });
    }

    private void saveBill() {

        String billId = billIdField.getText().trim();
        String month =
                (String) monthBox.getSelectedItem();

        String consumerNumber =
                consumerNumberField.getText().trim();

        String billDate =
                billDateField.getText().trim();

        String dueDate =
                dueDateField.getText().trim();

        String unitsText =
                unitsField.getText().trim();

        String amountText =
                amountField.getText().trim();

        String status =
                (String) statusBox.getSelectedItem();

        if (billId.isEmpty() ||
                consumerNumber.isEmpty() ||
                billDate.isEmpty() ||
                dueDate.isEmpty() ||
                unitsText.isEmpty() ||
                amountText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields."
            );

            return;
        }

        try {

            double units =
                    Double.parseDouble(unitsText);

            double amount =
                    Double.parseDouble(amountText);

            // Check duplicate ID
            for (BillData.Bill bill : BillData.bills) {

                if (bill.billId.equalsIgnoreCase(billId)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Bill ID already exists."
                    );

                    return;
                }
            }

            BillData.bills.add(
                    new BillData.Bill(
                            billId,
                            month,
                            consumerNumber,
                            billDate,
                            dueDate,
                            units,
                            amount,
                            status
                    )
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Bill added successfully!"
            );

            clearFields();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Units and Amount must be numeric."
            );
        }
    }

    private void clearFields() {

        billIdField.setText("");
        consumerNumberField.setText("");
        billDateField.setText("");
        dueDateField.setText("");
        unitsField.setText("");
        amountField.setText("");

        monthBox.setSelectedIndex(0);
        statusBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new AddBill().setVisible(true);
        });
    }
}