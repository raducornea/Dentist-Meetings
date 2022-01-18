package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.lang3.text.WordUtils;

public class Controller implements Initializable {
    static Database db;

    // Texts & Tables
    public TextField client_first_name_input;
    public TextField client_last_name_input;
    public TextField client_pid_input;
    public TextField client_birth_date_input;
    public TextField client_town_input;
    public TextField client_country_input;
    public TextField client_email_input;
    public TableView<Client> clients_table_view;
    public TableColumn<Client, Integer> client_id_column;
    public TableColumn<Client, String> client_first_name_column;
    public TableColumn<Client, String> client_last_name_column;
    public TableColumn<Client, String> client_pid_column;
    public TableColumn<Client, String> client_birth_date_column;
    public TableColumn<Client, String> client_town_column;
    public TableColumn<Client, String> client_country_column;
    public TableColumn<Client, String> client_email_column;

    public TextField dentist_first_name_input;
    public TextField dentist_last_name_input;
    public TextField dentist_pid_input;
    public TextField dentist_specialization_input;
    public TextField dentist_qualification_input;
    public TextField dentist_years_experience_input;
    public TextField dentist_email_input;
    public TableView<Dentist> dentists_table_view;
    public TableColumn<Dentist, Integer>  dentist_id_column;
    public TableColumn<Dentist, String> dentist_first_name_column;
    public TableColumn<Dentist, String> dentist_last_name_column;
    public TableColumn<Dentist, String> dentist_pid_column;
    public TableColumn<Dentist, String> dentist_specialization_column;
    public TableColumn<Dentist, String> dentist_qualification_column;
    public TableColumn<Dentist, Integer> dentist_years_experience_column;
    public TableColumn<Dentist, String> dentist_email_column;

    public TextField centre_name_input;
    public TextField centre_location_input;
    public TextField centre_phone_input;
    public TableView<Centre>  centres_table_view;
    public TableColumn<Centre, Integer> centre_id_column;
    public TableColumn<Centre, String> centre_name_column;
    public TableColumn<Centre, String> centre_location_column;
    public TableColumn<Centre, String> centre_phone_column;

    public ChoiceBox<String> agreement_dentist_choicebox;
    public ChoiceBox<String> agreement_centre_choicebox;
    public TableView<Agreement> agreements_table_view;
    public TableColumn<Agreement, Integer> agreement_id_column;
    public TableColumn<Agreement, String> agreement_first_name_colum;
    public TableColumn<Agreement, String> agreement_last_name_colum;
    public TableColumn<Agreement, String> agreement_name_column;
    public TableColumn<Agreement, String> agreement_phone_colum;

    public ChoiceBox<String> meeting_client_choicebox;
    public ChoiceBox<String> meeting_dentist_centre_choicebox;
    public TableView<Meeting> meetings_table_view;
    public TextField meeting_start_time_input;
    public TextField meeting_end_time_input;
    public TextField meeting_problem_input;
    public TableColumn<Meeting, String> meeting_client_first_name_column;
    public TableColumn<Meeting, String> meeting_client_last_name_column;
    public TableColumn<Meeting, String> meeting_centre_name_column;
    public TableColumn<Meeting, String> meeting_centre_location_column;
    public TableColumn<Meeting, String> meeting_dentist_first_name_column;
    public TableColumn<Meeting, String> meeting_dentist_last_name_column;
    public TableColumn<Meeting, String> meeting_start_column;
    public TableColumn<Meeting, String> meeting_end_column;
    public TableColumn<Meeting, String> meeting_problem_column;

    // Error at Fields - can be used by multiple classes
    public Text field_error;
    static public String field_error_lable = "Default Field Error";
    static public String initializer_type = "Main Screen";
    static public boolean commit_rollback_value = false;

    /**
     * Initializer
     * change @initializer_type BEFORE making a new Scene
     * also, initialize ONLY WHAT YOU NEED
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(initializer_type.equals("Main Screen")){
            try{
                // For clients
                List<Client> db_clients = db.fetch_clients();

                ObservableList<Client> clients = clients_table_view.getItems();
                clients.addAll(db_clients);
                clients_table_view.setItems(clients);

                client_id_column.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
                client_first_name_column.setCellValueFactory(new PropertyValueFactory<Client, String>("first_name"));
                client_last_name_column.setCellValueFactory(new PropertyValueFactory<Client, String>("last_name"));
                client_pid_column.setCellValueFactory(new PropertyValueFactory<Client, String>("pid"));
                client_birth_date_column.setCellValueFactory(new PropertyValueFactory<Client, String>("birth_date"));
                client_town_column.setCellValueFactory(new PropertyValueFactory<Client, String>("town"));
                client_country_column.setCellValueFactory(new PropertyValueFactory<Client, String>("country"));
                client_email_column.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));

                // For dentists
                List<Dentist> db_dentists = db.fetch_dentists();

                ObservableList<Dentist> dentists = dentists_table_view.getItems();
                dentists.addAll(db_dentists);
                dentists_table_view.setItems(dentists);

                dentist_id_column.setCellValueFactory(new PropertyValueFactory<Dentist, Integer>("id"));
                dentist_first_name_column.setCellValueFactory(new PropertyValueFactory<Dentist, String>("first_name"));
                dentist_last_name_column.setCellValueFactory(new PropertyValueFactory<Dentist, String>("last_name"));
                dentist_pid_column.setCellValueFactory(new PropertyValueFactory<Dentist, String>("pid"));
                dentist_specialization_column.setCellValueFactory(new PropertyValueFactory<Dentist, String>("specialization"));
                dentist_qualification_column.setCellValueFactory(new PropertyValueFactory<Dentist, String>("qualification"));
                dentist_years_experience_column.setCellValueFactory(new PropertyValueFactory<Dentist, Integer>("years_experience"));
                dentist_email_column.setCellValueFactory(new PropertyValueFactory<Dentist, String>("email"));

                // For centres
                List<Centre> db_centres = db.fetch_centres();

                ObservableList<Centre> centres = centres_table_view.getItems();
                centres.addAll(db_centres);
                centres_table_view.setItems(centres);

                centre_id_column.setCellValueFactory(new PropertyValueFactory<Centre, Integer>("id"));
                centre_name_column.setCellValueFactory(new PropertyValueFactory<Centre, String>("name"));
                centre_location_column.setCellValueFactory(new PropertyValueFactory<Centre, String>("location"));
                centre_phone_column.setCellValueFactory(new PropertyValueFactory<Centre, String>("phone"));

                // For agreements
                List<Agreement> db_agreements = db.fetch_agreements();

                ObservableList<Agreement> agreements = agreements_table_view.getItems();
                agreements.addAll(db_agreements);
                agreements_table_view.setItems(agreements);

                List<String> full_name = new ArrayList<>();
                db_dentists.forEach(it -> full_name.add(it.getFirst_name() + " | " + it.getLast_name()));
                ObservableList<String> full_name_observable = FXCollections.observableArrayList(full_name);
                agreement_dentist_choicebox.setItems(full_name_observable);

                List<String> name_phone = new ArrayList<>();
                db_centres.forEach(it -> name_phone.add(it.getName() + " | " + it.getPhone()));
                ObservableList<String> name_phone_observable = FXCollections.observableArrayList(name_phone);
                agreement_centre_choicebox.setItems(name_phone_observable);

                agreement_id_column.setCellValueFactory(new PropertyValueFactory<Agreement, Integer>("id"));
                agreement_first_name_colum.setCellValueFactory(new PropertyValueFactory<Agreement, String>("first_name"));
                agreement_last_name_colum.setCellValueFactory(new PropertyValueFactory<Agreement, String>("last_name"));
                agreement_name_column.setCellValueFactory(new PropertyValueFactory<Agreement, String>("name"));
                agreement_phone_colum.setCellValueFactory(new PropertyValueFactory<Agreement, String>("phone"));

                // For meetings
                List<Meeting> db_full_meetings = db.fetch_full_meetings();

                ObservableList<Meeting> meetings = meetings_table_view.getItems();
                meetings.addAll(db_full_meetings);
                meetings_table_view.setItems(meetings);

                meeting_client_first_name_column.setCellValueFactory(new PropertyValueFactory<Meeting, String>("client_first_name"));
                meeting_client_last_name_column.setCellValueFactory(new PropertyValueFactory<Meeting, String>("client_last_name"));
                meeting_centre_name_column.setCellValueFactory(new PropertyValueFactory<Meeting, String>("centre_name"));
                meeting_centre_location_column.setCellValueFactory(new PropertyValueFactory<Meeting, String>("centre_location"));
                meeting_dentist_first_name_column.setCellValueFactory(new PropertyValueFactory<Meeting, String>("dentist_first_name"));
                meeting_dentist_last_name_column.setCellValueFactory(new PropertyValueFactory<Meeting, String>("dentist_last_name"));
                meeting_start_column.setCellValueFactory(new PropertyValueFactory<Meeting, String>("start_time"));
                meeting_end_column.setCellValueFactory(new PropertyValueFactory<Meeting, String>("end_time"));
                meeting_problem_column.setCellValueFactory(new PropertyValueFactory<Meeting, String>("problem"));

                List<String> full_name_email = new ArrayList<>();
                db_clients.forEach(it -> full_name_email.add(it.getFirst_name() + " | " + it.getLast_name() + " | " + it.getEmail()));
                ObservableList<String> full_name_email_observable = FXCollections.observableArrayList(full_name_email);
                meeting_client_choicebox.setItems(full_name_email_observable);

                List<String> dentist_centre = new ArrayList<>();
                db_agreements.forEach(it -> {
                    try {
                        dentist_centre.add(it.getFirst_name() + " | " + it.getLast_name() + " | " + it.getName() + " | " + it.getLocation());
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
                ObservableList<String> dentist_centre_observable = FXCollections.observableArrayList(dentist_centre);
                meeting_dentist_centre_choicebox.setItems(dentist_centre_observable);

            }
            catch (Exception e){

            }
        }
        if(initializer_type.equals("Field Error")) {
            field_error.setText(field_error_lable);
        }
        if(initializer_type.equals("Yes_No")){

        }
    }

    static void define() throws SQLException, ClassNotFoundException {
        db = new Database();
        db.establish_connection();
    }

    static void undefine() throws SQLException {
        db.close_connection();
    }

    @FXML
    public void about(ActionEvent actionEvent) {
        db.about();
    }

    public void close_stage(ActionEvent actionEvent) {
        initializer_type = "Main Screen";
        //field_error_lable = "No Error";
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    // Meeting operations
    public void edit_meeting(ActionEvent actionEvent) throws SQLException {
        field_error_lable = "No Error";

        Meeting meeting = new Meeting(
                "full_name_email",
                "full_name_email",
                "full_name_centre_location",
                "full_name_centre_location",
                "full_name_centre_location",
                "full_name_centre_location",
                meeting_start_time_input.getText(),
                meeting_end_time_input.getText(),
                WordUtils.capitalizeFully(meeting_problem_input.getText(), ' ', '-')
        );

        if (!meeting_fields_check(meeting)) {
            fields_error(actionEvent);
            return;
        }


        try{
            int selectedID = meetings_table_view.getSelectionModel().getSelectedIndex();

            // before doing DB
            Meeting old_meeting = meetings_table_view.getItems().get(selectedID);
            meeting.setId(old_meeting.getId());
            meeting.setClient_last_name(old_meeting.getClient_last_name());
            meeting.setClient_first_name(old_meeting.getClient_first_name());
            meeting.setDentist_last_name(old_meeting.getDentist_last_name());
            meeting.setDentist_first_name(old_meeting.getDentist_first_name());
            meeting.setCentre_name(old_meeting.getCentre_name());
            meeting.setCentre_location(old_meeting.getCentre_location());

            // todo
//            meetings_table_view2 = meetings_table_view;
            commit_rollback(actionEvent, selectedID, old_meeting, meeting);

            // then try to edit table -> rollback and commit
            try {
                db.connection.setAutoCommit(false);
                db.change_meeting(old_meeting, meeting);
            }
            catch (SQLException throwables) {
                field_error_lable = "Exception at SQL! Try Uniques!";
                fields_error(actionEvent);
                throwables.printStackTrace();
            }
            finally {
                if(commit_rollback_value)
                    meetings_table_view.getItems().set(selectedID, meeting); // in interface
                else
                    meetings_table_view.getItems().set(selectedID, old_meeting);
            }
        }
        catch (Exception e){
            field_error_lable = "Select something before editing";
            fields_error(actionEvent);
        }
    }

    public void submit_meeting(ActionEvent actionEvent) {
        field_error_lable = "No Error";

        String dentist, centre;
        String[] full_name_email = new String[3];
        String[] full_name_centre_location = new String[4];
        try {
            dentist = meeting_client_choicebox.getValue();
            full_name_email = dentist.split(" \\| "); // Roxana | Chiuwerti

            centre = meeting_dentist_centre_choicebox.getValue();
            full_name_centre_location = centre.split(" \\| "); // Happy Tooth | 0736000000
        }
        catch (Exception e){
            field_error_lable = "Choice box is empty...";
            fields_error(actionEvent);
            return;
        }

        Meeting meeting = new Meeting(
                full_name_email[0],
                full_name_email[1],
                full_name_centre_location[2],
                full_name_centre_location[3],
                full_name_centre_location[0],
                full_name_centre_location[1],
                meeting_start_time_input.getText(),
                meeting_end_time_input.getText(),
                WordUtils.capitalizeFully(meeting_problem_input.getText(), ' ', '-')
        );

        String client_first_name = meeting.getClient_first_name();
        String client_last_name = meeting.getClient_last_name();
        String dentist_first_name = meeting.getDentist_first_name();
        String dentist_last_name = meeting.getDentist_last_name();
        String name = meeting.getCentre_name();
        String location = meeting.getCentre_location();
        String start_time = meeting.getStart_time();
        String end_time = meeting.getEnd_time();
        String problem = meeting.getProblem();

        if (!meeting_fields_check(meeting)) {
            fields_error(actionEvent);
            return;
        }

        // then try to introduce it into the database
        try {
            boolean check = db.introduce_meeting(client_first_name, client_last_name, dentist_first_name, dentist_last_name, name, location, start_time, end_time, problem);
            if (!check){
                field_error_lable = "Dentist is busy during that time!";
                fields_error(actionEvent);
                return;
            }

            ObservableList<Meeting> meetings = meetings_table_view.getItems();
            meetings.add(meeting);
            meetings_table_view.setItems(meetings);
        }
        catch (SQLException throwables) {
            field_error_lable = "Please put Meetings in the FUTURE dates, not past!";
            fields_error(actionEvent);
            throwables.printStackTrace();
        }
    }

    private boolean meeting_fields_check(Meeting meeting) {
        field_error_lable = "No Error";
        if (meeting.getProblem().equals("") || meeting.getStart_time().equals("") || meeting.getEnd_time().equals("")) {
            field_error_lable = "All Fields must be completed!";
            return false;
        }

        if (meeting.getProblem().length() > 100){
            field_error_lable = "Problem length < 100!";
            return false;
        }

        // Check if dentist is actually client
        if(meeting.getClient_first_name().equals(meeting.getDentist_first_name()) &&
                meeting.getClient_last_name().equals(meeting.getDentist_last_name())){
            field_error_lable = "Dentist must be different from Client!";
            return false;
        }

        String regex = "^(0[1-9]|[12][0-9]|3[01]).(0[1-9]|1[012]).((19|20)[0-9]{2}) (0[1-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
        if (!meeting.getStart_time().matches(regex)) {
            field_error_lable = "Start Date Invalid Format";
            return false;
        }

        if (!meeting.getEnd_time().matches(regex)) {
            field_error_lable = "End Date Invalid Format";
            return false;
        }

        if (!(meeting.getStart_time().compareTo(meeting.getEnd_time()) < 0)) {
            field_error_lable = "Start Date < End Date!";
            return false;
        }

        return true;
    }

    public void delete_meeting(ActionEvent actionEvent) {
        field_error_lable = "Invalid selection for Delete";

        try {
            int selectedID = meetings_table_view.getSelectionModel().getSelectedIndex();

            Meeting old_meeting = meetings_table_view.getItems().get(selectedID);
            // then try to delete from table
            try {
                db.delete_meeting(old_meeting);
            }
            catch (SQLException throwables) {
                field_error_lable = "Exception at SQL! Try Uniques!";
                throwables.printStackTrace();
            }

            meetings_table_view.getItems().remove(selectedID);
        }
        catch (Exception e){
            fields_error(actionEvent);
        }
    }

    // Agreement operations
    public void submit_agreement(ActionEvent actionEvent) {
        field_error_lable = "No Error";

        String dentist, centre;
        String[] full_name = new String[2];
        String[] name_phone = new String[2];
        try {
            dentist = agreement_dentist_choicebox.getValue();
            full_name = dentist.split(" \\| "); // Roxana | Chiuwerti

            centre = agreement_centre_choicebox.getValue();
            name_phone = centre.split(" \\| "); // Happy Tooth | 0736000000
        }
        catch (Exception e){
            field_error_lable = "Choice box is empty...";
            fields_error(actionEvent);
            return;
        }

        Agreement agreement = new Agreement(
                full_name[0],
                full_name[1],
                name_phone[0],
                name_phone[1]
        );

        Integer id = agreement.getId();
        String first_name = agreement.getFirst_name();
        String last_name = agreement.getLast_name();
        String name = agreement.getName();
        String phone = agreement.getPhone();

        // then try to introduce it into the database
        try {
            db.introduce_agreement(first_name, last_name, name, phone);

            ObservableList<Agreement> agreements = agreements_table_view.getItems();
            agreements.add(agreement);
            agreements_table_view.setItems(agreements);

            List<Agreement> db_agreements = db.fetch_agreements();
            List<String> dentist_centre = new ArrayList<>();
            db_agreements.forEach(it -> {
                try {
                    dentist_centre.add(it.getFirst_name() + " | " + it.getLast_name() + " | " + it.getName() + " | " + it.getLocation());
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            ObservableList<String> dentist_centre_observable = FXCollections.observableArrayList(dentist_centre);
            meeting_dentist_centre_choicebox.setItems(dentist_centre_observable);
        }
        catch (SQLException throwables) {
            field_error_lable = "Exception at SQL! Try Uniques!";
            fields_error(actionEvent);
            throwables.printStackTrace();
        }
    }

    public void delete_agreement(ActionEvent actionEvent) {
        field_error_lable = "Invalid selection for Delete";

        try {
            int selectedID = agreements_table_view.getSelectionModel().getSelectedIndex();

            Agreement old_agreement = agreements_table_view.getItems().get(selectedID);
            // then try to delete from table
            try {
                db.delete_agreement(old_agreement);
            }
            catch (SQLException throwables) {
                field_error_lable = "Exception at SQL! Try Uniques!";
                throwables.printStackTrace();
            }

            agreements_table_view.getItems().remove(selectedID);

            List<Agreement> db_agreements = db.fetch_agreements();
            List<String> dentist_centre = new ArrayList<>();
            db_agreements.forEach(it -> {
                try {
                    dentist_centre.add(it.getFirst_name() + " | " + it.getLast_name() + " | " + it.getName() + " | " + it.getLocation());
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            ObservableList<String> dentist_centre_observable = FXCollections.observableArrayList(dentist_centre);
            meeting_dentist_centre_choicebox.setItems(dentist_centre_observable);
        }
        catch (Exception e){
            fields_error(actionEvent);
        }
    }

    // Centres operations
    public void submit_centre(ActionEvent actionEvent) {
        field_error_lable = "No Error";

        Centre centre = new Centre(
                WordUtils.capitalizeFully(centre_name_input.getText(), ' ', '-'),
                WordUtils.capitalizeFully(centre_location_input.getText(), ' ', '-'),
                centre_phone_input.getText()
        );

        Integer id = centre.getId();
        String name = centre.getName();
        String location = centre.getLocation();
        String phone = centre.getPhone();

        if (!centre_fields_check(centre)) {
            fields_error(actionEvent);
            return;
        }

        // then try to introduce it into the database
        try {
            db.introduce_centre(name, location, phone);

            ObservableList<Centre> centres = centres_table_view.getItems();
            centres.add(centre);
            centres_table_view.setItems(centres);

            List<Centre> db_centres = db.fetch_centres();
            List<String> name_phone = new ArrayList<>();
            db_centres.forEach(it -> name_phone.add(it.getName() + " | " + it.getPhone()));
            ObservableList<String> name_phone_observable = FXCollections.observableArrayList(name_phone);
            agreement_centre_choicebox.setItems(name_phone_observable);

            List<Agreement> db_agreements = db.fetch_agreements();
            List<String> dentist_centre = new ArrayList<>();
            db_agreements.forEach(it -> {
                try {
                    dentist_centre.add(it.getFirst_name() + " | " + it.getLast_name() + " | " + it.getName() + " | " + it.getLocation());
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            ObservableList<String> dentist_centre_observable = FXCollections.observableArrayList(dentist_centre);
            meeting_dentist_centre_choicebox.setItems(dentist_centre_observable);
        }
        catch (SQLException throwables) {
            field_error_lable = "Exception at SQL! Try Uniques!";
            fields_error(actionEvent);
            throwables.printStackTrace();
        }
    }

    public void edit_centre(ActionEvent actionEvent) {
        field_error_lable = "No Error";

        Centre centre = new Centre(
                WordUtils.capitalizeFully(centre_name_input.getText(), ' ', '-'),
                WordUtils.capitalizeFully(centre_location_input.getText(), ' ', '-'),
                centre_phone_input.getText()
        );

        if (!centre_fields_check(centre)) {
            fields_error(actionEvent);
            return;
        }

        try{
            int selectedID = centres_table_view.getSelectionModel().getSelectedIndex();

            // before doing DB
            Centre old_centre = centres_table_view.getItems().get(selectedID);
            centre.setId(old_centre.getId());

            // then try to edit table
            try {
                db.change_centre(old_centre, centre);
                centres_table_view.getItems().set(selectedID, centre); // in interface

                List<Centre> db_centres = db.fetch_centres();
                List<String> name_phone = new ArrayList<>();
                db_centres.forEach(it -> name_phone.add(it.getName() + " | " + it.getPhone()));
                ObservableList<String> name_phone_observable = FXCollections.observableArrayList(name_phone);
                agreement_centre_choicebox.setItems(name_phone_observable);

                List<Agreement> db_agreements = db.fetch_agreements();
                List<String> dentist_centre = new ArrayList<>();
                db_agreements.forEach(it -> {
                    try {
                        dentist_centre.add(it.getFirst_name() + " | " + it.getLast_name() + " | " + it.getName() + " | " + it.getLocation());
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
                ObservableList<String> dentist_centre_observable = FXCollections.observableArrayList(dentist_centre);
                meeting_dentist_centre_choicebox.setItems(dentist_centre_observable);
            }
            catch (SQLException throwables) {
                field_error_lable = "Exception at SQL! Try Uniques!";
                fields_error(actionEvent);
                throwables.printStackTrace();
            }
        }
        catch (Exception e){
            field_error_lable = "Select something before editing";
            fields_error(actionEvent);
        }
    }

    public void delete_centre(ActionEvent actionEvent) {
        field_error_lable = "Invalid selection for Delete";

        try {
            int selectedID = centres_table_view.getSelectionModel().getSelectedIndex();

            Centre old_centre = centres_table_view.getItems().get(selectedID);
            // then try to delete from table
            try {
                db.delete_centre(old_centre);
            }
            catch (SQLException throwables) {
                field_error_lable = "Exception at SQL! Try Uniques!";
                throwables.printStackTrace();
            }

            centres_table_view.getItems().remove(selectedID);

            List<Centre> db_centres = db.fetch_centres();
            List<String> name_phone = new ArrayList<>();
            db_centres.forEach(it -> name_phone.add(it.getName() + " | " + it.getPhone()));
            ObservableList<String> name_phone_observable = FXCollections.observableArrayList(name_phone);
            agreement_centre_choicebox.setItems(name_phone_observable);

            List<Agreement> db_agreements = db.fetch_agreements();
            List<String> dentist_centre = new ArrayList<>();
            db_agreements.forEach(it -> {
                try {
                    dentist_centre.add(it.getFirst_name() + " | " + it.getLast_name() + " | " + it.getName() + " | " + it.getLocation());
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            ObservableList<String> dentist_centre_observable = FXCollections.observableArrayList(dentist_centre);
            meeting_dentist_centre_choicebox.setItems(dentist_centre_observable);

            update_agreements_table_view();
            update_meetings_table_view();
        }
        catch (Exception e){
            fields_error(actionEvent);
        }
    }

    private void update_agreements_table_view() throws SQLException {
        List<Agreement> db_agreements = db.fetch_agreements();
        ObservableList<Agreement> agreements = FXCollections.observableArrayList(db_agreements);
        agreements_table_view.setItems(agreements);
    }

    private void update_meetings_table_view() throws SQLException {
        List<Meeting> db_meetings = db.fetch_full_meetings();
        ObservableList<Meeting> meetings = FXCollections.observableArrayList(db_meetings);
        meetings_table_view.setItems(meetings);
    }

    private boolean centre_fields_check(Centre centre) {
        Integer id = centre.getId();
        String name = centre.getName();
        String location = centre.getLocation();
        String phone = centre.getPhone();

        field_error_lable = "No Error";
        if (name.equals("") || location.equals("") || phone.equals("")) {
            field_error_lable = "All Fields must be completed!";
            return false;
        }

        if (!name.matches("^[a-zA-Z -.:<>,';]*$")){
            field_error_lable = "Name Invalid Format";
            return false;
        }

        if (!(name.length() > 1 && name.length() <= 50)) {
            field_error_lable = "Name Length > 1 && <= 50!";
            return false;
        }

        if (!location.matches("^[a-zA-Z -]*$")){
            field_error_lable = "Location Invalid Format";
            return false;
        }

        if (!(location.length() > 1 && location.length() <= 50)) {
            field_error_lable = "Location Length > 1 && <= 50!";
            return false;
        }

        if (!(phone.length() == 10)) {
            field_error_lable = "Phone Length must be 10!";
            return false;
        }

        if (!phone.matches("^[0-9]*$")){
            field_error_lable = "Phone Invalid Format";
            return false;
        }

        return true;
    }

    // Dentist operations
    public void submit_dentist(ActionEvent actionEvent) {
        field_error_lable = "No Error";

        Dentist dentist = new Dentist(
                WordUtils.capitalizeFully(dentist_first_name_input.getText(), ' ', '-'),
                WordUtils.capitalizeFully(dentist_last_name_input.getText(), ' ', '-'),
                dentist_pid_input.getText(),
                WordUtils.capitalizeFully(dentist_specialization_input.getText(), ' ', '-'),
                WordUtils.capitalizeFully(dentist_qualification_input.getText(), ' ', '-'),
                0,
                dentist_email_input.getText()
        );

        try{
            Integer years = Integer.parseInt(dentist_years_experience_input.getText());
            dentist.setYears_experience(years);
        }
        catch (Exception e){

        }

        Integer id = dentist.getId();
        String first_name = dentist.getFirst_name();
        String last_name = dentist.getLast_name();
        String pid = dentist.getPid();
        String specialization = dentist.getSpecialization();
        String qualification = dentist.getQualification();
        Integer years_experience = dentist.getYears_experience();
        String email = dentist.getEmail();

        if (!dentist_fields_check(dentist)) {
            fields_error(actionEvent);
            return;
        }

        // then try to introduce it into the database
        try {
            db.introduce_dentist(first_name, last_name, pid, specialization, qualification, years_experience, email);

            ObservableList<Dentist> dentists = dentists_table_view.getItems();
            dentists.add(dentist);
            dentists_table_view.setItems(dentists);

            List<Dentist> db_dentists = db.fetch_dentists();
            List<String> full_name = new ArrayList<>();
            db_dentists.forEach(it -> full_name.add(it.getFirst_name() + " | " + it.getLast_name()));
            ObservableList<String> full_name_observable = FXCollections.observableArrayList(full_name);
            agreement_dentist_choicebox.setItems(full_name_observable);

            List<Agreement> db_agreements = db.fetch_agreements();
            List<String> dentist_centre = new ArrayList<>();
            db_agreements.forEach(it -> {
                try {
                    dentist_centre.add(it.getFirst_name() + " | " + it.getLast_name() + " | " + it.getName() + " | " + it.getLocation());
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            ObservableList<String> dentist_centre_observable = FXCollections.observableArrayList(dentist_centre);
            meeting_dentist_centre_choicebox.setItems(dentist_centre_observable);
        }
        catch (SQLException throwables) {
            field_error_lable = "Exception at SQL! Try Uniques!";
            fields_error(actionEvent);
            throwables.printStackTrace();
        }
    }

    public void edit_dentist(ActionEvent actionEvent) {
        field_error_lable = "No Error";

        Dentist dentist = new Dentist(
                WordUtils.capitalizeFully(dentist_first_name_input.getText(), ' ', '-'),
                WordUtils.capitalizeFully(dentist_last_name_input.getText(), ' ', '-'),
                dentist_pid_input.getText(),
                WordUtils.capitalizeFully(dentist_specialization_input.getText(), ' ', '-'),
                WordUtils.capitalizeFully(dentist_qualification_input.getText(), ' ', '-'),
                0,
                dentist_email_input.getText()
        );

        try{
            Integer years = Integer.parseInt(dentist_years_experience_input.getText());
            dentist.setYears_experience(years);
        }
        catch (Exception e){

        }

        if (!dentist_fields_check(dentist)) {
            fields_error(actionEvent);
            return;
        }

        try{
            int selectedID = dentists_table_view.getSelectionModel().getSelectedIndex();

            // before doing DB
            Dentist old_dentist = dentists_table_view.getItems().get(selectedID);
            dentist.setId(old_dentist.getId());

            // then try to edit table
            try {
                db.change_dentist(old_dentist, dentist);
                dentists_table_view.getItems().set(selectedID, dentist); // in interface

                List<Dentist> db_dentists = db.fetch_dentists();
                List<String> full_name = new ArrayList<>();
                db_dentists.forEach(it -> full_name.add(it.getFirst_name() + " | " + it.getLast_name()));
                ObservableList<String> full_name_observable = FXCollections.observableArrayList(full_name);
                agreement_dentist_choicebox.setItems(full_name_observable);

                List<Agreement> db_agreements = db.fetch_agreements();
                List<String> dentist_centre = new ArrayList<>();
                db_agreements.forEach(it -> {
                    try {
                        dentist_centre.add(it.getFirst_name() + " | " + it.getLast_name() + " | " + it.getName() + " | " + it.getLocation());
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
                ObservableList<String> dentist_centre_observable = FXCollections.observableArrayList(dentist_centre);
                meeting_dentist_centre_choicebox.setItems(dentist_centre_observable);
            }
            catch (SQLException throwables) {
                field_error_lable = "Exception at SQL! Try Uniques!";
                fields_error(actionEvent);
                throwables.printStackTrace();
            }
        }
        catch (Exception e){
            fields_error(actionEvent);
        }
    }

    public void delete_dentist(ActionEvent actionEvent) {
        field_error_lable = "Invalid selection for Delete";

        try {
            int selectedID = dentists_table_view.getSelectionModel().getSelectedIndex();

            Dentist old_dentist = dentists_table_view.getItems().get(selectedID);
            // then try to delete from table
            try {
                db.delete_dentist(old_dentist);
            }
            catch (SQLException throwables) {
                field_error_lable = "Exception at SQL! Try Uniques!";
                throwables.printStackTrace();
            }

            dentists_table_view.getItems().remove(selectedID);

            List<Dentist> db_dentists = db.fetch_dentists();
            List<String> full_name = new ArrayList<>();
            db_dentists.forEach(it -> full_name.add(it.getFirst_name() + " | " + it.getLast_name()));
            ObservableList<String> full_name_observable = FXCollections.observableArrayList(full_name);
            agreement_dentist_choicebox.setItems(full_name_observable);

            List<Agreement> db_agreements = db.fetch_agreements();
            List<String> dentist_centre = new ArrayList<>();
            db_agreements.forEach(it -> {
                try {
                    dentist_centre.add(it.getFirst_name() + " | " + it.getLast_name() + " | " + it.getName() + " | " + it.getLocation());
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            ObservableList<String> dentist_centre_observable = FXCollections.observableArrayList(dentist_centre);
            meeting_dentist_centre_choicebox.setItems(dentist_centre_observable);

            update_agreements_table_view();
            update_meetings_table_view();
        }
        catch (Exception e){
            fields_error(actionEvent);
        }
    }

    public boolean dentist_fields_check(Dentist dentist) {
        Integer id = dentist.getId();
        String first_name = dentist.getFirst_name();
        String last_name = dentist.getLast_name();
        String pid = dentist.getPid();
        String specialization = dentist.getSpecialization();
        String qualification = dentist.getQualification();
        Integer years_experience = dentist.getYears_experience();
        String email = dentist.getEmail();

        field_error_lable = "No Error";
        if (pid.length() != 13) {
            field_error_lable = "PID Must be 13 in Length!";
            return false;
        }
        if (first_name.equals("") || last_name.equals("") || specialization.equals("") || qualification.equals("") || years_experience.equals("")
                || email.equals("") || id.equals(0)) {
            field_error_lable = "All Fields must be completed!";
            return false;
        }

        if (years_experience == 0){
            field_error_lable = "Must be at least 1 year experienced";
            return false;
        }

        if (!specialization.matches("^[a-zA-Z -]*$")){
            field_error_lable = "Town Invalid Format";
            return false;
        }

        if (!(specialization.length() > 1 && specialization.length() <= 30)) {
            field_error_lable = "Specialization Length > 1 && <= 30!";
            return false;
        }

        if (!(qualification.length() > 1 && qualification.length() <= 30)) {
            field_error_lable = "Qualification Length > 1 && <= 30!";
            return false;
        }

        if (!qualification.matches("^[a-zA-Z -]*$")){
            field_error_lable = "Qualification Invalid Format";
            return false;
        }

        if (!(first_name.length() > 1 && first_name.length() <= 50)) {
            field_error_lable = "First Name Length > 1 && <= 50!";
            return false;
        }

        if (!first_name.matches("^[a-zA-Z -]*$")){
            field_error_lable = "First Name Invalid Format";
            return false;
        }

        if (!(last_name.length() > 1 && last_name.length() <= 50)){
            field_error_lable = "Last Name Length > 1 && <= 50!";
            return false;
        }

        if (!last_name.matches("^[a-zA-Z -]*$")){
            field_error_lable = "Last Name Invalid Format";
            return false;
        }

        if (!(email.length() <= 75)){
            field_error_lable = "Email Length <= 75!";
            return false;
        }

        if (!email.matches("[a-z0-9._%-]+@[a-z0-9._%-]+\\.[a-z]{2,4}")){
            field_error_lable = "Email Invalid Format";
            return false;
        }

        return true;
    }

    // Client Operations
    public void submit_client(ActionEvent actionEvent) {
        field_error_lable = "No Error";

        Client client = new Client(
                WordUtils.capitalizeFully(client_first_name_input.getText(), ' ', '-'),
                WordUtils.capitalizeFully(client_last_name_input.getText(), ' ', '-'),
                client_pid_input.getText(),
                client_birth_date_input.getText(),
                WordUtils.capitalizeFully(client_town_input.getText(), ' ', '-'),
                WordUtils.capitalizeFully(client_country_input.getText(), ' ', '-'),
                client_email_input.getText()
        );

        Integer id = client.getId();
        String first_name = client.getFirst_name();
        String last_name = client.getLast_name();
        String pid = client.getPid();
        String birth_date = client.getBirth_date();
        String town = client.getTown();
        String country = client.getCountry();
        String email = client.getEmail();

        if (!client_fields_check(client)) {
            fields_error(actionEvent);
            return;
        }

        // then try to introduce it into the database
        try {
            db.introduce_client(first_name, last_name, pid, birth_date, town, country, email);

            ObservableList<Client> clients = clients_table_view.getItems();
            clients.add(client);
            clients_table_view.setItems(clients);

            List<Client> db_clients = db.fetch_clients();
            List<String> full_name_email = new ArrayList<>();
            db_clients.forEach(it -> full_name_email.add(it.getFirst_name() + " | " + it.getLast_name() + " | " + it.getEmail()));
            ObservableList<String> full_name_email_observable = FXCollections.observableArrayList(full_name_email);
            meeting_client_choicebox.setItems(full_name_email_observable);
        }
        catch (SQLException throwables) {
            field_error_lable = "Exception at SQL! Try Uniques!";
            fields_error(actionEvent);
            throwables.printStackTrace();
        }

    }

    public boolean client_fields_check(Client client) {
        Integer id = client.getId();
        String first_name = client.getFirst_name();
        String last_name = client.getLast_name();
        String pid = client.getPid();
        String birth_date = client.getBirth_date();
        String town = client.getTown();
        String country = client.getCountry();
        String email = client.getEmail();

        field_error_lable = "No Error";
        if (pid.length() != 13) {
            field_error_lable = "PID Must be 13 in Length!";
            return false;
        }
        if (first_name.equals("") || last_name.equals("") || birth_date.equals("") || town.equals("") || country.equals("")
                || email.equals("") || id.equals(0)) {
            field_error_lable = "All Fields must be completed!";
            return false;
        }
        System.out.println(birth_date);

        // regex for 2017-12-31
        // String regex = "^((19|20)[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
        // regex for 31.12.2017
        String regex = "^(0[1-9]|[12][0-9]|3[01]).(0[1-9]|1[012]).((19|20)[0-9]{2})$";
        if (!birth_date.matches(regex)) {
            field_error_lable = "Birth Date Invalid Format";
            return false;
        }

//        String formated_date = human_to_computer_date(birth_date);
//        if (!(formated_date.compareTo("1900-01-01") > 0 && formated_date.compareTo("2021-11-18") < 0))
//            field_error_lable = "Birth Date not Valid!";

        if (!town.matches("^[a-zA-Z -]*$")){
            field_error_lable = "Town Invalid Format";
            return false;
        }

        if (!(town.length() > 1 && town.length() <= 50)) {
            field_error_lable = "Town Length > 1 && <= 50!";
            return false;
        }

        if (!(country.length() > 1 && country.length() <= 50)) {
            field_error_lable = "Country Length > 1 && <= 50!";
            return false;
        }

        if (!(first_name.length() > 1 && first_name.length() <= 50)) {
            field_error_lable = "First Name Length > 1 && <= 50!";
            return false;
        }

        if (!(last_name.length() > 1 && last_name.length() <= 50)){
            field_error_lable = "Last Name Length > 1 && <= 50!";
            return false;
        }

        if (!first_name.matches("^[a-zA-Z -]*$")){
            field_error_lable = "First Name Invalid Format";
            return false;
        }

        if (!last_name.matches("^[a-zA-Z -]*$")){
            field_error_lable = "Last Name Invalid Format";
            return false;
        }

        if (!country.matches("^[a-zA-Z -]*$")){
            field_error_lable = "Country Invalid Format";
            return false;
        }

        if (!(email.length() <= 75)){
            field_error_lable = "Email Length <= 75!";
            return false;
        }

        if (!email.matches("[a-z0-9._%-]+@[a-z0-9._%-]+\\.[a-z]{2,4}")){
            field_error_lable = "Email Invalid Format";
            return false;
        }

        return true;
    }

    public void edit_client(ActionEvent actionEvent) {
        field_error_lable = "No Error";

        Client client = new Client(
                WordUtils.capitalizeFully(client_first_name_input.getText(), ' ', '-'),
                WordUtils.capitalizeFully(client_last_name_input.getText(), ' ', '-'),
                client_pid_input.getText(),
                client_birth_date_input.getText(),
                WordUtils.capitalizeFully(client_town_input.getText(), ' ', '-'),
                WordUtils.capitalizeFully(client_country_input.getText(), ' ', '-'),
                client_email_input.getText()
        );

        if (!client_fields_check(client)) {
            fields_error(actionEvent);
            return;
        }

        try{
            int selectedID = clients_table_view.getSelectionModel().getSelectedIndex();

            // before doing DB
            Client old_client = clients_table_view.getItems().get(selectedID);
            client.setId(old_client.getId());

            // then try to edit table
            try {
                db.change_client(old_client, client);
                clients_table_view.getItems().set(selectedID, client); // in interface

                List<Client> db_clients = db.fetch_clients();
                List<String> full_name_email = new ArrayList<>();
                db_clients.forEach(it -> full_name_email.add(it.getFirst_name() + " | " + it.getLast_name() + " | " + it.getEmail()));
                ObservableList<String> full_name_email_observable = FXCollections.observableArrayList(full_name_email);
                meeting_client_choicebox.setItems(full_name_email_observable);
            }
            catch (SQLException throwables) {
                field_error_lable = "Exception at SQL! Try Uniques!";
                fields_error(actionEvent);
                throwables.printStackTrace();
            }
        }
        catch (Exception e){
            fields_error(actionEvent);
        }
    }

    public void delete_client(ActionEvent actionEvent) {
        field_error_lable = "Invalid selection for Delete";

        try {
            int selectedID = clients_table_view.getSelectionModel().getSelectedIndex();

            Client old_client = clients_table_view.getItems().get(selectedID);
            // then try to delete from table
            try {
                db.delete_client(old_client);
            }
            catch (SQLException throwables) {
                field_error_lable = "Exception at SQL! Try Uniques!";
                throwables.printStackTrace();
            }

            clients_table_view.getItems().remove(selectedID);

            List<Client> db_clients = db.fetch_clients();
            List<String> full_name_email = new ArrayList<>();
            db_clients.forEach(it -> full_name_email.add(it.getFirst_name() + " | " + it.getLast_name() + " | " + it.getEmail()));
            ObservableList<String> full_name_email_observable = FXCollections.observableArrayList(full_name_email);
            meeting_client_choicebox.setItems(full_name_email_observable);

            update_meetings_table_view();
        }
        catch (Exception e){
            fields_error(actionEvent);
        }
    }

    /** Error function that open new scenes
     */
    public void fields_error(ActionEvent actionEvent) {
        initializer_type = "Field Error";

        Parent root;
        try {
            root = FXMLLoader.load(Controller.class.getResource("fields_error.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Field Error");
            stage.setScene(new Scene(root, 250, 150));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int my_important_meeting_id;
    public static Meeting my_important_old_meeting;
    public static TableView<Meeting> meetings_table_view2;
    public void commit_rollback(ActionEvent actionEvent, int selectedID, Meeting old_meeting, Meeting meeting) {
        initializer_type = "Yes_No";
        my_important_old_meeting = old_meeting;
        my_important_meeting_id = selectedID;

        Parent root;
        try {
            root = FXMLLoader.load(Controller.class.getResource("yes_no.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Warning for Meetings");
            stage.setScene(new Scene(root, 250, 150));

            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close_stage_commit(ActionEvent actionEvent) throws SQLException {
        db.connection.commit();
        db.connection.setAutoCommit(true);
        commit_rollback_value = true;

        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public void close_stage_rollback(ActionEvent actionEvent) throws SQLException {
        db.connection.rollback();
        db.connection.setAutoCommit(true);
        commit_rollback_value = false;

        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}