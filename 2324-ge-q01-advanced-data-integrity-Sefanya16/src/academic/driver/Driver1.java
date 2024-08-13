package academic.driver;
import academic.model.Student;
import academic.model.Course;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import academic.model.Lecturer;
import academic.model.Enrollment;



/**
 * 12S22051 SEFANYA YEMIMA SINAGA
 */
public class Driver1 {

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
                //menambahkan objek student ke dalam arraylist
                student.add(s);
                Collections.sort(student, (a,b) -> a.getId().compareTo(b.getId()));
            } else if(data[0].equals("course-add")){
                //course-add#IF1234#Pemrograman Berorientasi Objek#3#D
                //membuat objek course
                Course c = new Course(data[1], data[2], data[3], data[4], new ArrayList<String>());
                //menambahkan objek course ke dalam arraylist
                course.add(c);
            }else if (data[0].equals("lecturer-add")){
                //buat objek lecturer
                Lecturer l = new Lecturer(data[1], data[2], data[3], data[4], data[5]);
                //menambahkan objek lecturer ke dalam arraylist
                // Jika lecturer belum ada dalam arraylist, tambahkan
                if (!lecturer.contains(l)) {
                lecturer.add(l);
                }
              

            }else if(data[0].equals("enrollment-add")){
                //enrollment-add#12S20999#IF1234#A#2020
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
                // If course or student doesn't exist, print error message and continue to next input
                if (!courseExists) {
                    System.out.println("invalid course|" + data[1]);
                    continue;
                }
                if (!studentExists) {
                    System.out.println("invalid student|" + data[2]);
                    continue;
                }
                // Add enrollment only if course and student exist
                Enrollment e = new Enrollment(data[1], data[2], data[4], "", data[3]);
                enrollment.add(e);
            }


            //print student
        }

        //print course
        for (Course c : course) {
            System.out.println(c.getCode() + "|" + c.getName() + "|" + c.getCredit() + "|" + c.getPassingGrade());
        }
    

         //print lecturer
         for (Lecturer l : lecturer) {
            System.out.println(l.getId() + "|" + l.getName() + "|" + l.getInitial() +"|" +l.getEmail() +"|" + l.getStudyProgram());
        }

        for (Student stu : student){
            System.out.println(stu);
        }
        
        //print enrollment
        for (Enrollment e : enrollment) {
            System.out.println(e.getCourse() + "|" + e.getStudent() + "|" + e.getGrade() + "|" + e.getAcademicYear() + "|" + "None");
        }

       


        masukan.close();
    }
}
