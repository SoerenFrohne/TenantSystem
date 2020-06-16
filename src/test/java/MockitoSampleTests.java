import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tenantsystem.core.Building;
import tenantsystem.core.Tenant;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockitoSampleTests {

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
    public void stubbedListExample() {

        // Mock the concrete LinkedList class
        LinkedList mockedList = mock(LinkedList.class);

        // stubbing
        when(mockedList.contains("first")).thenReturn(true);
        assert mockedList.contains("first");
        verify(mockedList).contains("first");

        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.remove()).thenThrow(new NoSuchElementException());

        System.out.println(mockedList.get(0));

        try {
            mockedList.remove();
        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException catched!");
        }

        System.out.println(mockedList.get(999));
    }

    @Test
    public void mockExample() {
        // Create mock
        Tenant tenantMock = mock(Tenant.class);

        // Perform action
        tenantMock.setBuilding(building);

        // Verify action
        verify(tenantMock).setBuilding(building);

        // This will fail. Remove comment to demonstrate
        assert tenantMock.getBuilding() == building;
    }

    @Test
    public void annotationMockExample() {
        // Calling the real method does not do anything
        tenantAnnotationMock.setBuilding(building);
        System.out.println(tenantAnnotationMock.getBuilding() == building);

        // Define behavior instead of calling the real method
        when(tenantAnnotationMock.getBuilding()).thenReturn(building);
        System.out.println(tenantAnnotationMock.getBuilding() == building);

        // Some assert statement to make it look like a real test
        assert tenantAnnotationMock.getBuilding().equals(building);
    }
}
