package sample;

// agreements between centre and dentist -> used with clients to get meetings
public class Meeting {
    static private Integer counter = 0;
    private Integer id;
    private String client_first_name;
    private String client_last_name;
    private String centre_name;
    private String centre_location;
    private String dentist_first_name;
    private String dentist_last_name;
    private String start_time;
    private String end_time;
    private String problem;

    public Meeting(String client_first_name, String client_last_name, String centre_name, String centre_location, String dentist_first_name, String dentist_last_name, String start_time, String end_time, String problem) {
        ++counter;
        this.id = counter;
        this.client_first_name = client_first_name;
        this.client_last_name = client_last_name;
        this.centre_name = centre_name;
        this.centre_location = centre_location;
        this.dentist_first_name = dentist_first_name;
        this.dentist_last_name = dentist_last_name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.problem = problem;
    }

    public Meeting(Integer id, String client_first_name, String client_last_name, String centre_name, String centre_location, String dentist_first_name, String dentist_last_name, String start_time, String end_time, String problem) {
        ++counter;
        this.id = id;
        this.client_first_name = client_first_name;
        this.client_last_name = client_last_name;
        this.centre_name = centre_name;
        this.centre_location = centre_location;
        this.dentist_first_name = dentist_first_name;
        this.dentist_last_name = dentist_last_name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.problem = problem;
    }

    public Meeting getMeeting(){
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClient_first_name() {
        return client_first_name;
    }

    public void setClient_first_name(String client_first_name) {
        this.client_first_name = client_first_name;
    }

    public String getClient_last_name() {
        return client_last_name;
    }

    public void setClient_last_name(String client_last_name) {
        this.client_last_name = client_last_name;
    }

    public String getCentre_location() {
        return centre_location;
    }

    public void setCentre_location(String centre_location) {
        this.centre_location = centre_location;
    }

    public String getCentre_name() {
        return centre_name;
    }

    public void setCentre_name(String centre_name) {
        this.centre_name = centre_name;
    }

    public void setDentist_first_name(String dentist_first_name) {
        this.dentist_first_name = dentist_first_name;
    }

    public void setDentist_last_name(String dentist_last_name) {
        this.dentist_last_name = dentist_last_name;
    }

    public String getDentist_first_name() {
        return dentist_first_name;
    }

    public String getDentist_last_name() {
        return dentist_last_name;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getProblem() {
        return problem;
    }
}
