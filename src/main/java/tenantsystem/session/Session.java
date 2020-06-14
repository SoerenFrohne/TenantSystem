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
    public void updateAddress (Tenant p, String newAddress) throws Exception {
        if (p == null)
            throw new NullPointerException();

        if (utils.validateAddress(newAddress))
            p.setAddress(newAddress);
        else
            throw new Exception("Not Updated");
    }

    public boolean updatePhoneNumber (Tenant p, String phoneNumber){

        if (p == null)
            return false;

        if (utils.validatePhoneNumber(phoneNumber)) {
            p.setPhoneNumber(phoneNumber);
            return true;
        }
        else
            return false;
    }

    public boolean updateIban (Tenant p, String iban){

        if (p == null)
            return false;

        if (utils.validateIban(iban)){
            p.setIban(iban);
            return true;
        }
        else {
            return false;
        }
    }
}
