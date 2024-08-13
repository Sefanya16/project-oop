package academic.driver;

import academic.model.Course;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import academic.model.Lecturer;



/**
 * 12S22051 SEFANYA YEMIMA SINAGA
 */
public class Driver2 {

    public static void main(String[] _args) {

        // SCANNER
        Scanner masukan = new Scanner(System.in);
        
        ArrayList<Course> course = new ArrayList<Course>();
        
        ArrayList<Lecturer> lecturer = new ArrayList<Lecturer>();

        while (masukan.hasNext()){
            String input = masukan.nextLine();

            // hentikan aplikasi jika input 
            if (input.equals("---")) {
                break;
            }
            // split input
            String[] data = input.split("#");
             if(data[0].equals("course-add")){
                //course-add#IF1234#Pemrograman Berorientasi Objek#3#D
                //membuat objek course
                 String[] lecturerInitials = data[5].split(",");
                List<String> lecturerInitialList = Arrays.asList(lecturerInitials);

                // Create Course object with lecturerInitialList
                Course c = new Course(data[1], data[2], data[3], data[4], lecturerInitialList);

                // Add Course object to ArrayList
                 course.add(c);
            }else if (data[0].equals("lecturer-add")){
                //buat objek lecturer
                Lecturer l = new Lecturer(data[1], data[2], data[3], data[4], data[5]);
              
                //menambahkan objek lecturer ke dalam arraylist
                if (!lecturer.contains(l)) {
                    lecturer.add(l);
                }

        }
        }

         //print lecturer
         for (Lecturer l : lecturer) {
            System.out.println(l.getId() + "|" + l.getName() + "|" + l.getInitial() +"|" +l.getEmail() +"|" + l.getStudyProgram());
        }


        //print course
        for (Course c : course) {
            StringBuilder lecturerDetails = new StringBuilder();
            for (String initial : c.getLecturerInitialList()) {
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
            System.out.println(c.getCode() + "|" + c.getName() + "|" + c.getCredit() + "|" + c.getPassingGrade() + "|" + lecturerDetails.toString());
        }

        masukan.close();
    
    }
}
