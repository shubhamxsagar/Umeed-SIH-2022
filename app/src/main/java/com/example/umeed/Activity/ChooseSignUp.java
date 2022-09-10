package com.example.umeed.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.umeed.R;

public class ChooseSignUp extends AppCompatActivity {

    Button loginbutton1;
    Button signupbutton1, guestLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_signup);

        signupbutton1 = (Button) findViewById(R.id.signupbutton1);
        loginbutton1 = (Button) findViewById(R.id.loginbutton1);
        guestLogin = findViewById(R.id.guestBtn);

        guestLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseSignUp.this, HomePage.class);
                startActivity(intent);
            }
        });

        loginbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseSignUp.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        signupbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseSignUp.this, AadharPage.class);
                startActivity(intent);

            }
        });
    }
}