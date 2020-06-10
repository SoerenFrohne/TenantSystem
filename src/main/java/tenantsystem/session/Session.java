package tenantsystem.session;

import lombok.Getter;
import lombok.Setter;
import tenantsystem.core.Building;
import tenantsystem.core.Tenant;

/**
 * Singleton-object as model
 */
public enum Session {
    INSTANCE;

    @Getter
    @Setter
    private Building currentBuilding;
    @Getter
    @Setter
    private Tenant currentTenant;
}
