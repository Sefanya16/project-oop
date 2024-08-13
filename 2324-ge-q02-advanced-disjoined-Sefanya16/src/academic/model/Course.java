package academic.model;

import java.util.List;

/**
 * @author 12S22051 SEFANYA YEMIMA SINAGA
 */
public class Course extends Inheritence{

    //DEKLARASI VARIABEL CODE NAME PASSING GRADE KREDIT

    private String passingGrade;
    private int credit;
    private List<String> lecturerInitialList;


    //konstructor
    public Course(String code, String name, int credit, String passingGrade) {
        super(name, null, null, null, null,null, null, null,code,null);  
        this.credit = credit;
        this.passingGrade = passingGrade;
  
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
