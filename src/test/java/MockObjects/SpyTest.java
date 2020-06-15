package MockObjects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import tenantsystem.core.Tenant;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SpyTest {
    @Spy
    List<Tenant> spyList = new ArrayList<Tenant>();
    //Use Mockito.spy() to initialize without annotation.

    @Test
    public void tenantSpyTest() {
        Tenant t1 = new Tenant("Andreas", "Duschanek", "Musterstraße 4", "012345 6780", "DE92 4552 45550 32", LocalDate.of(1990, 12, 31), null);
        Tenant t2 = new Tenant("Jonas", "Schnettker", "Musterstraße 4", "012345 6780", "DE92 4552 45550 32", LocalDate.of(1990, 12, 31), null);

        spyList.add(t1);
        spyList.add(t2);

        Mockito.verify(spyList).add(t1);
        Mockito.verify(spyList).add(t2);

        assertEquals(2, spyList.size());

        Mockito.doReturn(100).when(spyList).size();
        assertEquals(100, spyList.size());
    }
}
