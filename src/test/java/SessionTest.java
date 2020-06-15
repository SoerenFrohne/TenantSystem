import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import tenantsystem.core.Building;
import tenantsystem.core.Tenant;
import tenantsystem.core.utils.Calculator;
import tenantsystem.core.utils.PostService;
import tenantsystem.session.Session;

import java.sql.SQLException;
import java.time.LocalDate;

public class SessionTest {

    private Session sut;
    private final Building testBuilding = new Building("Musterstraße 1", "55675", "Mustercity", "Auenland", "Mittelerde");
    private final Tenant testTenant = new Tenant("Max", "Mustermann", "Musterstraße 3", "1234", "12345", LocalDate.of(1990, 12, 31));

    @Before
    public void setUp() {
        sut = Session.INSTANCE;
        sut.setCurrentBuilding(testBuilding);
        sut.setCurrentTenant(testTenant);
    }

    /**
     * Procedural Behavior Verification: Über einen TestSpy werden die benötigten Angaben aufgenommen
     * und können so mit den Erwartungswerten verglichen werden.
     *
     * Soll fehlschlagen, da kein PostServiceSpy realisiert wurde
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

    /**
     * Expected Behavior Verification: Einfacher Methodenaufruf
     */
    @Test
    public void testSendingFuelBills() throws SQLException {

        // setup: Konfigurieren der Mocks (Calculator liegt als Interface vor)
        Calculator calculator = Mockito.mock(Calculator.class);
        PostService postService = Mockito.mock(PostService.class);
        sut.setCalculator(calculator);
        sut.setPostService(postService);

        // exercise: Ausführen der Logik
        sut.sendFuelBills();

        // verify: Überprüfung, ob die Berechnungsmethode des Calculators aufgerufen wird
        Mockito.verify(calculator)
                .calculateFuelBill(testBuilding);
        Mockito.verify(postService)
                .sendBills(testBuilding.getTenants().toArray(new Tenant[0]));
    }

    /**
     * Methodenaufrufe mit expliziten Verhalten (Soll fehlschlagen
     */
    @Test
    public void testSendingFuelBillsWithExplicitPrice() {

        // setup:
        // Konfigurieren der Mocks
        Calculator calculator = Mockito.mock(Calculator.class);
        PostService postService = Mockito.mock(PostService.class);
        sut.setCalculator(calculator);
        sut.setPostService(postService);

        // exercise:
        // Ausführen der Logik
        sut.sendFuelBills();
        sut.sendFuelBills();

        // Wenn die Berechnungsmethode aufgerufen wird, soll diese zunächst 8750€ als Ergebnis zurückgeben.
        // Beim zweiten Aufruf der Methode dann 1750€ (Methoden-Chaining)
        Mockito.when(calculator.calculateFuelBill(sut.getCurrentBuilding()))
                .thenReturn(8750d)
                .thenReturn(1750d);

        // verify:
        // Überprüfe, ob Methode zwei Mal aufgerufen wird
        Mockito.verify(calculator, Mockito.times(2))
                .calculateFuelBill(sut.getCurrentBuilding());

        // Überprüfe, ob checkAdress-Methode mit irgeneinen String aufgerufen wird.
        // Test sollte fehlschlagen
        Mockito.verify(postService).checkAddress(Mockito.anyString());
    }


}
