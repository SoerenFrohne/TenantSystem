package MockObjects;

import javafx.fxml.FXML;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import tenantsystem.core.Building;
import tenantsystem.core.Tenant;
import tenantsystem.editor.IMenuItem;
import tenantsystem.session.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
