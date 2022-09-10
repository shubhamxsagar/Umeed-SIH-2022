package com.example.umeed.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umeed.APIs.ApiInterface;
import com.example.umeed.APIs.Controller;
import com.example.umeed.Models.StatusErrorModel;
import com.example.umeed.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    String[] gender = {"Male", "Female", "Prefer not to say"};
    String[] blood = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};

    TextInputEditText RLname, Rage, RFname, Remail, Rphone, Rallergy, Rpassword, Rconfirmpassword, Rayushman, Remergency1, Remergency2;
    AutoCompleteTextView Rgender, Rboodgroup, Rchronic;

    TextView chronic;
    Button next;
    AutoCompleteTextView autocompletetxtgender, autocompletetxtbgroup,Rcity,Rstate;
    ArrayAdapter<String> adapterItemsGender, adapterItemsBgroup;
    TextInputEditText edittext_confPass, edittext_Pass;
    TextInputLayout layout_pass, layout_confpass;
    boolean[] selectedDisease;
    ArrayList<Integer> diseaseList = new ArrayList<>();

    private String selectedDistrict, selectedState;
    private ArrayAdapter<CharSequence> stateAdapter, districtAdapter;

    String[] disease = {"Alzheimer", "Arthritis", "Asthma", "Bipolar Disorder",
            "Cancer", "COPD", "Cystic Fibrosis", "Depression", "Diabetes", "Eczema",
            "Ehlers-Danlos Syndrome", "Endometriosis", "Epilepsy", "Hashimoto’s Disease",
            "Heart Disease", "High Blood Pressure", "High Cholesterol", "HIV/AIDS",
            "Inflammatory Bowel Disease", "Lower Back Pain", "Lupus", "Mast Cell Activation Syndrome",
            "Maffucci’s Disease", "Migraine Headaches", "Scoliosis"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //EditText
        RLname = findViewById(R.id.RLname);
        RFname = findViewById(R.id.RFname);
        Remail = findViewById(R.id.Remail);
        Rphone = findViewById(R.id.Rphone);
        Rgender = findViewById(R.id.auto_complete_txt_gender);
        Rboodgroup = findViewById(R.id.auto_complete_txt_bgroup);
        Rage = findViewById(R.id.Rage);
        Rcity = findViewById(R.id.Rcity);
        Rstate = findViewById(R.id.Rstate);
        Rallergy = findViewById(R.id.Rallergy);
        Rpassword = findViewById(R.id.edittext_Pass);
        Rconfirmpassword = findViewById(R.id.edittext_confPass);
        Rayushman = findViewById(R.id.Rayushman);
        Remergency1 = findViewById(R.id.Rfirstemergency);
        Remergency2 = findViewById(R.id.Rsecondemergency);

        autocompletetxtgender = findViewById(R.id.auto_complete_txt_gender);
        autocompletetxtbgroup = findViewById(R.id.auto_complete_txt_bgroup);
        chronic = findViewById(R.id.chronic);
        edittext_confPass = findViewById(R.id.edittext_confPass);
        edittext_Pass = findViewById(R.id.edittext_Pass);
        layout_pass = findViewById(R.id.pass);
        layout_confpass = findViewById(R.id.confPass);
        next = findViewById(R.id.next);

        adapterItemsGender = new ArrayAdapter<String>(this, R.layout.list_item, gender);
        adapterItemsBgroup = new ArrayAdapter<String>(this, R.layout.list_item, blood);
        stateAdapter= ArrayAdapter.createFromResource(this, R.array.array_indian_states, R.layout.list_item);

        autocompletetxtgender.setAdapter(adapterItemsGender);
        autocompletetxtbgroup.setAdapter(adapterItemsBgroup);
        Rstate.setAdapter(stateAdapter);


        Intent intent = getIntent();
        String aadharno = intent.getExtras().getString("aadhar");

        autocompletetxtgender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });

        autocompletetxtbgroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });

        selectedDisease = new boolean[disease.length];

        chronic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);

                builder.setTitle("Select Chronic Diseases");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(disease, selectedDisease, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if (b) {
                            diseaseList.add(i);
                            Collections.sort(diseaseList);
                        } else {
                            diseaseList.remove(i);
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0; j < diseaseList.size(); j++) {
                            stringBuilder.append(disease[diseaseList.get(j)]);

                            if (j != diseaseList.size() - 1) {
                                stringBuilder.append(", ");
                            }
                        }
                        chronic.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int j = 0; j < selectedDisease.length; j++) {
                            selectedDisease[j] = false;
                            diseaseList.clear();
                            chronic.setText("Choose Chronic disease, if any");
                        }
                    }
                });

                builder.show();
            }
        });

        edittext_confPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateEditText(s);
            }
        });

        edittext_confPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateEditText(((EditText) v).getText());
                }
            }
        });

        Rstate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

                Rcity = findViewById(R.id.Rcity);

                selectedState = parent.getItemAtPosition(i).toString();  //Obtain the selected State

                Rcity.setText(" ");

                switch (selectedState){
                    case "Select Your State": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_default_districts, R.layout.list_item);
                        break;
                    case "Andhra Pradesh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_andhra_pradesh_districts, R.layout.list_item);
                        break;
                    case "Arunachal Pradesh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_arunachal_pradesh_districts, R.layout.list_item);
                        break;
                    case "Assam": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_assam_districts, R.layout.list_item);
                        break;
                    case "Bihar": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_bihar_districts, R.layout.list_item);
                        break;
                    case "Chhattisgarh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_chhattisgarh_districts, R.layout.list_item);
                        break;
                    case "Goa": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_goa_districts, R.layout.list_item);
                        break;
                    case "Gujarat": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_gujarat_districts, R.layout.list_item);
                        break;
                    case "Haryana": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_haryana_districts, R.layout.list_item);
                        break;
                    case "Himachal Pradesh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_himachal_pradesh_districts, R.layout.list_item);
                        break;
                    case "Jharkhand": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_jharkhand_districts, R.layout.list_item);
                        break;
                    case "Karnataka": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_karnataka_districts, R.layout.list_item);
                        break;
                    case "Kerala": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_kerala_districts, R.layout.list_item);
                        break;
                    case "Madhya Pradesh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_madhya_pradesh_districts, R.layout.list_item);
                        break;
                    case "Maharashtra": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_maharashtra_districts, R.layout.list_item);
                        break;
                    case "Manipur": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_manipur_districts, R.layout.list_item);
                        break;
                    case "Meghalaya": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_meghalaya_districts, R.layout.list_item);
                        break;
                    case "Mizoram": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_mizoram_districts, R.layout.list_item);
                        break;
                    case "Nagaland": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_nagaland_districts, R.layout.list_item);
                        break;
                    case "Odisha": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_odisha_districts, R.layout.list_item);
                        break;
                    case "Punjab": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_punjab_districts, R.layout.list_item);
                        break;
                    case "Rajasthan": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_rajasthan_districts, R.layout.list_item);
                        break;
                    case "Sikkim": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_sikkim_districts, R.layout.list_item);
                        break;
                    case "Tamil Nadu": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_tamil_nadu_districts, R.layout.list_item);
                        break;
                    case "Telangana": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_telangana_districts, R.layout.list_item);
                        break;
                    case "Tripura": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_tripura_districts, R.layout.list_item);
                        break;
                    case "Uttar Pradesh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_uttar_pradesh_districts, R.layout.list_item);
                        break;
                    case "Uttarakhand": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_uttarakhand_districts, R.layout.list_item);
                        break;
                    case "West Bengal": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_west_bengal_districts, R.layout.list_item);
                        break;
                    case "Andaman and Nicobar Islands": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_andaman_nicobar_districts, R.layout.list_item);
                        break;
                    case "Chandigarh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_chandigarh_districts, R.layout.list_item);
                        break;
                    case "Dadra and Nagar Haveli": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_dadra_nagar_haveli_districts, R.layout.list_item);
                        break;
                    case "Daman and Diu": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_daman_diu_districts, R.layout.list_item);
                        break;
                    case "Delhi": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_delhi_districts, R.layout.list_item);
                        break;
                    case "Jammu and Kashmir": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_jammu_kashmir_districts, R.layout.list_item);
                        break;
                    case "Lakshadweep": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_lakshadweep_districts, R.layout.list_item);
                        break;
                    case "Ladakh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_ladakh_districts, R.layout.list_item);
                        break;
                    case "Puducherry": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                            R.array.array_puducherry_districts, R.layout.list_item);
                        break;
                    default:  break;
                }
                districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     // Specify the layout to use when the list of choices appears
                Rcity.setAdapter(districtAdapter);        //Populate the list of Districts in respect of the State selected

                //To obtain the selected District from the spinner
                Rcity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        selectedDistrict = parent.getItemAtPosition(position).toString();
                    }
                });

            }

        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApiInterface apiInterface = Controller.getRetrofit().create(ApiInterface.class);
                Call<StatusErrorModel> call = apiInterface.patientregistration(

                        RFname.getText().toString() + ""
                        , RLname.getText().toString() + ""
                        , Rphone.getText().toString() + ""
                        , Remail.getText().toString() + ""
                        , Rage.getText().toString() + ""
                        , Rgender.getText().toString() + ""
                        , Rstate.getText().toString() + ""
                        , Rcity.getText().toString() + ""
                        , Rboodgroup.getText().toString() + ""
                        , chronic.getText().toString() + ""
                        , Rallergy.getText().toString() + ""
                        , Rpassword.getText().toString() + ""
                        , Rconfirmpassword.getText().toString() + ""
                        , aadharno + ""
                        , Rayushman.getText().toString() + ""
                        ,Remergency1.getText().toString()+""
                        ,Remergency2.getText().toString()+""

                );

                call.enqueue(new Callback<StatusErrorModel>() {
                    @Override
                    public void onResponse(Call<StatusErrorModel> call, Response<StatusErrorModel> response) {

                        StatusErrorModel statusErrorModel = response.body();
                        if (!statusErrorModel.isError()) {

                            Intent intent = new Intent(SignUp.this, Login.class);
                            startActivity(intent);
                            finish();
                        }
                        Toast.makeText(SignUp.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<StatusErrorModel> call, Throwable t) {

                        Log.e("Failed to Register", "onFailure: Register" + t);
                    }
                });

            }
        });

    }

    private void validateEditText(Editable s) {
        String password = edittext_Pass.getText().toString();
        String confpassword = edittext_confPass.getText().toString();
        if (password.equals(confpassword)) {
            layout_confpass.setError(null);
            layout_confpass.setEndIconDrawable(getDrawable(R.drawable.ic_baseline_check_24));
            layout_confpass.setBoxStrokeColor(getResources().getColor(R.color.green));
        } else {
            layout_confpass.setError("Password mismatch!!");
        }

    }
}