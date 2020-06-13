import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tenantsystem.core.Building;
import tenantsystem.core.Tenant;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

public class SimpleTest {

    private final Building building = new Building(
            "Musterstraße 3",
            "53756",
            "Musterhausen",
            "Rheinland-Pfalz",
            "Deutschland");

    @Mock
    private Tenant tenantAnnotationMock;

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

    @Test
    public void stubbedListExaple() {

        // mock the concrete LinkedList class
        LinkedList mockedList = mock(LinkedList.class);

        // stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        System.out.println(mockedList.get(0)); // -> "first"
        System.out.println(mockedList.get(1)); // -> RuntimeException
        System.out.println(mockedList.get(999)); // -> null
    }

    @Test
    public void mockExample() {
        Tenant tenantMock = mock(Tenant.class);
        tenantMock.setBuilding(building);
        verify(tenantMock).setBuilding(building);
    }

    @Test
    public void annotationMockExample() {
        MockitoAnnotations.initMocks(this);
        tenantAnnotationMock.setBuilding(building);
        verify(tenantAnnotationMock).setBuilding(building);
    }
}
