package tenantsystem.core;

public class HandleTenant {

    private CheckTenant checkTenant;


    public void setCheckTenant(CheckTenant checkTenant){
        this.checkTenant = checkTenant;
    }

    public void setIban (Tenant tenant, String iban){
        if (checkTenant.validateIban(iban)){
            tenant.setIban(iban);
        }
    }

    public void setAddress (Tenant tenant, String address) {
        if (checkTenant.validateAddress(address)) {
            tenant.setAddress(address);
        }
    }
}
