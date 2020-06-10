package tenantsystem.editor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import tenantsystem.core.BaseModel;
import tenantsystem.core.Tenant;

import java.sql.SQLException;

public class MainFrameControl {
    public ListView<Tenant> listView;
    private BaseModel baseModel = new BaseModel();

    @FXML
    public void initialize() throws SQLException {

        ObservableList<Tenant> items = FXCollections.observableArrayList(
                baseModel.getTenats()


                //new Tenant("Andreas", "Duschanek", "Musterstraße 4", "012345 6780", "DE92 4552 45550 32"),
                //new Tenant("Christian", "Mahr", "Musterstraße 4", "012345 6780", "DE92 4552 45550 32"),
                //new Tenant("Jonas", "Schnettker", "Musterstraße 4", "012345 6780", "DE92 4552 45550 32")
        );

        listView.setItems(items);
    }
}
