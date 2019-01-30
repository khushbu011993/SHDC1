package biz.thinkcomputers.shdc.model;

/**
 * Created by think on 8/9/2017.
 */
public class Circular {

    private int circularId;
    private String circularType;
    private String circularDiscription;
    private String circularFromDate;
    private String circularToDate;

    public int getCircularId() {
        return circularId;
    }

    public void setCircularId(int circularId) {
        this.circularId = circularId;
    }

    public String getCircularDiscription() {
        return circularDiscription;
    }

    public void setCircularDiscription(String circularDiscription) {
        this.circularDiscription = circularDiscription;
    }

    public String getCircularType() {
        return circularType;
    }

    public void setCircularType(String circularType) {
        this.circularType = circularType;
    }

    public String getCircularFromDate() {
        return circularFromDate;
    }

    public void setCircularFromDate(String circularFromDate) {
        this.circularFromDate = circularFromDate;
    }

    public String getCircularToDate() {
        return circularToDate;
    }

    public void setCircularToDate(String circularToDate) {
        this.circularToDate = circularToDate;
    }
}
