package biz.thinkcomputers.shdc;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.util.ArrayList;

import biz.thinkcomputers.shdc.student.StudentActivity;
import biz.thinkcomputers.shdc.teacher.TeacherActivity;


public class LoginActivity extends AppCompatActivity {


    WebServiceClass webservice1 = new WebServiceClass();
    private ProgressDialog dialog;

    EditText usernameEt, passwordEt ,academic_yearEt;
    Button submit;
    private String TAG = "ICampus360";
    private static String usernameinput;
    private static String passwordinput;
    private static String response1;
    int role;
    ArrayList<Integer> roleArray = new ArrayList<>();
    int college_id, student_id,id,academicyearid,Emp_Id;
    public SharedPreferences.Editor loginPrefsEditor;
    public SharedPreferences loginPreferences;
    private Boolean saveLogin;
    private CheckBox saveLoginCheckBox;
    Object errmsg;
    String value;
    JSONArray obj;
    String noData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextInputLayout usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        final TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);
        //final TextInputLayout academicYearWrapper = (TextInputLayout) findViewById(R.id.academicyearWrapper);

        usernameWrapper.setHint("Username");
        passwordWrapper.setHint("Password");
        //academicYearWrapper.setHint("Academic Year(YYYY-YYYY)");

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        submit = (Button) findViewById(R.id.btn);
        saveLoginCheckBox = (CheckBox)findViewById(R.id.saveLoginCheckBox);

        usernameEt = (EditText) findViewById(R.id.user_name);
        passwordEt = (EditText) findViewById(R.id.password);
        //academic_yearEt = (EditText) findViewById(R.id.academic_year);


      /*  loginPreferences = getSharedPreferences("loginPrefs", 0);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);*/

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);

        if (saveLogin == true) {
            usernameEt.setText(loginPreferences.getString("username", ""));
            passwordEt.setText(loginPreferences.getString("password", ""));
            //academic_yearEt.setText(loginPreferences.getString("academicyear",""));
            saveLoginCheckBox.setChecked(true);
        }



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(usernameEt.getWindowToken(), 0);

                if (usernameEt.getText().length() != 0 && usernameEt.getText().toString() != "") {
                    if (passwordEt.getText().length() != 0 && passwordEt.getText().toString() != "") {
                        usernameinput = usernameEt.getText().toString();
                        passwordinput = passwordEt.getText().toString();


                        AsyncCallWS task = new AsyncCallWS();
                        //Call execute
                        task.execute();
                    } else {
                        passwordEt.setError("Enter the password");
                    }
                } else {
                    usernameEt.setError("enter the username");
                }




                if (saveLoginCheckBox.isChecked()) {
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("username", usernameinput);
                    loginPrefsEditor.putString("password", passwordinput);
                    loginPrefsEditor.apply();
                } else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.apply();
                }

            }
        });

    }

    public void insert_data(String usernameinput, String passwordinput) {
        //Create request
  //      SoapObject request = new SoapObject(webservice1.getNAMESPACE(), webservice1.getMETHOD_NAME1());
       // 1 SoapObject request = new SoapObject("http://webservice.icampus360.in","LoginJson");

    }

    private class AsyncCallWS extends AsyncTask<String, Void, Object> {
        @Override
        protected Object doInBackground(String... params) {
            Log.i(TAG, "doInBackground");
            //insert_data(usernameinput, passwordinput);
            SoapObject request = new SoapObject(webservice1.getNAMESPACE(),webservice1.getMETHOD_NAME1());
            //Property which holds input parameters
            request.addProperty("email_Id", usernameinput);
            request.addProperty("password", passwordinput);


            //Create envelope
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            //Set output SOAP object
            envelope.setOutputSoapObject(request);
            //Create HTTP call object
            //HttpTransportSE androidHttpTransport = new HttpTransportSE(webservice1.getURL());
            // 2 HttpTransportSE httpTransportSE = new HttpTransportSE("http://webservice.icampus360.in/WebServices/ICampusGlobalServices.asmx");
            HttpTransportSE httpTransportSE = new HttpTransportSE(webservice1.getURL());

            try {
                //Invole web service
                // 3 httpTransportSE.call("http://webservice.icampus360.in/LoginJson", envelope);
                httpTransportSE.call(webservice1.getNAMESPACE()+webservice1.getMETHOD_NAME1(),envelope);
                //Get the response
                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();


                String jsonRootObject1 = response.toString();

                obj = new JSONArray(jsonRootObject1);
                errmsg = null;




                    for (int i = 0; i < obj.length(); i++) {
                        JSONObject obj1 = obj.getJSONObject(i);
                        if(obj1.has("Response")){
                            errmsg ="No Data";
                        }else{
                            id = obj1.getInt("Usr_Id");
                            value = obj1.getString("Usr_RoleId");
                            college_id = obj1.getInt("College_Id");
                            academicyearid = obj1.getInt("AcademicYrId");
                        }



                    }

                if(value == null){
                    errmsg ="No Data";
                }else{
                    String bigger[] = value.split(",");
                    for (int j = 0; j < bigger.length; j++) {
                        role = Integer.parseInt(bigger[j]);
                        roleArray.add(role);
                        System.out.print(role);
                }


                }



           /* if (role == 7) {
                Intent i = new Intent(LoginActivity.this, TeacherActivity.class);
                i.putExtra("college_id",college_id);
                i.putExtra("usr_Id",id);
                i.putExtra("Usr_RoleId",role);
                i.putExtra("academicyearid",academicyearid);
                startActivity(i);

            }*/
           /* if (roleArray.contains(3)) {

                role = 3;
                Intent i = new Intent(LoginActivity.this, TeacherActivity.class);
                i.putExtra("college_id",college_id);
                i.putExtra("usr_Id",id);
                i.putExtra("Usr_RoleId",role);
                i.putExtra("academicyearid",academicyearid);

                startActivity(i);

            }
                 if (roleArray.contains(99)) {
                     role = 99;
                Intent i = new Intent(LoginActivity.this, StudentActivity.class);
                i.putExtra("college_id",college_id);
                i.putExtra("usr_Id",id);
                i.putExtra("Usr_RoleId",role);
                i.putExtra("academicyearid",academicyearid);
                startActivity(i);

            }*/

            } catch (HttpResponseException e) {
                //errmsg="user name or password is wrong.Please enter correct detail";
                errmsg = e.toString();
                e.printStackTrace();
            }  catch (XmlPullParserException e) {
                errmsg = e.toString();
                e.printStackTrace();
            } catch (JSONException e) {
                errmsg = e.toString();
                e.printStackTrace();
            } catch (IOException e) {
                errmsg = e.toString();
                // errmsg="No Internet Connection.Please check your internet setting";
                e.printStackTrace();
            }

            return errmsg;
        }


        @Override
        protected void onPostExecute(Object errmsg) {
            Log.i(TAG, "onPostExecute");

            dialog.dismiss();
            dialog.setProgress(View.GONE);

            //System.out.print(errmsg.toString());
            if (errmsg == null) {

                if (roleArray.contains(3)) {

                    role = 3;
                    Intent i = new Intent(LoginActivity.this, TeacherActivity.class);
                    i.putExtra("college_id",college_id);
                    i.putExtra("usr_Id",id);
                    i.putExtra("Usr_RoleId",role);
                    i.putExtra("academicyearid",academicyearid);

                    startActivity(i);

                }
                if (roleArray.contains(99)) {
                    role = 99;
                    Intent i = new Intent(LoginActivity.this, StudentActivity.class);
                    i.putExtra("college_id",college_id);
                    i.putExtra("usr_Id",id);
                    i.putExtra("Usr_RoleId",role);
                    i.putExtra("academicyearid",academicyearid);
                    startActivity(i);

                }
                String text = "Login Successfully";
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

            }else if(errmsg.toString().equals("No Data")){
                String text="user name or password is wrong.Please enter correct detail";

                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
            }

            else if(errmsg.toString().equals("java.net.UnknownHostException: Unable to resolve host \"shdc.icampus360.in\": No address associated with hostname")){

                String text = "No Internet Connection.Please check your internet setting";
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();

            }


            //Toast.makeText(getApplicationContext(), errmsg, Toast.LENGTH_SHORT).show();
            //errmsg="Login Successfully";
        }

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute");
            //tv.setText("Sending...");
            dialog=new ProgressDialog(LoginActivity.this);
            dialog.setMessage("Loading...");
            dialog.setIndeterminate(false);
            dialog.show();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Log.i(TAG, "onProgressUpdate");
        }


    }


}