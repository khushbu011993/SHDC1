package biz.thinkcomputers.shdc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import biz.thinkcomputers.shdc.adapter.SemesterAdapter;
import biz.thinkcomputers.shdc.model.Degree;
import biz.thinkcomputers.shdc.model.Section;
import biz.thinkcomputers.shdc.model.Semester;
import biz.thinkcomputers.shdc.model.Stream;
import biz.thinkcomputers.shdc.model.Student;
import biz.thinkcomputers.shdc.student.viewattendance.StudentViewAttendanceListViewAdapter;

public class StudentViewAttendanceDaily extends AppCompatActivity {


    ListView listView;
    List<Student> list = new ArrayList<>();
    private StudentViewAttendanceListViewAdapter adapter;
    int college_Id,student_id,role,Degree_Id,Stream_Id,Section_ID;
    int usr_Id,academicyearid;
    String semester;
    int sNo;
    int counter = 0;
    String serialNo;
    String Current_Semester;
    int totalLectures;
    int percentage;
    int present;
    int absent;
    String CodeName;
    ProgressDialog dialog;
    WebServiceClass webServiceClass4 = new WebServiceClass();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_attendance);

        Intent reciverIntent = getIntent();
        college_Id = reciverIntent.getIntExtra("college_id",0);
        usr_Id = reciverIntent.getIntExtra("usr_Id", 1);
        student_id = reciverIntent.getIntExtra("student_id",2);
        role = reciverIntent.getIntExtra("Usr_RoleId",3);
        academicyearid = reciverIntent.getIntExtra("academicyearid",4);

        listView = (ListView) findViewById(R.id.listViewStudentAttendanceView);


        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                SoapObject request = new SoapObject(webServiceClass4.getNAMESPACE(), webServiceClass4.getMETHOD_NAME17());
                request.addProperty("college_Id", college_Id);
                request.addProperty("stdnt_Id", student_id);


                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                HttpTransportSE androidTransport = new HttpTransportSE(webServiceClass4.getURL());
                try {
                    androidTransport.call(webServiceClass4.getNAMESPACE()+webServiceClass4.getMETHOD_NAME17(),envelope);
                    SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                    String jsonRootObject = response.toString();

                    JSONArray jsonArray = new JSONArray(jsonRootObject);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        Current_Semester = object.getString("Current_Semester");
                        Section_ID = object.getInt("section_Id");

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;

            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                //adapter = new StudentViewAttendanceListViewAdapter(StudentViewAttendanceDaily.this,R.layout.list_item_student_view_attendance,list);
                //listView.setAdapter(adapter);


            }
        }.execute();

        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                SoapObject request = new SoapObject(webServiceClass4.getNAMESPACE(), webServiceClass4.getMETHOD_NAME15());
                request.addProperty("college_Id", college_Id);
                request.addProperty("acadmicYr_Id", academicyearid);
                request.addProperty("semester", Current_Semester);
                request.addProperty("section_Id", Section_ID);
                request.addProperty("stdnt_Id", student_id);



                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                HttpTransportSE androidTransport = new HttpTransportSE(webServiceClass4.getURL());
                try {
                    androidTransport.call(webServiceClass4.getNAMESPACE()+webServiceClass4.getMETHOD_NAME15(),envelope);
                    SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                    String jsonRootObject = response.toString();

                    JSONArray jsonArray = new JSONArray(jsonRootObject);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        //student_id = object.getInt("Stdnt_Id");


                        sNo = ++counter;
                        serialNo = Integer.toString(sNo);
                        totalLectures=object.getInt("TotalLectures");
                        percentage=object.getInt("percentage");
                        present=object.getInt("_present");
                        absent=object.getInt("_absent");

                        CodeName = object.getString("CodeName");
                        //Student student = new Student(serialNo,student_id,totalAttendance,Course_Id,Course_Name,Acadmic_Id,CodeName);
                        Student student = new Student(serialNo,totalLectures, percentage,present,absent,CodeName);
                        list.add(student);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;

            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                dialog.dismiss();
                dialog.setProgress(View.GONE);

                adapter = new StudentViewAttendanceListViewAdapter(StudentViewAttendanceDaily.this,R.layout.list_item_student_view_attendance,list);
                listView.setAdapter(adapter);


            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog=new ProgressDialog(StudentViewAttendanceDaily.this);
                dialog.setMessage("Loading...");
                dialog.setIndeterminate(false);
                dialog.show();
            }
        }.execute();

        //sNo = ++counter;
        /*sNo = counter;
        serialNo = Integer.toString(sNo);
        studentId = String.valueOf(student_id);
        //Acadmic_Id = object.getString("Acadmic_Id");
        Acadmic_Id = "001";
       // totalAttendance = object.getString("TotalAttendance");
        totalAttendance = "6";
        //percentage = "80";
        //color_present = "4";
        //color_absent = "1";
        //Course_Id = object.getString("Course_Id");
        Course_Id = "1";
        //Course_Name = object.getString("Course_Name");
        Course_Name="Special 26";
        //CodeName = object.getString("CodeName");
        CodeName = "abc";

        Student student = new Student(serialNo,studentId,totalAttendance, Course_Id,Course_Name,Acadmic_Id,CodeName);
        list.add(student);

        adapter = new StudentViewAttendanceListViewAdapter(StudentViewAttendanceDaily.this,R.layout.list_item_student_view_attendance,list);
        listView.setAdapter(adapter);
*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter = new StudentViewAttendanceListViewAdapter(StudentViewAttendanceDaily.this,R.layout.list_item_student_view_attendance,list);
        listView.setAdapter(adapter);
    }
}
