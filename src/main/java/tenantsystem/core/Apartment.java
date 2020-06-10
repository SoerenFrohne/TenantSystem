package tenantsystem.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import tenantsystem.editor.IMenuItem;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Apartment {
    private String label;
    private double coldRent;
    private int apartmentUnit; // in m^2
    private int energyConsumption; // in kWh
}
