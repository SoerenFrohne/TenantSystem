package tenantsystem.session;

import lombok.Getter;
import lombok.Setter;
import tenantsystem.core.Apartment;
import tenantsystem.core.Building;
import tenantsystem.core.Tenant;
import tenantsystem.core.utils.Calculator;
import tenantsystem.core.utils.PostService;
import tenantsystem.db.DatabaseService;
import tenantsystem.db.DatabaseMock;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Dieses Singleton Objekt dient als Modell.
 */
public enum Session {
    INSTANCE;

    @Getter private final DatabaseService databaseService = new DatabaseService();

    @Getter @Setter private Calculator calculator;
    @Getter @Setter private PostService postService;

    @Getter @Setter private Building currentBuilding;
    @Getter @Setter private Tenant currentTenant;

    Session() {
        try {
            Statement db = new DatabaseMock().createDatabase();
            databaseService.setStatement(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Apartment getCurrentApartment() {
        return currentBuilding.getApartment(currentTenant);
    }

    public Tenant[] getTenants() throws SQLException {
        return databaseService.readTenants();
    }

    public Apartment[] getApartments() throws SQLException {
        return databaseService.readApartments();
    }

    /**
     * Mit dieser Methode sollen die Heizkosten berechnet und folglich alle
     * Mieter mit ihrer Heizkostenabrechnung benachrichtigt werden.
     * Der Status der Session wird dabei nicht verändert.
     */
    public void sendFuelBills() {
        // Berechne Heizkosten
        double heatingCosts = calculator.calculateFuelBill(currentBuilding);

        // Sende Rechnung/E-Mail an alle Mieter
        postService.sendBills(currentBuilding.getTenants().toArray(new Tenant[0]));
    }
}
