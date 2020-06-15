package tenantsystem.db;

import lombok.Getter;
import lombok.Setter;
import tenantsystem.core.Apartment;
import tenantsystem.core.Tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseService {

    @Setter @Getter private Statement statement;

    public Apartment[] readApartments() throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM APARTMENTS");

        if(result == null){
            return null;
        }

        ArrayList<Apartment> apartments = new ArrayList<>();

        while (result.next()) {
            apartments.add(
                    new Apartment(result.getString(1), result.getDouble(2),
                            result.getInt(3), result.getInt(4)));
        }

        result.close();
        return apartments.toArray(new Apartment[0]);
    }

    public Tenant[] readTenants() throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM TENANT");

        ArrayList<Tenant> tenants = new ArrayList<>();

        while (result.next()) {
            tenants.add(
                    new Tenant(result.getString(1), result.getString(2),
                            result.getString(3), result.getString(4),
                            result.getString(5), result.getDate(6).toLocalDate(), null));
        }

        result.close();
        return tenants.toArray(new Tenant[0]);
    }

    public Tenant readTenant(Tenant tenant) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM TENANT WHERE lastName LIKE '" + tenant.getLastName() + "'");

        Tenant tenantResponse = new Tenant(result.getString(1), result.getString(2),
                result.getString(3), result.getString(4),
                result.getString(5), result.getDate(6).toLocalDate(), null);

        result.close();
        return tenantResponse;
    }
}
