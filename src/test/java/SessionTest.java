import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import tenantsystem.core.utils.Calculator;
import tenantsystem.session.Session;

public class SessionTest {

    private Session sut;
    private Calculator calculator;

    @Before
    public void setUp() throws Exception {
        sut = Session.INSTANCE;
    }

    /**
     * Einfacher Methodenaufruf
     */
    public void testSendingFuelBills() {

        // Konfigurieren der Mocks (Calculator liegt als Interface vor)
        Calculator calculator = Mockito.mock(Calculator.class);
        sut.setCalculator(calculator);

        // Überprüfung, ob die Berechnungsmethode des Calculators aufgerufen wird
        Mockito.verify(sut.getCalculator())
                .calculateFuelBill(sut.getCurrentBuilding());
    }

    /**
     * Methodenaufruf mit expliziten Verhalten
     */
    public void testSendingFuelBillsWithExplicitPrice() {

        // Konfigurieren der Mocks (Calculator liegt als Interface vor)
        Calculator calculator = Mockito.mock(Calculator.class);
        sut.setCalculator(calculator);

        // Wenn die Berechnungsmethode aufgerufen wird, soll diese zunächst 8750€ als Ergebnis zurückgeben.
        // Beim zweiten Aufruf der Methode dann 1750€
        Mockito.when(calculator.calculateFuelBill(sut.getCurrentBuilding()))
                .thenReturn(8750d)
                .thenReturn(1750d);

    }

}
