package com.example.meetingcalender;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class CalendarTimeController implements Initializable {

    private Random random;
    private LocalDate currentTimeWeek;
    private boolean[][] meetingSlots;
    @FXML
    private Button goToBack;
    @FXML
    private Button goToForward;
    @FXML
    private Text year;
    @FXML
    private Text month;
    @FXML
    private Text startOfWeek;
    @FXML
    private Text endOfWeek;
    @FXML
    private Text nameOfPerson;
    @FXML
    private Text idOfPerson;
    @FXML
    private GridPane calendarInGrid;
    @FXML
    private ImageView timeTableImageView;
    ClientForm clientForm;
    CheckFormController checkFormController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File timeTable = new File("C:\\Users\\abdul\\OneDrive\\Masaüstü\\Java\\MeetingCalender\\src\\main\\resources\\images\\timetable.png");
        Image timeTableImage = new Image(timeTable.toURI().toString());
        timeTableImageView.setImage(timeTableImage);
        random = new Random();
        currentTimeWeek = LocalDate.now();
        setDateCalendar();
        setActivitySlots();
        updateCalendar();

    }

    @FXML
    public void goBackOnAction(ActionEvent event) {

        /*
        LocalDate localDate = LocalDate.now();
        updateCalendar(currentTimeWeek.minusWeeks(1));
        */

        currentTimeWeek = currentTimeWeek.minusDays(7);
        setDateCalendar();
        setActivitySlots();
        updateCalendar();
    }

    @FXML
    public void goForwardOnAction(ActionEvent event) {

        /*
        LocalDate localDate = LocalDate.now();
        updateCalendar(currentTimeWeek.plusWeeks(1));
        */

        currentTimeWeek = currentTimeWeek.plusDays(7);
        setDateCalendar();
        setActivitySlots();
        updateCalendar();
    }

    public void setClient(ClientForm clientForm) {
        this.clientForm = clientForm;
        nameOfPerson.setText(clientForm.getFirstName() + " " + clientForm.getLastName());
        idOfPerson.setText(clientForm.getIdNumber());
    }

    public void setActivitySlots() {
        meetingSlots = new boolean[7][10];
        for (int i = 0; i < 19; i++) {
            meetingSlots[random.nextInt(7)][random.nextInt(10)] = true;
        }
    }

    public void setDateCalendar() {
        year.setText(String.valueOf(currentTimeWeek.getYear()));
        month.setText(String.valueOf(currentTimeWeek.getMonth()));
        startOfWeek.setText(String.valueOf(currentTimeWeek.getDayOfMonth()));
        endOfWeek.setText(String.valueOf(currentTimeWeek.plusDays(6).getDayOfMonth()));
    }

    public void updateCalendar() {
        calendarInGrid.getChildren().clear();
        AtomicBoolean hasAppointment = new AtomicBoolean(false);
        for (int day = 0; day < 7; day++) {
            for (int hour = 0; hour < 10; hour++) {
                Text slotText = new Text();
                //slotText.setText(LocalTime.of(hour + 9, 0).toString());

                if (meetingSlots[day][hour]) {
                    slotText.setFill(Color.RED);
                    slotText.setText("DOLU");
                } else {
                    slotText.setFill(Color.GREEN);
                    slotText.setText("MÜSAİT");
                    int finalDay = day;
                    int finalHour = hour;
                    slotText.setOnMouseClicked(mouseEvent -> {
                        if (!hasAppointment.get()) {
                            System.out.println("Randevu alındı: " + currentTimeWeek.plusDays(finalDay) + " " + LocalTime.of(finalHour + 9, 0));
                            meetingSlots[finalDay][finalHour] = true;
                            String currentDay = String.valueOf(currentTimeWeek.plusDays(finalDay));
                            String currentTime = String.valueOf(LocalTime.of(finalHour + 9, 0));
                            clientForm = new ClientForm(clientForm.getFirstName(), clientForm.getLastName(), clientForm.getIdNumber(), currentDay, currentTime);
                            hasAppointment.set(true);
                            updateCalendar();
                            goLastCheckForm();
                            Stage stage = (Stage) goToBack.getScene().getWindow();
                            stage.close();
                        } else System.out.println("Randevunuz Bulunmaktadır.");
                    });
                }
                GridPane.setHalignment(slotText, HPos.CENTER);
                GridPane.setValignment(slotText, VPos.CENTER);

                calendarInGrid.add(slotText, day, hour);
            }
        }
        System.out.println("Başarılı");
    }

    public Image icon() {
        File iconFile = new File("C:\\Users\\abdul\\OneDrive\\Masaüstü\\Java\\MeetingCalender\\src\\main\\resources\\images\\health.png");
        return new Image(iconFile.toURI().toString());
    }


    public void goLastCheckForm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("checkForm.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
            CheckFormController checkFormController = fxmlLoader.getController();
            checkFormController.setInformation(clientForm);

            Stage stage = new Stage();
            stage.setTitle("Meeting Calendar");
            stage.getIcons().add(icon());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


   /* public void drawGridPaneLines(){
        int numColumn = 7;
        int numRow = 10;

        for (int row = 0; row < numRow; row++){
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100.0/numRow);
            calendarInGrid.getRowConstraints().add(rowConstraints);
        }

        for (int col = 0; col < numColumn; col++){
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100.0/numColumn);
            calendarInGrid.getColumnConstraints().add(columnConstraints);
        }

        for (int col = 0; col < numColumn; col++){
            for (int row = 0; row < numRow; row++){
                Separator separator = new Separator();
                separator.getStyleClass().add("grid-line");

                GridPane.setConstraints(separator,col,row);
                calendarInGrid.getChildren().add(separator);
            }
        }
    }*/

