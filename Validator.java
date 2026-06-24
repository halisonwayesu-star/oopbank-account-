package bank.validation;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        String trimmedName = name.trim();
        if (!trimmedName.matches("^[a-zA-Z ]+$")) {
            return false;
        }
        return trimmedName.length() >= 2 && trimmedName.length() <= 30;
    }

    public static boolean isValidNIN(String nin) {
        if (nin == null) {
            return false;
        }
        return nin.length() == 14 && nin.matches("^[A-Z0-9]+$");
    }

    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null) {
            return false;
        }
        return phone.matches("\\+256\\d{9}");
    }

    public static boolean isValidPIN(String pin) {
        if (pin == null) {
            return false;
        }
        if (!pin.matches("\\d{4,6}")) {
            return false;
        }
        // Check for repeating digits like 0000, 1111, etc.
        for (int i = 0; i < 10; i++) {
            if (pin.matches(String.format("(%d)\\1+", i))) {
                return false;
            }
        }
        return true;
    }

    public static int calculateAge(LocalDate dob) {
        if (dob == null) {
            return 0;
        }
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public static boolean isValidAge(LocalDate dob, int minAge, int maxAge) {
        int age = calculateAge(dob);
        return age >= minAge && age <= maxAge;
    }

    public static boolean isMinimumDepositMet(double deposit, double minimumRequired) {
        return deposit >= minimumRequired;
    }
}
