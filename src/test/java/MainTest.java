import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tenantsystem.core.Tenant;
import tenantsystem.db.DatabaseService;
import tenantsystem.db.DatabaseMock;

import java.sql.SQLException;
import java.sql.Statement;

public class MainTest {

    private final String newAddress =  "neues Zuhause";
    private final String validIban = "DE 123";
    private final String invalidIban = "12 123";

    private Statement db;
    private final DatabaseService databaseService = new DatabaseService();

//    @Before
//    public void setUp() throws Exception {
//        handleTenant = new HandleTenant();
//        handleTenant.setCheckTenant(val);
//
//        db = new DatabaseMock().createDatabase();
//        databaseService.setStatement(db);
//    }
//
//    @Test
//    public void behaviorIban(){
//        final Tenant p = Mockito.mock(Tenant.class);
//
//        Mockito.when(val.validateIban(validIban)).thenReturn(true);
//        Mockito.when(val.validateIban(invalidIban)).thenReturn(false);
//
//        handleTenant.setIban(p,validIban);
//        handleTenant.setIban(p,invalidIban);
//
//        Mockito.verify(val).validateIban(validIban);
//        Mockito.verify(val).validateIban(invalidIban);
//        Mockito.verify(p).setIban(validIban);
//    }
//
//    @Test
//    public void behaviorAddress(){
//        final Tenant p = Mockito.mock(Tenant.class);
//
//        Mockito.when(val.validateAddress(newAddress)).thenReturn(false,true);
//
//        handleTenant.setAddress(p, newAddress);
//        handleTenant.setAddress(p, newAddress);
//
//        Mockito.verify(val, Mockito.times(2)).validateAddress(newAddress);
//
//        Mockito.verify(p, Mockito.times(1)).setAddress(newAddress);
//    }
//
//    @Test
//    public void state(){
//        Tenant p = new Tenant("","","","","");
//
//        Mockito.when(val.validateAddress(newAddress)).thenReturn(false,true);
//
//        handleTenant.setAddress(p, newAddress);
//        Assert.assertFalse(newAddress == p.getAddress());
//
//        handleTenant.setAddress(p, newAddress);
//        Assert.assertTrue(newAddress == p.getAddress());
//    }

    @Test
    public void dbAction() throws SQLException {

        Tenant[] tenants = databaseService.readTenants();

        Assert.assertTrue(4 == tenants.length);
    }

}
