package tenantsystem.editor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import tenantsystem.core.Apartment;
import tenantsystem.core.Building;
import tenantsystem.core.Tenant;
import tenantsystem.session.Session;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainFrameControl {
    public TreeView<IMenuItem> treeview;
    public BorderPane borderPane;

    @FXML
    public void initialize() {
        loadExampleData();
    }

    private void loadExampleData() {
        try {
            Building building = new Building("Musterstraße 3", "53756", "Musterhausen", "Rheinland-Pfalz", "Deutschland");
            building.getTenants().addAll(Arrays.asList(Session.INSTANCE.getTenants()));
            Apartment[] apartments = Session.INSTANCE.getApartments();

            TreeItem<IMenuItem> rootItem = new TreeItem<>(building);
            rootItem.setExpanded(true);

            for (int i = 0; i < building.getTenants().size(); i++) {
                Tenant t = building.getTenants().get(i);
                building.moveIn(apartments[i], t);
                rootItem.getChildren().add(new TreeItem<>(t));
            }

            treeview.setRoot(rootItem);

            // Change Menu depending on class
            treeview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource(newValue.getValue().getFxmlPath()));
                    borderPane.setCenter(root);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            treeview.getSelectionModel().select(0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
