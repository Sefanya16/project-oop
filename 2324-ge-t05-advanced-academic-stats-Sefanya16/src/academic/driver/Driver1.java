package academic.driver;
import academic.model.Student;
import academic.model.Course;

// import java.util.Collections;
import java.util.*;
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

            
            // split input
            String[] data = input.split("#");

            if (data[0].equals("student-add")){
                //student-add#12S20999#Wiro Sableng#2020#Information Systems
                //buat objek student
                Student s = new Student(data[1], data[2], data[3], data[4]);
                //menambahkan objek student ke dalam arraylist dan tidak ada duplikat
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
            } else if(data[0].equals("course-add")){
                //course-add#IF1234#Pemrograman Berorientasi Objek#3#D
                //membuat objek course
                String[] lecturerInitials = data[5].split(",");
                List<String> lecturerInitialList = Arrays.asList(lecturerInitials);

                // Create Course object with lecturerInitialList
                Course c = new Course(data[1], data[2], Integer.parseInt(data[3]), data[4], lecturerInitialList);


                // Add Course object to ArrayList
                 course.add(c);
            } else if(data[0].equals("enrollment-add")){
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
                    continue;
                }
                if (!studentExists) {
                    
                    continue;
                }
                // Add enrollment only if course and student exist
                Enrollment e = new Enrollment(data[1], data[2], data[3],data[4],"None");
                enrollment.add(e);
            }else if (data[0].equals("lecturer-add")){
                //buat objek lecturer
                Lecturer l = new Lecturer(data[1], data[2], data[3], data[4], data[5]);
                //menambahkan objek lecturer ke dalam arraylist dan tidak terjadi duplikat
                boolean isExist = false;
                for (Lecturer lec : lecturer) {
                    if (lec.getId().equals(data[1])) {
                        isExist = true;
                        break;
                    }
                }       
                if (!isExist)
                lecturer.add(l);

            }else if (data[0].equals("enrollment-grade")){
                //enrollment-grade#12S20999#IF1234#A#2020
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
                    
                    continue;
                }
                if (!studentExists) {
                    
                    continue;
                }
                // Add enrollment only if course and student exist
                for (Enrollment e : enrollment) {
                    if (e.getStudent().equals(data[2]) && e.getCourse().equals(data[1]) && e.getAcademicYear().equals(data[3])) {
                        e.setGrade(data[5]);
                    }
                }
            }else if (data[0].equals("student-details")) {
                //student-details#12S20999
                // Check if student exists
                boolean studentExists = false;
                for (Student s : student) {
                    if (s.getId().equals(data[1])) {
                        studentExists = true;
                        //cek gpa
                        double gpa = 0;
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
                                        gpa += 4 * c.getCredit();
                                    } else if (grade.equals("AB")) {
                                        gpa += 3.5 * c.getCredit();
                                    } else if (grade.equals("B")) {
                                        gpa += 3 * c.getCredit();
                                    } else if (grade.equals("BC")) {
                                        gpa += 2.5 * c.getCredit();
                                    } else if (grade.equals("C")) {
                                        gpa += 2 * c.getCredit();
                                    } else if (grade.equals("D")) {
                                        gpa += 1 * c.getCredit();
                                    }
                                }
                            }
                        }
                        //hitung gpa
                        // Set GPA and total credits for the student
                        if (totalCredit > 0) {
                            s.setGpa(gpa / totalCredit);
                        } else {
                            s.setGpa(0); // to avoid division by zero
                        }
                        s.setTotalCredit(totalCredit);

                        System.out.println(s.getId() + "|" + s.getName() + "|" + s.getYear() + "|" + s.getStudyProgram() + "|" + String.format("%.2f", s.getGpa()) + "|" + s.getTotalCredit());

                    }
                }
                // If student doesn't exist, print error message
                if (!studentExists) {
                    System.out.println("Student not found");
                }
            }

            // hentikan aplikasi jika input 
            if (input.equals("---")) {
                break;
            }
            
    }

        
        

        //print lecturer
        for (Lecturer l : lecturer) {
            System.out.println(l.getId() + "|" + l.getName() + "|" + l.getInitial() + "|" + l.getEmail() + "|" + l.getStudyProgram());
        
        }
        for (Course c : course) {
            StringBuilder lecturerDetails = new StringBuilder();
            for (String initial : c.getlecturerInitialList()) {
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

        //print sudent details <student-id>|<name>|<year>|<study-program>|<gpa>|<total-credit
         //print student
         for (Student stu : student){
            System.out.println(stu);
        }

        
        //print enrollment
        for (Enrollment e : enrollment) {
            System.out.println(e.getCourse() + "|" + e.getStudent()  + "|" + e.getAcademicYear()+"|" +e.getSemester() + "|" + e.getGrade());
        }

        
    
        masukan.close();
    }


    
}

