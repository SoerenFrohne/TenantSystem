package tenantsystem.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import tenantsystem.editor.IMenuItem;

@Data
@AllArgsConstructor
public class Apartment {
    private double coldRent;
    private int apartmentUnit; // in m^2
    private int energyConsumption; // in kWh
}
