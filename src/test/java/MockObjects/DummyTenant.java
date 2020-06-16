package MockObjects;

import tenantsystem.core.Building;
import tenantsystem.core.Tenant;
import tenantsystem.editor.IMenuItem;

import java.time.LocalDate;

public class DummyTenant extends Tenant implements IMenuItem{

    public DummyTenant(String firstName, String lastName, String address, String phoneNumber, String iban, LocalDate birthday, Building building) {
        super(null, null, null, null, null, null, null);
    }

    @Override
    public String toString() {
        throw new RuntimeException("Dummy tenant");
    }

    @Override
    public String getFxmlPath() {
        throw new RuntimeException("Dummy tenant");
    }
}

