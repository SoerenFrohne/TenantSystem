package tenantsystem.db;

import org.mockito.Mockito;
import tenantsystem.core.Tenant;

import java.sql.ResultSet;
import java.sql.Statement;

public class DbMock {
    public Statement dbErstellen() throws Exception {

        //final Mockery context = new Mockery();
        final Statement st = Mockito.mock(Statement.class);
        final Tenant[] sampleTenants = {
                new Tenant("Andreas", "Duschanek", "Musterstraße 4", "012345 6780", "DE92 4552 45550 32"),
                new Tenant("Christian", "Mahr", "Musterstraße 4", "012345 6780", "DE92 4552 45550 32"),
                new Tenant("Sören", "Frohne", "Musterstraße 4", "012345 6780", "DE92 4552 45550 32"),
                new Tenant("Jonas", "Schnettker", "Musterstraße 4", "012345 6780", "DE92 4552 45550 32")};

        ResultSet r1 = Mockito.mock(ResultSet.class);
        Mockito.when(r1.next()).thenReturn(true, true, true, true, false);
        Mockito.when(r1.getString(1)).thenReturn(sampleTenants[0].getFirstName(), sampleTenants[1].getFirstName(),sampleTenants[2].getFirstName(),sampleTenants[3].getFirstName());
        Mockito.when(r1.getString(2)).thenReturn(sampleTenants[0].getLastName(),sampleTenants[1].getLastName(), sampleTenants[2].getLastName(), sampleTenants[3].getLastName());
        Mockito.when(r1.getString(3)).thenReturn(sampleTenants[0].getAddress(), sampleTenants[1].getAddress(), sampleTenants[2].getAddress(), sampleTenants[3].getAddress());
        Mockito.when(r1.getString(4)).thenReturn(sampleTenants[0].getPhoneNumber(),sampleTenants[1].getPhoneNumber(),sampleTenants[2].getPhoneNumber(),sampleTenants[3].getPhoneNumber());
        Mockito.when(r1.getString(5)).thenReturn(sampleTenants[0].getIban(), sampleTenants[1].getIban(), sampleTenants[2].getIban(),sampleTenants[3].getIban());

        Mockito.when(st.executeQuery(
                "SELECT * FROM TENANT"))
                .thenReturn(r1);
        return st;
    }
}
