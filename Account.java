package bank.model;

import java.time.LocalDate;

public abstract class Account {
    protected String firstName;
    protected String lastName;
    protected String nin;
    protected String email;
    protected String phone;
    protected LocalDate dob;
    protected String branch;
    protected double deposit;
    protected String accountNumber;

    public Account(String firstName, String lastName, String nin, String email, String phone, LocalDate dob, String branch, double deposit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nin = nin;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.branch = branch;
        this.deposit = deposit;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNin() {
        return nin;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getBranch() {
        return branch;
    }

    public double getDeposit() {
        return deposit;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public abstract double minimumDeposit();

    @Override
    public String toString() {
        return "Account Details:\n" +
               "Name: " + firstName + " " + lastName + "\n" +
               "NIN: " + nin + "\n" +
               "Email: " + email + "\n" +
               "Phone: " + phone + "\n" +
               "Date of Birth: " + dob + "\n" +
               "Branch: " + branch + "\n" +
               "Initial Deposit: " + deposit + "\n" +
               "Account Number: " + accountNumber + "\n";
    }
}
