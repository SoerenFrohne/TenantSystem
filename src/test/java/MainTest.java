import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tenantsystem.core.HandleTenant;
import tenantsystem.core.Tenant;
import tenantsystem.core.CheckTenant;

public class MainTest {

    private HandleTenant handleTenant;
    final CheckTenant val = Mockito.mock(CheckTenant.class);

    private String newAddress =  "neues Zuhause";
    private String validIban = "DE 123";
    private String invalidIban = "12 123";

    @Before
    public void setUp() {
        handleTenant = new HandleTenant();
        handleTenant.setCheckTenant(val);
    }

    @Test
    public void behaviorIban(){
        final Tenant p = Mockito.mock(Tenant.class);

        Mockito.when(val.validateIban(validIban)).thenReturn(true);
        Mockito.when(val.validateIban(invalidIban)).thenReturn(false);

        handleTenant.setIban(p,validIban);
        handleTenant.setIban(p,invalidIban);

        Mockito.verify(val).validateIban(validIban);
        Mockito.verify(val).validateIban(invalidIban);
        Mockito.verify(p).setIban(validIban);
    }

    @Test
    public void behaviorAddress(){
        final Tenant p = Mockito.mock(Tenant.class);

        Mockito.when(val.validateAddress(newAddress)).thenReturn(false,true);

        handleTenant.setAddress(p, newAddress);
        handleTenant.setAddress(p, newAddress);

        Mockito.verify(val, Mockito.times(2)).validateAddress(newAddress);

        Mockito.verify(p, Mockito.times(1)).setAddress(newAddress);
    }

    @Test
    public void state(){
        Tenant p = new Tenant("","","","","");

        Mockito.when(val.validateAddress(newAddress)).thenReturn(false,true);

        handleTenant.setAddress(p, newAddress);
        Assert.assertFalse(newAddress == p.getAddress());

        handleTenant.setAddress(p, newAddress);
        Assert.assertTrue(newAddress == p.getAddress());
    }

}
