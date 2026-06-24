package bank.model;

import java.time.LocalDate;

public class FixedDepositAccount extends Account {
    private static final double MIN_DEPOSIT = 1000000;

    public FixedDepositAccount(String firstName, String lastName, String nin, String email, String phone, LocalDate dob, String branch, double deposit) {
        super(firstName, lastName, nin, email, phone, dob, branch, deposit);
    }

    @Override
    public double minimumDeposit() {
        return MIN_DEPOSIT;
    }
}
