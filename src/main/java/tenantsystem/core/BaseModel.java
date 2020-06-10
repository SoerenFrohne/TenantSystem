package tenantsystem.core;

import tenantsystem.db.DbAction;
import tenantsystem.db.DbMock;

import java.sql.SQLException;
import java.sql.Statement;

public class BaseModel {

    private DbAction dbAction = new DbAction();
    private Statement db;


    public BaseModel(){
        try {
            db = new DbMock().dbErstellen();
            dbAction.setStatement(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Tenant[] getTenats() throws SQLException {
       return dbAction.readTenats();
    }
}
