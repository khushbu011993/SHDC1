package biz.thinkcomputers.shdc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
import java.util.HashMap;
import java.util.List;


import biz.thinkcomputers.shdc.model.Student;
import biz.thinkcomputers.shdc.teacher.TeacherActivity;
import biz.thinkcomputers.shdc.teacher.markattendance.MarkAttendanceListViewAdapter;


public class MarkAttendanceStudentList extends AppCompatActivity  {

    WebServiceClass webServiceClass6 = new WebServiceClass();
    TextView text_date;
    int user_id,college_id,acadmicYr_Id,degree_id,section_id,emp_Id,stream_Id,course_Id;
    String stdnt_name,stdnt_Id,sRollNo,semester,StAttnd_Id,Acadmic_Id,lectureTime,lectureType,responseValue;
    int sNo;
    int counter = 0;
    String serialNo;
    String date;
   // ArrayList<HashMap<String,List<String>>> arrayList;
    List<Student> list = new ArrayList<>();
    List<Student> studentClass = new ArrayList<>();
    Boolean mark = true;
    ProgressDialog dialog ;
    ListView listView;
    Button save;
    public MarkAttendanceListViewAdapter mAdapter;
    JSONArray jsonArray = new JSONArray();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);

        Intent receiveIntent = getIntent();

        college_id = receiveIntent.getIntExtra("college_id",0);
        user_id = receiveIntent.getIntExtra("usr_Id",1);
        acadmicYr_Id = receiveIntent.getIntExtra("acadmicYr_Id",2);
        section_id = receiveIntent.getIntExtra("section_id", 3);
        semester = receiveIntent.getStringExtra("semester");
        stream_Id = receiveIntent.getIntExtra("stream_Id", 4);
        course_Id = receiveIntent.getIntExtra("course_Id", 5);
        date = receiveIntent.getStringExtra("text_date");
        lectureTime = receiveIntent.getStringExtra("lectureTime");
        lectureType = receiveIntent.getStringExtra("lectureType");


        save = (Button) findViewById(R.id.btnSave);

        text_date = (TextView) findViewById(R.id.attendance_textView_date);
        text_date.setText(date);
        listView = (ListView) findViewById(R.id.listView2);

        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                SoapObject request = new SoapObject(webServiceClass6.getNAMESPACE(), webServiceClass6.getMETHOD_NAME13());

                request.addProperty("college_Id", college_id);
                request.addProperty("acadmicYr_Id", acadmicYr_Id);
                request.addProperty("section_Id", section_id);
                request.addProperty("semester", semester);
                request.addProperty("stream_Id", stream_Id);
                request.addProperty("course_Id", course_Id);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                HttpTransportSE httpTransportSE = new HttpTransportSE(webServiceClass6.getURL());
                try {
                    httpTransportSE.call(webServiceClass6.getNAMESPACE()+webServiceClass6.getMETHOD_NAME13(), envelope);
                    SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                    String result = response.toString();
                   // arrayList = new ArrayList<HashMap<String, List<String>>>();
                    //List<String> stringList = new ArrayList<String>();
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {
                       // HashMap<String, List<String>> map = new HashMap<String, List<String>>();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        sNo = ++counter;
                        serialNo = Integer.toString(sNo);
                        stdnt_Id = jsonObject.getString("Stdnt_Id");
                        stdnt_name = jsonObject.getString("Stdnt_Name");
                        //sNo = jsonObject.getString("SrNo");

                        sRollNo = jsonObject.getString("Stdnt_RegNo");
                        StAttnd_Id = jsonObject.getString("StAttnd_Id");
                        Acadmic_Id = jsonObject.getString("Acadmic_Id");

                        //stringList.add(stdnt_Id);
                        //stringList.add(sNo);
                        //stringList.add(sRollNo);
                        //stringList.add(stdnt_name);
                        Student student = new Student(serialNo,stdnt_Id,sRollNo,stdnt_name,StAttnd_Id,Acadmic_Id,mark);
                        list.add(student);
                       // map.put(stdnt_Id,list);
                       // arrayList.add(map);


                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                dialog.dismiss();
                dialog.setProgress(View.GONE);

                mAdapter = new MarkAttendanceListViewAdapter(MarkAttendanceStudentList.this,R.layout.list_markattendance,list);

                if(mAdapter.isEmpty()){
                    String msg = "No Student Available";
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                }else{
                    listView.setAdapter(mAdapter);
                }

            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = new ProgressDialog(MarkAttendanceStudentList.this);
                dialog.setMessage("Loading...");
                dialog.setIndeterminate(false);
                dialog.show();
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }
        }.execute();

       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Student obj = (Student) listView.getAdapter().getItem(position);
                obj.setIsChecked(false);
            }
        });*/


       ///create by khushbu ////////28/1/2019
        save.setOnClickListener((View.OnClickListener) this);
    }


    //attendan
        /*String data = "";
        List<Student> stList = ((MarkAttendanceListViewAdapter) mAdapter)
                .getStudentist();
        ArrayList<Student> absentStudent = new ArrayList<>();

        for (int i = 0; i < stList.size(); i++) {
            Student singleStudent = stList.get(i);
            if (singleStudent.isChecked() == true) {

                //data = data + "\n" + singleStudent.getStudent_name().toString();

            }else{
                data = data + "\n" + singleStudent.getStudent_name().toString();

                absentStudent.add(singleStudent);

            }

        }

        JSONArray jsonArray = new JSONArray(absentStudent);
        String dataToSend = jsonArray.toString();

        if(data.isEmpty()){
            Toast.makeText(MarkAttendanceStudentList.this,
                    "All Students Present \n" + data, Toast.LENGTH_LONG)
                    .show();

        }else{

            Toast.makeText(MarkAttendanceStudentList.this,
                    "Absent Students: \n" + data, Toast.LENGTH_LONG)
                    .show();
        }*/

  /*  @Override
    public void onBackPressed() {

        Intent i=new Intent(MarkAttendanceStudentList.this,TeacherActivity.class);
        startActivity(i);
       finish();

    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {

            Intent i=new Intent(MarkAttendanceStudentList.this,LoginActivity.class);
            startActivity(i);

            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    }


