import org.junit.Assert;
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
import static org.mockito.ArgumentMatchers.*;

public class MainTest {

    private final DatabaseService databaseService = new DatabaseService();

    @Test
    public void state() throws Exception {
        Tenant actualTenat = new Tenant("", "","","","", LocalDate.of(1990, 12, 31));

        final Utils utils = Mockito.mock(Utils.class);
        Session session = Session.INSTANCE;
        session.setUtils(utils);
        session.setCurrentTenant(actualTenat);

        Mockito.when(utils.validateAddress("abc")).thenReturn(true);
        try {
            session.updateAddress("abc");
        }
        catch (Exception e) {
            fail("not expected");
        }
        Assert.assertEquals("abc", session.getCurrentTenant().getAddress());


        Mockito.when(utils.validateAddress(anyString())).thenReturn(false);
        try {
              session.updateAddress("XXX");
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
        session.setCurrentTenant(actualTenant);


        Mockito.when(utils.validateAddress(valideAddress)).thenReturn(true);
        Mockito.when(utils.validateAddress(invalideAddress)).thenReturn(false);
        Mockito.when(utils.validateIban(valideIban)).thenReturn(true);
        Mockito.when(utils.validateIban(invalidIban)).thenReturn(true);
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
            session.updateAddress(valideAddress);
        }catch (Exception e){
        }
        //Adress Attribute überprüfen.
        Assert.assertEquals("check new Address",valideAddress, session.getCurrentTenant().getAddress());


        //Versuch eine invalide Adresse zu setzen!
        try {
            session.updateAddress(invalideAddress);
        }catch (Exception e){
        }
        //Adresse darf sich verändert haben.
        Assert.assertEquals("check invalid Address", valideAddress, session.getCurrentTenant().getAddress());


        /*
         *Iban / Telefonnummer updaten.
         * Funktion liefert keine Expception sondern ein boolean als Rückgabewert.
         */
        //valide Eingabe
        session.updatePhoneNumber(validPhoneNumber);
        Assert.assertEquals("check new phone number",validPhoneNumber, session.getCurrentTenant().getPhoneNumber());

        //invalide Eingabe
        session.updatePhoneNumber(invalidPhoneNumber);
        Assert.assertEquals("check invalid phone number",validPhoneNumber, session.getCurrentTenant().getPhoneNumber());

        //valide Eingabe
        session.updateIban(valideIban);
        Assert.assertEquals("check new Iban", valideIban, session.getCurrentTenant().getIban());

        //invalide Eingabe
        session.updateIban(invalidIban);
        Assert.assertEquals("ckeck invalide Iban", valideIban, session.getCurrentTenant().getIban());
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
        session.setCurrentTenant(actualTenant);


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
            session.updateAddress(expectedTenant.getAddress());
            session.updateIban(expectedTenant.getIban());
            session.updatePhoneNumber(expectedTenant.getPhoneNumber());
        }catch (Exception e){
        }

        //Test mit invaliden argumenten
        try {
            session.updateAddress(invalideAddress);
        }catch (Exception e){
        }

        session.updatePhoneNumber(invalidPhoneNumber);
        session.updateIban(invalidIban);


        /* TODO
         * Verweis auf eigenen Matcher.
         * Besonders die Ausgaben sind von Interesse.
         */
        Assert.assertEquals("End of Test. Check state", expectedTenant, session.getCurrentTenant());

        Assert.assertThat(session.getCurrentTenant(), new TenantMatcher(expectedTenant));
    }


    @Test
    public void dbAction() throws SQLException {

        Tenant[] tenants = databaseService.readTenants();

        Assert.assertTrue(4 == tenants.length);
    }
}
