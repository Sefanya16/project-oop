package academic.model;

import java.util.List;

/**
 * @author 12S22051 SEFANYA YEMIMA SINAGA
 */
public record Course(String code, String name, String credit, String passingGrade, List<String> lecturerInitialList) {}