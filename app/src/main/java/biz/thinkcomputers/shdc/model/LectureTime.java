package biz.thinkcomputers.shdc.model;

/**
 * Created by think on 8/18/2017.
 */
public class LectureTime {

    private int LectureTime_Id;
    private String LectureTime_Name;
    private String LectureTime_Type;

    public int getLectureTime_Id() {
        return LectureTime_Id;
    }

    public void setLectureTime_Id(int lectureTime_Id) {
        LectureTime_Id = lectureTime_Id;
    }

    public String getLectureTime_Name() {
        return LectureTime_Name;
    }

    public void setLectureTime_Name(String lectureTime_Name) {
        LectureTime_Name = lectureTime_Name;
    }

    public String getLectureTime_Type() {
        return LectureTime_Type;
    }

    public void setLectureTime_Type(String lectureTime_Type) {
        LectureTime_Type = lectureTime_Type;
    }
}
