package com.example.clinicmanagment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AppointmentController {

    @FXML
    private ComboBox<Patient> patientComboBox;

    @FXML
    private ComboBox<Doctor> doctorComboBox;

    @FXML
    private ComboBox<String> timeComboBox;

    @FXML
    private TableView<Appointment> appointmentTable;

    @FXML
    private TableColumn<Appointment, String> patientColumn;

    @FXML
    private TableColumn<Appointment, String> doctorColumn;

    @FXML
    private TableColumn<Appointment, String> timeColumn;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableColumn<Appointment, String> dateColumn;

    @FXML
    private TextField searchField;



    private ObservableList<Appointment> appointmentList =
            FXCollections.observableArrayList();


    private ObservableList<Appointment> allAppointments =
            FXCollections.observableArrayList();


    @FXML
    public void initialize() {

        // TABLE COLUMNS

        patientColumn.setCellValueFactory(cellData ->
                javafx.beans.binding.Bindings.createStringBinding(
                        () -> cellData.getValue()
                                .getPatient()
                                .getName()
                ));

        doctorColumn.setCellValueFactory(cellData ->
                javafx.beans.binding.Bindings.createStringBinding(
                        () -> cellData.getValue()
                                .getDoctor()
                                .getName()
                ));

        dateColumn.setCellValueFactory(
                new PropertyValueFactory<>("date"));


        timeColumn.setCellValueFactory(
                new PropertyValueFactory<>("time"));

        // TABLE DATA

        appointmentTable.setItems(appointmentList);
        allAppointments.addAll(
                FileManager.loadAppointments());

        appointmentList.addAll(allAppointments);



        // LOAD PATIENTS

        patientComboBox.getItems().addAll(
                FileManager.loadPatients());


        // LOAD DOCTORS

        doctorComboBox.getItems().addAll(
                FileManager.loadDoctors());


        doctorComboBox.setOnAction(e ->
                updateAvailableTimes());

        datePicker.setOnAction(e ->
                updateAvailableTimes());


        // TIMES

        updateAvailableTimes();
    }

    public void updateAvailableTimes() {

        timeComboBox.getItems().clear();

        timeComboBox.getItems().addAll(
                "09:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00"
        );

        Doctor selectedDoctor =
                doctorComboBox.getValue();

        if(selectedDoctor == null ||
                datePicker.getValue() == null) {

            return;
        }

        String selectedDate =
                datePicker.getValue().toString();

        for(Appointment appointment : allAppointments) {

            if(appointment.getDoctor()
                    .getName()
                    .equals(selectedDoctor.getName())
                    &&
                    appointment.getDate()
                            .equals(selectedDate)) {

                timeComboBox.getItems()
                        .remove(appointment.getTime());
            }
        }
    }

    @FXML
    public void bookAppointment() {

        Patient patient =
                patientComboBox.getValue();

        Doctor doctor =
                doctorComboBox.getValue();

        String date = "";

        if(datePicker.getValue() != null) {

            date = datePicker.getValue().toString();
        }

        String time =
                timeComboBox.getValue();

        // VALIDATION

        if(patient == null ||
                doctor == null ||
                time == null ||
                datePicker.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setContentText(
                    "Please fill all fields!");

            alert.showAndWait();

            return;
        }

        // DUPLICATE CHECK

        for(Appointment app : appointmentList) {

            if(app.getDoctor().getName()
                    .equals(doctor.getName())
                    &&
                    app.getTime().equals(time) && app.getDate().equals(date)) {

                Alert alert =
                        new Alert(Alert.AlertType.ERROR);

                alert.setContentText(
                        "Doctor already has appointment at this time!");

                alert.showAndWait();

                return;
            }
        }

        // CREATE APPOINTMENT

        Appointment appointment =
                new Appointment(patient, doctor, date, time);

        appointmentList.add(appointment);
        FileManager.saveAppointment(appointment);

        allAppointments.add(appointment);

        updateAvailableTimes();

        // CLEAR

        patientComboBox.setValue(null);
        doctorComboBox.setValue(null);
        datePicker.setValue(null);
        timeComboBox.setValue(null);
    }

    @FXML
    public void deleteAppointment() {

        Appointment selectedAppointment =
                appointmentTable.getSelectionModel()
                        .getSelectedItem();

        if(selectedAppointment == null) {

            Alert alert =
                    new Alert(Alert.AlertType.ERROR);

            alert.setContentText(
                    "Please select an appointment!");

            alert.showAndWait();

            return;
        }

        Alert confirmAlert =
                new Alert(Alert.AlertType.CONFIRMATION);

        confirmAlert.setTitle("Confirm Delete");

        confirmAlert.setHeaderText(null);

        confirmAlert.setContentText(
                "Are you sure you want to delete this appointment?");

        if(confirmAlert.showAndWait().get()
                == ButtonType.OK) {

            appointmentList.remove(selectedAppointment);
            allAppointments.remove(selectedAppointment);
        }
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
    public void openPatients(javafx.event.ActionEvent event) {

        try {

            javafx.scene.Parent root =
                    javafx.fxml.FXMLLoader.load(
                            getClass().getResource("patients.fxml"));

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
    public void searchAppointments() {

        String searchText =
                searchField.getText().toLowerCase();

        appointmentList.clear();

        for(Appointment appointment : allAppointments) {

            if(appointment.getDoctor()
                    .getName()
                    .toLowerCase()
                    .contains(searchText)) {

                appointmentList.add(appointment);
            }
        }
    }

    @FXML
    public void showAllAppointments() {

        appointmentList.clear();

        appointmentList.addAll(allAppointments);
    }

}
