package MockObjects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import tenantsystem.core.Tenant;
import tenantsystem.db.DatabaseMock;
import tenantsystem.db.DatabaseService;
import tenantsystem.editor.MainFrameControl;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StubTest {
    private final StubConnectionTimedOutDAO databaseService = new StubConnectionTimedOutDAO();
    private Tenant tenant = new Tenant("Andreas", "Duschanek", "Musterstraße 4", "012345 6780", "DE92 4552 45550 32", LocalDate.of(1990, 12, 31), null);

    @Test
    public void connectionTimeOut(){
        try {
            Mockito.when(databaseService.readTenant(tenant)).thenThrow(SQLException.class);
            fail("DB connection did not time out");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Test
    public void stubbedListExaple() {

        // mock the concrete LinkedList class
        LinkedList mockedList = mock(LinkedList.class);

        // stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        System.out.println(mockedList.get(0)); // -> "first"
        //System.out.println(mockedList.get(1)); // -> RuntimeException
        System.out.println(mockedList.get(999)); // -> null
    }
}
