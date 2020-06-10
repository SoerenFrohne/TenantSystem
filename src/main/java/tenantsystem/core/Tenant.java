package tenantsystem.core;

import lombok.AllArgsConstructor;
import lombok.Data;
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

