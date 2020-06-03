package tenantsystem.editor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import tenantsystem.core.Tenant;

public class MainFrameControl {
    public ListView<Tenant> listView;

    @FXML
    public void initialize() {
        ObservableList<Tenant> items = FXCollections.observableArrayList(
                new Tenant("Andreas", "Duschanek", "Musterstraße 4", "012345 6780", "DE92 4552 45550 32"),
                new Tenant("Christian", "Mahr", "Musterstraße 4", "012345 6780", "DE92 4552 45550 32"),
                new Tenant("Jonas", "Schnettker", "Musterstraße 4", "012345 6780", "DE92 4552 45550 32")
        );
        listView.setItems(items);
    }
}
