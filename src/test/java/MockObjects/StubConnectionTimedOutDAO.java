package MockObjects;

import tenantsystem.core.Apartment;
import tenantsystem.core.Tenant;
import tenantsystem.db.DatabaseService;

import java.sql.SQLException;

public class StubConnectionTimedOutDAO extends DatabaseService {

    public Tenant readTenant(Tenant tenant) throws SQLException {
        throw new SQLException("DB connection timed out");
    }
    //all other functions remain unchanged
}
