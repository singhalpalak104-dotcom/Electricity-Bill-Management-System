import java.util.ArrayList;

public class BillData {

    public static ArrayList<Bill> bills = new ArrayList<>();

    static {
        bills.add(new Bill(
                "B001",
                "August",
                "C1001",
                "2026-08-01",
                "2026-08-15",
                120,
                850,
                "Unpaid"
        ));

        bills.add(new Bill(
                "B002",
                "July",
                "C1002",
                "2026-07-01",
                "2026-07-15",
                180,
                1250,
                "Paid"
        ));
    }

    public static class Bill {

        String billId;
        String month;
        String consumerNumber;
        String billDate;
        String dueDate;
        double units;
        double amount;
        String status;

        public Bill(String billId, String month, String consumerNumber,
                    String billDate, String dueDate, double units,
                    double amount, String status) {

            this.billId = billId;
            this.month = month;
            this.consumerNumber = consumerNumber;
            this.billDate = billDate;
            this.dueDate = dueDate;
            this.units = units;
            this.amount = amount;
            this.status = status;
        }
    }
}