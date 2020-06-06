package tenantsystem.core;

import java.util.regex.Pattern;

public class CheckTenant {

    public boolean validateIban(String iban){
        String first = iban.substring(0,2);
        Pattern pat = Pattern.compile("[A-Z]+");

        return  first.matches("[A-Z]+");
    }

    //currently not available. maybe available in next sprint :)
    public boolean validateAddress(String address){
        return true;
    }
}
