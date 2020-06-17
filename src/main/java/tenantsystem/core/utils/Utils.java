package tenantsystem.core.utils;

public interface Utils {

    boolean validateIban(String iban);

    boolean validateAddress(String address);

    boolean validatePhoneNumber (String phoneNumber);
}
