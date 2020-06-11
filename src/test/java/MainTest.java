import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tenantsystem.core.Tenant;
import tenantsystem.core.utils.Utils;
import tenantsystem.db.DatabaseService;
import tenantsystem.session.Session;


import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;

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
    @Test
    public void state() throws Exception {
        Tenant actualTenat = new Tenant("", "","","","", LocalDate.of(1990, 12, 31));

        final Utils utils = Mockito.mock(Utils.class);
        Session session = Session.INSTANCE;
        session.setUtils(utils);

        Mockito.when(utils.validateAddress("abc")).thenReturn(true);
        try {
            session.updateAddress(actualTenat, "abc");
        }
        catch (Exception e) {
            fail("not expected");
        }
        Assert.assertEquals("abc", actualTenat.getAddress());


        Mockito.when(utils.validateAddress(anyString())).thenReturn(false);
        try {
              session.updateAddress(actualTenat, "XXX");
              fail("No Exeption thrown on UpdateAddress");              //only reached when Exception is not thrown
        }
        catch (Exception e) {
            Assert.assertTrue("Not Updated" == e.getMessage()); //check the Exception
        }
        Assert.assertEquals("abc", actualTenat.getAddress());   //Check state of tenant, should not changed!!!
    }

    @Test
    public void dbAction() throws SQLException {

        Tenant[] tenants = databaseService.readTenants();

        Assert.assertTrue(4 == tenants.length);
    }
}
