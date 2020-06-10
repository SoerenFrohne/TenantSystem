package tenantsystem.core.utils;

public class Utils {

    public static boolean validateIban(String iban) {
        String first = iban.substring(0, 2);
        //Pattern pat = Pattern.compile("[A-Z]+");
        return first.matches("[A-Z]+");
    }

    public static boolean validateAddress(String address) {
        return true;
    }
}
