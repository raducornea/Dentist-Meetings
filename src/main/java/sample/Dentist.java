package sample;

public class Dentist {
    static private Integer counter = 0;
    private Integer id;
    private String first_name;
    private String last_name;
    private String pid;
    private String specialization;
    private String qualification;
    private Integer years_experience;
    private String email;

    public Dentist(String first_name, String last_name,
                   String pid, String specialization, String qualification, Integer years_experience, String email) {
        ++counter;
        this.id = counter;
        this.first_name = first_name;
        this.last_name = last_name;
        this.pid = pid;
        this.specialization = specialization;
        this.qualification = qualification;
        this.years_experience = years_experience;
        this.email = email;
    }

    public Dentist(Integer id, String first_name, String last_name,
                   String pid, String specialization, String qualification, Integer years_experience, String email) {
        ++counter;
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.pid = pid;
        this.specialization = specialization;
        this.qualification = qualification;
        this.years_experience = years_experience;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Integer getYears_experience() {
        return years_experience;
    }

    public void setYears_experience(Integer years_experience) {
        this.years_experience = years_experience;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
