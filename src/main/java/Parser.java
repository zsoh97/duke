import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;


/**
 * Parser object used to parse user input into format to be displayed or saved in file.
 * Parser object can parse date input by user into LocalDate format.
 */
public class Parser {

    /**
     * Constructor for Parser object.
     */
    public Parser(){

    }

    /**
     * Parses duke user input into date using LocalDate.
     * @param unformattedDate unformatted date input by user.
     * @return Formatted date input.
     */
    protected static String parseDate(String unformattedDate){
        LocalDate localDate = LocalDate.parse(unformattedDate);
        int day = localDate.getDayOfMonth();
        Month month = localDate.getMonth();
        int year = localDate.getYear();
        return day + " " + month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " "
                + year + " ";
    }
}
