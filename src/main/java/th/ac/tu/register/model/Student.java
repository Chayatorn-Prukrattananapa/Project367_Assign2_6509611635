package th.ac.tu.register.model;

public class Student {
    private String studentId;
    private String firstName;
    private String lastName;
    private String faculty;
    private String major;
    private String minor;
    private int year;

    public Student(String studentId, String firstName, String lastName, String faculty, String major, String minor, int year) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.faculty = faculty;
        this.major = major;
        this.minor = minor;
        this.year = year;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
