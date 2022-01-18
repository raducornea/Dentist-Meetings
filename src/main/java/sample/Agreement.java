package sample;

import java.sql.SQLException;

import static sample.Controller.db;

// agreements between centre and dentist -> used with clients to get meetings
public class Agreement {
    static private Integer counter = 0;
    private Integer id;
    private String first_name;
    private String last_name;
    private String name;
    private String phone;

    public Agreement(String first_name, String last_name, String name, String phone) {
        ++counter;
        this.id = counter;
        this.first_name = first_name;
        this.last_name = last_name;
        this.name = name;
        this.phone = phone;
    }

    public Agreement(Integer id, String first_name, String last_name, String name, String phone) {
        ++counter;
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.name = name;
        this.phone = phone;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() throws SQLException {
        String location = db.get_location_by_phone(phone);
        return location;
    }
}
