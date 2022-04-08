package com.laioffer.tinnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.network.NewsApi;
import com.laioffer.tinnews.network.RetrofitClient;

public class MainActivity extends AppCompatActivity {

    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController);

        NewsApi api = RetrofitClient.newInstance().create(NewsApi.class);
        Log.d("getTopHeadlines", "CREATED");
        api.getTopHeadlines("US").enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                Log.d("getTopHeadlines", "RESPONSE");
                if (response.isSuccessful()) {
                    Log.d("getTopHeadlines", "SUCCESS "+response.body().toString());
                } else {
                    Log.d("getTopHeadlines", "ELSE "+response.toString());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.d("getTopHeadlines", "FAILED "+t.toString());
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }



//        binding.submitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name = binding.nameEdittext.getText().toString();
//                String email = binding.emailEdittext.getText().toString();
//                binding.welcomeTextview.setText("welcome "+ name + ", " + email);
//            }
//        });
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.d(TAG, "onStart");
//        // App logo load screen in the start. This is where it does its thing
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.d(TAG, "onResume");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.d(TAG, "onPause");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.d(TAG, "onStop");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.d(TAG, "onDestroy");
//    }
}