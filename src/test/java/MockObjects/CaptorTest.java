package MockObjects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import tenantsystem.core.Tenant;
import tenantsystem.db.DatabaseService;

import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CaptorTest {
    @Captor
    ArgumentCaptor acTenant;

    @Mock
    DatabaseService dbService;

    @Mock
    Tenant tenant1, tenant2;

    @Test
    public void captorTest(){
        try {
            when(dbService.readTenant(tenant1)).thenReturn(tenant2);
            assertEquals(tenant2, dbService.readTenant(tenant1));
            Mockito.verify(dbService).readTenant((Tenant) acTenant.capture());
            assertTrue(tenant1 == acTenant.getValue());
        } catch (SQLException throwables) {
            fail(throwables.toString());
        }
    }
}
