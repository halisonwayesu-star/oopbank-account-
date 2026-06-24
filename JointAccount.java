package bank.model;

import java.time.LocalDate;

public class JointAccount extends Account {
    private static final double MIN_DEPOSIT = 100000;
    private String secondNin;

    public JointAccount(String firstName, String lastName, String nin, String email, String phone, LocalDate dob, String branch, double deposit, String secondNin) {
        super(firstName, lastName, nin, email, phone, dob, branch, deposit);
        this.secondNin = secondNin;
    }

    public String getSecondNin() {
        return secondNin;
    }

    public void setSecondNin(String secondNin) {
        this.secondNin = secondNin;
    }

    @Override
    public double minimumDeposit() {
        return MIN_DEPOSIT;
    }

    @Override
    public String toString() {
        return super.toString() +
               "Second NIN: " + secondNin + "\n";
    }
}
