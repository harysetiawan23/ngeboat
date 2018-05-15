package com.example.harry.ngeboat.View.Fragment;


import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.harry.ngeboat.R;
import com.example.harry.ngeboat.View.SearchView;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment   {
    private View mView;

    private RadioButton oneWay,returning;
    private LinearLayout returning_date;
    private View returning_line;

    private SliderLayout sliderShow;

    private TextView redirect_link;

    private Button mSearchButton;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_search, container, false);

//        Returning View
        returning_date = (LinearLayout)mView.findViewById(R.id.returning_date);
        returning_line = (View) mView.findViewById(R.id.returning_line);


        //Radio Button
        oneWay = (RadioButton)mView.findViewById(R.id.r_one_way);
        returning = (RadioButton)mView.findViewById(R.id.r_returning);

        //Redirect Link
        redirect_link = (TextView)mView.findViewById(R.id.redrect_link);
        redirect_link.setMovementMethod(LinkMovementMethod.getInstance());


        //Search Button
        mSearchButton = (Button)mView.findViewById(R.id.search_frag_button);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SearchView.class);

                View sharedView = mSearchButton;
                String transitionName = getString(R.string.search_To_filter);

                ActivityOptions transitionActivityOptions = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(getActivity(), sharedView, transitionName);
                }
                startActivity(i, transitionActivityOptions.toBundle());

            }
        });


        //Slider
        sliderShow = (SliderLayout)mView.findViewById(R.id.slider);


        defaultCondition();
        setSliderShow();

        return mView;
    }

    private void setSliderShow(){
        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("Mac Ku", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTxOC5SG1s4n2clJf8hhcMu_PpgReX5vIF1icmThrmSnWPggIIQ");

        for(String name : url_maps.keySet()){
            DefaultSliderView textSliderView = new DefaultSliderView(getContext());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name));

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            sliderShow.addSlider(textSliderView);
        }


        sliderShow.setDuration(4000);
        sliderShow.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
        sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Left_Top);


    }

    private void defaultCondition(){
        returning_date.setVisibility(View.GONE);
        returning_line.setVisibility(View.GONE);

        oneWay.setChecked(true);

        oneWay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    returning_date.setVisibility(View.GONE);
                    returning_line.setVisibility(View.GONE);
                }

            }
        });

        returning.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    returning_date.setVisibility(View.VISIBLE);
                    returning_line.setVisibility(View.VISIBLE);
                }

            }
        });


    }

    @Override
    public void onStop() {
        sliderShow.startAutoCycle();
        super.onStop();
    }



}
