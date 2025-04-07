package app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class HomeController {

    private void openNewWindow(String s) {
    }
    public void openPersonsManagement(ActionEvent event) {
        openNewWindow("/app/views/views/persons-view.fxml","Customers Management",event);
    }
    public void openSalesAndOffersManagement(ActionEvent event) {
        openNewWindow("/app/views/views/sale-view.fxml", "Sales & Offers Management", event);
    }
    public void openOrderManagement(ActionEvent event) {
        openNewWindow("/app/views/views/order-view.fxml", "Order Management", event);
    }
    public void openSupplierManagement(ActionEvent event) {
        openNewWindow("/app/views/views/supplier-view.fxml", "Supplier Management", event);
    }
    private void openNewWindow(String fxmlPath, String title, ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 700, 400));
            stage.show();


            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

