package layout.model;

/**
 * Created by DELL1545 on 4/21/2017.
 */
public class Student {
    private String stdnt_Id;
    private String sRollNo;
    private String student_name;
    private boolean isChecked;
    private int markObtain;
    private int maxMark;

    public Student(String sNo,String sRollNo, String student_name, boolean isChecked) {
        this.stdnt_Id = sNo;
        this.sRollNo = sRollNo;
        this.student_name = student_name;
        this.isChecked = isChecked;
    }

    public Student(String sNo,String sRollNo, String student_name) {
        this.stdnt_Id = sNo;
        this.sRollNo = sRollNo;
        this.student_name = student_name;

    }

    public Student(String sNo,String sRollNo,String student_name,int markObtain,int maxMark){
        this.stdnt_Id = sNo;
        this.sRollNo = sRollNo;
        this.student_name = student_name;
        this.markObtain = markObtain;
        this.maxMark = maxMark;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getsRollNo() {
        return sRollNo;
    }

    public void setsRollNo(String sRollNo) {
        this.sRollNo = sRollNo;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String getStdnt_Id() {
        return stdnt_Id;
    }

    public void setStdnt_Id(String stdnt_Id) {
        this.stdnt_Id = stdnt_Id;
    }

    public int getMarkObtain() {
        return markObtain;
    }

    public void setMarkObtain(int markObtain) {
        this.markObtain = markObtain;
    }

    public int getMaxMark() {
        return maxMark;
    }

    public void setMaxMark(int maxMark) {
        this.maxMark = maxMark;
    }
}
