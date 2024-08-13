package academic.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
import academic.model.*;

/**
 * 12S22051 SEFANYA YEMIMA SINAGA
 */
public class Driver1 {

    public static class GradeCalculator {
        public static double calculateGPA(String grade, int credit) {
            double gpa = 0.0;
            if (grade.equals("A")) {
                gpa = 4 * credit;
            } else if (grade.equals("AB")) {
                gpa = 3.5 * credit;
            } else if (grade.equals("B")) {
                gpa = 3 * credit;
            } else if (grade.equals("BC")) {
                gpa = 2.5 * credit;
            } else if (grade.equals("C")) {
                gpa = 2 * credit;
            } else if (grade.equals("D")) {
                gpa = 1 * credit;
            }
            return gpa;
        }
    }

    public static void main(String[] _args) {

        Scanner masukan = new Scanner(System.in);
        ArrayList<Student> student = new ArrayList<Student>();
        ArrayList<Course> course = new ArrayList<Course>();
        ArrayList<Enrollment> enrollment = new ArrayList<Enrollment>();
        ArrayList<CourseOpening> courseOpening = new ArrayList<CourseOpening>();
        ArrayList<Lecturer> lecturer = new ArrayList<Lecturer>();

        while (masukan.hasNext()) {
            String input = masukan.nextLine();

            if (input.equals("---")) {
                break;
            }
            // split input
            String[] data = input.split("#");

            if (data[0].equals("student-add")) {
                // student-add#12S20999#Wiro Sableng#2020#Information Systems
                // buat objek student
                Student s = new Student(data[1], data[2], data[3], data[4]);
                // menambahkan objek student ke dalam arraylist dan tidak ada duplikat
                boolean isExist = false;
                for (Student stu : student) {
                    if (stu.getName().equals(data[2])) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist)
                    student.add(s);
                // Collections.sort(course, (a,b) -> a.getCode().compareTo(b.getCode()));
            }
            if (data[0].equals("course-add")) {
                // Add course
                Course c = new Course(data[1], data[2], Integer.parseInt(data[3]), data[4]);
                course.add(c);
            } else if (data[0].equals("course-open")) {
                String[] lecturerInitials = data[4].split(",");
                List<String> lecturerInitialList = Arrays.asList(lecturerInitials);

                CourseOpening co = new CourseOpening(data[1], data[2], data[3], lecturerInitialList);

                courseOpening.add(co);

                // Print combined output of course-add and course-open
            } else if (data[0].equals("course-history")) {

                // Sort courseOpening by year (odd or even)
                Collections.sort(courseOpening, new Comparator<CourseOpening>() {
                    @Override
                    public int compare(CourseOpening co1, CourseOpening co2) {
                        // If both semesters are the same, they are equal in the ordering
                        if (co1.getSemester().equals(co2.getSemester())) {
                            return 0;
                        }
                        // If co1 is "odd", it should come before "even"
                        else if (co1.getSemester().equals("odd")) {
                            return -1;
                        }
                        // If co1 is "even", it should come after "odd"
                        else {
                            return 1;
                        }
                    }
                });

                for (CourseOpening co : courseOpening) {
                    for (Course c : course) {
                        if (co.getLecturerInitialList() != null) {
                            StringBuilder lecturerDetails = new StringBuilder();
                            for (String initial : co.getLecturerInitialList()) {
                                for (Lecturer l : lecturer) {
                                    if (l.getInitial().equals(initial)) {
                                        lecturerDetails.append(initial).append(" (").append(l.getEmail()).append(");");
                                        break;
                                    }
                                }
                            }
                            // Menghapus titik koma terakhir jika ada
                            if (lecturerDetails.length() > 0) {
                                lecturerDetails.deleteCharAt(lecturerDetails.length() - 1);
                            }
                            
                                //12S1101|Dasar Sistem Informasi|3|D|2020/2021|odd|IUS (iustisia.simbolon@del.ac.id)
                                System.out.println(c.getCode() + "|" + c.getName() + "|" + c.getCredit() + "|" + c.getPassingGrade() + "|" + co.getAcademicYear() + "|" + co.getYear() + "|" + lecturerDetails);
                            
                        }
                        
                    }
                }
            } else if (data[0].equals("enrollment-add")) {
                // enrollment-add#12S20999#IF1234#A#2020
                // Check if course exists
                boolean courseExists = false;
                for (Course c : course) {
                    if (c.getCode().equals(data[1])) {
                        courseExists = true;
                        break;
                    }
                }
                // Check if student exists
                boolean studentExists = false;
                for (Student s : student) {
                    if (s.getId().equals(data[2])) {
                        studentExists = true;
                        break;
                    }
                }
                // If course or student doesn't exist, print error message and continue to next
                // input
                if (!courseExists) {
                    continue;
                }
                if (!studentExists) {

                    continue;
                }
                // Add enrollment only if course and student exist
                Enrollment e = new Enrollment(data[1], data[2], data[3], data[4], "None");
                enrollment.add(e);
            } else if (data[0].equals("lecturer-add")) {
                // buat objek lecturer
                Lecturer l = new Lecturer(data[1], data[2], data[3], data[4], data[5]);
                // menambahkan objek lecturer ke dalam arraylist dan tidak terjadi duplikat
                boolean isExist = false;
                for (Lecturer lec : lecturer) {
                    if (lec.getId().equals(data[1])) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist)
                    lecturer.add(l);

            } else if (data[0].equals("enrollment-grade")) {
                // enrollment-grade#12S20999#IF1234#A#2020
                // Check if course exists
                boolean courseExists = false;
                for (Course c : course) {
                    if (c.getCode().equals(data[1])) {
                        courseExists = true;
                        break;
                    }
                }
                // Check if student exists
                boolean studentExists = false;
                for (Student s : student) {
                    if (s.getId().equals(data[2])) {
                        studentExists = true;
                        break;
                    }
                }

                if (!courseExists) {

                    continue;
                }
                if (!studentExists) {

                    continue;
                }

                // Add enrollment only if course and student exist
                for (Enrollment e : enrollment) {
                    if (e.getStudent().equals(data[2]) && e.getCourse().equals(data[1])
                            && e.getAcademicYear().equals(data[3]) && e.getSemester().equals(data[4])) {
                        e.setGrade(data[5]);
                    }
                }
            } else if (data[0].equals("student-details")) {
                // student-details#12S20999
                // Check if student exists
                boolean studentExists = false;
                for (Student s : student) {
                    if (s.getId().equals(data[1])) {
                        studentExists = true;
                        double oldgpa = 0.0;
                        int totalCredit = 0;
                        // Map to store course and its grade for the student
                        Map<String, String> courseGrades = new HashMap<>();
                        for (Enrollment e : enrollment) {
                            if (e.getStudent().equals(data[1])) {
                                // Only update the course grade if it's not "None"
                                if (!e.getGrade().equals("None")) {
                                    courseGrades.put(e.getCourse(), e.getGrade());
                                }
                            }
                        }
                        // Calculate GPA and total credits using only the latest course grades
                        for (String courseCode : courseGrades.keySet()) {
                            for (Course c : course) {
                                if (c.getCode().equals(courseCode)) {
                                    totalCredit += c.getCredit();
                                    String grade = courseGrades.get(courseCode);
                                    if (grade.equals("A")) {
                                        oldgpa += 4 * c.getCredit();
                                    } else if (grade.equals("AB")) {
                                        oldgpa += 3.5 * c.getCredit();
                                    } else if (grade.equals("B")) {
                                        oldgpa += 3 * c.getCredit();
                                    } else if (grade.equals("BC")) {
                                        oldgpa += 2.5 * c.getCredit();
                                    } else if (grade.equals("C")) {
                                        oldgpa += 2 * c.getCredit();
                                    } else if (grade.equals("D")) {
                                        oldgpa += 1 * c.getCredit();
                                    }
                                }
                            }
                        }

                        // hitung gpa sebeulum dan sesudah remedial
                        double oldGpa = oldgpa / totalCredit;

                        // Print student details
                        System.out.println(s.getId() + "|" + s.getName() + "|" + s.getYear() + "|" + s.getStudyProgram()
                                + "|" + String.format("%.2f", oldGpa) + "|" + totalCredit);

                        break;

                    }
                }
                // If student doesn't exist, print error message
                if (!studentExists) {
                    System.out.println("Student not found");
                }
            } else if (data[0].equals("enrollment-remedial")) {

                // cek
                boolean courseExists = false;
                for (Course c : course) {
                    if (c.getCode().equals(data[1])) {
                        courseExists = true;
                        break;
                    }
                }
                // Check if student exists
                boolean studentExists = false;
                for (Student s : student) {
                    if (s.getId().equals(data[2])) {
                        studentExists = true;
                        break;
                    }
                }
                // If course or student doesn't exist, print error message and continue to next
                // input
                if (!courseExists) {
                    continue;
                }
                if (!studentExists) {
                    continue;
                }
                // Add enrollment only if course and student exist
                for (Enrollment e : enrollment) {
                    if (e.getStudent().equals(data[2]) && e.getCourse().equals(data[1])
                            && e.getAcademicYear().equals(data[3]) && e.getSemester().equals(data[4])) {
                        if (e.getGrade().equals("None")) {
                            continue;
                        }
                        if (e.getStudent().equals(data[2]) && !e.isRemedialUsed()) {
                            for (Course c : course) {
                                if (c.getCode().equals(data[1])) {
                                    e.setGrade(e.getGrade());
                                    e.setGrade(data[5]);

                                    // Mark that the student has used remedial for this course
                                    e.setRemedialUsed(true);
                                }
                            }
                        }

                    }
                }

            } else if (data[0].equals("student-transcript")) {
                // student-transcript#12S20003
                HashMap<String, Enrollment> latestEnrollments = new HashMap<String, Enrollment>();

                for (Enrollment e : enrollment) {
                    if (e.getStudent().equals(data[1]) && e.getSemester().equals("odd")) {
                        latestEnrollments.put(e.getCourse(), e);
                    }
                }
                for (Student s : student) {

                    if (s.getId().equals(data[1])) {

                        double oldgpa = 0.0;
                        int totalCredit = 0;
                        // Map to store course and its grade for the student
                        Map<String, String> courseGrades = new HashMap<>();
                        for (Enrollment e : enrollment) {
                            if (e.getStudent().equals(data[1]) && e.getSemester().equals("odd")) {
                                // Only update the course grade if it's not "None"
                                if (!e.getGrade().equals("None")) {
                                    courseGrades.put(e.getCourse(), e.getGrade());
                                }
                            }
                        }
                        // Calculate GPA and total credits using only the latest course grades
                        for (String courseCode : courseGrades.keySet()) {
                            for (Course c : course) {
                                if (c.getCode().equals(courseCode)) {
                                    totalCredit += c.getCredit();
                                    String grade = courseGrades.get(courseCode);
                                    oldgpa += GradeCalculator.calculateGPA(grade, c.getCredit());
                                }
                            }
                        }

                        // hitung gpa sebeulum dan sesudah remedial
                        double oldGpa = oldgpa / totalCredit;

                        // Print student details
                        System.out.println(s.getId() + "|" + s.getName() + "|" + s.getYear() + "|" + s.getStudyProgram()
                                + "|" + String.format("%.2f", oldGpa) + "|" + totalCredit);

                    }

                }

                for (Enrollment e : latestEnrollments.values()) {

                    if (!e.getPreviousGrade().equals("None")) {
                        System.out.println(e.getCourse() + "|" + e.getStudent() + "|" + e.getAcademicYear() + "|"
                                + e.getSemester() + "|" + e.getGrade() + "(" + e.getPreviousGrade() + ")");
                    } else if (e.getPreviousGrade().equals("None")) {
                        System.out.println(e.getCourse() + "|" + e.getStudent() + "|" + e.getAcademicYear() + "|"
                                + e.getSemester() + "|" + e.getGrade());
                    }

                }

            }
        }

        // print lecturer
        for (Lecturer l : lecturer) {
            System.out.println(l.getId() + "|" + l.getName() + "|" + l.getInitial() + "|" + l.getEmail() + "|"
                    + l.getStudyProgram());

        }
        for (Course c : course) {
            System.out.println(c.getCode() + "|" + c.getName() + "|" + c.getCredit() + "|" + c.getPassingGrade());
        }

        // print student
        for (Student stu : student) {
            System.out.println(stu);
        }

        // print enrollment
        for (Enrollment e : enrollment) {

            if (e.getPreviousGrade() != null && !e.getPreviousGrade().equals("None")) {
                System.out.println(e.getCourse() + "|" + e.getStudent() + "|" + e.getAcademicYear() + "|"
                        + e.getSemester() + "|" + e.getGrade() + "(" + e.getPreviousGrade() + ")");
            } else if (e.getPreviousGrade() != null && e.getPreviousGrade().equals("None")) {
                System.out.println(e.getCourse() + "|" + e.getStudent() + "|" + e.getAcademicYear() + "|"
                        + e.getSemester() + "|" + e.getGrade());

            }
        }

        masukan.close();
    }

}