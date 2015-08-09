package com.example.ronak.demo;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener {
    Button reg;
    public static final String Key = "Already registered";
    EditText e,e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12,e13,e14;
    List<String> home_array;
    List<String> work_array;
    RadioButton home,work;
    String name,phone,email,home_address,work_address,delivery_address;
   // String home1,home2,home_locality,home_state,home_city,home_pincode;
   // String work1,work2,work_locality,work_state,work_city,work_pincode;
    List<NameValuePair> nameValuePairs;
    InputStream is;
    private String url="http://192.168.56.1/demo123/index1.php";
    SharedPreferences sharedpreferences;
    UserLocalStore userLocalDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        home_array=new ArrayList<>();
        work_array=new ArrayList<>();
        userLocalDatabase = new UserLocalStore(this);
        sharedpreferences = this.getPreferences(Context.MODE_PRIVATE);
         String flag = sharedpreferences.getString(Key,"N");
        Log.e("checking",flag);
          if(flag.equals("Y"))
        {
            Log.e("in if flag", flag);
            Intent in = new Intent(getApplicationContext(),SecondActivity.class);
            startActivity(in);
            finish();
        }
        reg= (Button)findViewById(R.id.button);
        home=(RadioButton)findViewById(R.id.radioButton);
        work=(RadioButton)findViewById(R.id.radioButton2);
        e=  (EditText)findViewById(R.id.editText);
        e1=  (EditText)findViewById(R.id.editText1);
        e2=  (EditText)findViewById(R.id.editText2);
        e3=  (EditText)findViewById(R.id.editText3);
        e4=  (EditText)findViewById(R.id.editText4);
        e5=  (EditText)findViewById(R.id.editText5);
        e6=  (EditText)findViewById(R.id.editText6);
        e7=  (EditText)findViewById(R.id.editText7);
        e8=  (EditText)findViewById(R.id.editText8);
        e9=  (EditText)findViewById(R.id.editText9);
        e10=  (EditText)findViewById(R.id.editText10);
        e11=  (EditText)findViewById(R.id.editText11);
        e12=  (EditText)findViewById(R.id.editText12);
        e13=  (EditText)findViewById(R.id.editText13);
        e14=  (EditText)findViewById(R.id.editText14);
        reg.setOnClickListener(this);

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Log.e("ccdcd", "in on click");
       // Toast.makeText(this,"button pressed",Toast.LENGTH_SHORT).show();
        name=   e.getText().toString();
        phone=  e1.getText().toString();

        if(name.trim().equals("")||name.trim().matches(".*\\d.*"))
        {
            Toast.makeText(this,"Invalid Name Input.",Toast.LENGTH_SHORT).show();
            return;
        }

        if(phone.trim().equals("")||phone.trim().length()>10||!phone.trim().matches("[0-9]+"))
        {
            Toast.makeText(this,"Invalid Phone Input.",Toast.LENGTH_SHORT).show();
            return;
        }
        email=  e2.getText().toString();

        if(email.trim().equals("")||!email.trim().matches("[A-Z a-z 0-9 _]+@[A-Z a-z]+[.][a-z]+"))
        {
            Toast.makeText(this,"Invalid Email Input.",Toast.LENGTH_SHORT).show();
            return;
        }

        home_array.add(0, e3.getText().toString());
        home_array.add(1, e4.getText().toString());
        home_array.add(2, e5.getText().toString());
        home_array.add(3, e6.getText().toString());
        home_array.add(4, e7.getText().toString());
        home_array.add(5, e8.getText().toString());
        Log.e("Home_array", "" + home_array.get(0));

        work_array.add(0, e9.getText().toString());
        work_array.add(1, e10.getText().toString());
        work_array.add(2, e11.getText().toString());
        work_array.add(3, e12.getText().toString());
        work_array.add(4, e13.getText().toString());
        work_array.add(5, e14.getText().toString());
        System.out.println(work_array);
        //Log.e("work array",System.out.println(work_array));





        if((home_array.get(0) + home_array.get(1) +  home_array.get(2) + home_array.get(3) + home_array.get(4) + home_array.get(5) ).trim().equals("") && home.isChecked())
        {
            Toast.makeText(this,"No home address selected and delivery address as home is selected ",Toast.LENGTH_SHORT).show();
            return;
        }


        if((work_array.get(0) + work_array.get(1) +  work_array.get(2) + work_array.get(3) + work_array.get(4) + work_array.get(5)).trim().equals("") && work.isChecked())
        {
            Toast.makeText(this, "No work address selected and delivery address as work is selected ", Toast.LENGTH_SHORT).show();
            return;
        }





        home_address=   home_array.get(0) +". "+ home_array.get(1) + ".\nLocality=" + home_array.get(2) + ".\nState=" + home_array.get(3)  + ".\nCity= " + home_array.get(4) + ".\nPincode " + home_array.get(5);
        work_address=   work_array.get(0) +". "+ work_array.get(1) + ".\nLocality=" + work_array.get(2) + ".\nState=" + work_array.get(3)  + " .\nCity= " + work_array.get(4) + ".\nPincode " + work_array.get(5);
        Log.e(home_address+"",work_address+"");

        if(home.isChecked())
        {
            delivery_address="H";
        }
        else
        {
            delivery_address="W";
        }





        nameValuePairs=new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("tag","register_address"));
        nameValuePairs.add(new BasicNameValuePair("name", name));
        nameValuePairs.add(new BasicNameValuePair("phone", phone));
        nameValuePairs.add(new BasicNameValuePair("email", email));
        nameValuePairs.add(new BasicNameValuePair("home_address", home_address));
        nameValuePairs.add(new BasicNameValuePair("work_address", work_address));
        nameValuePairs.add(new BasicNameValuePair("delivery_address",delivery_address));


        Task k = new Task();
        k.execute(url);






    }

    private class Task extends AsyncTask<String, Void, JSONObject>
    {
        JSONObject jo;


        @Override
        protected JSONObject doInBackground(String... params) {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            Log.e("test", params[0]);
            HttpPost httpPost = new HttpPost(params[0]);
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            try {
                HttpResponse httpResponse = httpClient.execute(httpPost);
                Log.e("testbbbbbbbbbbbbb", "mjjjjjjjjjjjjm");

                HttpEntity httpEntity = httpResponse.getEntity();
                Log.e("testtt", httpResponse + "  " + httpEntity + " ");

                is = httpEntity.getContent();
                byte b[] = new byte[1024];
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                int i = 0;
                while ((i = is.read(b, 0, b.length)) != -1) {
                    Log.e("aaaaaaaaa", "in while");
                    os.write(b, 0, i);
                }
                String str = os.toString().trim();
                Log.e("sssssssssss", str);
                jo = new JSONObject(str);

                try {
                    if (jo.getString("success") != null) {
                        if (
                                Integer.parseInt(jo.getString("success")) == 1) {
                                String n=jo.getJSONObject("user").getString("name");
                                String p=jo.getJSONObject("user").getString("phone");
                                String e=jo.getJSONObject("user").getString("email");
                                String da=jo.getJSONObject("user").getString("delivery_address");
                                User user= new User(n,p,e,home_array,work_array,da);
                                userLocalDatabase.storeUserData(user);

                                Log.e("", jo.getJSONObject("user").getString("phone"));
                                Log.e("", jo.getJSONObject("user").getString("home_address"));

                                SharedPreferences.Editor editor = sharedpreferences.edit();

                                editor.putString(Key, "Y");

                                editor.commit();


                            Log.e("is success", sharedpreferences.getString(Key, "does not exist"));


                            //Intent in = new Intent(getApplicationContext(),SecondActivity.class);
                            //in.putExtra("uid",jo.getString("uid"));
                            //in.putExtra("name",jo.getJSONObject("user").getString("name"));
                            //in.putExtra("name", jo.getString("name"));
                            // startActivity(in);
                            //finish();

                        } else {


                        }


                    }
                } catch (JSONException e) {
                    Log.e("test1", "json");

                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }




            } catch (JSONException e15) {
                e15.printStackTrace();
            } catch (ClientProtocolException e15) {
                e15.printStackTrace();
            } catch (IOException e15) {
                e15.printStackTrace();
            }

                return jo;
        }


            protected void onPostExecute(JSONObject jo) {


            try {
                if(jo.getString("success")!=null)
                {
                    if(Integer.parseInt(jo.getString("success"))==1)
                    {

                        Intent in = new Intent(getApplicationContext(),SecondActivity.class);
                        //in.putExtra("uid",jo.getString("uid"));
                     //   in.putExtra("name",jo.getJSONObject("user").getString("name"));
                        //in.putExtra("name", jo.getString("name"));
                        startActivity(in);
                       finish();

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Registration not successfull",Toast.LENGTH_SHORT);

                    }
                }
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // TODO Auto-generated method stub
            super.onPostExecute(jo);
        }




    }




}






