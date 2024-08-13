package academic.model;

import java.util.List;

/**
 * @author 12S22051 SEFANYA YEMIMA SINAGA
 */
public class Course {

    //DEKLARASI VARIABEL CODE NAME PASSING GRADE KREDIT
    private String code;
    private String name;
    private String passingGrade;
    private int credit;
    //daftar dosen (```lecturer-initial-list```).
    private List<String> lecturerInitialList;

    //konstructor
    public Course(String code, String name, int credit, String passingGrade, List<String> lecturerInitialList) {
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.passingGrade = passingGrade;
        this.lecturerInitialList = lecturerInitialList;
    }
    //getter 
    public String getCode() {
        return code;
    }
    //getter
    public String getName() {
        return name;
    }

    //getter
    public List <String> getlecturerInitialList(){
        return lecturerInitialList; 
    }

    public String getPassingGrade(){
        return passingGrade;
    }

    public int getCredit(){
        return credit; 
    }    

} 
