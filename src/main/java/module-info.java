
module com.example.demo3.demo6 {
        requires javafx.controls;
        requires javafx.fxml;

        exports app;  // Export the controllers package

        opens app.controllers to javafx.fxml;  // Allow javafx.fxml to access the controllers package for reflection
        }
