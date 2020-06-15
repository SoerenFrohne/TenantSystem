import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import tenantsystem.core.Building;
import tenantsystem.core.utils.Calculator;
import tenantsystem.core.utils.PostService;
import tenantsystem.session.Session;

public class SessionTest {

    private Session sut;
    private Calculator calculator;
    private final Building testBuilding = new Building("Musterstraße 1", "55675", "Mustercity", "Auenland", "Mittelerde");

    @Before
    public void setUp() {
        sut = Session.INSTANCE;
        sut.setCurrentBuilding(testBuilding);
    }

    /**
     * Expected Behavior Verification: Einfacher Methodenaufruf
     */
    @Test
    public void testSendingFuelBills() {

        // setup: Konfigurieren der Mocks (Calculator liegt als Interface vor)
        Calculator calculator = Mockito.mock(Calculator.class);
        PostService postService = Mockito.mock(PostService.class);
        sut.setCalculator(calculator);
        sut.setPostService(postService);

        // exercise: Ausführen der Logik
        sut.sendFuelBills();

        // verify: Überprüfung, ob die Berechnungsmethode des Calculators aufgerufen wird
        Mockito.verify(calculator)
                .calculateFuelBill(sut.getCurrentBuilding());
    }

    /**
     * Methodenaufruf mit expliziten Verhalten
     */
    @Test
    public void testSendingFuelBillsWithExplicitPrice() {

        // setup: Konfigurieren der Mocks (Calculator liegt als Interface vor)
        Calculator calculator = Mockito.mock(Calculator.class);
        sut.setCalculator(calculator);

        // exercise: Ausführen der Logik
        sut.sendFuelBills();

        // Wenn die Berechnungsmethode aufgerufen wird, soll diese zunächst 8750€ als Ergebnis zurückgeben.
        // Beim zweiten Aufruf der Methode dann 1750€ (Methoden-Chaining)
        Mockito.when(calculator.calculateFuelBill(sut.getCurrentBuilding()))
                .thenReturn(8750d)
                .thenReturn(1750d);

    }

    /**
     * Procedural Behavior Verification: Über einen TestSpy werden die benötigten Angaben aufgenommen
     * und können so mit den Erwartungswerten verglichen werden.
     */
    @Test
    public void testSendingFuelBillsByPBV() {

        // setup: Erstellen der Spy-Objekte
        CalculatorSpy calculator = new CalculatorSpy();
        sut.setCalculator(calculator);

        // exercise: Ausführen der Logik
        sut.sendFuelBills();

        // verify: Überprüfung, ob die Berechnungsmethode des Calculators aufgerufen wurde, indem das Spy-Objekt untersucht wird.
        Assert.assertEquals("number of calls", 1, calculator.getNumberOfCalls());
        Assert.assertEquals("Action", "calculateFuelBill", calculator.getAction());
    }



}
