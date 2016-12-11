package com.harpreet.moviereviews;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText first, last, email, mobile, pass, confpass;
    Button save, cancel;
    DatabaseHandler db;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Username1 = "nameKey";
    public static final String Username2 = "emailKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        first = (EditText) findViewById(R.id.editfirstname);
        last = (EditText) findViewById(R.id.editlastname);
        email = (EditText) findViewById(R.id.editemail);
        mobile = (EditText) findViewById(R.id.editusername);
        pass = (EditText) findViewById(R.id.editpassword);
        confpass = (EditText) findViewById(R.id.editconformpassword);

        save = (Button) findViewById(R.id.btnsave);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);




        save.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // TODO Auto-generated method stub

                String edfirst = first.getText().toString();
                String edlast = last.getText().toString();
                String edemail = email.getText().toString();
                String edmobile = mobile.getText().toString();
                String edpass = pass.getText().toString();
                String edConf = confpass.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Username1,edfirst);
                editor.putString(Username2,edlast);
                editor.commit();


                //String StoredPassword =db.isEmail(edemail);

                if (edConf.equals(edpass) && isValidEmail(edemail) && isValidPassword(edpass))
                {


                    db = new DatabaseHandler(RegisterActivity.this, null, null, 2);
                    Registerdata reg = new Registerdata();

                    reg.setfirstName(edfirst);
                    reg.setlastName(edlast);
                    reg.setEmailId(edemail);
                    reg.setMobNo(edmobile);
                    reg.setPassword(edpass);
                    db.addregister(reg);
                    Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                }

                else  {

                    email.setError("email must be .....@mail.com");
                    pass.setError("Password must be longer than 6 sybmols");
                    Toast.makeText(getApplicationContext(), "empty fields ", Toast.LENGTH_SHORT).show();
                }
            }






            private boolean isValidEmail(String email) {
                String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                Pattern pattern = Pattern.compile(EMAIL_PATTERN);
                Matcher matcher = pattern.matcher(email);
                return matcher.matches();
            }

            private boolean isValidPassword(String pass) {
                if (pass != null && pass.length() >= 6) {
                    return true;
                }
                return false;
            }





        });

    }
}

