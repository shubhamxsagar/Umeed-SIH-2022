package com.example.umeed.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.umeed.R;

public class Policies extends AppCompatActivity {

    LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4,linearLayout5,linearLayout6,linearLayout7,linearLayout8;
    TextView policyInfo1,policyInfo2,policyInfo3,policyInfo4,policyInfo5,policyInfo6,policyInfo7,policyInfo8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policies);

        linearLayout1 = findViewById(R.id.linearLayout1);
        linearLayout2 = findViewById(R.id.linearLayout2);
        linearLayout3 = findViewById(R.id.linearLayout3);
        linearLayout4 = findViewById(R.id.linearLayout4);
        linearLayout5 = findViewById(R.id.linearLayout5);
        linearLayout6 = findViewById(R.id.linearLayout6);
        linearLayout7 = findViewById(R.id.linearLayout7);
        linearLayout8 = findViewById(R.id.linearLayout8);
        policyInfo1 = findViewById(R.id.policyInfo1);
        policyInfo2 = findViewById(R.id.policyInfo2);
        policyInfo3 = findViewById(R.id.policyInfo3);
        policyInfo4 = findViewById(R.id.policyInfo4);
        policyInfo5 = findViewById(R.id.policyInfo5);
        policyInfo6 = findViewById(R.id.policyInfo6);
        policyInfo7 = findViewById(R.id.policyInfo7);
        policyInfo8 = findViewById(R.id.policyInfo8);

        linearLayout1.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        linearLayout2.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        linearLayout3.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        linearLayout4.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        linearLayout5.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        linearLayout6.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        linearLayout7.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        linearLayout8.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        policyInfo1.setMovementMethod(LinkMovementMethod.getInstance());
        policyInfo2.setMovementMethod(LinkMovementMethod.getInstance());
        policyInfo3.setMovementMethod(LinkMovementMethod.getInstance());
        policyInfo4.setMovementMethod(LinkMovementMethod.getInstance());
        policyInfo5.setMovementMethod(LinkMovementMethod.getInstance());
        policyInfo6.setMovementMethod(LinkMovementMethod.getInstance());
        policyInfo7.setMovementMethod(LinkMovementMethod.getInstance());
        policyInfo8.setMovementMethod(LinkMovementMethod.getInstance());

    }

    public void expand1(View view) {

        int v = (policyInfo1.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(linearLayout1, new AutoTransition());
        policyInfo1.setVisibility(v);

    }

    public void expand2(View view) {

        int v = (policyInfo2.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(linearLayout2, new AutoTransition());
        policyInfo2.setVisibility(v);
    }

    public void expand3(View view) {

        int v = (policyInfo3.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(linearLayout3, new AutoTransition());
        policyInfo3.setVisibility(v);

    }

    public void expand4(View view) {

        int v = (policyInfo4.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(linearLayout4, new AutoTransition());
        policyInfo4.setVisibility(v);

    }

    public void expand5(View view) {

        int v = (policyInfo5.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(linearLayout5, new AutoTransition());
        policyInfo5.setVisibility(v);

    }

    public void expand6(View view) {

        int v = (policyInfo6.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(linearLayout6, new AutoTransition());
        policyInfo6.setVisibility(v);

    }

    public void expand7(View view) {

        int v = (policyInfo7.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(linearLayout7, new AutoTransition());
        policyInfo7.setVisibility(v);

    }

    public void expand8(View view) {

        int v = (policyInfo8.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(linearLayout8, new AutoTransition());
        policyInfo8.setVisibility(v);

    }
}