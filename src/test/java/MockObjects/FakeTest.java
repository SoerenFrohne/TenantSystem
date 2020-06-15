package MockObjects;

import org.junit.Before;
import tenantsystem.db.DatabaseMock;
import tenantsystem.db.DatabaseService;

import java.sql.Statement;

public class FakeTest {
    private Statement db;
    private final DatabaseService databaseService = new DatabaseService();

    @Before
    public void setUp() throws Exception {
        db = new DatabaseMock().createDatabase();
        databaseService.setStatement(db);
    }
}
