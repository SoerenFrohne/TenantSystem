package MockObjects;

import org.junit.Before;
import org.junit.Test;
import tenantsystem.core.Apartment;
import tenantsystem.db.DatabaseMock;
import tenantsystem.db.DatabaseService;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class FakeTest {
    private Statement fakeStatement = new FakeStatement();
    private final DatabaseService databaseService = new DatabaseService();
    private Apartment[] apartments;

    @Test
    public void testDataQuery(){
        try {
            databaseService.setStatement(fakeStatement);
            assertNull(databaseService.readApartments());
        } catch (SQLException throwables) {
            fail(throwables.toString());
        }
    }
}
