package utilities;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static utilities.Validator.isStringNullOrEmpty;

public class DateUtil {

    public static Date createSqlDate(String date) throws ParseException {
        if(isStringNullOrEmpty(date)) {
            return null;
        }

        DateFormat format = new SimpleDateFormat("MMMM d, yyyy h:mm a", Locale.ENGLISH);
        java.util.Date newDate = format.parse(date);

        //TODO doriesit cas, toto vytvori sql Date bez casovej informacie aj ked util Date ju obsahuje. Treba pouzit Timestamp . Na backende, aj databazovej urovnit, pravdepodobne budu nutna zmena aj na klientovi
        // https://stackoverflow.com/questions/29392755/how-to-store-time-from-java-util-date-into-java-sql-date
        return new Date(newDate.getTime());
    }
}
