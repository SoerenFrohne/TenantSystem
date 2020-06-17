package MockObjects;

import javafx.fxml.FXML;
import org.junit.Test;
import tenantsystem.core.Building;
import tenantsystem.core.Tenant;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class DummyTest {
    Building building;
    DummyTenant dummyTenant;


    @FXML
    public void initialize() {
        testDummyBuilding();
    }

    @Test
    public void testDummyBuilding() {
        ArrayList<Tenant> tenantList = new ArrayList();
        tenantList.add(dummyTenant);
        building = new Building();
        building.setTenants(tenantList);
        assertEquals(tenantList, building.getTenants());
    }
}
