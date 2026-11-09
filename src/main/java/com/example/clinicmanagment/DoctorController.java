package com.example.clinicmanagment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DoctorController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField specialtyField;

    @FXML
    private TableView<Doctor> doctorTable;

    @FXML
    private TableColumn<Doctor, String> nameColumn;

    @FXML
    private TableColumn<Doctor, String> specialtyColumn;

    private ObservableList<Doctor> doctorList =
            FXCollections.observableArrayList();

    private ObservableList<Doctor> allDoctors =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        specialtyColumn.setCellValueFactory(
                new PropertyValueFactory<>("specialty"));

        doctorTable.setItems(doctorList);

        allDoctors.addAll(FileManager.loadDoctors());

        doctorList.addAll(allDoctors);
    }

    @FXML
    public void addDoctor() {

        String name = nameField.getText();
        String specialty = specialtyField.getText();

        if(name.isEmpty() || specialty.isEmpty()) {

            Alert alert =
                    new Alert(Alert.AlertType.ERROR);

            alert.setContentText(
                    "Please fill all fields!");

            alert.showAndWait();

            return;
        }

        Doctor doctor =
                new Doctor(name, specialty);

        doctorList.add(doctor);
        allDoctors.add(doctor);

        FileManager.saveDoctor(doctor);

        nameField.clear();
        specialtyField.clear();
    }

    // NAVIGATION

    @FXML
    public void openDashboard(javafx.event.ActionEvent event) {

        try {

            Parent root =
                    FXMLLoader.load(
                            getClass().getResource("dashboard.fxml"));

            Stage stage =
                    (Stage)((javafx.scene.Node)
                            event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(
                    getClass().getResource("style.css")
                            .toExternalForm());

            stage.setScene(scene);

            stage.setMaximized(false);

            stage.setWidth(1600);

            stage.setHeight(900);

            stage.centerOnScreen();

            stage.show();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    public void openPatients(javafx.event.ActionEvent event) {

        try {

            Parent root =
                    FXMLLoader.load(
                            getClass().getResource("patients.fxml"));

            Stage stage =
                    (Stage)((javafx.scene.Node)
                            event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(
                    getClass().getResource("style.css")
                            .toExternalForm());

            stage.setScene(scene);

            stage.setMaximized(false);

            stage.setWidth(1600);

            stage.setHeight(900);

            stage.centerOnScreen();

            stage.show();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    public void openAppointments(javafx.event.ActionEvent event) {

        try {

            Parent root =
                    FXMLLoader.load(
                            getClass().getResource("appointments.fxml"));

            Stage stage =
                    (Stage)((javafx.scene.Node)
                            event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(
                    getClass().getResource("style.css")
                            .toExternalForm());

            stage.setScene(scene);

            stage.setMaximized(false);

            stage.setWidth(1600);

            stage.setHeight(900);

            stage.centerOnScreen();

            stage.show();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    public void deleteDoctor() {

        Doctor selectedDoctor =
                doctorTable.getSelectionModel()
                        .getSelectedItem();

        if(selectedDoctor == null) {

            Alert alert =
                    new Alert(Alert.AlertType.ERROR);

            alert.setContentText(
                    "Please select a doctor!");

            alert.showAndWait();

            return;
        }

        Alert confirmAlert =
                new Alert(Alert.AlertType.CONFIRMATION);

        confirmAlert.setTitle("Confirm Delete");

        confirmAlert.setHeaderText(null);

        confirmAlert.setContentText(
                "Are you sure you want to delete this doctor?");

        if(confirmAlert.showAndWait().get()
                == ButtonType.OK) {

            doctorList.remove(selectedDoctor);
        }
    }

}
