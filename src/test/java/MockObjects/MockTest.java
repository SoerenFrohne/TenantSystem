package MockObjects;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tenantsystem.core.Building;
import tenantsystem.core.Tenant;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class MockTest {
    private final Building building = new Building(
            "Musterstraße 3",
            "53756",
            "Musterhausen",
            "Rheinland-Pfalz",
            "Deutschland");

    @Mock
    private Tenant tenantAnnotationMock;

    @Test
    public void mockExample() {
        Tenant tenantMock = mock(Tenant.class);
        tenantMock.setBuilding(building);
        verify(tenantMock).setBuilding(building);
    }

    @Test
    public void annotationMockExample() {
        MockitoAnnotations.initMocks(this);
        //mocks can also be initialized with '@RunWith(MockitoJUnitRunner.class)' or '@Before'

        tenantAnnotationMock.setBuilding(building);
        verify(tenantAnnotationMock).setBuilding(building);
    }

    @Test
    public void mockedListExample() {

        // mock the list interface
        List mockedList = mock(List.class);

        // using mock object
        mockedList.add("one");
        mockedList.clear();

        // verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }
}
