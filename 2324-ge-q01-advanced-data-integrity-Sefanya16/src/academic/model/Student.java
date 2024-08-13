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

    //Display student adalah 12S20999|Wiro Sableng|2020|Information Systems
    @Override
    public String toString() {
        return id + "|" + name + "|" + year + "|" + studyProgram;
    }
    
 




}