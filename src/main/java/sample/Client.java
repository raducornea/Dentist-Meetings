package sample;

public class Client {
    static private Integer counter = 0;
    private Integer id;
    private String first_name;
    private String last_name;
    private String pid;
    private String birth_date;
    private String town;
    private String country;
    private String email;

    public Client(String first_name, String last_name,
                  String pid, String birth_date, String town, String country, String email) {
        ++counter;
        this.id = counter;
        this.first_name = first_name;
        this.last_name = last_name;
        this.pid = pid;
        this.birth_date = birth_date;
        this.town = town;
        this.country = country;
        this.email = email;
    }

    public Client(Integer id, String first_name, String last_name,
                  String pid, String birth_date, String town, String country, String email) {
        ++counter;
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.pid = pid;
        this.birth_date = birth_date;
        this.town = town;
        this.country = country;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
