package utilities;

public class Validator {
    public static String isStringNumber(String value){
        try {
             Integer.parseInt(value);
        }catch (NumberFormatException e){
            return null;
        }
        return  value;
    }

    public static boolean isStringNullOrEmpty(String value){
        return  value.isEmpty()|| value == null;
    }
}
