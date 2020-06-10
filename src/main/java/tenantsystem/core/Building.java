package tenantsystem.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tenantsystem.editor.IMenuItem;
import tenantsystem.session.Session;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Building implements IMenuItem {
    private String streetAddress;
    private String postalCode;
    private String city;
    private String region;
    private String country;

    private ArrayList<Tenant> tenants;

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
