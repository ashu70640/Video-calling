package com.example.vediocalling.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.vediocalling.R;
import com.example.vediocalling.databinding.ActivityRewardBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class rewardActivity extends AppCompatActivity {

     ActivityRewardBinding binding;
    private RewardedAd mRewardedAd;
    FirebaseDatabase database;
    String currentUid;
    int coins=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRewardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database=FirebaseDatabase.getInstance();
        currentUid= FirebaseAuth.getInstance().getUid();
      loadAd();

      database.getReference()
              .child("profiles")
              .child(currentUid)
              .child("coins")
              .addValueEventListener(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot snapshot) {
                   coins=snapshot.getValue(Integer.class);
                   binding.coins.setText(String.valueOf(coins));
                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError error) {

                  }
              });
      binding.vedio.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if (mRewardedAd != null) {
                  Activity activityContext = rewardActivity.this;
                  mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                      @Override
                      public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                          // Handle the reward.
                          loadAd();
                          coins=coins+200;
                          database.getReference().child("profiles")
                                  .child(currentUid)
                                  .child("coins")
                                  .setValue(coins);
                          binding.videoIcon.setImageResource(R.drawable.check);

                      }
                  });
              } else {

              }
          }
      });
        binding.vedio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRewardedAd != null) {
                    Activity activityContext = rewardActivity.this;
                    mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            // Handle the reward.
                            coins=coins+200;
                            database.getReference().child("profiles")
                                    .child(currentUid)
                                    .child("coins")
                                    .setValue(coins);
                            binding.videoIcon2.setImageResource(R.drawable.check);

                        }
                    });
                } else {

                }
            }
        });
        binding.vedio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRewardedAd != null) {
                    Activity activityContext = rewardActivity.this;
                    mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            // Handle the reward.
                            coins=coins+200;
                            database.getReference().child("profiles")
                                    .child(currentUid)
                                    .child("coins")
                                    .setValue(coins);
                            binding.videoIcon3.setImageResource(R.drawable.check);

                        }
                    });
                } else {

                }
            }
        });
        binding.vedio4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRewardedAd != null) {
                    Activity activityContext = rewardActivity.this;
                    mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            // Handle the reward.
                            coins=coins+200;
                            database.getReference().child("profiles")
                                    .child(currentUid)
                                    .child("coins")
                                    .setValue(coins);
                            binding.videoIcon4.setImageResource(R.drawable.check);

                        }
                    });
                } else {

                }
            }
        });
        binding.vedio5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRewardedAd != null) {
                    Activity activityContext = rewardActivity.this;
                    mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            // Handle the reward.
                            coins=coins+200;
                            database.getReference().child("profiles")
                                    .child(currentUid)
                                    .child("coins")
                                    .setValue(coins);
                            binding.videoIcon5.setImageResource(R.drawable.check);

                        }
                    });
                } else {

                }
            }
        });

    }
    void loadAd(){
        AdRequest adRequest = new AdRequest.Builder().build();

        RewardedAd.load(this, "ca-app-pub-3940256099942544/5224354917",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.

                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;

                    }
                });
    }
}