package biz.thinkcomputers.shdc.teacher.viewattendance;

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
import android.widget.Spinner;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import biz.thinkcomputers.shdc.LoginActivity;
import biz.thinkcomputers.shdc.R;
import biz.thinkcomputers.shdc.WebServiceClass;
import biz.thinkcomputers.shdc.adapter.DegreeAdapter;
import biz.thinkcomputers.shdc.adapter.LectureTimeAdapter;
import biz.thinkcomputers.shdc.adapter.SectionAdapter;
import biz.thinkcomputers.shdc.adapter.SemesterAdapter;
import biz.thinkcomputers.shdc.adapter.StreamAdapter;
import biz.thinkcomputers.shdc.adapter.SubjectAdapter;
import biz.thinkcomputers.shdc.model.Degree;
import biz.thinkcomputers.shdc.model.LectureTime;
import biz.thinkcomputers.shdc.model.Section;
import biz.thinkcomputers.shdc.model.Semester;
import biz.thinkcomputers.shdc.model.Stream;
import biz.thinkcomputers.shdc.model.Subject;


public class ViewAttendanceClassActivity extends AppCompatActivity implements View.OnClickListener{

    int college_id,user_id,academicyearid,emp_Id,role;

    String stdnt_Id,semester;
    TextView markAttendance_textView,mark_date;

    int Section_ID,Degree_Id,Stream_Id,Subject_Id,Lecture_Id;
    String Degree_Name,Stream_Name,Section_Name;
    int i;
    //int Stream_Id=i;
    //int Degree_Id = i;
    //int Section_Id = i;
    private int mYear, mMonth, mDay;

    ArrayList<Degree> degreeArrayList = new ArrayList<>();
    ArrayList<Stream> streamArrayList = new ArrayList<>();
    ArrayList<Semester> semesterArrayList = new ArrayList<>();
    ArrayList<Section> sectionArrayList= new ArrayList<>();
    ArrayList<Subject> subjectArrayList= new ArrayList<>();
    ArrayList<LectureTime> lectureTimeArrayList= new ArrayList<>();

    DegreeAdapter degreeAdapter;
    StreamAdapter streamAdapter;
    SemesterAdapter semesterAdapter;
    SectionAdapter sectionAdapter;
    SubjectAdapter subjectAdapter;
    LectureTimeAdapter lectureTimeAdapter;


    Spinner degreeSpinner,streamSpinner,semesterSpinner,sectionSpinner,subjectSpinner,lectureTimeSpinner;
    String stdnt_name,sNo,sRollNo,degree_name,section_name,lectureTime,lectureType;
    int streamitem;
    //in.thinkcomputers.icampus.MarkAttendanceListViewAdapter markAttendanceListViewAdapter;


    Degree d1 = new Degree();
    Stream st1 = new Stream();
    Semester se1 = new Semester();
    Section s1 = new Section();
    Subject sub = new Subject();

    WebServiceClass webServiceClass7 = new WebServiceClass();
    JSONArray jsonArray;
    String date;
    private ProgressDialog dialog;
    Button button;
    String todayDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_view_attendance);

        Intent receiveIntent = getIntent();
        college_id = receiveIntent.getIntExtra("college_id", 0);
        user_id = receiveIntent.getIntExtra("usr_Id", 1);

        role = receiveIntent.getIntExtra("Usr_RoleId",2);
        academicyearid = receiveIntent.getIntExtra("academicyearid", 3);


        //markAttendance_textView = (TextView) findViewById(R.id.markAttendance_textView);
        mark_date = (TextView) findViewById(R.id.tv_mark_date);
        //lectureTypeTV = (TextView) findViewById(R.id.lecture_type);
        //className = (TextView)findViewById(R.id.class_name);
        //sectionName = (TextView)findViewById(R.id.section_name);
        degreeSpinner = (Spinner) findViewById(R.id.degreeSpinner);
        streamSpinner = (Spinner) findViewById(R.id.streamSpinner);
        semesterSpinner = (Spinner) findViewById(R.id.semesterSpinner);
        sectionSpinner = (Spinner) findViewById(R.id.sectionSpinner);
        subjectSpinner = (Spinner) findViewById(R.id.subjectSpinner);
        //lectureTimeSpinner = (Spinner) findViewById(R.id.lectureTimeSpinner);
        dialog =new ProgressDialog(ViewAttendanceClassActivity.this);
        button = (Button)findViewById(R.id.searchBtn);


        //SimpleDateFormat dateF = new SimpleDateFormat("dd-mmm-yyyy", Locale.getDefault());
        //String date = dateF.format(Calendar.getInstance().getTime());
        //mark_date.setText(date);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        String date = sdf.format(Calendar.getInstance().getTime());
        mark_date.setText(date);
        todayDate = mark_date.getText().toString();


        new AsyncTask<String,Void,Void>(){

            @Override
            protected Void doInBackground(String... params) {
                SoapObject request = new SoapObject(webServiceClass7.getNAMESPACE(),webServiceClass7.getMETHOD_NAME2());
                request.addProperty("college_Id", college_id);
                request.addProperty("user_Id", user_id);
                request.addProperty("user_RoleId", role);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                HttpTransportSE androidTransport = new HttpTransportSE(webServiceClass7.getURL());
                try {
                    androidTransport.call(webServiceClass7.getNAMESPACE()+webServiceClass7.getMETHOD_NAME2(),envelope);
                    SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                    String jsonRootObject = response.toString();

                    jsonArray = new JSONArray(jsonRootObject);


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        emp_Id = object.getInt("Emp_Id");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog=new ProgressDialog(ViewAttendanceClassActivity.this);
                dialog.setMessage("Loading...");
                dialog.setIndeterminate(false);
                dialog.show();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                dialog.dismiss();
                dialog.setProgress(View.GONE);

                if(jsonArray== null){

                    Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_LONG).show();


                }
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

        }.execute();

        new AsyncTask<String,Void,Void>(){

            @Override
            protected Void doInBackground(String... params) {
                SoapObject request = new SoapObject(webServiceClass7.getNAMESPACE(), webServiceClass7.getMETHOD_NAME3());
                request.addProperty("college_Id", college_id);
                // request.addProperty("user_Id",user_id);
                //request.addProperty("user_RoleId",role);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                HttpTransportSE httpTransportSE = new HttpTransportSE(webServiceClass7.getURL());
                try {
                    httpTransportSE.call(webServiceClass7.getNAMESPACE()+webServiceClass7.getMETHOD_NAME3(), envelope);
                    SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                    String result = response.toString();
                    JSONArray jsonArray = new JSONArray(result);


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Degree degree = new Degree();
                        degree.setDegree_Id(jsonObject.getInt("Degree_Id"));
                        degree.setDegree_Name(jsonObject.getString("Degree_Name"));

                        degreeArrayList.add(degree);

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

                if(jsonArray== null){

                     Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_LONG).show();


                }
                d1.setDegree_Id(0);
                d1.setDegree_Name("Select Program");
                degreeArrayList.add(0, d1);
                degreeAdapter = new DegreeAdapter(getApplicationContext(),degreeArrayList);


                if(degreeAdapter.getCount() > 1){

                    degreeSpinner.setAdapter(degreeAdapter);

                }else{
                    String msg = "No Program Available";
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                /*dialog.setMessage("Loading...");
                dialog.setIndeterminate(false);
                dialog.show();*/

            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }
        }.execute();

        degreeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long j) {

                //Section_Id = parent.getSelectedItemPosition();


                if (position == 0) {

                    // Toast.makeText(getApplicationContext(), "Select Program", Toast.LENGTH_LONG).show();


                } else {


                    lectureTimeArrayList.clear();
                    subjectArrayList.clear();
                    sectionArrayList.clear();
                    semesterArrayList.clear();
                    streamArrayList.clear();


                    streamSpinner.setAdapter(null);
                    semesterSpinner.setAdapter(null);
                    sectionSpinner.setAdapter(null);
                    subjectSpinner.setAdapter(null);
                    //lectureTimeSpinner.setAdapter(null);

                    Degree degree = (Degree) parent.getItemAtPosition(position);

                    Degree_Id = degree.getDegree_Id();

                    new AsyncTask<String, Void, Void>() {
                        @Override
                        protected Void doInBackground(String... params) {
                            SoapObject request = new SoapObject(webServiceClass7.getNAMESPACE(), webServiceClass7.getMETHOD_NAME5());
                            request.addProperty("college_Id", college_id);
                            request.addProperty("degree_Id", Degree_Id);

                            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                            envelope.dotNet = true;
                            envelope.setOutputSoapObject(request);

                            HttpTransportSE httpTransportSE = new HttpTransportSE(webServiceClass7.getURL());
                            try {
                                httpTransportSE.call(webServiceClass7.getNAMESPACE()+webServiceClass7.getMETHOD_NAME5(), envelope);
                                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                                String result = response.toString();
                                JSONArray jsonArray = new JSONArray(result);


                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    Stream stream = new Stream();
                                    stream.setStream_Id(jsonObject.getInt("Stream_Id"));
                                    stream.setStream_Name(jsonObject.getString("Stream_Name"));
                                    streamArrayList.add(stream);
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

                            if (jsonArray== null) {

                                Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_LONG).show();


                            }
                            st1.setStream_Id(0);
                            st1.setStream_Name("Select Stream");
                            streamArrayList.add(0, st1);
                            streamAdapter = new StreamAdapter(getApplicationContext(), streamArrayList);
                            streamSpinner.setAdapter(streamAdapter);

                            if(streamAdapter.getCount() > 1){
                                semesterSpinner.setAdapter(semesterAdapter);

                            }else{
                                String msg = "No Stream Available";
                                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();

                        }

                        @Override
                        protected void onProgressUpdate(Void... values) {
                            super.onProgressUpdate(values);
                        }
                    }.execute();

                   /* AsyncCallWS6 task6 = new AsyncCallWS6();
                    //Call execute
                    task6.execute();*/
                }


            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        streamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long j) {

                //Section_Id = parent.getSelectedItemPosition();


                if (position == 0) {


                    //Toast.makeText(getApplicationContext(), "Select Stream", Toast.LENGTH_LONG).show();


                } else {


                    lectureTimeArrayList.clear();
                    subjectArrayList.clear();
                    sectionArrayList.clear();
                    semesterArrayList.clear();


                    semesterSpinner.setAdapter(null);
                    sectionSpinner.setAdapter(null);
                    subjectSpinner.setAdapter(null);
                    //lectureTimeSpinner.setAdapter(null);

                    Stream stream = (Stream) parent.getItemAtPosition(position);

                    Stream_Id = stream.getStream_Id();

                    new AsyncTask<String, Void, Void>() {
                        @Override
                        protected Void doInBackground(String... params) {
                            SoapObject request = new SoapObject(webServiceClass7.getNAMESPACE(), webServiceClass7.getMETHOD_NAME6());

                            request.addProperty("college_Id", college_id);
                            request.addProperty("acadmicYr_Id", academicyearid);
                            request.addProperty("stream_Id", Stream_Id);

                            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                            envelope.dotNet = true;
                            envelope.setOutputSoapObject(request);

                            HttpTransportSE httpTransportSE = new HttpTransportSE(webServiceClass7.getURL());
                            try {
                                httpTransportSE.call(webServiceClass7.getNAMESPACE()+webServiceClass7.getMETHOD_NAME6(), envelope);
                                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                                String result = response.toString();
                                JSONArray jsonArray = new JSONArray(result);



                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    Semester semester = new Semester();
                                    semester.setSemester_Id(jsonObject.getInt("Line_Id"));
                                    semester.setSemester_Name(jsonObject.getString("Semester"));

                                    semesterArrayList.add(semester);


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
                        protected void onPreExecute() {
                            super.onPreExecute();

                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);

                            if(jsonArray== null){

                                Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_LONG).show();


                            }

                            se1.setSemester_Id(0);
                            se1.setSemester_Name("Select Semester");
                            semesterArrayList.add(0, se1);
                            semesterAdapter = new SemesterAdapter(getApplicationContext(), semesterArrayList);


                            if(semesterAdapter.getCount() > 1){

                                semesterSpinner.setAdapter(semesterAdapter);

                            }else{
                                String msg = "No Semester Available";
                                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                            }

                        }
                    }.execute();

                   /* AsyncCallWS6 task6 = new AsyncCallWS6();
                    //Call execute
                    task6.execute();*/
                }



            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        semesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long j) {

                //Section_Id = parent.getSelectedItemPosition();


                if (position == 0) {

                    // Toast.makeText(getApplicationContext(), "Select Semester", Toast.LENGTH_LONG).show();


                } else {


                    lectureTimeArrayList.clear();
                    subjectArrayList.clear();
                    sectionArrayList.clear();


                    sectionSpinner.setAdapter(null);
                    subjectSpinner.setAdapter(null);
                    //lectureTimeSpinner.setAdapter(null);


                    Semester semester1 = (Semester) parent.getItemAtPosition(position);


                    semester = semester1.getSemester_Name();

                    new AsyncTask<String, Void, Void>() {
                        @Override
                        protected Void doInBackground(String... params) {
                            SoapObject request = new SoapObject(webServiceClass7.getNAMESPACE(), webServiceClass7.getMETHOD_NAME7());

                            request.addProperty("college_Id", college_id);
                            request.addProperty("semester", semester);
                            request.addProperty("acadmicYr_Id", academicyearid);
                            request.addProperty("stream_Id", Stream_Id);

                            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                            envelope.dotNet = true;
                            envelope.setOutputSoapObject(request);

                            HttpTransportSE httpTransportSE = new HttpTransportSE(webServiceClass7.getURL());
                            try {
                                httpTransportSE.call(webServiceClass7.getNAMESPACE()+webServiceClass7.getMETHOD_NAME7(), envelope);
                                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                                String result = response.toString();
                                JSONArray jsonArray = new JSONArray(result);



                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    Section section = new Section();
                                    section.setSection_ID(jsonObject.getInt("Section_ID"));
                                    section.setSection_Name(jsonObject.getString("Section_Name"));

                                    sectionArrayList.add(section);


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
                        protected void onPreExecute() {
                            super.onPreExecute();

                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);

                            if(jsonArray== null){

                                Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_LONG).show();


                            }

                            s1.setSection_ID(0);
                            s1.setSection_Name("Select Section");
                            sectionArrayList.add(0, s1);
                            sectionAdapter = new SectionAdapter(getApplicationContext(), sectionArrayList);


                            if(sectionAdapter.getCount() > 1){
                                sectionSpinner.setAdapter(sectionAdapter);

                            }else{
                                String msg = "No Section Available";
                                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                            }

                        }
                    }.execute();

                   /* AsyncCallWS6 task6 = new AsyncCallWS6();
                    //Call execute
                    task6.execute();*/
                }


            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long j) {

                //Section_Id = parent.getSelectedItemPosition();


                if (position == 0) {

                    //sectionArrayList.clear();

                    //Toast.makeText(getApplicationContext(), "Select Section", Toast.LENGTH_LONG).show();


                } else {


                    lectureTimeArrayList.clear();
                    subjectArrayList.clear();


                    subjectSpinner.setAdapter(null);
                    //lectureTimeSpinner.setAdapter(null);


                    Section section = (Section) parent.getItemAtPosition(position);


                    Section_ID = section.getSection_ID();

                    new AsyncTask<String, Void, Void>() {
                        @Override
                        protected Void doInBackground(String... params) {
                            SoapObject request = new SoapObject(webServiceClass7.getNAMESPACE(), webServiceClass7.getMETHOD_NAME8());

                            request.addProperty("college_Id", college_id);
                            request.addProperty("acadmicYr_Id", academicyearid);
                            request.addProperty("semester", semester);
                            request.addProperty("section_Id", Section_ID);
                            request.addProperty("emp_Id", emp_Id);

                            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                            envelope.dotNet = true;
                            envelope.setOutputSoapObject(request);

                            HttpTransportSE httpTransportSE = new HttpTransportSE(webServiceClass7.getURL());
                            try {
                                httpTransportSE.call(webServiceClass7.getNAMESPACE()+webServiceClass7.getMETHOD_NAME8(), envelope);
                                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                                String result = response.toString();
                                JSONArray jsonArray = new JSONArray(result);


                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    Subject subject = new Subject();
                                    subject.setSubject_Id(jsonObject.getInt("Course_Id"));
                                    subject.setSubject_Name(jsonObject.getString("CodeName"));

                                    subjectArrayList.add(subject);


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
                        protected void onPreExecute() {
                            super.onPreExecute();

                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);


                            if(jsonArray== null){

                                Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_LONG).show();


                            }
                            sub.setSubject_Id(0);
                            sub.setSubject_Name("Select Subject");
                            subjectArrayList.add(0, sub);
                            subjectAdapter = new SubjectAdapter(getApplicationContext(), subjectArrayList);

                            if(subjectAdapter.getCount() > 1){

                                subjectSpinner.setAdapter(subjectAdapter);

                            }else{
                                String msg = "No Subject Available";
                                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                            }

                        }
                    }.execute();

                   /* AsyncCallWS6 task6 = new AsyncCallWS6();
                    //Call execute
                    task6.execute();*/
                }



            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long j) {

                //Section_Id = parent.getSelectedItemPosition();


                if (position == 0) {

                    //Toast.makeText(getApplicationContext(), "Select Subject", Toast.LENGTH_LONG).show();


                } else {


                    lectureTimeArrayList.clear();
                    //lectureTimeSpinner.setAdapter(null);

                    Subject subject = (Subject) parent.getItemAtPosition(position);


                    Subject_Id = subject.getSubject_Id();

                    /*new AsyncTask<String, Void, Void>() {
                        @Override
                        protected Void doInBackground(String... params) {
                            SoapObject request = new SoapObject("http://tempuri.org/", "bindLectureTimeAccToSubject");

                            request.addProperty("college_Id", college_id);
                            request.addProperty("acadmicYr_Id", academicyearid);
                            request.addProperty("semester", semester);
                            request.addProperty("section_Id", Section_ID);
                            request.addProperty("course_Id", Subject_Id);
                            request.addProperty("attendanceDate", todayDate);

                            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                            envelope.dotNet = true;
                            envelope.setOutputSoapObject(request);

                            HttpTransportSE httpTransportSE = new HttpTransportSE("http://shdc.icampus360.in/webservices/webservice.asmx");
                            try {
                                httpTransportSE.call("http://tempuri.org/bindLectureTimeAccToSubject", envelope);
                                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                                String result = response.toString();
                                JSONArray jsonArray = new JSONArray(result);


                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    LectureTime lectureTime = new LectureTime();
                                    lectureTime.setLectureTime_Id(jsonObject.getInt("TTbl_Id"));
                                    lectureTime.setLectureTime_Name(jsonObject.getString("LectureTime"));
                                    lectureTime.setLectureTime_Type(jsonObject.getString("LectureType"));

                                    lectureTimeArrayList.add(lectureTime);


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
                        protected void onPreExecute() {
                            super.onPreExecute();

                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);


                            if (jsonArray.length() == 0) {

                                Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_LONG).show();


                            }
                        }
                    }.execute();*/

                   /* AsyncCallWS6 task6 = new AsyncCallWS6();
                    //Call execute
                    task6.execute();*/
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        /*if (v == mark_date) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            mark_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            date = mark_date.getText().toString();

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.show();
        }*/
        if (v== button){
            //Toast.makeText(getApplicationContext(),stdnt_Id,Toast.LENGTH_LONG).show();

            if(subjectArrayList.isEmpty()) {

                Toast.makeText(getApplicationContext(),"Please Select All",Toast.LENGTH_LONG).show();

            }else {

                Intent intent = new Intent(getApplicationContext(), ViewClassAttendance.class);
                intent.putExtra("college_id", college_id);
                intent.putExtra("usr_Id", user_id);
                intent.putExtra("acadmicYr_Id", academicyearid);
                intent.putExtra("section_id", Section_ID);
                intent.putExtra("semester", semester);
                intent.putExtra("stream_Id", Stream_Id);
                intent.putExtra("course_Id", Subject_Id);
                intent.putExtra("text_date", mark_date.getText().toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);


            }
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

            Intent i=new Intent(ViewAttendanceClassActivity.this,LoginActivity.class);
            startActivity(i);

            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

