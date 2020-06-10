package tenantsystem.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import tenantsystem.editor.IMenuItem;
import tenantsystem.session.Session;

@Data
@AllArgsConstructor
public class Tenant implements IMenuItem {
    public String firstName;
    public String lastName;
    public String address;
    public String phoneNumber;
    public String iban;

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

