package tenantsystem.editor;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import tenantsystem.session.Session;


public class BuildingControl {
    public TextField cityField;
    public TextField codeField;
    public TextField streetField;

    @FXML
    public void initialize() {
        cityField.setText(Session.INSTANCE.getCurrentBuilding().getCity());
        codeField.setText(Session.INSTANCE.getCurrentBuilding().getPostalCode());
        streetField.setText(Session.INSTANCE.getCurrentBuilding().getStreetAddress());
    }
}
