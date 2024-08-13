package academic.model;

import java.util.List;

/**
 * @author 12S22051 SEFANYA YEMIMA SINAGA
 */
public class Course {

    private String code;
    private String name;
    private String passingGrade;
    private String credit;
    private List<String> lecturerInitialList; // List of lecturer initials

    // Constructor with lecturerInitialList parameter
    public Course(String code, String name, String credit, String passingGrade, List<String> lecturerInitialList) {
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.passingGrade = passingGrade;
        this.lecturerInitialList = lecturerInitialList;
    }

    // Getter and setter methods for lecturerInitialList
    public List<String> getLecturerInitialList() {
        return lecturerInitialList;
    }

    public void setLecturerInitialList(List<String> lecturerInitialList) {
        this.lecturerInitialList = lecturerInitialList;
    }

    // Other getters and setters remain the same
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassingGrade() {
        return passingGrade;
    }

    public void setPassingGrade(String passingGrade) {
        this.passingGrade = passingGrade;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }
}
