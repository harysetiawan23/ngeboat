package com.example.harry.ngeboat.View;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionInflater;
import com.example.harry.ngeboat.R;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class SearchView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);

//        if(Build.VERSION.SDK_INT>=21)
//        {
//            getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.shared_element_transation));
//        }
    }



    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
    }
}
