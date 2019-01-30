package biz.thinkcomputers.shdc;


public class WebServiceClass {

   // private final String NAMESPACE = "http://tempuri.org/";
    private final String NAMESPACE="http://tempuri.org/";
    private final String URL = "http://shdc.icampus360.in/webservices/webservice.asmx";
   // private final String URL = "http://icampus360.in/icampuswebservice/icampus360.asmx";
    //private final String SOAP_ACTION = "http://tempuri.org/loginCheck";
   // private final String METHOD_NAME1 = "WS_LoginWebService";
    //private final String SOAP_ACTION="http://webservice.icampus360.in/LoginXml";

    private final String METHOD_NAME1 = "login";
    private final String METHOD_NAME2 = "InsertLoginHistory_GetEmployeeDetails";
    private final String METHOD_NAME3 = "bindDegree";
    private final String METHOD_NAME4 = "bindAcademicYear";
    private final String METHOD_NAME5 = "bindStreamAccToDegree";
    private final String METHOD_NAME6 = "bindSemesterAccToStream";
    private final String METHOD_NAME7 = "bindSectionAccToSemester";
    private final String METHOD_NAME8 = "bindSubjectsAccToSection";
    private final String METHOD_NAME9 = "bindLectureTimeAccToSubject";
    private final String METHOD_NAME10 = "bindLectureTypeAccToLectureTime";

    private final String METHOD_NAME12 = "getMarkedAttendance";
    private final String METHOD_NAME13 = "BindStudentForAttend";
    private final String METHOD_NAME14 = "viewAttendance";
    private final String METHOD_NAME15 = "viewAttendanceForStudent ";
    private final String METHOD_NAME16 = "studentProfile";
    private final String METHOD_NAME17 = "GetStudentByID";




    public WebServiceClass() {
    }

    public String getNAMESPACE() {
        return NAMESPACE;
    }

    public String getURL() {
        return URL;
    }

    public String getMETHOD_NAME1() {
        return METHOD_NAME1;
    }

    public String getMETHOD_NAME2() {
        return METHOD_NAME2;
    }

    public String getMETHOD_NAME3() {
        return METHOD_NAME3;
    }

    public String getMETHOD_NAME4() {
        return METHOD_NAME4;
    }

    public String getMETHOD_NAME5() {
        return METHOD_NAME5;
    }

    public String getMETHOD_NAME6() {
        return METHOD_NAME6;
    }

    public String getMETHOD_NAME7() {
        return METHOD_NAME7;
    }

    public String getMETHOD_NAME8() {
        return METHOD_NAME8;
    }

    public String getMETHOD_NAME9() {
        return METHOD_NAME9;
    }

    public String getMETHOD_NAME10() {
        return METHOD_NAME10;
    }


    public String getMETHOD_NAME12() {
        return METHOD_NAME12;
    }

    public String getMETHOD_NAME13() {
        return METHOD_NAME13;
    }

    public String getMETHOD_NAME14() {
        return METHOD_NAME14;
    }

    public String getMETHOD_NAME15() {
        return METHOD_NAME15;
    }

    public String getMETHOD_NAME16() {
        return METHOD_NAME16;
    }

    public String getMETHOD_NAME17() {
        return METHOD_NAME17;
    }
}

