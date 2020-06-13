package tenantsystem.db;

import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import tenantsystem.core.Apartment;
import tenantsystem.core.Tenant;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class DatabaseMock {

    public Statement createDatabase() throws Exception {
        //final Mockery context = new Mockery();
        final Statement statement = Mockito.mock(Statement.class);
        final Tenant[] sampleTenants = {
                new Tenant("Andreas", "Duschanek", "Musterstraße 4", "012345 6780", "DE92 4552 45550 32", LocalDate.of(1990, 12, 31), null),
                new Tenant("Christian", "Mahr", "Musterstraße 4", "012345 6780", "DE92 4552 45550 32", LocalDate.of(1990, 12, 31), null),
                new Tenant("Sören", "Frohne", "Musterstraße 4", "012345 6780", "DE92 4552 45550 32", LocalDate.of(1990, 12, 31), null),
                new Tenant("Jonas", "Schnettker", "Musterstraße 4", "012345 6780", "DE92 4552 45550 32", LocalDate.of(1990, 12, 31), null)
        };

        //behaviour of tenant return
        ResultSet r = Mockito.mock(ResultSet.class);
        Mockito.when(r.next()).thenReturn(true, true, true, true, false);
        Mockito.when(r.getString(1)).thenReturn(sampleTenants[0].getFirstName(), sampleTenants[1].getFirstName(), sampleTenants[2].getFirstName(), sampleTenants[3].getFirstName());
        Mockito.when(r.getString(2)).thenReturn(sampleTenants[0].getLastName(), sampleTenants[1].getLastName(), sampleTenants[2].getLastName(), sampleTenants[3].getLastName());
        Mockito.when(r.getString(3)).thenReturn(sampleTenants[0].getAddress(), sampleTenants[1].getAddress(), sampleTenants[2].getAddress(), sampleTenants[3].getAddress());
        Mockito.when(r.getString(4)).thenReturn(sampleTenants[0].getPhoneNumber(), sampleTenants[1].getPhoneNumber(), sampleTenants[2].getPhoneNumber(), sampleTenants[3].getPhoneNumber());
        Mockito.when(r.getString(5)).thenReturn(sampleTenants[0].getIban(), sampleTenants[1].getIban(), sampleTenants[2].getIban(), sampleTenants[3].getIban());
        Mockito.when(r.getDate(6)).thenReturn(Date.valueOf(sampleTenants[0].getBirthday()), Date.valueOf(sampleTenants[1].getBirthday()), Date.valueOf(sampleTenants[2].getBirthday()), Date.valueOf(sampleTenants[3].getBirthday()));

        final Apartment[] sampleApartments = {
                new Apartment("Keller" , 460, 56, 17350),
                new Apartment("EG" , 580, 64, 18750),
                new Apartment("1. OG" , 600, 68, 17645),
                new Apartment("2. OG" , 600, 68, 16050),
        };

        //behaviour of apartment return
        ResultSet a = Mockito.mock(ResultSet.class);
        Mockito.when(a.next()).thenReturn(true, true, true, true, false);
        Mockito.when(a.getString(1)).thenReturn(sampleApartments[0].getLabel(), sampleApartments[1].getLabel(), sampleApartments[2].getLabel(), sampleApartments[3].getLabel());
        Mockito.when(a.getDouble(2)).thenReturn(sampleApartments[0].getColdRent(), sampleApartments[1].getColdRent(), sampleApartments[2].getColdRent(), sampleApartments[3].getColdRent());
        Mockito.when(a.getInt(3)).thenReturn(sampleApartments[0].getApartmentUnit(), sampleApartments[1].getApartmentUnit(), sampleApartments[2].getApartmentUnit(), sampleApartments[3].getApartmentUnit());
        Mockito.when(a.getInt(4)).thenReturn(sampleApartments[0].getEnergyConsumption(), sampleApartments[1].getEnergyConsumption(), sampleApartments[2].getEnergyConsumption(), sampleApartments[3].getEnergyConsumption());


        //return SQLException on unexpected database access
        when(statement.executeQuery(anyString())).thenAnswer((Answer) invocation -> {
            String sql = invocation.getArgument(0);
            if ("SELECT * FROM TENANT" == sql.toString()){
                return r;
            }

            if ("SELECT * FROM APARTMENTS" == sql.toString()){
                return a;
            }
            throw new SQLException();
        });

        return statement;
    }
}
