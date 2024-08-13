package academic.model;

/**
 * @author 12S22051 SEFANYA YEMIMA SINAGA
 */
public class Student {

    // class definition
    //Variabel ID ,NAME, Year, study program
    private double gpa;
    private int totalCredit;
    private String id;
    private String name; 
    private String  year;
    private String studyProgram;
     
    
    //konstruktor 
    public Student(String id, String name, String year, String studyProgram) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.studyProgram = studyProgram;
    }

    //getter
    public String getName() {
        return name;
    }

    //getter
    public String getYear() {
        return year;
    }


    //getter
    public String getStudyProgram() {
        return studyProgram;
    }

    //getter
    public String getId() {
        return id;
    }

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
        return getId() + "|" + getName() + "|" + getYear() + "|" + getStudyProgram();
    }


}