package biz.thinkcomputers.shdc.teacher.viewattendance;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

import biz.thinkcomputers.shdc.LoginActivity;
import biz.thinkcomputers.shdc.R;
import biz.thinkcomputers.shdc.WebServiceClass;
import biz.thinkcomputers.shdc.model.Student;
import biz.thinkcomputers.shdc.teacher.TeacherActivity;

public class ViewClassAttendance extends AppCompatActivity {

    WebServiceClass webServiceClass8 = new WebServiceClass();
    TextView text_date;
    int user_id,college_id,acadmicYr_Id,degree_id,section_id,emp_Id,stream_Id,course_Id;
    String stdnt_name,stdnt_Id,sRollNo,semester,StAttnd_Id,Acadmic_Id,present;
    int sNo;
    String serialNo;
    String date;
    // ArrayList<HashMap<String,List<String>>> arrayList;
    List<Student> list = new ArrayList<>();
    List<Student> studentClass = new ArrayList<>();
    Boolean mark = true;
    ProgressDialog dialog ;
    ListView listView;
    Button save;
    private ViewAttendanceListViewAdapter mAdapter;
    JSONArray jsonArray = new JSONArray();
    Button btnOk;
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_attendance);

        Intent receiveIntent = getIntent();

        college_id = receiveIntent.getIntExtra("college_id",0);
        user_id = receiveIntent.getIntExtra("usr_Id",1);
        acadmicYr_Id = receiveIntent.getIntExtra("acadmicYr_Id",2);
        section_id = receiveIntent.getIntExtra("section_id", 3);
        semester = receiveIntent.getStringExtra("semester");
        stream_Id = receiveIntent.getIntExtra("stream_Id", 4);
        course_Id = receiveIntent.getIntExtra("course_Id", 5);
        date = receiveIntent.getStringExtra("text_date");

        text_date = (TextView) findViewById(R.id.today_date_textView);
        text_date.setText(date);
        listView = (ListView) findViewById(R.id.listView4);
       // btnOk = (Button) findViewById(R.id.btnOk);

        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                SoapObject request = new SoapObject(webServiceClass8.getNAMESPACE(), webServiceClass8.getMETHOD_NAME14());

                request.addProperty("college_Id", college_id);
                request.addProperty("acadmicYr_Id", acadmicYr_Id);
                request.addProperty("section_Id", section_id);
                request.addProperty("semester", semester);
                request.addProperty("stream_Id", stream_Id);
                request.addProperty("course_Id", course_Id);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                HttpTransportSE httpTransportSE = new HttpTransportSE(webServiceClass8.getURL());
                try {
                    httpTransportSE.call(webServiceClass8.getNAMESPACE()+webServiceClass8.getMETHOD_NAME14(), envelope);
                    SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                    String result = response.toString();
                    // arrayList = new ArrayList<HashMap<String, List<String>>>();
                    //List<String> stringList = new ArrayList<String>();
                     jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        sNo = ++counter;
                        serialNo = Integer.toString(sNo);
                        stdnt_Id = jsonObject.getString("Stdnt_Id");
                        stdnt_name = jsonObject.getString("Stdnt_Name");
                        //sNo = jsonObject.getString("SrNo");

                        sRollNo = jsonObject.getString("Stdnt_RegNo");
                        present = jsonObject.getString("Present");
                        if(present.equals("0")){
                            present = "Absent";
                        }else{

                            present = "Present";
                        }
                        Acadmic_Id = jsonObject.getString("Acadmic_Id");

                        //stringList.add(stdnt_Id);
                        //stringList.add(sNo);
                        //stringList.add(sRollNo);
                        //stringList.add(stdnt_name);
                        Student student = new Student(serialNo,stdnt_Id,sRollNo,stdnt_name,Acadmic_Id,present);
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

                mAdapter = new ViewAttendanceListViewAdapter(ViewClassAttendance.this,R.layout.list_viewattendance,list);

                if(mAdapter.isEmpty()){
                        String msg = "Today Attendance Not Marked";
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                }else {
                    listView.setAdapter(mAdapter);
                }


            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = new ProgressDialog(ViewClassAttendance.this);
                dialog.setMessage("Loading...");
                dialog.setIndeterminate(false);
                dialog.show();
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }
        }.execute();

        //btnOk.setOnClickListener(this);
    }

    /*@Override
    public void onClick(View v) {

        if(v == btnOk){
            Intent intentOk = new Intent(ViewClassAttendance.this, TeacherActivity.class);
            intentOk.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentOk);
        }
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

            Intent i=new Intent(ViewClassAttendance.this,LoginActivity.class);
            startActivity(i);

            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
