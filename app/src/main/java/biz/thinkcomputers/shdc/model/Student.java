package biz.thinkcomputers.shdc.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;


public class Student implements Serializable {
    private String sNo;
    private String stdnt_Id;
    private String sRollNo;
    private String student_name;
    private String StAttnd_Id;
    private String Acadmic_Id;
    private String status;
    private boolean isChecked;
    private int markObtain;
    private int maxMark;
    private String totalAttendance;
    private int percentage;
    private String Course_Name;
    private String Course_Id;
    private String CodeName;
    private String color_present;
    private String color_absent;
    private int present;
    private int absent;
    private int TotalLectures;

    public Student(String sNo,String studentId,String sRollNo, String student_name,String StAttnd_Id,String Acadmic_Id,boolean isChecked) {
        this.sNo = sNo;
        this.stdnt_Id = studentId;
        this.sRollNo = sRollNo;
        this.student_name = student_name;
        this.StAttnd_Id = StAttnd_Id;
        this.Acadmic_Id = Acadmic_Id;
        this.isChecked = isChecked;
    }

    public Student(String sNo,String sRollNo, String student_name) {
        this.stdnt_Id = sNo;
        this.sRollNo = sRollNo;
        this.student_name = student_name;

    }
    public Student(String sNo,String studentId,String sRollNo, String student_name,String Acadmic_Id,String status) {
        this.sNo = sNo;
        this.stdnt_Id = studentId;
        this.sRollNo = sRollNo;
        this.student_name = student_name;
        this.Acadmic_Id = Acadmic_Id;
        this.status = status;

    }

    public Student(String sNo,int TotalLectures,int percentage,int present,int absent,String CodeName) {
        this.sNo = sNo;
        this.TotalLectures = TotalLectures;
        this.percentage=percentage;
        this.present=present;
        this.absent=absent;
        this.CodeName = CodeName;

    }
    public Student(String sNo,String studentId,String sRollNo,String student_name,int markObtain,int maxMark){
        this.sNo = sNo;
        this.stdnt_Id = studentId;
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

    public String getStAttnd_Id() {
        return StAttnd_Id;
    }

    public void setStAttnd_Id(String stAttnd_Id) {
        StAttnd_Id = stAttnd_Id;
    }

    public String getAcadmic_Id() {
        return Acadmic_Id;
    }

    public void setAcadmic_Id(String acadmic_Id) {
        Acadmic_Id = acadmic_Id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo;
    }

    public String getTotalAttendance() {
        return totalAttendance;
    }

    public void setTotalAttendance(String totalAttendance) {
        this.totalAttendance = totalAttendance;
    }

    public String getCourse_Name() {
        return Course_Name;
    }

    public void setCourse_Name(String course_Name) {
        Course_Name = course_Name;
    }

    public String getCourse_Id() {
        return Course_Id;
    }

    public void setCourse_Id(String course_Id) {
        Course_Id = course_Id;
    }

    public String getCodeName() {
        return CodeName;
    }

    public void setCodeName(String codeName) {
        CodeName = codeName;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getColor_present() {
        return color_present;
    }

    public void setColor_present(String color_present) {
        this.color_present = color_present;
    }

    public String getColor_absent() {
        return color_absent;
    }

    public void setColor_absent(String color_absent) {
        this.color_absent = color_absent;
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }

    public int getAbsent() {
        return absent;
    }

    public void setAbsent(int absent) {
        this.absent = absent;
    }

    public int getTotalLectures() {
        return TotalLectures;
    }

    public void setTotalLectures(int totalLectures) {
        TotalLectures = totalLectures;
    }

   //endend
}
