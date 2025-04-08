package app.controllers;

import app.models.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SupplierController {
    @FXML private TableView<Supplier> suppliersTable;
    @FXML private TableColumn<Supplier, Number> idColumn;
    @FXML private TableColumn<Supplier, String> nameColumn;
    @FXML private TableColumn<Supplier, String> contactColumn;
    @FXML private TableColumn<Supplier, String> phoneColumn;
    @FXML private TableColumn<Supplier, String> emailColumn;      // New: Email Column
    @FXML private TableColumn<Supplier, String> addressColumn;    // New: Address Column
    @FXML private TableColumn<Supplier, Number> ratingColumn;

    @FXML private TextField nameField, contactField, phoneField, emailField, addressField;
    @FXML private Spinner<Double> ratingSpinner;

    private final ObservableList<Supplier> suppliers = FXCollections.observableArrayList();

    // Keep track of the supplier selected for update
    private Supplier selectedSupplier;

    @FXML
    public void initialize() {
        // Configure table columns to bind to corresponding Supplier properties
        idColumn.setCellValueFactory(cellData -> cellData.getValue().supplierIdProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        contactColumn.setCellValueFactory(cellData -> cellData.getValue().contactPersonProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        ratingColumn.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());

        // Configure the rating spinner (0-5 scale with default 3 and step of 0.5)
        ratingSpinner.setValueFactory(
                new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 5, 3, 0.5));

        // Set the suppliers list into the TableView
        suppliersTable.setItems(suppliers);

        // Listen for table selection to load supplier details for editing
        suppliersTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedSupplier = newSelection;
                loadSupplierDetails(selectedSupplier);
            }
        });
    }

    @FXML
    private void addSupplier() {
        Supplier newSupplier = new Supplier(
                suppliers.size() + 1, // Auto-generated ID
                nameField.getText(),
                contactField.getText(),
                phoneField.getText(),
                emailField.getText(),
                addressField.getText(),
                ratingSpinner.getValue(),
                true
        );
        if (newSupplier.validate()) {
            suppliers.add(newSupplier);
            clearFields();
        } else {
            showAlert("Validation Error", "Please check the supplier details.");
        }
    }

    @FXML
    private void updateSupplier() {
        if (selectedSupplier != null) {
            selectedSupplier.setName(nameField.getText());
            selectedSupplier.setContactPerson(contactField.getText());
            selectedSupplier.setPhone(phoneField.getText());
            selectedSupplier.setEmail(emailField.getText());
            selectedSupplier.setAddress(addressField.getText());
            selectedSupplier.setRating(ratingSpinner.getValue());
            if (selectedSupplier.validate()) {
                suppliersTable.refresh();
                clearFields();
            } else {
                showAlert("Validation Error", "Please check the supplier details.");
            }
        } else {
            showAlert("No Selection", "Please select a supplier to update.");
        }
    }

    @FXML
    private void deleteSupplier() {
        Supplier supplierToDelete = suppliersTable.getSelectionModel().getSelectedItem();
        if (supplierToDelete != null) {
            suppliers.remove(supplierToDelete);
            clearFields();
        } else {
            showAlert("No Selection", "Please select a supplier to delete.");
        }
    }

    // Loads the details of the selected supplier into the form fields.
    private void loadSupplierDetails(Supplier supplier) {
        nameField.setText(supplier.getName());
        contactField.setText(supplier.getContactPerson());
        phoneField.setText(supplier.getPhone());
        emailField.setText(supplier.getEmail());
        addressField.setText(supplier.getAddress());
        ratingSpinner.getValueFactory().setValue(supplier.getRating());
    }

    // Clears the input fields and resets the table selection.
    private void clearFields() {
        nameField.clear();
        contactField.clear();
        phoneField.clear();
        emailField.clear();
        addressField.clear();
        ratingSpinner.getValueFactory().setValue(3.0);
        suppliersTable.getSelectionModel().clearSelection();
        selectedSupplier = null;
    }

    // Utility method to display alert dialogs.
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
