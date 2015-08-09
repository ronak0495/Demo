package com.example.ronak.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class View_Profile extends Activity {
TextView name,phone,email,home_address_line_1,home_address_line_2,home_address_locality,home_address_state,home_address_city,home_address_pincode,work_address_line_1,work_address_line_2,work_address_locality,work_address_state,work_address_city,work_address_pincode,delivery_address;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        User user =(User) getIntent().getExtras().getSerializable("user object");
        Log.e("dedefe",user.delivery_address + user.email + user.phone);
        name=(TextView)findViewById(R.id.textView101);
        phone=(TextView)findViewById(R.id.textView103);
        email=(TextView)findViewById(R.id.textView105);
        home_address_line_1=(TextView)findViewById(R.id.textView107);
        home_address_line_2=(TextView)findViewById(R.id.textView108);
        home_address_locality=(TextView)findViewById(R.id.textView109);
        home_address_state=(TextView)findViewById(R.id.textView110);
        home_address_city=(TextView)findViewById(R.id.textView111);
        home_address_pincode=(TextView)findViewById(R.id.textView112);
        work_address_line_1=(TextView)findViewById(R.id.textView114);
        work_address_line_2=(TextView)findViewById(R.id.textView115);
        work_address_locality=(TextView)findViewById(R.id.textView116);
        work_address_state=(TextView)findViewById(R.id.textView117);
        work_address_city=(TextView)findViewById(R.id.textView118);
        work_address_pincode=(TextView)findViewById(R.id.textView119);
        delivery_address=(TextView)findViewById(R.id.textView121);
        name.setText(user.name);
        phone.setText(user.phone);
        email.setText(user.email);
        home_address_line_1.setText(user.home_array.get(0));
        home_address_line_2.setText(user.home_array.get(1));
        home_address_locality.setText(user.home_array.get(2));
        home_address_state.setText(user.home_array.get(3));
        home_address_city.setText(user.home_array.get(4));
        home_address_pincode.setText(user.home_array.get(5));
        work_address_line_1.setText(user.work_array.get(0));
        work_address_line_2.setText(user.work_array.get(1));
        work_address_locality.setText(user.work_array.get(2));
        work_address_state.setText(user.work_array.get(3));
        work_address_city.setText(user.work_array.get(4));
        work_address_pincode.setText(user.work_array.get(5));
        if(user.delivery_address.equals("H"))
        {
            delivery_address.setText("Home Address");
        }
        else
        {
            delivery_address.setText("Work Address");
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view__profile, menu);
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
}
