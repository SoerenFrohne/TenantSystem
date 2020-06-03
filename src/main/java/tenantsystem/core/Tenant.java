package tenantsystem.core;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tenant {
    public String firstName;
    public String lastName;
    public String address;
    public String phoneNumber;
    public String iban;

    @Override
    public String toString() {
        return address + ", " + lastName;
    }
}

