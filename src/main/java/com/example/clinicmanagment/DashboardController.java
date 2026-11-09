package com.example.clinicmanagment;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.time.LocalDate;

public class DashboardController {

    @FXML
    private TableView<Appointment> appointmentTable;

    @FXML
    private TableColumn<Appointment, String> patientColumn;

    @FXML
    private TableColumn<Appointment, String> doctorColumn;

    @FXML
    private TableColumn<Appointment, String> specialtyColumn;

    @FXML
    private TableColumn<Appointment, String> timeColumn;

    @FXML
    private javafx.scene.control.Label patientsLabel;

    @FXML
    private javafx.scene.control.Label doctorsLabel;

    @FXML
    private javafx.scene.control.Label appointmentsLabel;


    private ObservableList<Appointment> appointmentList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        patientColumn.setCellValueFactory(cellData ->
                Bindings.createStringBinding(
                        () -> cellData.getValue()
                                .getPatient()
                                .getName()
                ));

        doctorColumn.setCellValueFactory(cellData ->
                Bindings.createStringBinding(
                        () -> cellData.getValue()
                                .getDoctor()
                                .getName()
                ));

        specialtyColumn.setCellValueFactory(cellData ->
                Bindings.createStringBinding(
                        () -> cellData.getValue()
                                .getDoctor()
                                .getSpecialty()
                ));

        timeColumn.setCellValueFactory(
                new PropertyValueFactory<>("time"));

        appointmentTable.setItems(appointmentList);

        String today =
                LocalDate.now().toString();

        for(Appointment appointment :
                FileManager.loadAppointments()) {

            if(appointment.getDate()
                    .equals(today)) {

                appointmentList.add(appointment);
            }
        }

        patientsLabel.setText(
                String.valueOf(
                        FileManager.loadPatients().size()
                ));

        doctorsLabel.setText(
                String.valueOf(
                        FileManager.loadDoctors().size()
                ));

        appointmentsLabel.setText(
                String.valueOf(
                        FileManager.loadAppointments().size()
                ));

    }

    // PATIENTS PAGE

    public void openPatientsPage(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(
                    getClass().getResource("patients.fxml"));

            Stage stage = (Stage)((javafx.scene.Node)
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

    // APPOINTMENTS PAGE

    public void openAppointmentsPage(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(
                    getClass().getResource("appointments.fxml"));

            Stage stage = (Stage)((javafx.scene.Node)
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

    // DOCTORS PAGE

    public void openDoctorsPage(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(
                    getClass().getResource("doctors.fxml"));

            Stage stage = (Stage)((javafx.scene.Node)
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
}
