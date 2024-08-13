package academic.model;

/**
 * @author 12S22051 SEFANYA YEMIMA SINAGA
 */
public class Student {

    // class definition
    //Variabel ID ,NAME, Year, study program
    private String id;
    private String name;
    private String year;
    private String studyProgram;
    private double gpa;
    private int totalCredit;

    //konstruktor
    public Student() {
    }

    //konstruktor
    public Student(String id, String name, String year, String studyProgram) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.studyProgram = studyProgram;
    }

    //getter
    public String getId() {
        return id;
    }

    //setter

    public void setId(String id) {
        this.id = id;
    }

    //getter
    public String getName() {
        return name;
    }

    //setter
    public void setName(String name) {
        this.name = name;
    }

    //getter

    public String getYear() {
        return year;
    }

    //setter

    public void setYear(String year){
        this.year = year;
    } 

    //getter
    public String getStudyProgram() {
        return studyProgram; 
    }
 
    //setter
    public void setStudyProgram(String studyProgram) {
        this.studyProgram = studyProgram;
    }

    //getter
    public double getGpa() {
        return gpa;
    }

    //setter
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    //getter
    public int getTotalCredit() {
        return totalCredit;
    }

    //setter
    public void setTotalCredit(int totalCredit) {
        this.totalCredit = totalCredit;
    }

    //Display student adalah 12S20999|Wiro Sableng|2020|Information Systems
    @Override
    public String toString() {
        return id + "|" + name + "|" + year + "|" + studyProgram;
    }
    
 




}