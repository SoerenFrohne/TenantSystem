import jdk.jshell.execution.Util;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.matchers.ThrowableMessageMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Matches;
import tenantsystem.core.Tenant;
import tenantsystem.core.utils.Utils;
import tenantsystem.db.DatabaseService;
import tenantsystem.session.Session;


import java.rmi.MarshalledObject;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.*;

public class MainTest {

    private final String newAddress =  "neues Zuhause";
   //private final String validIban = "DE 123";
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
    public void proceduralStateTest() {
        Tenant actualTenant = new Tenant("", "","","","", LocalDate.of(1990, 12, 12));
        final String valideAddress = "valideAddress";
        final String invalideAddress = "invalidAddress";
        final String valideIban = "validIban";
        final String invalidIban = "invalideIban";
        final String validPhoneNumber = "validePhoneNumber";
        final String invalidPhoneNumber = "invalidePhoneNumber";


        Utils utils = Mockito.mock(Utils.class);
        Session session = Session.INSTANCE;
        session.setUtils(utils);


        Mockito.when(utils.validateAddress(valideAddress)).thenReturn(true);
        Mockito.when(utils.validateAddress(invalideAddress)).thenReturn(false);
        Mockito.when(utils.validateIban(valideIban)).thenReturn(true);
        Mockito.when(utils.validateIban(invalidIban)).thenReturn(false);
        Mockito.when(utils.validatePhoneNumber(validPhoneNumber)).thenReturn(true);
        Mockito.when(utils.validatePhoneNumber(invalidPhoneNumber)).thenReturn(false);

        /*
         * Hier betrachten wir wirklich nur den Zustand des Objektes.
         * Fehlermeldungen werden nicht näher betrachtet bzw. komplett ignoriert in diesem Beispiel.
         *
         * Es wird nach jedem Schritt eine Überprüfung des Objektes vorgenommen.
        */

        //Schreiben einer validen Adresse
        try {
            session.updateAddress(actualTenant, valideAddress);
        }catch (Exception e){
        }
        //Adress Attribute überprüfen.
        Assert.assertEquals("check new Address",valideAddress, actualTenant.getAddress());


        //Versuch eine invalide Adresse zu setzen!
        try {
            session.updateAddress(actualTenant, invalideAddress);
        }catch (Exception e){
        }
        //Adresse darf sich verändert haben.
        Assert.assertEquals("check invalid Address", valideAddress, actualTenant.getAddress());


        /*
         *Iban / Telefonnummer updaten.
         * Funktion liefert keine Expception sondern ein boolean als Rückgabewert.
         */
        //valide Eingabe
        session.updatePhoneNumber(actualTenant, validPhoneNumber);
        Assert.assertEquals("check new phone number",validPhoneNumber, actualTenant.getPhoneNumber());

        //invalide Eingabe
        session.updatePhoneNumber(actualTenant, invalidPhoneNumber);
        Assert.assertEquals("check invalid phone number",validPhoneNumber, actualTenant.getPhoneNumber());

        //valide Eingabe
        session.updateIban(actualTenant, valideIban);
        Assert.assertEquals("check new Iban", valideIban, actualTenant.getIban());

        //invalide Eingabe
        session.updateIban(actualTenant, invalidIban);
        Assert.assertEquals("ckeck invalide Iban", valideIban, actualTenant.getIban());
    }

    @Test
    public void expectedObjectStateTesting(){
        final Tenant expectedTenant = new Tenant("", "", "valideAddress", "validePhoneNumber",
                                                    "valideIban", LocalDate.of(1990,12, 12));

        Tenant actualTenant = new Tenant("","","","","",LocalDate.of(1990, 12, 12));

        final String invalideAddress = "invalidAddress";
        final String invalidIban = "invalideIban";
        final String invalidPhoneNumber = "invalidePhoneNumber";


        Utils utils = Mockito.mock(Utils.class);
        Session session = Session.INSTANCE;
        session.setUtils(utils);


        Mockito.when(utils.validateAddress(expectedTenant.getAddress())).thenReturn(true);
        Mockito.when(utils.validateAddress(invalideAddress)).thenReturn(true);
        Mockito.when(utils.validateIban(expectedTenant.getIban())).thenReturn(true);
        Mockito.when(utils.validateIban(invalidIban)).thenReturn(false);
        Mockito.when(utils.validatePhoneNumber(expectedTenant.getPhoneNumber())).thenReturn(true);
        Mockito.when(utils.validatePhoneNumber(invalidPhoneNumber)).thenReturn(false);

        /*
         * Erst am Ende des Tests wird überprüft ob das zu testende Objekt den erwarteten Zustand erreicht hat.
         */

        try {
            session.updateAddress(actualTenant, expectedTenant.getAddress());
            session.updateIban(actualTenant, expectedTenant.getIban());
            session.updatePhoneNumber(actualTenant, expectedTenant.getPhoneNumber());
        }catch (Exception e){
        }

        //Test mit invaliden argumenten
        try {
            session.updateAddress(actualTenant, invalideAddress);
        }catch (Exception e){
        }

        session.updatePhoneNumber(actualTenant, invalidPhoneNumber);
        session.updateIban(actualTenant, invalidIban);


        /* TODO
         * Verweis auf eigenen Matcher.
         * Besonders die Ausgaben sind von Interesse.
         */
        Assert.assertEquals("End of Test. Check state", expectedTenant, actualTenant);

        Assert.assertThat(actualTenant, new TenantMatcher(expectedTenant));
    }


    @Test
    public void dbAction() throws SQLException {

        Tenant[] tenants = databaseService.readTenants();

        Assert.assertTrue(4 == tenants.length);
    }
}
