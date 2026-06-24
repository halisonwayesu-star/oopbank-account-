package bank.util;

import java.time.Year;
import java.util.HashMap;
import java.util.Map;

public class AccountNumberGenerator {

    private static final Map<String, String> branchCodes = new HashMap<>();
    private static long counter = 0;

    static {
        branchCodes.put("Kampala", "KLA");
        branchCodes.put("Gulu", "GUL");
        branchCodes.put("Mbarara", "MBR");
        branchCodes.put("Jinja", "JIN");
        branchCodes.put("Mbale", "MBA");
    }

    public static String generateAccountNumber(String branch) {
        String code = branchCodes.getOrDefault(branch, "UNK"); // UNK for unknown branch
        String year = String.valueOf(Year.now().getValue());
        counter++;
        return String.format("%s-%s-%06d", code, year, counter);
    }

    // For testing or resetting purposes
    public static void resetCounter() {
        counter = 0;
    }
}
