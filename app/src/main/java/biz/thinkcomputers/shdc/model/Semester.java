package biz.thinkcomputers.shdc.model;

/**
 * Created by Dheeraj on 8/18/2017.
 */
public class Semester {
    private int Semester_Id;
    private String Semester_Name;

    public int getSemester_Id() {
        return Semester_Id;
    }

    public void setSemester_Id(int semester_Id) {
        Semester_Id = semester_Id;
    }

    public String getSemester_Name() {
        return Semester_Name;
    }

    public void setSemester_Name(String semester_Name) {
        Semester_Name = semester_Name;
    }
}
