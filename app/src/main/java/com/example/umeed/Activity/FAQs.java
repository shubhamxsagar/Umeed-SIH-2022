package com.example.umeed.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.umeed.R;

public class FAQs extends AppCompatActivity {

    TextView general;
    LinearLayout generalquestion;

    TextView general1;
    TextView general1answer;

    TextView general2;
    TextView general2answer;

    TextView general3;
    TextView general3answer;

    TextView general4;
    TextView general4answer;

    TextView general5;
    TextView general5answer;

    TextView general6;
    TextView general6answer;

    TextView privacy;
    LinearLayout privacyquestion;

    TextView privacy1;
    TextView privacy1answer;

    TextView privacy2;
    TextView privacy2answer;

    TextView techincal;
    LinearLayout techincalquestion;

    TextView technical1;
    TextView technical1answer;

    TextView technical2;
    TextView technical2answer;

    LinearLayout register;
    LinearLayout guestuse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);

        general=(TextView) findViewById(R.id.general);
        generalquestion=(LinearLayout)  findViewById(R.id.generalquestions);
        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generalquestion.setVisibility(view.VISIBLE);
            }
        });


        general1=(TextView) findViewById(R.id.general1);
        general1answer=(TextView) findViewById(R.id.general1answer);
        general1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                general1answer.setVisibility(view.VISIBLE);
            }
        });
        general1answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                general1answer.setVisibility(view.GONE);
            }
        });

        general2=(TextView) findViewById(R.id.general2);
        general2answer=(TextView) findViewById(R.id.general2answer);
        general2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                general2answer.setVisibility(view.VISIBLE);
            }
        });
        general2answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                general2answer.setVisibility(view.GONE);
            }
        });

        general3=(TextView) findViewById(R.id.general3);
        general3answer=(TextView) findViewById(R.id.general3answer);
        general3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                general3answer.setVisibility(view.VISIBLE);
            }
        });
        general3answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                general3answer.setVisibility(view.GONE);
            }
        });
        general4=(TextView) findViewById(R.id.general4);
        general4answer=(TextView) findViewById(R.id.general4answer);
        general4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                general4answer.setVisibility(view.VISIBLE);
            }
        });
        general4answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                general4answer.setVisibility(view.GONE);
            }
        });

        general5=(TextView) findViewById(R.id.general5);
        general5answer=(TextView) findViewById(R.id.general5answer);
        general5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                general5answer.setVisibility(view.VISIBLE);
            }
        });
        general5answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                general5answer.setVisibility(view.GONE);
            }
        });

        general6=(TextView) findViewById(R.id.general6);
        general6answer=(TextView) findViewById(R.id.general6answer);
        general6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                general6answer.setVisibility(view.VISIBLE);
            }
        });
        general6answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                general6answer.setVisibility(view.GONE);
            }
        });

        privacy=(TextView) findViewById(R.id.privacy);
        privacyquestion=(LinearLayout)  findViewById(R.id.privacyquestions);
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                privacyquestion.setVisibility(view.VISIBLE);
            }
        });

        privacy1=(TextView) findViewById(R.id.privacy1);
        privacy1answer=(TextView) findViewById(R.id.privacy1answer);
        privacy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                privacy1answer.setVisibility(view.VISIBLE);
            }
        });
        privacy1answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                privacy1answer.setVisibility(view.GONE);
            }
        });
        privacy2=(TextView) findViewById(R.id.privacy2);
        privacy2answer=(TextView) findViewById(R.id.privacy2answer);
        privacy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                privacy2answer.setVisibility(view.VISIBLE);
            }
        });
        privacy2answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                privacy2answer.setVisibility(view.GONE);
            }
        });

        techincal=(TextView) findViewById(R.id.technical);
        techincalquestion=(LinearLayout)  findViewById(R.id.technicalquestions);
        techincal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                techincalquestion.setVisibility(view.VISIBLE);
            }
        });

        technical1=(TextView) findViewById(R.id.technical1);
        technical1answer=(TextView) findViewById(R.id.technical1answer);
        technical1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                technical1answer.setVisibility(view.VISIBLE);
            }
        });
        technical1answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                technical1answer.setVisibility(view.GONE);
            }
        });
        technical2=(TextView) findViewById(R.id.technical2);
        technical2answer=(TextView) findViewById(R.id.technical2answer);
        technical2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                technical2answer.setVisibility(view.VISIBLE);
            }
        });
        technical2answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                technical2answer.setVisibility(view.GONE);
            }
        });

        register=(LinearLayout) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FAQs.this, FAQsRegisater.class);
                startActivity(intent);
            }
        });

        guestuse=(LinearLayout) findViewById(R.id.guestuse);
        guestuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FAQs.this, FAQsGuest.class);
                startActivity(intent);
            }
        });

    }
}