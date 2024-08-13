package academic.model;

public class Lecturer {

    //deklarsi variabel
    private String id;
    private String name;
    private String initial;
    private String email; 
    private String studyProgram;
    
    //konstruktor
    public Lecturer(String id, String name, String initial, String email, String studyProgram) {
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

    public String getEmail() {
        return email;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    
}
