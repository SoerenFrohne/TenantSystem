package tenantsystem.session;

import lombok.Getter;
import lombok.Setter;
import tenantsystem.core.Apartment;
import tenantsystem.core.Building;
import tenantsystem.core.Tenant;
import tenantsystem.core.utils.Utils;
import tenantsystem.db.DatabaseService;
import tenantsystem.db.DatabaseMock;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Singleton-object as model
 */
public enum Session {
    INSTANCE;

    @Getter private final DatabaseService databaseService = new DatabaseService();
    @Getter @Setter private Utils utils;

    @Getter @Setter private Building currentBuilding;
    @Getter @Setter private Tenant currentTenant;

    Session() {
        try {
            Statement db = new DatabaseMock().createDatabase();
            databaseService.setStatement(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Apartment getCurrentApartment() {
        return currentBuilding.getApartment(currentTenant);
    }

    public Tenant[] getTenants() throws SQLException {
        return databaseService.readTenants();
    }

    public Apartment[] getApartments() throws SQLException {
        return databaseService.readApartments();
    }

    //check the address and update it if is valid.
    public boolean updateAddress (String newAddress) {
        if (currentTenant == null)
            return false;

        if (utils.validateAddress(newAddress)){
            currentTenant.setAddress(newAddress);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean updatePhoneNumber (String phoneNumber){

        if (currentTenant == null)
            return false;

        if (utils.validatePhoneNumber(phoneNumber)) {
            currentTenant.setPhoneNumber(phoneNumber);
            return true;
        }
        else
            return false;
    }

    public boolean updateIban (String iban){

        if (currentTenant == null)
            return false;

        if (utils.validateIban(iban)){
            currentTenant.setIban(iban);
            return true;
        }
        else {
            return false;
        }
    }
}
