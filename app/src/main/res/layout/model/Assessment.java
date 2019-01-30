package layout.model;

/**
 * Created by DELL1545 on 4/13/2017.
 */
public class Assessment {
    private int Assessment_Id;
    private String Assessment_Name;
    private int Assessment_Sequence;

    public int getAssessment_Id() {
        return Assessment_Id;
    }

    public void setAssessment_Id(int assessment_Id) {
        Assessment_Id = assessment_Id;
    }

    public String getAssessment_Name() {
        return Assessment_Name;
    }

    public void setAssessment_Name(String assessment_Name) {
        Assessment_Name = assessment_Name;
    }

    public int getAssessment_Sequence() {
        return Assessment_Sequence;
    }

    public void setAssessment_Sequence(int assessment_Sequence) {
        Assessment_Sequence = assessment_Sequence;
    }
}
