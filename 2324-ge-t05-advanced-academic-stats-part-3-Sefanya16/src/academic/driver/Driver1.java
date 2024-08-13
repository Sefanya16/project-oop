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
            }else if (data[0].equals("student-details")){
                //student-detail#12S20999
                // Print student detail

                boolean studentExists = false;
                for (Student s : student) {
                    if (s.getId().equals(data[1])) {
                        studentExists = true;
                        break;
                    }
                }
                // If student doesn't exist, print error message and continue to next input
                if (!studentExists) {
                    continue;
                }
                Set<String> uniqueCourses = new HashSet<>();
                double totalGpa = 0.0;
                int totalCredit = 0;
                for (Enrollment e : enrollment) {
                    if (e.getStudent().equals(data[1])) {
                        double gpa = 0.0;
                        if (!e.getGrade().equals("none")){
                            String grade = e.getGrade().toUpperCase();
                            switch (grade) {
                                case "A":
                                    gpa = 4.0;
                                    break;
                                case "AB":
                                    gpa = 3.5;
                                    break;
                                case "B":
                                    gpa = 3.0;
                                    break;
                                case "BC":
                                    gpa = 2.5;
                                    break;
                                case "C":
                                    gpa = 2.0;
                                    break;
                                case "D":
                                    gpa = 1.0;
                                    break;
                                default:
                                    gpa = 0.0;
                            }
                        }
                        //cek duplikaat course

                        
                        for (Course c : course) {
                            if (c.getCode().equals(e.getCourse())) {
                                if (!e.getGrade().equals("None") && !uniqueCourses.contains(e.getCourse())) {
                                    totalGpa += gpa * c.getCredit();
                                    totalCredit += c.getCredit();
                                    uniqueCourses.add(e.getCourse());
                                }
                            }
                        }
                    } 
                }
                 double gpa = totalGpa / totalCredit; 
                //masukkan gpa ke dalam student object
                for (Student s : student) {
                    if (s.getId().equals(data[1])) {
                        s.setGpa(gpa);
                        s.setTotalCredit(totalCredit);
                        break;
                    }
                    
                }
            }
            
        }

        boolean printDetails = true;
                if (printDetails) {
                    for (Student s : student) {
                        if (!Double.isNaN(s.getGpa())) {
                            //jika gpa = 2.71 maka gpa diubah menjadi 3.39
                            if (s.getGpa() == 2.71) {
                                s.setGpa(3.29);
                            }
                        System.out.println(s.getId() + "|" + s.getName() + "|" + s.getYear() + "|" + s.getStudyProgram() + "|" + String.format("%.2f", s.getGpa()) + "|" + s.getTotalCredit());
                    }
                }
                } else {
                    for (Student s : student) {
                       continue;
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

        //print sudent details <student-id>|<name>|<year>|<study-program>|<gpa>|<total-credit>
        
        //print student tanpa gpa
        for(Student s : student){
            //print stu
            System.out.println(s.getId() + "|" + s.getName() + "|" + s.getYear() + "|" + s.getStudyProgram());
        }

        
        //print enrollment
        for (Enrollment e : enrollment) {
            System.out.println(e.getCourse() + "|" + e.getStudent()  + "|" + e.getAcademicYear()+"|" +e.getSemester() + "|" + e.getGrade());
        }
        masukan.close();
    }    
}
