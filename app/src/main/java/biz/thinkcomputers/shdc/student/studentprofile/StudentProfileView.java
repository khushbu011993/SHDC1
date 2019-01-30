package biz.thinkcomputers.shdc.student.studentprofile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import biz.thinkcomputers.shdc.LoginActivity;
import biz.thinkcomputers.shdc.R;
import biz.thinkcomputers.shdc.WebServiceClass;


public class StudentProfileView extends AppCompatActivity  {

     WebServiceClass webservice3 = new WebServiceClass();
    int college_id, studentid, usr_Id;
    EditText studentName_et, studentFatherName_et, studentDateOfBirth_et, studentEmail_et, studentAddress_et, studentState_et, studentfatherMobileNo_et;
    String student_Name, student_FatherName, student_DateOfBirth, student_Email, student_Address, student_city, student_State, student_fatherMobileNo, student_FatherEmail;

    Button btnOk;

    Task task;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.student_profile);

        Intent reciverIntent = getIntent();
        college_id = reciverIntent.getIntExtra("college_id", 0);
        usr_Id = reciverIntent.getIntExtra("usr_Id", 1);
        studentid = reciverIntent.getIntExtra("student_id", 2);
       // btnOk = (Button) findViewById(R.id.btnOk);

        final TextInputLayout studentNameWrapper = (TextInputLayout) findViewById(R.id.studentNameWrapper);
        final TextInputLayout studentFatherNameWrapper = (TextInputLayout) findViewById(R.id.studentFatherNameWrapper);
        //final TextInputLayout studentMotherNameWrapper = (TextInputLayout) findViewById(R.id.studentMotherNameWrapper);
        //final TextInputLayout studentGenderWrapper = (TextInputLayout) findViewById(R.id.studentGenderWrapper);
        final TextInputLayout studentDateOBirthWrapper = (TextInputLayout) findViewById(R.id.studentDateOBirthWrapper);
        final TextInputLayout studentEmailWrapper = (TextInputLayout) findViewById(R.id.studentEmailWrapper);
        final TextInputLayout studentAddressWrapper = (TextInputLayout) findViewById(R.id.studentAddressWrapper);
        final TextInputLayout studentStateWrapper = (TextInputLayout) findViewById(R.id.studentStateWrapper);
        final TextInputLayout studentfatherMobileNoWrapper = (TextInputLayout) findViewById(R.id.studentfatherMobileNoWrapper);
        //final TextInputLayout studentFatherEmailWrapper = (TextInputLayout) findViewById(R.id.studentFatherEmailWrapper);


        studentNameWrapper.setHint("Student Name");
        studentFatherNameWrapper.setHint("Father Name");
        //studentMotherNameWrapper.setHint("Mother Name");
        //studentGenderWrapper.setHint("Student Gender");
        studentDateOBirthWrapper.setHint("Date Of Birth");
        studentEmailWrapper.setHint("Student Email Address");
        studentAddressWrapper.setHint("Student Address");
        studentStateWrapper.setHint("Student State");
        studentfatherMobileNoWrapper.setHint("Father Mobile No");
        //studentFatherEmailWrapper.setHint("Father Email Address");

        studentName_et = (EditText) findViewById(R.id.studentName);
        studentFatherName_et = (EditText) findViewById(R.id.studentFatherName);
        //studentMotherName = (EditText) findViewById(R.id.studentMotherName);
        //studentGender = (EditText) findViewById(R.id.studentGender);
        studentDateOfBirth_et = (EditText) findViewById(R.id.studentDateOfBirth);
        studentEmail_et = (EditText) findViewById(R.id.studentEmail);
        studentAddress_et = (EditText) findViewById(R.id.studentAddress);
        studentState_et = (EditText) findViewById(R.id.studentState);
        studentfatherMobileNo_et = (EditText) findViewById(R.id.studentfatherMobileNo);
        //studentFatherEmail = (EditText) findViewById(R.id.studentFatherEmail);
        // btn_ok = (Button) findViewById(R.id.btn_ok);

        task = new Task();
        task.execute();





       /* btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(StudentProfileView.this, StudentActivity.class);
                startActivity(i);
                finish();
            }

        });*/
        //btnOk.setOnClickListener(this);
    }


    public void student_profile(int college_id, int studentid) {


        SoapObject request = new SoapObject(webservice3.getNAMESPACE(), webservice3.getMETHOD_NAME16());
        //Property which holds input parameters
        request.addProperty("college_Id", college_id);
        request.addProperty("stdnt_Id", studentid);
        //Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        //Set output SOAP object
        envelope.setOutputSoapObject(request);
        //Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(webservice3.getURL());

        try {
            //Invole web service using SOAPACTION(NAMESPACE+METHODNAME)
            androidHttpTransport.call(webservice3.getNAMESPACE()+webservice3.getMETHOD_NAME16(), envelope);
            //Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

            String jsonRootObject2 = response.toString();

            try {

                JSONArray obj = new JSONArray(jsonRootObject2);


                for (int i = 0; i < obj.length(); i++) {

                    JSONObject obj1 = obj.getJSONObject(i);


                    student_Name = obj1.getString("Stdnt_Name");
                    student_FatherName = obj1.getString("Stdnt_FatherName");
                    //student_MotherName = obj1.getString("Stdnt_MotherName");
                    //student_Gender = obj1.getString("Gender");
                    student_DateOfBirth = obj1.getString("Stdnt_Dob");
                    student_Email = obj1.getString("Email_Id");
                    student_Address = obj1.getString("Address");
                    student_city = obj1.getString("City");
                    student_State = obj1.getString("State");
                    student_fatherMobileNo = obj1.getString("FatherContact_No");
                    //student_FatherEmail = obj1.getString("ParentEmailId");
                    //school_id = obj1.getInt("School_Id");

                    /*String bigger[] = value.split(",");
                    for (int j=0;j<bigger.length;j++){


                        role= Integer.parseInt(bigger[j]);

                    }*/

                }


            } catch (Throwable tx) {
                Log.e("My App", "Could not parse malformed JSON: \"" + jsonRootObject2 + "\"");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

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

            Intent i = new Intent(StudentProfileView.this, LoginActivity.class);
            startActivity(i);

            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

   /* @Override
    public void onClick(View v) {

        if (v == btnOk) {
            Intent intentOk = new Intent(StudentProfileView.this, StudentActivity.class);
            //intentOk.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //intentOk.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            //.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intentOk);

        }
    }*/

    private class Task extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            student_profile(college_id, studentid);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            dialog.dismiss();
            dialog.setProgress(View.GONE);

            studentName_et.setText(student_Name);
            studentFatherName_et.setText(student_FatherName);
            //studentMotherName.setText(student_MotherName);
            //studentGender.setText(student_Gender);
            studentDateOfBirth_et.setText(student_DateOfBirth);
            studentEmail_et.setText(student_Email);
            studentAddress_et.setText(student_Address + " , " + student_city);
            studentState_et.setText(student_State);
            studentfatherMobileNo_et.setText(student_fatherMobileNo);
            // studentFatherEmail.setText(student_FatherEmail);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(StudentProfileView.this);
            dialog.setMessage("Loading...");
            dialog.setIndeterminate(false);
            dialog.show();
        }
    }

   /* @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putString("student_Name", student_Name);
        savedInstanceState.putString("student_FatherName", student_FatherName);
        savedInstanceState.putString("student_DateOfBirth", student_DateOfBirth);
        savedInstanceState.putString("student_Email", student_Email);
        savedInstanceState.putString("student_Address", student_Address);
        savedInstanceState.putString("student_State", student_State);
        savedInstanceState.putString("student_fatherMobileNo", student_fatherMobileNo);



        // etc.
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

         student_Name = savedInstanceState.getString("student_Name");
         student_FatherName = savedInstanceState.getString("student_FatherName");
         student_DateOfBirth = savedInstanceState.getString("student_DateOfBirth");
         student_Email = savedInstanceState.getString("student_Email");
         student_Address = savedInstanceState.getString("student_Address");
         student_State = savedInstanceState.getString("student_State");
         student_fatherMobileNo = savedInstanceState.getString("student_fatherMobileNo");

       *//* studentName_et.setText(student_Name);
        studentFatherName_et.setText(student_FatherName);
        //studentMotherName.setText(student_MotherName);
        //studentGender.setText(student_Gender);
        studentDateOfBirth_et.setText(student_DateOfBirth);
        studentEmail_et.setText(student_Email);
        studentAddress_et.setText(student_Address + " , " + student_city);
        studentState_et.setText(student_State);
        studentfatherMobileNo_et.setText(student_fatherMobileNo);*//*
    }

    @Override
    protected void onResume() {
        super.onResume();

        studentName_et.setText(student_Name);
        studentFatherName_et.setText(student_FatherName);
        //studentMotherName.setText(student_MotherName);
        //studentGender.setText(student_Gender);
        studentDateOfBirth_et.setText(student_DateOfBirth);
        studentEmail_et.setText(student_Email);
        studentAddress_et.setText(student_Address + " , " +student_city);
        studentState_et.setText(student_State);
        studentfatherMobileNo_et.setText(student_fatherMobileNo);
    }*/

    /*@Override
    protected void onRestart() {
        super.onRestart();
    }*/

   /* @Override
    protected void onDestroy() {
        super.onDestroy();
        if (task != null) {
            task.cancel(true);
        }
    }*/

    @Override
    protected void onSaveInstanceState(Bundle savedInstance) {


        savedInstance.putInt("college_id", college_id);
        savedInstance.putInt("studentid", studentid);
        super.onSaveInstanceState(savedInstance);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        college_id = savedInstanceState.getInt("college_id");
        studentid = savedInstanceState.getInt("studentid");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (task != null) {
            task.cancel(true);
        }
    }
}