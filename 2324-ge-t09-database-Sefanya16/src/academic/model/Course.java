package academic.model;

import java.util.List;

/**
 * @author 12S22051 SEFANYA YEMIMA SINAGA
 */
public class Course {

    //DEKLARASI VARIABEL CODE NAME PASSING GRADE KREDIT

    private String passingGrade;
    private String name; 
    private String code; 
    
    private int credit; 
    private List<String> lecturerInitialList;


    //konstructor
    public Course(String code, String name, int credit, String passingGrade) {
        this.name = name;
        this.credit = credit;
        this.code = code;

        this.passingGrade = passingGrade;
  
    }
    //getter
    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
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
