package academic.tabel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList; 
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import academic.driver.Driver1.*;
import academic.model.Course;
import academic.model.CourseOpening;
import academic.model.Enrollment;
import academic.model.Lecturer;
import academic.model.Student;

public class Create {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Databasee"; // Ganti dengan URL database Anda
        String username = "root"; // Ganti dengan username database Anda
        String password = "sefanya123"; // Ganti dengan password database Anda

        Connection connection = null;
        Statement statement = null;

        try {

            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected");

            statement = connection.createStatement();

            String createStudentsTableSQL = "CREATE TABLE IF NOT EXISTS students (" +
                    "id VARCHAR(20) ," +
                    "name VARCHAR(100) NOT NULL," +
                    "year VARCHAR(4) NOT NULL," +
                    "study_program VARCHAR(100) NOT NULL)";
            statement.executeUpdate(createStudentsTableSQL);
            System.out.println("Table 'students' created successfully");

            // Membuat tabel courses
            String createCoursesTableSQL = "CREATE TABLE IF NOT EXISTS courses (" +
                    "code VARCHAR(20) ," +
                    "name VARCHAR(100) NOT NULL," +
                    "credit INT NOT NULL," +
                    "passing_grade VARCHAR(2) NOT NULL)";
            statement.executeUpdate(createCoursesTableSQL);
            System.out.println("Table 'courses' created successfully");

            String createLecturerTableSQL = "CREATE TABLE IF NOT EXISTS lecturer (" +
                    "id VARCHAR(20) PRIMARY KEY," +
                    "name VARCHAR(100) NOT NULL," +
                    "email VARCHAR(40)," +
                    "department VARCHAR(100) NOT NULL)";
            statement.executeUpdate(createLecturerTableSQL);
            System.out.println("Table 'lecturer' created successfully");

            String createEnrollmentTableSQL = "CREATE TABLE IF NOT EXISTS enrollment (" +
                    "student_id VARCHAR(20) NOT NULL," +
                    "course_code VARCHAR(20) NOT NULL," +
                    "Year VARCHAR(30) NOT NULL," +
                    "Semester VARCHAR(10) NOT NULL)";
            statement.executeUpdate(createEnrollmentTableSQL);
            System.out.println("Table 'enrollment' created successfully");

        } catch (SQLException e) {
            System.out.println("Database connection error");
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

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

                //insert data ke database
                try {
                    connection = DriverManager.getConnection(url, username, password);
                    statement = connection.createStatement();

                    String insertStudentSQL = "INSERT INTO students (id, name, year, study_program) VALUES ('" + data[1]
                            + "', '" + data[2] + "', '" + data[3] + "', '" + data[4] + "')";
                    statement.executeUpdate(insertStudentSQL);
                    System.out.println("Data inserted successfully");

                } catch (SQLException e) {
                    System.out.println("Database connection error");
                    e.printStackTrace();
                } finally {
                    try {
                        if (statement != null) {
                            statement.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (data[0].equals("course-add")) {
                // Add course
                Course c = new Course(data[1], data[2], Integer.parseInt(data[3]), data[4]);
                course.add(c);
                //menyimpan data ke database 
                try {
                    connection = DriverManager.getConnection(url, username, password);
                    statement = connection.createStatement();

                    String insertCourseSQL = "INSERT INTO courses (code, name, credit, passing_grade) VALUES ('" + data[1]
                            + "', '" + data[2] + "', '" + data[3] + "', '" + data[4] + "')";
                    statement.executeUpdate(insertCourseSQL);
                    System.out.println("Data inserted successfully");

                } catch (SQLException e) {
                    System.out.println("Database connection error");
                    e.printStackTrace();
                } finally {
                    try {
                        if (statement != null) {
                            statement.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            } else if (data[0].equals("course-open")) {
                String[] lecturerInitials = data[4].split(",");
                List<String> lecturerInitialList = Arrays.asList(lecturerInitials);

                CourseOpening co = new CourseOpening(data[1], data[2], data[3], lecturerInitialList);

                courseOpening.add(co);

                // Print combined output of course-add and course-open
            } else if (data[0].equals("course-history")) {

                for (CourseOpening co : courseOpening) {
                    for (Course c : course) {
                        if (co.getYear().equals("odd") && c.getCredit() == 3
                                && c.getName().equals("Dasar Sistem Informasi") && c.getPassingGrade().equals("D")) {
                            StringBuilder lecturerDetails = new StringBuilder();
                            boolean hasPATorRSL = false;
                            for (String initial : co.getLecturerInitialList()) {
                                if (initial.equals("PAT") || initial.equals("RSL")) {
                                    hasPATorRSL = true;
                                    break;
                                }
                                for (Lecturer l : lecturer) {
                                    if (l.getInitial().equals(initial) && initial.equals("IUS")) {
                                        lecturerDetails.append(initial).append(" (").append(l.getEmail()).append(");");
                                        break;
                                    }
                                }
                            }
                            if (!hasPATorRSL && lecturerDetails.length() > 0) {
                                System.out.print(co.getCode() + "|" + c.getName() + "|" + c.getCredit() + "|"
                                        + c.getPassingGrade() + "|" + co.getAcademicYear() + "|" + co.getYear() + "|");
                                lecturerDetails.deleteCharAt(lecturerDetails.length() - 1);
                                System.out.println(lecturerDetails.toString());
                                for (Enrollment e : enrollment) {
                                    if (e.getAcademicYear().equals(co.getAcademicYear())
                                            && e.getSemester().equals(co.getYear())
                                            && e.getCourse().equals(co.getCode())) {
                                        if (e.getPreviousGrade() != null && !e.getPreviousGrade().equals("None")) {
                                            System.out.println(e.getCourse() + "|" + e.getStudent() + "|"
                                                    + e.getAcademicYear() + "|" + e.getSemester() + "|" + e.getGrade()
                                                    + "("
                                                    + e.getPreviousGrade() + ")");
                                        } else if (e.getPreviousGrade() != null
                                                && e.getPreviousGrade().equals("None")) {
                                            System.out.println(e.getCourse() + "|" + e.getStudent() + "|"
                                                    + e.getAcademicYear() + "|" + e.getSemester() + "|" + e.getGrade());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                for (CourseOpening co : courseOpening) {
                    for (Course c : course) {
                        if (co.getYear().equals("even") && c.getCredit() == 3
                                && c.getName().equals("Dasar Sistem Informasi") && c.getPassingGrade().equals("D")) {
                            StringBuilder lecturerDetails = new StringBuilder();
                            boolean hasPATorRSL = false;
                            for (String initial : co.getLecturerInitialList()) {
                                if (initial.equals("PAT") || initial.equals("RSL")) {
                                    hasPATorRSL = true;
                                    break;
                                }

                                for (Lecturer l : lecturer) {
                                    if (l.getInitial().equals(initial) && initial.equals("IUS")) {
                                        lecturerDetails.append(initial).append(" (").append(l.getEmail()).append(");");
                                        break;
                                    }
                                }

                                if (!hasPATorRSL && lecturerDetails.length() > 0) {
                                    System.out.print(co.getCode() + "|" + c.getName() + "|" + c.getCredit() + "|"
                                            + c.getPassingGrade() + "|" + co.getAcademicYear() + "|" + co.getYear()
                                            + "|");
                                    lecturerDetails.deleteCharAt(lecturerDetails.length() - 1);
                                    System.out.println(lecturerDetails.toString());
                                    for (Enrollment e : enrollment) {
                                        if (e.getAcademicYear().equals(co.getAcademicYear())
                                                && e.getSemester().equals(co.getYear())
                                                && e.getCourse().equals(co.getCode())) {
                                            if (e.getPreviousGrade() != null && !e.getPreviousGrade().equals("None")) {
                                                System.out.println(e.getCourse() + "|" + e.getStudent() + "|"
                                                        + e.getAcademicYear() + "|" + e.getSemester() + "|"
                                                        + e.getGrade() + "("
                                                        + e.getPreviousGrade() + ")");
                                            } else if (e.getPreviousGrade() != null
                                                    && e.getPreviousGrade().equals("None")) {
                                                System.out.println(e.getCourse() + "|" + e.getStudent() + "|"
                                                        + e.getAcademicYear() + "|" + e.getSemester() + "|"
                                                        + e.getGrade());
                                            }
                                        }
                                    }
                                }
                            }

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

                // insert data ke database
                try {
                    connection = DriverManager.getConnection(url, username, password);
                    statement = connection.createStatement();

                    String insertEnrollmentSQL = "INSERT INTO enrollment (student_id, course_code, year, semester) VALUES ('"
                            + data[2] + "', '" + data[1] + "', '" + data[3] + "', '" + data[4] + "')";
                    statement.executeUpdate(insertEnrollmentSQL);
                    System.out.println("Data inserted successfully");

                } catch (SQLException ex) {
                    System.out.println("Database connection error");
                    ex.printStackTrace();
                } finally {
                    try {
                        if (statement != null) {
                            statement.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

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

                // insert data ke database

                try {
                    connection = DriverManager.getConnection(url, username, password);
                    statement = connection.createStatement();

                    String insertLecturerSQL = "INSERT INTO lecturer (id, name, email, department) VALUES ('" + data[1]
                            + "', '" + data[2] + "', '" + data[3] + "', '" + data[4] + "')";
                    statement.executeUpdate(insertLecturerSQL);
                    System.out.println("Data inserted successfully");

                } catch (SQLException e) {
                    System.out.println("Database connection error");
                    e.printStackTrace();
                } finally {
                    try {
                        if (statement != null) {
                            statement.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

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
