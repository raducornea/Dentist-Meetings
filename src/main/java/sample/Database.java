package sample;

import java.sql.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public Connection connection;
    public Statement statement;

    static public String computer_to_human_date(String date){
        return "" + date.charAt(8) + date.charAt(9) + "." + date.charAt(5) + date.charAt(6) + "." + date.charAt(0) + date.charAt(1) + date.charAt(2) + date.charAt(3);
    }
    static public String human_to_computer_date(String date){
        return "" + date.charAt(6) + date.charAt(7) + date.charAt(8) + date.charAt(9) + "-" + date.charAt(3) + date.charAt(4) + "-" + date.charAt(0) + date.charAt(1);
    }

    // Meeting Methods
    public void change_meeting(Meeting old_meeting, Meeting meeting) throws SQLException {
        // client_id
        ResultSet result = statement.executeQuery("select client_id from clients where " +
                "client_first_name = '" + old_meeting.getClient_first_name() + "' and client_last_name = '" + old_meeting.getClient_last_name() + "'");
        ResultSetMetaData mydata = result.getMetaData(); // aparent linia asta este salvatoare... ok...
        int client_id = 0;
        while (result.next()) {
            client_id = result.getInt(1);
        }

        // get agreement id by centre_id + dentist_id
        result = statement.executeQuery("select centre_id from centres where " +
                "centre_name = '" + old_meeting.getCentre_name() + "' and centre_location = '" + old_meeting.getCentre_location() + "'");
        mydata = result.getMetaData(); // aparent linia asta este salvatoare... ok...
        int centre_id = 0;
        while (result.next()) {
            centre_id = result.getInt(1);
        }

        result = statement.executeQuery("select dentist_id from dentists where " +
                "dentist_first_name = '" + old_meeting.getDentist_first_name() + "' and dentist_last_name = '" + old_meeting.getDentist_last_name() + "'");
        mydata = result.getMetaData(); // aparent linia asta este salvatoare... ok...
        int dentist_id = 0;
        while (result.next()) {
            dentist_id = result.getInt(1);
        }

        // agreement_id
        result = statement.executeQuery("" +
                "select a.agreement_id\n" +
                "from agreements a, dentists d, centres c\n" +
                "where a.dentists_dentist_id = " + dentist_id + " and a.centres_centre_id = " + centre_id + "");
        mydata = result.getMetaData(); // aparent linia asta este salvatoare... ok...
        int agreement_id = 0;
        while (result.next()) {
            agreement_id = result.getInt(1);
        }

        connection.setAutoCommit(false);

        String start_time = meeting.getStart_time();
        String end_time = meeting.getEnd_time();
        // edit the targeted meeting
        result = statement.executeQuery("update meetings\n" +
                "set meeting_start_time = TO_DATE('" + start_time + "', 'dd.mm.yyyy HH24:MI'),\n" +
                "        meeting_end_time = TO_DATE('" + end_time + "', 'dd.mm.yyyy HH24:MI'),\n" +
                "        meeting_problem = '" + meeting.getProblem() + "'\n" +
                "where meeting_start_time = TO_DATE('" + old_meeting.getStart_time() + "', 'dd.mm.yyyy HH24:MI')\n" +
                "and meeting_end_time = TO_DATE('" + old_meeting.getEnd_time() + "', 'dd.mm.yyyy HH24:MI')\n" +
                "and meeting_problem = '" + old_meeting.getProblem() +  "'\n" +
                "and clients_client_id = " + client_id + "\n" +
                "and agreements_agreement_id = " + agreement_id);
    }

    public void delete_meeting(Meeting meeting) throws SQLException{
        // client_id
        ResultSet result = statement.executeQuery("select client_id from clients where " +
                "client_first_name = '" + meeting.getClient_first_name() + "' and client_last_name = '" + meeting.getClient_last_name() + "'");
        ResultSetMetaData mydata = result.getMetaData(); // aparent linia asta este salvatoare... ok...
        int client_id = 0;
        while (result.next()) {
            client_id = result.getInt(1);
        }

        // get agreement id by centre_id + dentist_id
        result = statement.executeQuery("select centre_id from centres where " +
                "centre_name = '" + meeting.getCentre_name() + "' and centre_location = '" + meeting.getCentre_location() + "'");
        mydata = result.getMetaData(); // aparent linia asta este salvatoare... ok...
        int centre_id = 0;
        while (result.next()) {
            centre_id = result.getInt(1);
        }

        result = statement.executeQuery("select dentist_id from dentists where " +
                "dentist_first_name = '" + meeting.getDentist_first_name() + "' and dentist_last_name = '" + meeting.getDentist_last_name() + "'");
        mydata = result.getMetaData(); // aparent linia asta este salvatoare... ok...
        int dentist_id = 0;
        while (result.next()) {
            dentist_id = result.getInt(1);
        }

        // agreement_id
        result = statement.executeQuery("" +
                "select a.agreement_id\n" +
                "from agreements a, dentists d, centres c\n" +
                "where a.dentists_dentist_id = " + dentist_id + " and a.centres_centre_id = " + centre_id + "");
        mydata = result.getMetaData(); // aparent linia asta este salvatoare... ok...
        int agreement_id = 0;
        while (result.next()) {
            agreement_id = result.getInt(1);
        }

        statement.executeQuery("delete from meetings " +
                "where MEETING_START_TIME = TO_DATE('" + meeting.getStart_time() + "', 'dd.mm.yyyy HH24:MI') " +
                "and MEETING_END_TIME = TO_DATE('" + meeting.getEnd_time() + "', 'dd.mm.yyyy HH24:MI') " +
                "and MEETING_PROBLEM = '" + meeting.getProblem() + "' " +
                "and CLIENTS_CLIENT_ID = " + client_id + " " +
                "and AGREEMENTS_AGREEMENT_ID = " + agreement_id + " ");
    }

    public boolean introduce_meeting(String client_first_name, String client_last_name, String dentist_first_name, String dentist_last_name, String name, String location, String start_time, String end_time, String problem)  throws SQLException {
        // client_id
        ResultSet result = statement.executeQuery("select client_id from clients where " +
                "client_first_name = '" + client_first_name + "' and client_last_name = '" + client_last_name + "'");
        ResultSetMetaData mydata = result.getMetaData(); // aparent linia asta este salvatoare... ok...
        int client_id = 0;
        while (result.next()) {
            client_id = result.getInt(1);
        }

        // get agreement id by centre_id + dentist_id
        result = statement.executeQuery("select centre_id from centres where " +
                "centre_name = '" + name + "' and centre_location = '" + location + "'");
        mydata = result.getMetaData(); // aparent linia asta este salvatoare... ok...
        int centre_id = 0;
        while (result.next()) {
            centre_id = result.getInt(1);
        }

        result = statement.executeQuery("select dentist_id from dentists where " +
                "dentist_first_name = '" + dentist_first_name + "' and dentist_last_name = '" + dentist_last_name + "'");
        mydata = result.getMetaData(); // aparent linia asta este salvatoare... ok...
        int dentist_id = 0;
        while (result.next()) {
            dentist_id = result.getInt(1);
        }

        // agreement_id
        result = statement.executeQuery("" +
                "select a.agreement_id\n" +
                "from agreements a, dentists d, centres c\n" +
                "where a.dentists_dentist_id = d.dentist_id and a.centres_centre_id = c.centre_id");
        mydata = result.getMetaData(); // aparent linia asta este salvatoare... ok...
        int agreement_id = 0;
        while (result.next()) {
            agreement_id = result.getInt(1);
        }

        // incerci sa inserezi in intervale orare similare (trebuie verificat 1. daca clientul poate, 2. daca pe contract (cu dentistul) se poate)
        // se poate doar in 2 cazuri: (start & end) < TOATE meeting-urile SAU (start & end) > TOATE meeting-urile -> atat pentru client, cat si contract
        result = statement.executeQuery("select m.meeting_start_time, m.meeting_end_time, m.clients_client_id, m.agreements_agreement_id\n" +
                "        from meetings m\n" +
                "\n" +
                "        where (m.clients_client_id = " + client_id + "\n" +
                "                and not((TO_DATE('" + start_time + "', 'dd.mm.yyyy HH24:MI') < m.meeting_start_time AND TO_DATE('" + end_time + "', 'dd.mm.yyyy HH24:MI') < m.meeting_end_time) or\n" +
                "                        (TO_DATE('" + start_time + "', 'dd.mm.yyyy HH24:MI') > m.meeting_start_time AND TO_DATE('" + end_time + "', 'dd.mm.yyyy HH24:MI') > m.meeting_end_time))\n" +
                "                )\n" +
                "\n" +
                "        or (m.agreements_agreement_id = " + agreement_id + "\n" +
                "                and not((TO_DATE('" + start_time + "', 'dd.mm.yyyy HH24:MI') < m.meeting_start_time AND TO_DATE('" + end_time + "', 'dd.mm.yyyy HH24:MI') < m.meeting_end_time) or\n" +
                "                        (TO_DATE('" + start_time + "', 'dd.mm.yyyy HH24:MI') > m.meeting_start_time AND TO_DATE('" + end_time + "', 'dd.mm.yyyy HH24:MI') > m.meeting_end_time))\n" +
                "                )");
        mydata = result.getMetaData(); // aparent linia asta este salvatoare... ok...
        int counter = 0;
        while (result.next()) {
            counter++;
        }

        if(counter == 0){
            statement.executeQuery("insert into meetings(meeting_start_time, meeting_end_time, meeting_problem, clients_client_id, agreements_agreement_id)\n" +
                    "values(TO_DATE('" + start_time + "', 'dd.mm.yyyy HH24:MI'), TO_DATE('" + end_time + "', 'dd.mm.yyyy HH24:MI'), '" + problem + "', " + client_id + ", " + agreement_id + ")");
            return true;
        }
        else{
            return false;
        }
    }

    public List<Meeting> fetch_full_meetings() throws SQLException {
        List<Meeting> meetings = new ArrayList<>();

        // Careful -> in table we can only get the ID of the Dentist and ID of the Centre
        // -> we will select from THREE tables (Agreement (ID), Dentist (First + Last Name), Centre (Name + Phone))
        ResultSet result = statement.executeQuery("" +
                "with my_clients as (select client_first_name, client_last_name, client_id from clients), \n" +
                "my_agreements as (select * from agreements)\n" +
                "select cl.client_first_name, cl.client_last_name, cntr.centre_name, cntr.centre_location, dents.dentist_first_name, dents. dentist_last_name, (TO_CHAR(m.meeting_start_time, 'dd.mm.yyyy HH24:MI')), (TO_CHAR(m.meeting_end_time, 'dd.mm.yyyy HH24:MI')), m.meeting_problem\n" +
                "from meetings m, my_clients cl, my_agreements agr, (select centre_id, centre_name, centre_location from centres) cntr, (select dentist_id, dentist_first_name, dentist_last_name from dentists) dents\n" +
                "where cl.client_id = m.clients_client_id\n" +
                "and agr.agreement_id = m.agreements_agreement_id\n" +
                "and cntr.centre_id = agr.centres_centre_id\n" +
                "and dents.dentist_id = agr.dentists_dentist_id");
        ResultSetMetaData mydata = result.getMetaData();

        while (result.next()){
//            System.out.println(mydata.getColumnName(1) + ": " + result.getString(1));
//            System.out.println(mydata.getColumnName(2) + ": " + result.getString(2));
//            System.out.println(mydata.getColumnName(3) + ": " + result.getString(3));
//            System.out.println(mydata.getColumnName(4) + ": " + result.getString(4));
//            System.out.println(mydata.getColumnName(5) + ": " + result.getString(5));
//            System.out.println(mydata.getColumnName(6) + ": " + result.getString(6));
//            System.out.println(mydata.getColumnName(7) + ": " + result.getString(7));
//            System.out.println(mydata.getColumnName(8) + ": " + result.getString(8));
//            System.out.println(mydata.getColumnName(9) + ": " + result.getString(9));

            Meeting meeting = new Meeting(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6),
                    result.getString(7),
                    result.getString(8),
                    result.getString(9)
            );
            meetings.add(meeting);

//            System.out.print("*".repeat(50));
//            System.out.println();
        }

        return meetings;
    }

    // Agreement Methods
    public void delete_agreement(Agreement agreement) throws SQLException {
        ResultSet result = statement.executeQuery("select dentist_id from dentists where " +
                "dentist_first_name = '" + agreement.getFirst_name() + "' and dentist_last_name = '" + agreement.getLast_name() + "'");
        ResultSetMetaData mydata = result.getMetaData(); // aparent linia asta este salvatoare... ok...
        int dentist_id = 0;
        while (result.next()) {
            dentist_id = result.getInt(1);
        }

        result = statement.executeQuery("select centre_id from centres where " +
                "centre_name = '" + agreement.getName() + "' and centre_phone = '" + agreement.getPhone() + "'");
        mydata = result.getMetaData(); // aparent linia asta este salvatoare... ok...
        int centre_id = 0;
        while (result.next()) {
            centre_id = result.getInt(1);
        }

        statement.executeQuery("delete from agreements " +
                "where dentists_dentist_id = " + dentist_id + " " +
                "and centres_centre_id = " + centre_id);
    }

    public String get_location_by_phone(String phone) throws SQLException {
        ResultSet result = statement.executeQuery("select centre_location \n" +
                "from centres\n" +
                "where centre_phone = '" + phone +"'");
        ResultSetMetaData mydata = result.getMetaData();

        String location = "";
        while (result.next()) {
            location = result.getString(1);
        }

        return location;
    }

    public List<Agreement> fetch_agreements() throws SQLException {
        List<Agreement> agreements = new ArrayList<>();

        // Careful -> in table we can only get the ID of the Dentist and ID of the Centre
        // -> we will select from THREE tables (Agreement (ID), Dentist (First + Last Name), Centre (Name + Phone))
        ResultSet result = statement.executeQuery("" +
                "select a.agreement_id, d.dentist_first_name, d.dentist_last_name, c.centre_name, c.centre_phone, c.centre_location " +
                "from agreements a, dentists d, centres c " +
                "where a.dentists_dentist_id = d.dentist_id and a.centres_centre_id = c.centre_id");
        ResultSetMetaData mydata = result.getMetaData();

        while (result.next()){
//            System.out.println(mydata.getColumnName(1) + ": " + result.getInt(1));
//            System.out.println(mydata.getColumnName(2) + ": " + result.getString(2));
//            System.out.println(mydata.getColumnName(3) + ": " + result.getString(3));
//            System.out.println(mydata.getColumnName(4) + ": " + result.getString(4));
//            System.out.println(mydata.getColumnName(5) + ": " + result.getString(5));

            Agreement centre = new Agreement(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)
            );
            agreements.add(centre);

//            System.out.print("*".repeat(50));
//            System.out.println();
        }

        return agreements;
    }

    public void introduce_agreement(String first_name, String last_name, String name, String phone) throws SQLException {
        ResultSet result = statement.executeQuery("select dentist_id from dentists where " +
                "dentist_first_name = '" + first_name + "' and dentist_last_name = '" + last_name + "'");
        ResultSetMetaData mydata = result.getMetaData(); // aparent linia asta este salvatoare... ok...
        int dentist_id = 0;
        while (result.next()) {
            dentist_id = result.getInt(1);
        }

        result = statement.executeQuery("select centre_id from centres where " +
                "centre_name = '" + name + "' and centre_phone = '" + phone + "'");
        mydata = result.getMetaData(); // aparent linia asta este salvatoare... ok...
        int centre_id = 0;
        while (result.next()) {
            centre_id = result.getInt(1);
        }

        statement.executeQuery("insert into agreements(dentists_dentist_id, centres_centre_id) values(" + dentist_id + ", " + centre_id + ")");
    }

    // Centre Methods
    public List<Centre> fetch_centres() throws SQLException {
        List<Centre> centres = new ArrayList<>();

        ResultSet result = statement.executeQuery("select * from centres");
        ResultSetMetaData mydata = result.getMetaData();

        while (result.next()){
//            System.out.println(mydata.getColumnName(1) + ": " + result.getInt(1));
//            System.out.println(mydata.getColumnName(2) + ": " + result.getString(2));
//            System.out.println(mydata.getColumnName(3) + ": " + result.getString(3));
//            System.out.println(mydata.getColumnName(4) + ": " + result.getString(4));

            Centre centre = new Centre(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            );
            centres.add(centre);

//            System.out.print("*".repeat(50));
//            System.out.println();
        }

        return centres;
    }

    public void introduce_centre(String name, String location, String phone) throws SQLException {
        statement.executeQuery("insert into centres(centre_name, centre_location, centre_phone) values('" + name + "', '" + location + "', '" + phone + "')");
    }

    public void change_centre(Centre old_centre, Centre centre) throws SQLException {
        statement.executeQuery("update centres " +
                "set centre_id = " + centre.getId() + ", " +
                "centre_name = '" + centre.getName() + "', " +
                "centre_location = '" + centre.getLocation() + "', " +
                "centre_phone = '" + centre.getPhone() + "' " +
                "WHERE centre_id = " + old_centre.getId());
    }

    public void delete_centre(Centre centre) throws SQLException {
        statement.executeQuery("delete from centres " +
                "where " +
                "centre_phone = '" + centre.getPhone() + "' "
        );
    }

    // Dentists Methods
    public void delete_dentist(Dentist dentist) throws SQLException {
        statement.executeQuery("delete from dentists " +
                "where dentist_first_name = '" + dentist.getFirst_name() + "' " +
                "and dentist_last_name = '" + dentist.getLast_name() + "' ");
    }

    public void change_dentist(Dentist old_dentist, Dentist dentist) throws SQLException {
        statement.executeQuery("update dentists " +
                "set dentist_id = " + dentist.getId() + ", " +
                "dentist_first_name = '" + dentist.getFirst_name() + "', " +
                "dentist_last_name = '" + dentist.getLast_name() + "' " +
                "WHERE dentist_id = " + old_dentist.getId());
        statement.executeQuery("update dentist_details " +
                "set pid = '" + dentist.getPid() + "', " +
                "dentist_specialization = '" + dentist.getSpecialization() + "', " +
                "dentist_qualification = '" + dentist.getQualification() + "', " +
                "dentist_years_experience = " + dentist.getYears_experience() + ", " +
                "email = '" + dentist.getEmail() + "', " +
                "dentists_dentist_id = " + dentist.getId() + " " +
                "WHERE dentists_dentist_id = " + old_dentist.getId());
    }

    public void introduce_dentist(String first_name, String last_name, String pid, String specialization, String qualification, Integer years_experience, String email) throws SQLException {
        statement.executeQuery("insert into dentists(dentist_first_name, dentist_last_name) values('" + first_name + "', '" + last_name + "')");

        ResultSet result = statement.executeQuery("select dentist_id from dentists where " +
                "dentist_first_name = '" + first_name + "' and dentist_last_name = '" + last_name + "'");
        ResultSetMetaData mydata = result.getMetaData(); // aparent linia asta este salvatoare... ok...

        int id = 0;
        while (result.next()) {
            id = result.getInt(1);
        }

        statement.executeQuery("insert into dentist_details(pid, dentist_specialization, dentist_qualification, dentist_years_experience, email, dentists_dentist_id) " +
                "values('" + pid + "', '" + specialization + "', '" + qualification + "', " + years_experience + ", '" + email + "', " + id +")");
    }

    public List<Dentist> fetch_dentists() throws SQLException {
        List<Dentist> dentists = new ArrayList<>();

        ResultSet result = statement.executeQuery("select * from dentists a, dentist_details b " +
                "where a.dentist_id = b.dentists_dentist_id");
        ResultSetMetaData mydata = result.getMetaData();

        while (result.next()){
//            System.out.println(mydata.getColumnName(1) + ": " + result.getInt(1));
//            System.out.println(mydata.getColumnName(2) + ": " + result.getString(2));
//            System.out.println(mydata.getColumnName(3) + ": " + result.getString(3));
//            System.out.println(mydata.getColumnName(4) + ": " + result.getString(4));
//            System.out.println(mydata.getColumnName(5) + ": " + result.getString(5));
//            System.out.println(mydata.getColumnName(6) + ": " + result.getString(6));
//            System.out.println(mydata.getColumnName(7) + ": " + result.getInt(7));
//            System.out.println(mydata.getColumnName(8) + ": " + result.getString(8));

            Dentist dentist = new Dentist(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6),
                    result.getInt(7),
                    result.getString(8)
            );
            dentists.add(dentist);

//            System.out.print("*".repeat(50));
//            System.out.println();
        }

        return dentists;
    }

    // Client Methods
    public void introduce_client(String first_name, String last_name, String pid, String birth_date, String town, String country, String email) throws SQLException {
        statement.executeQuery("insert into clients(client_first_name, client_last_name) values('" + first_name + "', '" + last_name + "')");

        ResultSet result = statement.executeQuery("select client_id from clients where " +
                "client_first_name = '" + first_name + "' and client_last_name = '" + last_name + "'");
        ResultSetMetaData mydata = result.getMetaData(); // aparent linia asta este salvatoare... ok...

        int id = 0;
        while (result.next()) {
            id = result.getInt(1);
        }

        statement.executeQuery("insert into client_details(pid, client_birth_date, client_town, client_country, email, clients_client_id) " +
                "values('" + pid + "', TO_DATE('" + birth_date + "', 'dd.mm.yyyy'), '" + town + "', '" + country + "', '" + email + "', " + id +")");
    }

    public void change_client(Client old_client, Client client) throws SQLException {
        statement.executeQuery("update clients " +
                "set client_id = " + client.getId() + ", " +
                "client_first_name = '" + client.getFirst_name() + "', " +
                "client_last_name = '" + client.getLast_name() + "' " +
                "WHERE client_id = " + old_client.getId());
        statement.executeQuery("update client_details " +
                "set pid = '" + client.getPid() + "', " +
                "client_birth_date = TO_DATE('" + client.getBirth_date() + "', 'dd.mm.yyyy'), " +
                "client_town = '" + client.getTown() + "', " +
                "client_country = '" + client.getCountry() + "', " +
                "email = '" + client.getEmail() + "', " +
                "clients_client_id = " + client.getId() + " " +
                "WHERE clients_client_id = " + old_client.getId());
    }

    public void delete_client(Client client) throws SQLException {
        statement.executeQuery("delete from clients " +
                "where client_first_name = '" + client.getFirst_name() + "' " +
                "and client_last_name = '" + client.getLast_name() + "' ");
    }

    public List<Client> fetch_clients() throws SQLException {
        List<Client> clients = new ArrayList<>();

        ResultSet result = statement.executeQuery("select * from clients a, client_details b " +
                "where a.client_id = b.clients_client_id");
        ResultSetMetaData mydata = result.getMetaData();

        while (result.next()){
            String date = result.getString(5);
            String new_date = computer_to_human_date(date);
//            System.out.println(new_date);

//            System.out.println(mydata.getColumnName(1) + ": " + result.getInt(1));
//            System.out.println(mydata.getColumnName(2) + ": " + result.getString(2));
//            System.out.println(mydata.getColumnName(3) + ": " + result.getString(3));
//            System.out.println(mydata.getColumnName(4) + ": " + result.getString(4));
//            System.out.println(mydata.getColumnName(5) + ": " + new_date);
//            System.out.println(mydata.getColumnName(6) + ": " + result.getString(6));
//            System.out.println(mydata.getColumnName(7) + ": " + result.getString(7));
//            System.out.println(mydata.getColumnName(8) + ": " + result.getString(8));

            Client client = new Client(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    new_date,
                    result.getString(6),
                    result.getString(7),
                    result.getString(8)
            );
            clients.add(client);

//            System.out.print("*".repeat(50));
//            System.out.println();
        }

        return clients;
    }

    public void establish_connection() throws ClassNotFoundException, SQLException {
        // class driver
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // connection object // url // user // password - they are secret :P
        connection = DriverManager.getConnection("",
                "", "");

        // create statement
        statement = connection.createStatement();
    }

    public void about(){
        System.out.println("This project is about making Appointments to Dentist and do operations of this kind.");
    }

    public void close_connection() throws SQLException {
        connection.close();
    }
}
