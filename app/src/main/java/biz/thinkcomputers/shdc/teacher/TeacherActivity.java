package biz.thinkcomputers.shdc.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;



import biz.thinkcomputers.shdc.CustomGridViewAdapter;
import biz.thinkcomputers.shdc.LoginActivity;

import biz.thinkcomputers.shdc.MarkAttendanceClassActivity;
import biz.thinkcomputers.shdc.R;
import biz.thinkcomputers.shdc.teacher.viewattendance.ViewAttendanceClassActivity;


public class TeacherActivity extends AppCompatActivity {

    int college_id,id,academicyearid,role;
    GridView androidGridView;


    int[] gridViewImageId = {
            R.drawable.view_attendance,
            R.drawable.mark_attendance,
            R.drawable.view_result,
            R.drawable.mark_result,
            R.drawable.view_timetable,
            R.drawable.circular

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        Intent receiveIntent = getIntent();
        college_id = receiveIntent.getIntExtra("college_id",0);
        id=receiveIntent.getIntExtra("usr_Id",1);
        role = receiveIntent.getIntExtra("Usr_RoleId",2);
        academicyearid = receiveIntent.getIntExtra("academicyearid",3);



        CustomGridViewAdapter adapterViewAndroid = new CustomGridViewAdapter(TeacherActivity.this, gridViewImageId);
        androidGridView = (GridView) findViewById(R.id.grid_view_image);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if(position == 0) {
                    Intent intentAttendance = new Intent(TeacherActivity.this, ViewAttendanceClassActivity.class);

                    intentAttendance.putExtra("college_id",college_id);
                    intentAttendance.putExtra("usr_Id",id);
                    intentAttendance.putExtra("academicyearid",academicyearid);
                    intentAttendance.putExtra("Usr_RoleId",role);
                    startActivity(intentAttendance);
                }

                if(position == 1) {
                    Intent markAttendance = new Intent(TeacherActivity.this, MarkAttendanceClassActivity.class);
                    markAttendance.putExtra("college_id",college_id);
                    markAttendance.putExtra("usr_Id",id);
                    markAttendance.putExtra("academicyearid",academicyearid);
                    markAttendance.putExtra("Usr_RoleId",role);
                    startActivity(markAttendance);
                }
              /* if(position == 2) {
                    Intent resultAttendance = new Intent(TeacherActivity.this, ViewResultClassActivity.class);
                    resultAttendance.putExtra("college_id",college_id);
                    resultAttendance.putExtra("usr_Id",id);
                    resultAttendance.putExtra("academicyearid",academicyearid);
                    startActivity(resultAttendance);
                }
                if(position == 3) {
                    Intent intentAttendance = new Intent(TeacherActivity.this, ClassMarkResult.class);
                    intentAttendance.putExtra("college_id",college_id);
                    intentAttendance.putExtra("usr_Id",id);
                    intentAttendance.putExtra("academicyearid",academicyearid);
                    startActivity(intentAttendance);
                }
               if(position == 4) {
                    Intent intentTimeTable = new Intent(TeacherActivity.this, TimeTableClassActivity.class);
                    intentTimeTable.putExtra("college_id",college_id);
                    intentTimeTable.putExtra("usr_Id",id);
                    intentTimeTable.putExtra("academicyearid",academicyearid);
                    startActivity(intentTimeTable);
                }
               if(position == 5) {
                    Intent intentProfile = new Intent(TeacherActivity.this, TeacherProfileView.class);
                    intentProfile.putExtra("college_id",college_id);
                    intentProfile.putExtra("usr_Id",id);
                    intentProfile.putExtra("user_RoleId",role);
                    startActivity(intentProfile);
                }if(position == 5) {
                    Intent intentCircular = new Intent(TeacherActivity.this, CircularView.class);
                    intentCircular.putExtra("college_id",college_id);
                    intentCircular.putExtra("usr_Id",id);
                    intentCircular.putExtra("academicyearid",academicyearid);
                    startActivity(intentCircular);
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

            Intent i=new Intent(TeacherActivity.this,LoginActivity.class);
            startActivity(i);

            finish();

        }

        return super.onOptionsItemSelected(item);
    }
}
