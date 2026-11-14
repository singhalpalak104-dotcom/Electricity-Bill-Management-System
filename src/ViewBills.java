import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewBills extends JFrame {

    private JTable billsTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public ViewBills() {

        setTitle("Electricity Bill Management System - View Bills");
        setSize(800, 550);
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
                new JPanel(new FlowLayout(FlowLayout.LEFT));

        searchField = new JTextField(15);

        JButton searchButton =
                new JButton("Search");

        JButton refreshButton =
                new JButton("Show All");

        JButton backButton =
                new JButton("Back to Dashboard");

        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(refreshButton);
        searchPanel.add(backButton);

        topPanel.add(
                searchPanel,
                BorderLayout.CENTER
        );

        add(topPanel, BorderLayout.NORTH);

        // ================= TABLE =================

        String[] columns = {
                "Bill ID",
                "Month",
                "Consumer No.",
                "Bill Date",
                "Due Date",
                "Units",
                "Amount",
                "Status"
        };

        tableModel =
                new DefaultTableModel(columns, 0);

        billsTable = new JTable(tableModel);

        add(
                new JScrollPane(billsTable),
                BorderLayout.CENTER
        );

        // ================= LOAD DATA =================

        loadAllBills();

        // ================= SEARCH =================

        searchButton.addActionListener(e -> {

            searchBills(
                    searchField.getText().trim()
            );
        });

        // ================= SHOW ALL =================

        refreshButton.addActionListener(e -> {

            loadAllBills();
        });

        // ================= BACK =================

        backButton.addActionListener(e -> {

            new Dashboard().setVisible(true);
            dispose();
        });
    }

    // ================= LOAD ALL BILLS =================

    private void loadAllBills() {

        tableModel.setRowCount(0);

        for (BillData.Bill bill : BillData.bills) {

            tableModel.addRow(
                    new Object[]{
                            bill.billId,
                            bill.month,
                            bill.consumerNumber,
                            bill.billDate,
                            bill.dueDate,
                            bill.units,
                            bill.amount,
                            bill.status
                    }
            );
        }
    }

    // ================= SEARCH BILL =================

    private void searchBills(String keyword) {

        if (keyword.isEmpty()) {

            loadAllBills();
            return;
        }

        tableModel.setRowCount(0);

        boolean found = false;

        for (BillData.Bill bill : BillData.bills) {

            if (bill.billId.equalsIgnoreCase(keyword)
                    || bill.consumerNumber.equalsIgnoreCase(keyword)) {

                found = true;

                tableModel.addRow(
                        new Object[]{
                                bill.billId,
                                bill.month,
                                bill.consumerNumber,
                                bill.billDate,
                                bill.dueDate,
                                bill.units,
                                bill.amount,
                                bill.status
                        }
                );
            }
        }

        if (!found) {

            JOptionPane.showMessageDialog(
                    this,
                    "No matching bill found."
            );

            loadAllBills();
        }
    }

    // ================= MAIN =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new ViewBills().setVisible(true);

        });
    }
}