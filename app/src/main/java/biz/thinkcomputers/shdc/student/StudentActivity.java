package biz.thinkcomputers.shdc.student;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import biz.thinkcomputers.shdc.CustomGridViewAdapter;
import biz.thinkcomputers.shdc.LoginActivity;
import biz.thinkcomputers.shdc.R;
import biz.thinkcomputers.shdc.StudentViewAttendanceDaily;
import biz.thinkcomputers.shdc.WebServiceClass;
import biz.thinkcomputers.shdc.student.studentprofile.StudentProfileView;


public class StudentActivity extends AppCompatActivity {


    GridView androidGridView;

    int college_Id,student_id,role;
    int usr_Id,academicyearid;
    WebServiceClass webservice2 = new WebServiceClass();

    int[] gridViewImageId = {
            R.drawable.profile,
            R.drawable.view_attendance,
            R.drawable.view_result,
            R.drawable.view_timetable,
            R.drawable.circular,
            R.drawable.library
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        Intent reciverIntent = getIntent();
        college_Id = reciverIntent.getIntExtra("college_id",0);
        usr_Id = reciverIntent.getIntExtra("usr_Id", 1);
        role = reciverIntent.getIntExtra("Usr_RoleId",2);
        academicyearid = reciverIntent.getIntExtra("academicyearid",2);

        /*Intent reciverIntent1 = getIntent();
        student_id*/

        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                SoapObject request = new SoapObject(webservice2.getNAMESPACE(), webservice2.getMETHOD_NAME2());
                request.addProperty("college_Id", college_Id);
                request.addProperty("user_Id", usr_Id);
                request.addProperty("user_RoleId",role);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                HttpTransportSE androidTransport = new HttpTransportSE(webservice2.getURL());
                try {
                    androidTransport.call(webservice2.getNAMESPACE()+webservice2.getMETHOD_NAME2(),envelope);
                    SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                    String jsonRootObject = response.toString();

                    JSONArray jsonArray = new JSONArray(jsonRootObject);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        student_id = object.getInt("Stdnt_Id");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;

            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);


            }
        }.execute();

        CustomGridViewAdapter adapterViewAndroid = new CustomGridViewAdapter(StudentActivity.this, gridViewImageId);
        androidGridView = (GridView) findViewById(R.id.grid_view_image_student);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                if(position == 0) {
                    Intent intentProfile = new Intent(StudentActivity.this, StudentProfileView.class);
                    intentProfile.putExtra("college_id",college_Id);
                    intentProfile.putExtra("usr_Id", usr_Id);
                    intentProfile.putExtra("student_id",student_id);
                    intentProfile.putExtra("academicyearid", academicyearid);
                    //intentProfile.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                    startActivity(intentProfile);



                }
                if(position == 1){
                    Intent intentAttendance = new Intent(StudentActivity.this, StudentViewAttendanceDaily.class);
                    intentAttendance.putExtra("college_id",college_Id);
                    intentAttendance.putExtra("usr_Id", usr_Id);
                    intentAttendance.putExtra("student_id",student_id);
                    intentAttendance.putExtra("user_RoleId",role);
                    intentAttendance.putExtra("academicyearid", academicyearid);
                    startActivity(intentAttendance);

                }
            /*
                if(position == 2){
                    Intent intentSubjectList = new Intent(StudentActivity.this, SubjectList.class);
                    startActivity(intentSubjectList);

                }
                if(position == 3){
                    Intent intentTimeTable = new Intent(StudentActivity.this, in.thinkcomputers.icampus.TimeTableView.class);
                    startActivity(intentTimeTable);
                }*/
            }
        });
    }

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

            Intent i=new Intent(StudentActivity.this,LoginActivity.class);
            startActivity(i);

            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
    @Override
    protected void onSaveInstanceState (Bundle savedInstance) {


        savedInstance.putInt("college_Id",college_Id);
        savedInstance.putInt("student_id",student_id);
        savedInstance.putInt("role",role);
        savedInstance.putInt("usr_Id",usr_Id);
        savedInstance.putInt("academicyearid",academicyearid);

        super.onSaveInstanceState(savedInstance);
    }

    @Override
    protected void onRestoreInstanceState (Bundle savedInstanceState) {


        college_Id = savedInstanceState.getInt("college_Id");
        student_id = savedInstanceState.getInt("student_id");
        role = savedInstanceState.getInt("role");
        usr_Id = savedInstanceState.getInt("usr_Id");
        academicyearid = savedInstanceState.getInt("academicyearid");

        super.onRestoreInstanceState(savedInstanceState);
    }

}
