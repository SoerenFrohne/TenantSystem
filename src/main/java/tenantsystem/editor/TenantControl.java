package tenantsystem.editor;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import tenantsystem.session.Session;

import java.time.format.DateTimeFormatter;

public class TenantControl {

    public TextField firstNameField;
    public TextField lastNameField;
    public TextField birthdayField;
    public TextField telephoneField;
    public TextField ibanField;
    public TextField apartmentSizeField;
    public TextField apartmentLabelField;


    @FXML
    public void initialize() {
        firstNameField.setText(Session.INSTANCE.getCurrentTenant().getFirstName());
        lastNameField.setText(Session.INSTANCE.getCurrentTenant().getLastName());
        telephoneField.setText(Session.INSTANCE.getCurrentTenant().getPhoneNumber());
        birthdayField.setText(Session.INSTANCE.getCurrentTenant().getBirthday().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))); //.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG)));
        ibanField.setText(Session.INSTANCE.getCurrentTenant().getIban());

        apartmentLabelField.setText(Session.INSTANCE.getCurrentApartment().getLabel());
        apartmentSizeField.setText(String.valueOf(Session.INSTANCE.getCurrentApartment().getApartmentUnit()));
    }
}
