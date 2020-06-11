package tenantsystem.core.utils;

import tenantsystem.core.Apartment;
import tenantsystem.core.Building;

/**
 * Das hier betrachtete Framework Mockito geht dabei davon aus, dass die fehlende, also
 * zu mockende Klasse als Interface vorliegt. Dies ist keine echte Einschränkung, da Interfaces
 * oder vollständig abstrakte Klassen eine elementare Bedeutung im systematischen
 * Software-Engineering haben. Mit der Idee des Design-by-Contract kann ein Interface als
 * Vertrag angesehen werden. Vertragsbeteiligte sind dabei die Entwickler, die garantieren,
 * das vereinbarte Interface zu realisieren, aber Freiheiten bei der Umsetzung haben (S.149).
 */
public interface Calculator {
    double calculateFuelBill(Building building);
}
