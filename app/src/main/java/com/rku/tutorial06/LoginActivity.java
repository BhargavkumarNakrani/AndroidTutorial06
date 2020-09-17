package com.rku.tutorial06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button button;
    EditText username,password;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button=(Button)findViewById(R.id.btnSubmit);
        username=(EditText)findViewById(R.id.editUserName);
        password=(EditText)findViewById(R.id.editPassword);
        preferences=getSharedPreferences("Login",MODE_PRIVATE);
        editor=preferences.edit();

        if(preferences.contains("isLogin")){
            Intent intent=new Intent(this,WelcomeActivity.class);
            startActivity(intent);
        }
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String valusername = username.getText().toString();
                String valpassword = password.getText().toString();
                if (valusername.isEmpty()) {
                    username.setError("Enter Email Id.");
                    return;
                }
                if (valpassword.isEmpty()){
                    password.setError("Enter Password");
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(valusername).matches()) {
                    //Toast.makeText(LoginActivity.this, "Email is not valid.", Toast.LENGTH_LONG).show();
                    username.setError("Please Enter valid Email Address.");
                    return;
                }
                if (valusername.equals("admin@gmail.com") &&
                      valpassword.equals("admin")) {
                editor.putString("Username",valusername);
                editor.putBoolean("isLogin",true);
                editor.commit();
                Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class); // redirecting to LoginActivity.
                startActivity(intent);
                finish();
                } else {
                      Toast.makeText(LoginActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}