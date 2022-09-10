package com.example.umeed.Map;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.umeed.R;


public class MapPermission extends AppCompatActivity {

    private Button btnGrant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_permission);

//        if(ContextCompat.checkSelfPermission(MapPermission.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
//            startActivity(new Intent(MapPermission.this, MapsFragment.class));
//            finish();
//            return;
//        }
//
//        btnGrant = findViewById(R.id.btn_grant);
//
//        btnGrant.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Dexter.withActivity(MapPermission.this)
//                        .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
//                        .withListener(new PermissionListener() {
//                            @Override
//                            public void onPermissionGranted(PermissionGrantedResponse response) {
//                                startActivity(new Intent(MapPermission.this, MapsFragment.class));
//                                finish();
//                            }
//
//                            @Override
//                            public void onPermissionDenied(PermissionDeniedResponse response) {
//                                if(response.isPermanentlyDenied()){
//                                    AlertDialog.Builder builder = new AlertDialog.Builder(MapPermission.this);
//                                    builder.setTitle("Permission Denied")
//                                            .setMessage("Permission to access device location is permanently denied. you need to go to setting to allow the permission.")
//                                            .setNegativeButton("Cancel", null)
//                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialog, int which) {
//                                                    Intent intent = new Intent();
//                                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                                                    intent.setData(Uri.fromParts("package", getPackageName(), null));
//                                                }
//                                            })
//                                            .show();
//                                } else {
//                                    Toast.makeText(MapPermission.this, "Permission Denied", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//
//                            @Override
//                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
//                                token.continuePermissionRequest();
//                            }
//                        })
//                        .check();
//            }
//        });
    }
}