package sample;

public class Centre {
    static private Integer counter = 0;
    private Integer id;
    private String name;
    private String location;
    private String phone;

    public Centre(String name, String location, String phone) {
        ++counter;
        this.id = counter;
        this.name = name;
        this.location = location;
        this.phone = phone;
    }

    public Centre(Integer id, String name, String location, String phone) {
        ++counter;
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPhone() {
        return phone;
    }
}
