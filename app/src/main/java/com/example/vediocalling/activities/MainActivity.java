package com.example.vediocalling.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.opengl.GLES31Ext;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.vediocalling.R;
import com.example.vediocalling.databinding.ActivityMainBinding;
import com.example.vediocalling.models.User;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    long AvailCoins=0;
    String[] permissions=new String[]{Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO};
    private int requestCode=1;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        FirebaseUser currentUser= auth.getCurrentUser();
        database.getReference().child("profiles")
                .child(currentUser.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                             user=snapshot.getValue(User.class);
                             AvailCoins=user.getCoins();
                             binding.AvailCoins.setText("You have: "+ AvailCoins);
                             Glide.with(MainActivity.this)
                                .load(user.getProfile())
                                .into(binding.profilePic);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        binding.findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPermissionGranted()) {
                    if (AvailCoins > 5) {
                        AvailCoins=AvailCoins-5;
                        database.getReference().child("profiles")
                                .child(currentUser.getUid())
                                .child("coins")
                                .setValue(AvailCoins);
                        Intent intent=new Intent(MainActivity.this,connectingActivity.class);
//                        startActivity(new Intent(MainActivity.this, connectingActivity.class));
                        intent.putExtra("profile",user.getProfile());
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Insufficient Coins", Toast.LENGTH_SHORT).show();
                    }
                }else {
                   askPermissions();
                }
            }
        });
        binding.rewardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,rewardActivity.class));
            }
        });
    }

    void askPermissions(){
        ActivityCompat.requestPermissions(this,permissions,requestCode);
    }
    private boolean isPermissionGranted(){
        for(String permission: permissions){
            if(ActivityCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }
}