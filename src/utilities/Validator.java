package utilities;

public class Validator {
    public static Boolean isStringNumber(String value){
        try {
             Integer.parseInt(value);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    public static boolean isStringNullOrEmpty(String value){
        return value == null || value.isEmpty();
    }
}
