package com.example.clinicmanagment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;

public class PatientController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField searchField;


    @FXML
    private TableView<Patient> patientTable;

    @FXML
    private TableColumn<Patient, String> nameColumn;

    @FXML
    private TableColumn<Patient, Integer> ageColumn;

    @FXML
    private TableColumn<Patient, String> phoneColumn;

    private ObservableList<Patient> patientList =
            FXCollections.observableArrayList();

    private ArrayList<Patient> allPatients =
            new ArrayList<>();


    @FXML
    public void initialize() {

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        ageColumn.setCellValueFactory(
                new PropertyValueFactory<>("age"));

        phoneColumn.setCellValueFactory(
                new PropertyValueFactory<>("phone"));

        patientTable.setItems(patientList);

        allPatients = FileManager.loadPatients();

        patientList.addAll(allPatients);

    }

    @FXML
    public void addPatient() {

        String name = nameField.getText();
        String ageText = ageField.getText();
        String phone = phoneField.getText();

        if(name.isEmpty() || ageText.isEmpty() || phone.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all fields!");

            alert.showAndWait();

            return;
        }

        int age;

        try {

            age = Integer.parseInt(ageText);

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setContentText(
                    "Age must be a number!");

            alert.showAndWait();

            return;
        }

        if(!phone.matches("[0-9]+")) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setContentText(
                    "Phone must contain only numbers!");

            alert.showAndWait();

            return;
        }

        Patient patient = new Patient(name, age, phone);

        patientList.add(patient);
        FileManager.savePatient(patient);
        allPatients.add(patient);

        nameField.clear();
        ageField.clear();
        phoneField.clear();
    }

    @FXML
    public void openDashboard(javafx.event.ActionEvent event) {

        try {

            javafx.scene.Parent root =
                    javafx.fxml.FXMLLoader.load(
                            getClass().getResource("dashboard.fxml"));

            javafx.stage.Stage stage =
                    (javafx.stage.Stage)((javafx.scene.Node)
                            event.getSource()).getScene().getWindow();

            javafx.scene.Scene scene =
                    new javafx.scene.Scene(root);

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

            javafx.scene.Parent root =
                    javafx.fxml.FXMLLoader.load(
                            getClass().getResource("appointments.fxml"));

            javafx.stage.Stage stage =
                    (javafx.stage.Stage)((javafx.scene.Node)
                            event.getSource()).getScene().getWindow();

            javafx.scene.Scene scene =
                    new javafx.scene.Scene(root);

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
    public void openDoctors(javafx.event.ActionEvent event) {

        try {

            javafx.scene.Parent root =
                    javafx.fxml.FXMLLoader.load(
                            getClass().getResource("doctors.fxml"));

            javafx.stage.Stage stage =
                    (javafx.stage.Stage)((javafx.scene.Node)
                            event.getSource()).getScene().getWindow();

            javafx.scene.Scene scene =
                    new javafx.scene.Scene(root);

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
    public void searchPatient() {

        String searchText =
                searchField.getText().toLowerCase();

        patientList.clear();

        for(Patient patient : allPatients) {

            if(patient.getName()
                    .toLowerCase()
                    .contains(searchText)) {

                patientList.add(patient);
            }
        }
    }

    @FXML
    public void showAllPatients() {

        patientList.clear();

        patientList.addAll(allPatients);
    }

    @FXML
    public void deletePatient() {

        Patient selectedPatient =
                patientTable.getSelectionModel()
                        .getSelectedItem();

        if(selectedPatient == null) {

            Alert alert =
                    new Alert(Alert.AlertType.ERROR);

            alert.setContentText(
                    "Please select a patient!");

            alert.showAndWait();

            return;
        }

        Alert confirmAlert =
                new Alert(Alert.AlertType.CONFIRMATION);

        confirmAlert.setTitle("Confirm Delete");

        confirmAlert.setHeaderText(null);

        confirmAlert.setContentText(
                "Are you sure you want to delete this patient?");

        if(confirmAlert.showAndWait().get()
                == ButtonType.OK) {

            patientList.remove(selectedPatient);
        }
    }

}
