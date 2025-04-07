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
    // Add other columns...

    @FXML private TextField nameField, contactField, phoneField, emailField, addressField;
    @FXML private Spinner<Double> ratingSpinner;

    private final ObservableList<Supplier> suppliers = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configure table columns
        idColumn.setCellValueFactory(cellData -> cellData.getValue().supplierIdProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        // Configure other columns...

        // Set up rating spinner (0-5 scale)
        ratingSpinner.setValueFactory(
                new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 5, 3, 0.5));

        suppliersTable.setItems(suppliers);
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

        suppliers.add(newSupplier);
        clearFields();
    }

    private void clearFields() {
        nameField.clear();
        contactField.clear();
        phoneField.clear();
        emailField.clear();
        addressField.clear();
        ratingSpinner.getValueFactory().setValue(3.0);
    }
}
