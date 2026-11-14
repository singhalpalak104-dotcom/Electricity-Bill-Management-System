import javax.swing.*;
import java.awt.*;

public class DeleteBill extends JFrame {

    private JTextField billIdField;
    private JTextArea detailsArea;

    public DeleteBill() {

        setTitle("Electricity Bill Management System - Delete Bill");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ================= TOP SECTION =================

        JPanel topPanel =
                new JPanel(new BorderLayout());

        // LOGO AT TOP
        topPanel.add(
                LogoUtil.getLogoLabel(),
                BorderLayout.NORTH
        );

        // SEARCH PANEL
        JPanel searchPanel =
                new JPanel(new FlowLayout());

        billIdField =
                new JTextField(15);

        JButton searchButton =
                new JButton("Search");

        searchPanel.add(
                new JLabel("Bill ID:")
        );

        searchPanel.add(
                billIdField
        );

        searchPanel.add(
                searchButton
        );

        topPanel.add(
                searchPanel,
                BorderLayout.CENTER
        );

        add(
                topPanel,
                BorderLayout.NORTH
        );

        // ================= DETAILS =================

        detailsArea =
                new JTextArea();

        detailsArea.setEditable(false);

        detailsArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        detailsArea.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15
                )
        );

        add(
                new JScrollPane(detailsArea),
                BorderLayout.CENTER
        );

        // ================= BUTTONS =================

        JButton deleteButton =
                new JButton("Delete Bill");

        JButton backButton =
                new JButton("Back to Dashboard");

        JPanel bottomPanel =
                new JPanel();

        bottomPanel.add(deleteButton);
        bottomPanel.add(backButton);

        add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // ================= SEARCH =================

        searchButton.addActionListener(e -> {

            searchBill();
        });

        // ================= DELETE =================

        deleteButton.addActionListener(e -> {

            deleteBill();
        });

        // ================= BACK =================

        backButton.addActionListener(e -> {

            new Dashboard().setVisible(true);
            dispose();
        });
    }

    // ================= SEARCH BILL =================

    private void searchBill() {

        String id =
                billIdField.getText().trim();

        if (id.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Bill ID."
            );

            return;
        }

        for (BillData.Bill bill : BillData.bills) {

            if (bill.billId.equalsIgnoreCase(id)) {

                detailsArea.setText(
                        "Bill Details\n\n" +

                                "Bill ID: " +
                                bill.billId +

                                "\nMonth: " +
                                bill.month +

                                "\nConsumer Number: " +
                                bill.consumerNumber +

                                "\nBill Date: " +
                                bill.billDate +

                                "\nDue Date: " +
                                bill.dueDate +

                                "\nUnits Consumed: " +
                                bill.units +

                                "\nBill Amount: ₹" +
                                bill.amount +

                                "\nPayment Status: " +
                                bill.status
                );

                return;
            }
        }

        detailsArea.setText("");

        JOptionPane.showMessageDialog(
                this,
                "Bill not found."
        );
    }

    // ================= DELETE BILL =================

    private void deleteBill() {

        String id =
                billIdField.getText().trim();

        if (id.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Bill ID."
            );

            return;
        }

        for (int i = 0;
             i < BillData.bills.size();
             i++) {

            BillData.Bill bill =
                    BillData.bills.get(i);

            if (bill.billId.equalsIgnoreCase(id)) {

                int result =
                        JOptionPane.showConfirmDialog(
                                this,
                                "Are you sure you want to delete this bill?",
                                "Confirm Delete",
                                JOptionPane.YES_NO_OPTION
                        );

                if (result ==
                        JOptionPane.YES_OPTION) {

                    BillData.bills.remove(i);

                    detailsArea.setText("");

                    billIdField.setText("");

                    JOptionPane.showMessageDialog(
                            this,
                            "Bill deleted successfully!"
                    );
                }

                return;
            }
        }

        JOptionPane.showMessageDialog(
                this,
                "Bill not found."
        );
    }

    // ================= MAIN =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new DeleteBill().setVisible(true);

        });
    }
}