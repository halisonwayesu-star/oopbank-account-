package bank.model;

import java.time.LocalDate;

public class StudentAccount extends Account {
    private static final double MIN_DEPOSIT = 10000;

    public StudentAccount(String firstName, String lastName, String nin, String email, String phone, LocalDate dob, String branch, double deposit) {
        super(firstName, lastName, nin, email, phone, dob, branch, deposit);
    }

    @Override
    public double minimumDeposit() {
        return MIN_DEPOSIT;
    }
}
