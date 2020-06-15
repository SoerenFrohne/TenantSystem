import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import tenantsystem.core.Tenant;

public class TenantMatcher extends BaseMatcher<Tenant>   {
    Tenant expectedTenant;

    TenantMatcher(Tenant expectedTenant){
        this.expectedTenant = expectedTenant;
    }

    @Override
    public boolean matches(Object item) {
        return  expectedTenant.getAddress().equals(((Tenant)item).getAddress()) &&
                expectedTenant.getIban().equals(((Tenant)item).getIban()) &&
                expectedTenant.getPhoneNumber().equals(((Tenant)item).getPhoneNumber());
    }

    @Override
    public void describeMismatch(Object item, Description mismatchDescription) {

        mismatchDescription.appendText("\nAddress:      " + ((Tenant)item).getAddress() + "\n"+
                                        "Iban:          " + ((Tenant)item).getIban() + "\n"+
                                        "PhonenNumber:  " + ((Tenant)item).getPhoneNumber());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("\nAddress:      " + expectedTenant.getAddress() + "\n" +
                                "Iban:          " + expectedTenant.getIban()+ "\n" +
                                "PhoneNumber:   " + expectedTenant.getPhoneNumber());
    }
}
