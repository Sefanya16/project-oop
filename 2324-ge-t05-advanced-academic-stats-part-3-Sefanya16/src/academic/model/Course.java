package academic.model;

import java.util.List;

/**
 * @author 12S22051 SEFANYA YEMIMA SINAGA
 */
public class Course extends Inheritence{

    //DEKLARASI VARIABEL CODE NAME PASSING GRADE KREDIT

    private String passingGrade;
    private int credit;
    //daftar dosen (```lecturer-initial-list```).
    private List<String> lecturerInitialList;


    //konstructor
    public Course(String code, String name, int credit, String passingGrade, List<String> lecturerInitialList) {
        super(name, null, null, null, null,null, null, null, null, code);  
        this.credit = credit;
        this.passingGrade = passingGrade;
        this.lecturerInitialList = lecturerInitialList;
    }
    //getter
 

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
