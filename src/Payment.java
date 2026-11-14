import javax.swing.*;
import java.awt.*;

public class Payment extends JFrame {

    private JTextField billIdField;
    private JTextArea detailsArea;

    private BillData.Bill currentBill;

    public Payment() {

        setTitle("Electricity Bill Management System - Payment");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ================= TOP SECTION =================

        JPanel topPanel = new JPanel(new BorderLayout());

        // LOGO AT TOP
        topPanel.add(
                LogoUtil.getLogoLabel(),
                BorderLayout.NORTH
        );

        // SEARCH PANEL
        JPanel searchPanel =
                new JPanel(new FlowLayout());

        billIdField = new JTextField(15);

        JButton searchButton =
                new JButton("Search Bill");

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


        // ================= BILL DETAILS =================

        detailsArea = new JTextArea();

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

        JButton payButton =
                new JButton("Pay Now");

        JButton backButton =
                new JButton("Back to Dashboard");

        JPanel bottomPanel =
                new JPanel();

        bottomPanel.add(payButton);
        bottomPanel.add(backButton);

        add(
                bottomPanel,
                BorderLayout.SOUTH
        );


        // ================= SEARCH =================

        searchButton.addActionListener(e -> {

            searchBill();

        });


        // ================= PAYMENT =================

        payButton.addActionListener(e -> {

            makePayment();

        });


        // ================= BACK =================

        backButton.addActionListener(e -> {

            new Dashboard().setVisible(true);
            dispose();

        });
    }


    // =====================================================
    // SEARCH BILL
    // =====================================================

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

        currentBill = null;

        for (BillData.Bill bill : BillData.bills) {

            if (bill.billId.equalsIgnoreCase(id)) {

                currentBill = bill;
                break;
            }
        }


        if (currentBill == null) {

            detailsArea.setText(
                    "Bill not found."
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Bill not found."
            );

            return;
        }

        showDetails();
    }


    // =====================================================
    // SHOW BILL DETAILS
    // =====================================================

    private void showDetails() {

        detailsArea.setText(

                "              BILL DETAILS\n\n" +

                        "Bill ID: " +
                        currentBill.billId +

                        "\nMonth: " +
                        currentBill.month +

                        "\nConsumer Number: " +
                        currentBill.consumerNumber +

                        "\nBill Date: " +
                        currentBill.billDate +

                        "\nDue Date: " +
                        currentBill.dueDate +

                        "\nUnits Consumed: " +
                        currentBill.units +

                        "\nBill Amount: ₹" +
                        currentBill.amount +

                        "\nPayment Status: " +
                        currentBill.status
        );
    }


    // =====================================================
    // MAKE PAYMENT
    // =====================================================

    private void makePayment() {

        if (currentBill == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please search for a bill first."
            );

            return;
        }


        if (currentBill.status.equalsIgnoreCase("Paid")) {

            JOptionPane.showMessageDialog(
                    this,
                    "This bill is already paid."
            );

            return;
        }


        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Pay ₹" +
                                currentBill.amount +
                                "?",
                        "Confirm Payment",
                        JOptionPane.YES_NO_OPTION
                );


        if (result ==
                JOptionPane.YES_OPTION) {

            currentBill.status = "Paid";

            showDetails();

            JOptionPane.showMessageDialog(
                    this,
                    "Payment Successful!"
            );
        }
    }


    // =====================================================
    // MAIN
    // =====================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new Payment().setVisible(true);

        });
    }
}