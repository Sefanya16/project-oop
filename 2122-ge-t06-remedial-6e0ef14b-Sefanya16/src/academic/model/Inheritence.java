package academic.model;
//saya mennggunakan imheritence untuk mengambil data dari class Inheritence 
public class Inheritence {
    private String name;
    private String  year;
    private String studyProgram;
    private String id;
    private String course;
    private String student;
    private String academicYear;
    //terdapat tiga semester, ganjil (```odd```), genap (```even```), dan pendek (```short```).
    
    private String semester;
    private String code;


    //contruc
    public Inheritence(String name, String year, String studyProgram, String id, String course, String academicYear, String semester, String grade, String code, String student) {
        this.name = name;
        this.year = year;
        this.studyProgram = studyProgram;
        this.id = id;
        this.course = course;
        this.student = student;
        this.academicYear = academicYear;
        this.semester = semester;
       
        this.code = code;


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

    //getter
    public String getCourse() {
        return course;
    }

    //getter
    public String getStudent() {
        return student;
    }

    //getter
    public String getAcademicYear() {
        return academicYear;
    }
    

    //getter
    public String getSemester() {
        return semester;
    }

    //getter
    public String getCode() {
        return code;
    }

    
}
