package academic.model;

import java.util.List;

/**
 * @author 12S22051 SEFANYA YEMIMA SINAGA
 */
public class CourseOpening extends Inheritence {

    private List<String> lecturerInitialList;
    private int credits;
    private String passingGrade;

    // konstructor
    public CourseOpening(String code, String academicYear, String semester, List<String> lecturerInitialList) {
        super(null, semester, null, null, null, academicYear, null, null, code, null);
        this.lecturerInitialList = lecturerInitialList;

    }

    // getter for year

    // getter for lecturerInitialList
    public List<String> getLecturerInitialList() {
        return lecturerInitialList;
    }

    // getter passinggrade
    public String getPassingGrade() {
        return passingGrade;
    }

    // getter credits
    public int getCredits() {
        return credits;
    }

}
