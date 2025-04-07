package app.controllers;

import app.models.Person;
import app.models.PersonsStore;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class PersonsController {

    @FXML
    private Button addBtn;
    @FXML
    private TableColumn<Person, String> addressCol;
    @FXML
    private TableColumn<Person, Integer> ageCol;
    @FXML
    private TableColumn<Person, Long> phoneCol;
    @FXML
    private TableColumn<Person, String> nameCol;
    @FXML
    private TableView<Person> personsTable;

    @FXML
    private TextField addressFld;

    @FXML
    private TextField ageFld;

    @FXML
    private TextField phoneFld;
    @FXML
    private Button deleteBtn;

    @FXML
    private TextField nameFld;

    @FXML
    private Button updateBtn;
    @FXML
    private Text errorMsg;
    private final PersonsStore personStore = new PersonsStore();

    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        ageCol.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());
        addressCol.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        phoneCol.setCellValueFactory(cellData -> cellData.getValue().phoneProperty().asObject());

        personsTable.setEditable(true);
        nameCol.setEditable(true);
        ageCol.setEditable(true);
        addressCol.setEditable(true);
        phoneCol.setEditable(true);

        ObservableList<Person> persons = personStore.getPersonsList();
        personsTable.setItems(persons);

        personsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Person selectedPerson = personsTable.getSelectionModel().getSelectedItem();
            if (selectedPerson != null) {
                nameFld.setText(selectedPerson.getName());
                ageFld.setText(Integer.toString(selectedPerson.getAge()));
                addressFld.setText(selectedPerson.getAddress());
                phoneFld.setText(Long.toString(selectedPerson.getPhone()));
            }
        });
    }

    @FXML
    void addPerson(ActionEvent event) {
        String error = "";
        boolean isValid = true;
        String name = nameFld.getText();

        if (name.isEmpty()) {
            error += "Error: Name is required\n";
            isValid = false;
        }

        Integer age = null;
        if (ageFld.getText().isEmpty()) {
            error += "Error: Age is required\n";
            isValid = false;
        } else {
            try {
                age = Integer.parseInt(ageFld.getText());
            } catch (NumberFormatException ex) {
                error += "Error: Invalid age value!\n";
                isValid = false;
            }
        }

        Long phone = null;
        if (phoneFld.getText().isEmpty()) {
            error += "Error: Phone is required\n";
            isValid = false;
        } else {
            try {
                phone = Long.parseLong(phoneFld.getText());
            } catch (NumberFormatException ex) {
                error += "Error: Invalid phone number!\n";
                isValid = false;
            }
        }

        String address = addressFld.getText();
        if (address.isEmpty()) {
            error += "Error: Address is required\n";
            isValid = false;
        }

        if (isValid) {
            personStore.addPerson(new Person(name, age, phone, address));
            nameFld.clear();
            ageFld.clear();
            phoneFld.clear();
            addressFld.clear();
            errorMsg.setText("");
        } else {
            errorMsg.setText(error);
        }
    }

    @FXML
    void deletePerson(ActionEvent event) {
        Person selectedPerson = personsTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            personStore.removePerson(selectedPerson);
            personsTable.getItems().remove(selectedPerson); // Refresh TableView
            errorMsg.setText(""); // Clear any error messages
        } else {
            errorMsg.setText("Error: No person selected for deletion!");
        }
    }

    @FXML
    void updatePerson(ActionEvent event) {
        Person selectedPerson = personsTable.getSelectionModel().getSelectedItem();

        if (selectedPerson != null) {
            String error = "";
            boolean isValid = true;

            String name = nameFld.getText();
            if (name.isEmpty()) {
                error += "Error: Name is required!\n";
                isValid = false;
            }

            Integer age = null;
            if (ageFld.getText().isEmpty()) {
                error += "Error: Age is required!\n";
                isValid = false;
            } else {
                try {
                    age = Integer.parseInt(ageFld.getText());
                } catch (NumberFormatException ex) {
                    error += "Error: Invalid age value!\n";
                    isValid = false;
                }
            }

            String address = addressFld.getText();
            if (address.isEmpty()) {
                error += "Error: Address is required!\n";
                isValid = false;
            }

            Long phone = null;
            if (phoneFld.getText().isEmpty()) {
                error += "Error: Phone is required!\n";
                isValid = false;
            } else {
                try {
                    phone = Long.parseLong(phoneFld.getText());
                } catch (NumberFormatException ex) {
                    error += "Error: Invalid phone number!\n";
                    isValid = false;
                }
            }

            if (isValid) {
                personStore.updatePerson(selectedPerson, name, age, phone, address);
                personsTable.refresh();
                errorMsg.setText("");
            } else {
                errorMsg.setText(error);
            }
        }
    }
}
