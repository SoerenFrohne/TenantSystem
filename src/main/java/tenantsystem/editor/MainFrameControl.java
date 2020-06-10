package tenantsystem.editor;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import tenantsystem.core.Building;
import tenantsystem.core.Tenant;
import tenantsystem.session.Session;

import java.io.IOException;
import java.util.ArrayList;

public class MainFrameControl {
    public TreeView<IMenuItem> treeview;
    public BorderPane borderPane;

    @FXML
    public void initialize() {
        loadExampleData();
    }

    private void loadExampleData() {
        Building building = new Building("Musterstraße 3", "53756", "Musterhausen", "Rheinland-Pfalz", "Deutschland", new ArrayList<>());
        building.getTenants().add(new Tenant("Max", "Mustermann", "Musterstraße 3", "0123456", "DE12500105170648489890"));
        building.getTenants().add(new Tenant("Mia", "Musterfrau", "Musterstraße 3", "0123456", "DE12500105170648489890"));
        TreeItem<IMenuItem> rootItem = new TreeItem<>(building);
        rootItem.setExpanded(true);

        for (Tenant t : building.getTenants()) {
            rootItem.getChildren().add(new TreeItem<>(t));
        }

        treeview.setRoot(rootItem);

        // Change Menu depending on class
        treeview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            TreeItem<IMenuItem> selectedItem = newValue;
            try {
                Parent root = FXMLLoader.load(getClass().getResource(selectedItem.getValue().getFxmlPath()));
                borderPane.setCenter(root);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
