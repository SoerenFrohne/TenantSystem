package tenantsystem.db;

import tenantsystem.core.Tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DbAction {

    private Statement statement;

    public void setStatement(Statement statement){
        this.statement = statement;
    }


    public Tenant[] readTenats() throws SQLException {
        ResultSet ergebnis;
        ergebnis = this.statement.executeQuery("SELECT * FROM TENANT");

        ArrayList<Tenant> tenants = new ArrayList<Tenant>();

        while(ergebnis.next()) {
            tenants.add(
                    new Tenant(ergebnis.getString(1), ergebnis.getString(2),
                               ergebnis.getString(3), ergebnis.getString(4),
                               ergebnis.getString(5)));
        }

        ergebnis.close();
        return tenants.toArray(new Tenant[0]);
    }
}
