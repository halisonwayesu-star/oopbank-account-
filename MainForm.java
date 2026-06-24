package bank.gui;

import bank.database.AccountDAO;
import bank.model.*;
import bank.util.AccountNumberGenerator;
import bank.validation.Validator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Year;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainForm extends JFrame {
    private JTextField firstNameField, lastNameField, ninField, secondNinField, emailField, confirmEmailField, phoneField, depositField;
    private JPasswordField pinField, confirmPinField;
    private JComboBox<Integer> yearComboBox, monthComboBox, dayComboBox;
    private JComboBox<String> accountTypeComboBox, branchComboBox;
    private JButton submitButton, resetButton;
    private JTextArea summaryTextArea;

    private JLabel firstNameError, lastNameError, ninError, secondNinError, emailError, confirmEmailError, phoneError, pinError, confirmPinError, dobError, accountTypeError, branchError, depositError;

    public MainForm() {
        setTitle("FirstBank Uganda Account System");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners();
        updateDayComboBox(); // Initial update for day combo box
    }

    private void initComponents() {
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        ninField = new JTextField(20);
        secondNinField = new JTextField(20);
        emailField = new JTextField(20);
        confirmEmailField = new JTextField(20);
        phoneField = new JTextField(20);
        pinField = new JPasswordField(20);
        confirmPinField = new JPasswordField(20);
        depositField = new JTextField(20);

        yearComboBox = new JComboBox<>();
        for (int i = Year.now().getValue(); i >= 1900; i--) {
            yearComboBox.addItem(i);
        }
        monthComboBox = new JComboBox<>();
        for (int i = 1; i <= 12; i++) {
            monthComboBox.addItem(i);
        }
        dayComboBox = new JComboBox<>();

        accountTypeComboBox = new JComboBox<>(new String[]{"Select Account Type", "Savings", "Current", "Fixed Deposit", "Student", "Joint"});
        branchComboBox = new JComboBox<>(new String[]{"Select Branch", "Kampala", "Gulu", "Mbarara", "Jinja", "Mbale"});

        submitButton = new JButton("Submit");
        resetButton = new JButton("Reset");

        summaryTextArea = new JTextArea(10, 50);
        summaryTextArea.setEditable(false);
        summaryTextArea.setBorder(BorderFactory.createTitledBorder("Account Summary is Below:"));

        // Error labels
        firstNameError = new JLabel("");
        lastNameError = new JLabel("");
        ninError = new JLabel("");
        secondNinError = new JLabel("");
        emailError = new JLabel("");
        confirmEmailError = new JLabel("");
        phoneError = new JLabel("");
        pinError = new JLabel("");
        confirmPinError = new JLabel("");
        dobError = new JLabel("");
        accountTypeError = new JLabel("");
        branchError = new JLabel("");
        depositError = new JLabel("");

        setErrorLabelColors();
        secondNinField.setVisible(false);
        secondNinError.setVisible(false);
    }

    private void setErrorLabelColors() {
        Color errorColor = Color.RED;
        firstNameError.setForeground(errorColor);
        lastNameError.setForeground(errorColor);
        ninError.setForeground(errorColor);
        secondNinError.setForeground(errorColor);
        emailError.setForeground(errorColor);
        confirmEmailError.setForeground(errorColor);
        phoneError.setForeground(errorColor);
        pinError.setForeground(errorColor);
        confirmPinError.setForeground(errorColor);
        dobError.setForeground(errorColor);
        accountTypeError.setForeground(errorColor);
        branchError.setForeground(errorColor);
        depositError.setForeground(errorColor);
    }

    private void layoutComponents() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // First Name
        gbc.gridx = 0; gbc.gridy = row; mainPanel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = row; mainPanel.add(firstNameField, gbc);
        gbc.gridx = 2; gbc.gridy = row; mainPanel.add(firstNameError, gbc);
        row++;

        // Last Name
        gbc.gridx = 0; gbc.gridy = row; mainPanel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = row; mainPanel.add(lastNameField, gbc);
        gbc.gridx = 2; gbc.gridy = row; mainPanel.add(lastNameError, gbc);
        row++;

        // NIN
        gbc.gridx = 0; gbc.gridy = row; mainPanel.add(new JLabel("National ID (NIN):"), gbc);
        gbc.gridx = 1; gbc.gridy = row; mainPanel.add(ninField, gbc);
        gbc.gridx = 2; gbc.gridy = row; mainPanel.add(ninError, gbc);
        row++;

        // Second NIN (for Joint Account)
        gbc.gridx = 0; gbc.gridy = row; mainPanel.add(new JLabel("Second NIN:"), gbc);
        gbc.gridx = 1; gbc.gridy = row; mainPanel.add(secondNinField, gbc);
        gbc.gridx = 2; gbc.gridy = row; mainPanel.add(secondNinError, gbc);
        row++;

        // Email
        gbc.gridx = 0; gbc.gridy = row; mainPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.gridy = row; mainPanel.add(emailField, gbc);
        gbc.gridx = 2; gbc.gridy = row; mainPanel.add(emailError, gbc);
        row++;

        // Confirm Email
        gbc.gridx = 0; gbc.gridy = row; mainPanel.add(new JLabel("Confirm Email:"), gbc);
        gbc.gridx = 1; gbc.gridy = row; mainPanel.add(confirmEmailField, gbc);
        gbc.gridx = 2; gbc.gridy = row; mainPanel.add(confirmEmailError, gbc);
        row++;

        // Phone Number
        gbc.gridx = 0; gbc.gridy = row; mainPanel.add(new JLabel("Phone Number:"), gbc);
        gbc.gridx = 1; gbc.gridy = row; mainPanel.add(phoneField, gbc);
        gbc.gridx = 2; gbc.gridy = row; mainPanel.add(phoneError, gbc);
        row++;

        // PIN
        gbc.gridx = 0; gbc.gridy = row; mainPanel.add(new JLabel("PIN:"), gbc);
        gbc.gridx = 1; gbc.gridy = row; mainPanel.add(pinField, gbc);
        gbc.gridx = 2; gbc.gridy = row; mainPanel.add(pinError, gbc);
        row++;

        // Confirm PIN
        gbc.gridx = 0; gbc.gridy = row; mainPanel.add(new JLabel("Confirm PIN:"), gbc);
        gbc.gridx = 1; gbc.gridy = row; mainPanel.add(confirmPinField, gbc);
        gbc.gridx = 2; gbc.gridy = row; mainPanel.add(confirmPinError, gbc);
        row++;

        // Date of Birth
        gbc.gridx = 0; gbc.gridy = row; mainPanel.add(new JLabel("Date of Birth:"), gbc);
        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dobPanel.add(yearComboBox);
        dobPanel.add(monthComboBox);
        dobPanel.add(dayComboBox);
        gbc.gridx = 1; gbc.gridy = row; mainPanel.add(dobPanel, gbc);
        gbc.gridx = 2; gbc.gridy = row; mainPanel.add(dobError, gbc);
        row++;

        // Account Type
        gbc.gridx = 0; gbc.gridy = row; mainPanel.add(new JLabel("Account Type:"), gbc);
        gbc.gridx = 1; gbc.gridy = row; mainPanel.add(accountTypeComboBox, gbc);
        gbc.gridx = 2; gbc.gridy = row; mainPanel.add(accountTypeError, gbc);
        row++;

        // Branch
        gbc.gridx = 0; gbc.gridy = row; mainPanel.add(new JLabel("Branch:"), gbc);
        gbc.gridx = 1; gbc.gridy = row; mainPanel.add(branchComboBox, gbc);
        gbc.gridx = 2; gbc.gridy = row; mainPanel.add(branchError, gbc);
        row++;

        // Opening Deposit
        gbc.gridx = 0; gbc.gridy = row; mainPanel.add(new JLabel("Opening Deposit:"), gbc);
        gbc.gridx = 1; gbc.gridy = row; mainPanel.add(depositField, gbc);
        gbc.gridx = 2; gbc.gridy = row; mainPanel.add(depositError, gbc);
        row++;

        // Buttons
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2; 
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submitButton);
        buttonPanel.add(resetButton);
        mainPanel.add(buttonPanel, gbc);
        row++;

        // Summary Text Area
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 3; gbc.weighty = 1.0; // Allow vertical resizing
        mainPanel.add(new JScrollPane(summaryTextArea), gbc);

        add(mainPanel);
    }

    private void addListeners() {
        yearComboBox.addActionListener(e -> updateDayComboBox());
        monthComboBox.addActionListener(e -> updateDayComboBox());

        accountTypeComboBox.addActionListener(e -> {
            if ("Joint".equals(accountTypeComboBox.getSelectedItem())) {
                secondNinField.setVisible(true);
                secondNinError.setVisible(true);
            } else {
                secondNinField.setVisible(false);
                secondNinError.setVisible(false);
            }
            pack(); // Adjust frame size after visibility change
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });
    }

    private void updateDayComboBox() {
        dayComboBox.removeAllItems();
        Integer year = (Integer) yearComboBox.getSelectedItem();
        Integer month = (Integer) monthComboBox.getSelectedItem();

        if (year == null || month == null) {
            return;
        }

        Calendar calendar = new GregorianCalendar(year, month - 1, 1);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 1; i <= daysInMonth; i++) {
            dayComboBox.addItem(i);
        }
    }

    private void clearErrors() {
        firstNameError.setText("");
        lastNameError.setText("");
        ninError.setText("");
        secondNinError.setText("");
        emailError.setText("");
        confirmEmailError.setText("");
        phoneError.setText("");
        pinError.setText("");
        confirmPinError.setText("");
        dobError.setText("");
        accountTypeError.setText("");
        branchError.setText("");
        depositError.setText("");
    }

    private void handleSubmit() {
        clearErrors();
        StringBuilder errorSummary = new StringBuilder();
        boolean hasError = false;

        String firstName = firstNameField.getText().trim();
        if (!Validator.isValidName(firstName)) {
            firstNameError.setText("Invalid First Name");
            errorSummary.append("Invalid First Name\n");
            hasError = true;
        }

        String lastName = lastNameField.getText().trim();
        if (!Validator.isValidName(lastName)) {
            lastNameError.setText("Invalid Last Name\n");
            errorSummary.append("Invalid Last Name\n");
            hasError = true;
        }

        String nin = ninField.getText().trim();
        if (!Validator.isValidNIN(nin)) {
            ninError.setText("Invalid NIN (14 alphanumeric, uppercase)");
            errorSummary.append("Invalid NIN (14 alphanumeric, uppercase)\n");
            hasError = true;
        }

        String email = emailField.getText().trim();
        if (!Validator.isValidEmail(email)) {
            emailError.setText("Invalid Email");
            errorSummary.append("Invalid Email\n");
            hasError = true;
        }

        String confirmEmail = confirmEmailField.getText().trim();
        if (!email.equals(confirmEmail)) {
            confirmEmailError.setText("Emails do not match");
            errorSummary.append("Emails do not match\n");
            hasError = true;
        }

        String phone = phoneField.getText().trim();
        if (!Validator.isValidPhone(phone)) {
            phoneError.setText("Invalid Phone (+256XXXXXXXXX)");
            errorSummary.append("Invalid Phone (+256XXXXXXXXX)\n");
            hasError = true;
        }

        String pin = new String(pinField.getPassword());
        if (!Validator.isValidPIN(pin)) {
            pinError.setText("Invalid PIN (4-6 digits, no repeating)");
            errorSummary.append("Invalid PIN (4-6 digits, no repeating)\n");
            hasError = true;
        }

        String confirmPin = new String(confirmPinField.getPassword());
        if (!pin.equals(confirmPin)) {
            confirmPinError.setText("PINs do not match");
            errorSummary.append("PINs do not match\n");
            hasError = true;
        }

        LocalDate dob = null;
        try {
            dob = LocalDate.of((Integer) yearComboBox.getSelectedItem(), (Integer) monthComboBox.getSelectedItem(), (Integer) dayComboBox.getSelectedItem());
            if (!Validator.isValidAge(dob, 18, 75)) {
                dobError.setText("Age must be between 18 and 75");
                errorSummary.append("Age must be between 18 and 75\n");
                hasError = true;
            }
        } catch (Exception ex) {
            dobError.setText("Invalid Date of Birth");
            errorSummary.append("Invalid Date of Birth\n");
            hasError = true;
        }

        String accountType = (String) accountTypeComboBox.getSelectedItem();
        if (accountType == null || accountType.equals("Select Account Type")) {
            accountTypeError.setText("Account Type is required");
            errorSummary.append("Account Type is required\n");
            hasError = true;
        }

        String branch = (String) branchComboBox.getSelectedItem();
        if (branch == null || branch.equals("Select Branch")) {
            branchError.setText("Branch is required\n");
            errorSummary.append("Branch is required\n");
            hasError = true;
        }

        double deposit = 0;
        try {
            deposit = Double.parseDouble(depositField.getText());
            double minDeposit = 0;
            switch (accountType) {
                case "Savings": minDeposit = 50000; break;
                case "Current": minDeposit = 200000; break;
                case "Fixed Deposit": minDeposit = 1000000; break;
                case "Student": minDeposit = 10000; break;
                case "Joint": minDeposit = 100000; break;
            }
            if (!Validator.isMinimumDepositMet(deposit, minDeposit)) {
                depositError.setText("Min deposit: " + minDeposit);
                errorSummary.append("Minimum deposit not met: " + minDeposit + "\n");
                hasError = true;
            }
        } catch (NumberFormatException ex) {
            depositError.setText("Invalid Deposit Amount");
            errorSummary.append("Invalid Deposit Amount\n");
            hasError = true;
        }

        String secondNin = secondNinField.getText().trim();
        if ("Joint".equals(accountType)) {
            if (!Validator.isValidNIN(secondNin)) {
                secondNinError.setText("Invalid Second NIN");
                errorSummary.append("Invalid Second NIN\n");
                hasError = true;
            }
        }

        // Special validation for Student Account age
        if ("Student".equals(accountType) && dob != null) {
            if (!Validator.isValidAge(dob, 18, 25)) {
                dobError.setText("Student age must be between 18 and 25");
                errorSummary.append("Student age must be between 18 and 25\n");
                hasError = true;
            }
        }

        if (hasError) {
            JOptionPane.showMessageDialog(this, errorSummary.toString(), "Validation Error", JOptionPane.WARNING_MESSAGE);
            summaryTextArea.setText("Validation failed. Please check the errors above.");
            return;
        }

        // If validation succeeds, create account and save to DB
        try {
            Account newAccount;
            if ("Joint".equals(accountType)) {
                newAccount = new JointAccount(firstName, lastName, nin, email, phone, dob, branch, deposit, secondNin);
            } else if ("Savings".equals(accountType)) {
                newAccount = new SavingsAccount(firstName, lastName, nin, email, phone, dob, branch, deposit);
            } else if ("Current".equals(accountType)) {
                newAccount = new CurrentAccount(firstName, lastName, nin, email, phone, dob, branch, deposit);
            } else if ("Fixed Deposit".equals(accountType)) {
                newAccount = new FixedDepositAccount(firstName, lastName, nin, email, phone, dob, branch, deposit);
            } else if ("Student".equals(accountType)) {
                newAccount = new StudentAccount(firstName, lastName, nin, email, phone, dob, branch, deposit);
            } else {
                throw new IllegalArgumentException("Unknown account type");
            }

            String generatedAccountNumber = AccountNumberGenerator.generateAccountNumber(branch);
            newAccount.setAccountNumber(generatedAccountNumber);

            AccountDAO accountDAO = new AccountDAO();
            accountDAO.addAccount(newAccount);

            summaryTextArea.setText(newAccount.toString());
            JOptionPane.showMessageDialog(this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            summaryTextArea.setText("Error saving account to database.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            summaryTextArea.setText("An unexpected error occurred.");
        }
    }

    private void resetForm() {
        firstNameField.setText("");
        lastNameField.setText("");
        ninField.setText("");
        secondNinField.setText("");
        emailField.setText("");
        confirmEmailField.setText("");
        phoneField.setText("");
        pinField.setText("");
        confirmPinField.setText("");
        depositField.setText("");

        yearComboBox.setSelectedIndex(0);
        monthComboBox.setSelectedIndex(0);
        dayComboBox.setSelectedIndex(0);

        accountTypeComboBox.setSelectedIndex(0);
        branchComboBox.setSelectedIndex(0);

        secondNinField.setVisible(false);
        secondNinError.setVisible(false);

        clearErrors();
        summaryTextArea.setText("");
    }
}
