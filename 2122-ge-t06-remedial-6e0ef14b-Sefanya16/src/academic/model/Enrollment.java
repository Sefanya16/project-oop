package academic.model;

/**
 * @author 12S22051 SEFANYA YEMIMA SINAGA
 */
public class Enrollment {

    // class definition
//Sebuah ```enrollment``` adalah relasi antara ```course``` dan ```student```. Sebuah ```course``` dapat dikontrak oleh banyak ```student``` dan seorang ```student``` dapat mengontrak banyak ```course```. Suatu ```enrollment``` terikat pada suatu ```semester``` di suatu tahun akademik ```academic year```. Sebuah ```enrellment``` memiliki sebuah ```course```, ```student```, ```academic year```, ```semester```, dan nilai yang diperoleh mahasiswa bersangkutan (```grade```).
    private String course;
    private String student;
    private String academicYear;
    //terdapat tiga semester, ganjil (```odd```), genap (```even```), dan pendek (```short```).
    private String grade;
    private String semester;
    private String previousGrade;
    private boolean remedialUsed;

    
    

    //konstructor
    public Enrollment(String course, String student, String academicYear, String semester, String grade) {
        this.course = course;
        this.student = student;
        this.academicYear = academicYear;
        this.semester = semester;
        this.grade = grade;
        this.remedialUsed = false;

    } 

    //getter
    public String getCourse() {
        return course;
    }

    public boolean isRemedialUsed() {
        return remedialUsed;
    }


    public void setRemedialUsed(boolean remedialUsed) {
        this.remedialUsed = remedialUsed;
    }

    //setter
    public void setCourse(String course) {
        this.course = course;
    }

    //getter
    public String getStudent() {
        return student;
    }

    //setter
    public void setStudent(String student) {
        this.student = student;
    }

    //getter
    public String getAcademicYear() {
        return academicYear;
    }

    //setter
    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    //getter
    public String getSemester() {
        return semester;
    }
 
    //setter  
  
    public void setSemester(String semester) {
        this.semester = semester;
    }

    //getter
    public String getGrade() {
        return grade;
    }
  
    //setter
     

    public String getPreviousGrade() {
        return previousGrade;
    }
     

    // When setting a new grade, also update the previous grade
    public void setGrade(String grade) {
        this.previousGrade = this.grade;
        this.grade = grade;
    }
  
    
    
 
         
}