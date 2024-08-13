package academic.driver;
import academic.model.Student;
import academic.model.Course;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
// import java.util.Collections;
import java.util.List;

import academic.model.Lecturer;
import academic.model.Enrollment;


/**
 * 12S22051 SEFANYA YEMIMA SINAGA
 */
public class Driver2 {

    public static void main(String[] _args) {

        // SCANNER
        Scanner masukan = new Scanner(System.in);
        ArrayList<Student> student = new ArrayList<Student>();
        ArrayList<Course> course = new ArrayList<Course>();
        ArrayList<Enrollment> enrollment = new ArrayList<Enrollment>();
        ArrayList<Lecturer> lecturer = new ArrayList<Lecturer>();

        while (masukan.hasNext()){
            String input = masukan.nextLine();

            // hentikan aplikasi jika input 
            if (input.equals("---")) {
                break;
            }
            // split input
            String[] data = input.split("#");

            if (data[0].equals("student-add")){
                //student-add#12S20999#Wiro Sableng#2020#Information Systems
                //buat objek student
                Student s = new Student(data[1], data[2], data[3], data[4]);
                //menambahkan objek student ke dalam arraylist dan tidak ada duplikat
                boolean isExist = false;
                for (Student stu : student) {
                    if (stu.name().equals(data[2])) {
                        isExist = true;
                        break;
                    }
                }       
                if (!isExist)
                student.add(s);
                // Collections.sort(course, (a,b) -> a.getCode().compareTo(b.getCode()));
            } else if(data[0].equals("course-add")){
                //course-add#IF1234#Pemrograman Berorientasi Objek#3#D
                //membuat objek course
                String[] lecturerInitials = data[5].split(",");
                List<String> lecturerInitialList = Arrays.asList(lecturerInitials);

                // Create Course object with lecturerInitialList
                Course c = new Course(data[1], data[2], data[3], data[4], lecturerInitialList);

                // Add Course object to ArrayList
                 course.add(c);
            } else if(data[0].equals("enrollment-add")){
                //enrollment-add#12S20999#IF1234#A#2020
                // Check if course exists
                boolean courseExists = false;
                for (Course c : course) {
                    if (c.code().equals(data[1])) {
                        courseExists = true;
                        break;
                    }
                }
                // Check if student exists
                boolean studentExists = false;
                for (Student s : student) {
                    if (s.id().equals(data[2])) {
                        studentExists = true;
                        break;
                    }
                } 
                // If course or student doesn't exist, print error message and continue to next input
                if (!courseExists) {
                    
                    continue;
                }
                if (!studentExists) {
                    
                    continue;
                }
                // Add enrollment only if course and student exist
                Enrollment e = new Enrollment(data[1], data[2], data[4], "");
                enrollment.add(e);
            }else if (data[0].equals("lecturer-add")){
                //buat objek lecturer
                Lecturer l = new Lecturer(data[1], data[2], data[3], data[4], data[5]);
                //menambahkan objek lecturer ke dalam arraylist dan tidak terjadi duplikat
                boolean isExist = false;
                for (Lecturer lec : lecturer) {
                    if (lec.id().equals(data[1])) {
                        isExist = true;
                        break;
                    }
                }       
                if (!isExist)
                lecturer.add(l);

            }

        }

        //print lecturer
        for (Lecturer l : lecturer) {
            System.out.println(l.id() + "|" + l.name() + "|" + l.initial() + "|" + l.email() + "|" + l.studyProgram());
        
        }
        for (Course c : course) {
            StringBuilder lecturerDetails = new StringBuilder();
            for (String initial : c.lecturerInitialList()) {
                for (Lecturer l : lecturer) {
                    if (l.initial().equals(initial)) {
                        lecturerDetails.append(initial).append(" (").append(l.email()).append(");");
                        break;
                    }
                }
            }
            // Menghapus titik koma terakhir jika ada
            if (lecturerDetails.length() > 0) {
                lecturerDetails.deleteCharAt(lecturerDetails.length() - 1);
            }
            System.out.println(c.code() + "|" + c.name() + "|" + c.credit() + "|" + c.passingGrade() + "|" + lecturerDetails.toString());
        }

        //print student
        for (Student stu : student){
            System.out.println(stu);
        }
        
        //print enrollment
        for (Enrollment e : enrollment) {
            System.out.println(e.course() + "|" + e.student() + "|" + e.grade() + "|" + e.academicYear() + "|" + "None");
        }

        masukan.close();
    }
}
