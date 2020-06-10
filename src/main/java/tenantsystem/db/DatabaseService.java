package tenantsystem.db;

import lombok.Getter;
import lombok.Setter;
import tenantsystem.core.Apartment;
import tenantsystem.core.Tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseService {

    @Setter @Getter private Statement statement;

    public Apartment[] readApartments() throws SQLException {
        // ResultSet result = statement.executeQuery("SELECT * FROM APARTMENTS");
        //Todo
        return new Apartment[]{
                new Apartment("Keller" , 460, 56, 17350),
                new Apartment("EG" , 580, 64, 18750),
                new Apartment("1. OG" , 600, 68, 17645),
                new Apartment("2. OG" , 600, 68, 16050),
        };
    }

    public Tenant[] readTenants() throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM TENANT");

        ArrayList<Tenant> tenants = new ArrayList<>();

        while (result.next()) {
            tenants.add(
                    new Tenant(result.getString(1), result.getString(2),
                            result.getString(3), result.getString(4),
                            result.getString(5), result.getDate(6).toLocalDate()));
        }

        result.close();
        return tenants.toArray(new Tenant[0]);
    }
}
