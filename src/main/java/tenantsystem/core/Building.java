package tenantsystem.core;

import lombok.*;
import tenantsystem.editor.IMenuItem;
import tenantsystem.session.Session;

import java.util.ArrayList;
import java.util.HashMap;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Building implements IMenuItem {
    @NonNull private String streetAddress;
    @NonNull private String postalCode;
    @NonNull private String city;
    @NonNull private String region;
    @NonNull private String country;

    private ArrayList<Tenant> tenants = new ArrayList<>();
    private ArrayList<Apartment> apartments = new ArrayList<>();
    private HashMap<Tenant, Apartment> apartmentTenantMap = new HashMap<>();

    public Apartment getApartment(Tenant t) {
        return apartmentTenantMap.get(t);
    }

    public void moveIn(Apartment a, Tenant t) {
        apartmentTenantMap.put(t, a);
    }


    @Override
    public String toString() {
        return streetAddress;
    }

    @Override
    public String getFxmlPath() {
        Session.INSTANCE.setCurrentBuilding(this);
        return "/gui/building.fxml";
    }
}
