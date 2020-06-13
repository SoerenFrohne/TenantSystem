package tenantsystem.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import tenantsystem.core.utils.Utils;
import tenantsystem.editor.IMenuItem;
import tenantsystem.session.Session;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Tenant implements IMenuItem {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String iban;
    private LocalDate birthday;
    private Building building;

    public void setAddress(String address) {
        if (Utils.validateAddress(address)) this.address = address;
    }

    public void setIban(String iban) {
        if(Utils.validateIban(iban)) this.iban = iban;
    }

    @Override
    public String toString() {
        return lastName + ", " + firstName;
    }

    @Override
    public String getFxmlPath() {
        Session.INSTANCE.setCurrentTenant(this);
        return "/gui/tenant.fxml";
    }
}

