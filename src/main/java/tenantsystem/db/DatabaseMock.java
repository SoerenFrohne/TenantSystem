package tenantsystem.db;

import org.mockito.Mockito;
import tenantsystem.core.Tenant;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

public class DatabaseMock {

    public Statement createDatabase() throws Exception {
        //final Mockery context = new Mockery();
        final Statement statement = Mockito.mock(Statement.class);
        final Tenant[] sampleTenants = {
                new Tenant("Andreas", "Duschanek", "Musterstraße 4", "012345 6780", "DE92 4552 45550 32", LocalDate.of(1990, 12, 31)),
                new Tenant("Christian", "Mahr", "Musterstraße 4", "012345 6780", "DE92 4552 45550 32", LocalDate.of(1990, 12, 31)),
                new Tenant("Sören", "Frohne", "Musterstraße 4", "012345 6780", "DE92 4552 45550 32", LocalDate.of(1990, 12, 31)),
                new Tenant("Jonas", "Schnettker", "Musterstraße 4", "012345 6780", "DE92 4552 45550 32", LocalDate.of(1990, 12, 31))
        };

        ResultSet r = Mockito.mock(ResultSet.class);
        Mockito.when(r.next()).thenReturn(true, true, true, true, false);
        Mockito.when(r.getString(1)).thenReturn(sampleTenants[0].getFirstName(), sampleTenants[1].getFirstName(), sampleTenants[2].getFirstName(), sampleTenants[3].getFirstName());
        Mockito.when(r.getString(2)).thenReturn(sampleTenants[0].getLastName(), sampleTenants[1].getLastName(), sampleTenants[2].getLastName(), sampleTenants[3].getLastName());
        Mockito.when(r.getString(3)).thenReturn(sampleTenants[0].getAddress(), sampleTenants[1].getAddress(), sampleTenants[2].getAddress(), sampleTenants[3].getAddress());
        Mockito.when(r.getString(4)).thenReturn(sampleTenants[0].getPhoneNumber(), sampleTenants[1].getPhoneNumber(), sampleTenants[2].getPhoneNumber(), sampleTenants[3].getPhoneNumber());
        Mockito.when(r.getString(5)).thenReturn(sampleTenants[0].getIban(), sampleTenants[1].getIban(), sampleTenants[2].getIban(), sampleTenants[3].getIban());
        Mockito.when(r.getDate(6)).thenReturn(Date.valueOf(sampleTenants[0].getBirthday()), Date.valueOf(sampleTenants[1].getBirthday()), Date.valueOf(sampleTenants[2].getBirthday()), Date.valueOf(sampleTenants[3].getBirthday()));

        Mockito.when(statement.executeQuery("SELECT * FROM TENANT"))
                .thenReturn(r);

        return statement;
    }
}
