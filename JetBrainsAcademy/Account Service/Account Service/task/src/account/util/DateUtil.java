package account.util;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class DateUtil {

    private static final Pattern mmYYYY = Pattern.compile("[0-1]\\d-20\\d{2}");

    public static LocalDate mmYYYYToLocalDate(String input) {
        if (!mmYYYY.matcher(input).matches())
            throw new IllegalArgumentException("Required format: mm-YYYY");

        String[] dateTokens = input.split("-");
        final int month = Integer.parseInt(dateTokens[0]);
        if (month>12) throw new IllegalArgumentException("Invalid month");
        final int year = Integer.parseInt(dateTokens[1]);

        LocalDate date = LocalDate.of(month < 12 ? year : year+1,
                month < 12 ? month+1 : 1, 1);
        return date.minusDays(1L);
    }

    public static String localDateTommYYYY(LocalDate input) {
        return input.getMonth().getValue() + "-" + input.getYear();
    }
}
