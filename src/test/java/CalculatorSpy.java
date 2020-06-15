import lombok.Getter;
import lombok.Setter;
import tenantsystem.core.Building;
import tenantsystem.core.utils.Calculator;

public class CalculatorSpy implements Calculator {

    @Getter @Setter private String action;
    @Getter @Setter private int numberOfCalls;

    @Override
    public double calculateFuelBill(Building building) {
        this.action = "calculateFuelBill";
        this.numberOfCalls++;
        return 0;
    }
}
