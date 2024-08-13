
package academic.model;

import java.util.Objects; 


public class Lecturer {
    //Deklarasi variabel id , name , intial , Study Program
    private String id;
    private String name;
    private String initial;
    private String email;
    private String studyProgram;   

    //konstruktor
    public Lecturer(String id, String name, String initial, String email , String studyProgram) {
        this.id = id;
        this.name = name;
        this.initial = initial;
        this.email = email;
        this.studyProgram = studyProgram;
    }

    //getter
    public String getId() {
        return id;
    } 

    public String getName() {
        return name;
    }

    public String getInitial() {
        return initial;
    }

    public String getStudyProgram() {
        return studyProgram;
    } 

    public String getEmail() {
        return email; 
    } 
  
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecturer lecturer = (Lecturer) o;
        return Objects.equals(id, lecturer.id) &&
               Objects.equals(name, lecturer.name) &&
               Objects.equals(initial, lecturer.initial) &&
               Objects.equals(email, lecturer.email) &&
               Objects.equals(studyProgram, lecturer.studyProgram);
    }

@Override
public int hashCode() {
    return Objects.hash(id, name, initial, email, studyProgram);
}

}
