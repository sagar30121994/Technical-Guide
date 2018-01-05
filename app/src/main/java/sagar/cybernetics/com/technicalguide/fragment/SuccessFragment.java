package sagar.cybernetics.com.technicalguide.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sagar.cybernetics.com.technicalguide.Communication.QuestionContact;
import sagar.cybernetics.com.technicalguide.R;
import sagar.cybernetics.com.technicalguide.Utils.Animations;
import sagar.cybernetics.com.technicalguide.Utils.Dialogs;
import sagar.cybernetics.com.technicalguide.activity.Home;
import sagar.cybernetics.com.technicalguide.adaptor.SuccessQeustionAdaptor;
import sagar.cybernetics.com.technicalguide.api_Client.api_Client;
import sagar.cybernetics.com.technicalguide.api_interface.Api_Interface;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuccessFragment extends Fragment {


    private ShareButton shareButton;

    private RingProgressBar ringProgressBar;
    private Button btnSubmit;
    String name="null";
    RecyclerView recyclerView;
    SuccessQeustionAdaptor sucsess_question_adaptor;
    private List<QuestionContact> contacts;
    private Api_Interface api_interface;
    private RecyclerView.LayoutManager layoutManager;

    String id;
    String test;
    String[] answers=new String[50];


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_success, container, false);


        ringProgressBar = (RingProgressBar) view.findViewById(R.id.progress_bar_2);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));



        shareButton=(ShareButton)view.findViewById(R.id.shareButton);

        Animations.animatorRecycler(recyclerView);


        Bundle bundle=getArguments();
        int abc= bundle.getInt("progress");
        test=bundle.getString("test");
        answers=bundle.getStringArray("answers");
        id=bundle.getString("id");

        ringProgressBar.setProgress(abc);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), Home.class);
                startActivity(i);
                getActivity().finish();
            }
        });


        activateTestModule(answers,test);



        ShareLinkContent content = new ShareLinkContent.Builder()
                //.setContentUrl(Uri.parse("https://developers.facebook.com"))
                .setContentUrl(Uri.parse("https://technicalguide.000webhostapp.com/Images/img8.jpg"))
                .setContentTitle("Technical Guide")
                .setQuote("Best Learning App for online exams...")
                .setContentDescription("Use Technical Guide for the online exams preparations all engineering branches included")
                .build();



        // ShareButton shareButton = (ShareButton)findViewById(R.id.fb_share_button);
        shareButton.setShareContent(content);



        return view;
    }


    public void activateTestModule(final String[] answer, String test){


        if (test.equals("true")){

            api_interface = api_Client.Api_Client.getApi_Client().create(Api_Interface.class);

            final ProgressDialog progressDoalog;

            progressDoalog = new ProgressDialog(getActivity());
            progressDoalog.setMessage("Please wait..");
            progressDoalog.setTitle("Getting Review.");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.setIndeterminate(true);
            progressDoalog.setCancelable(false);
            // show it
            progressDoalog.show();


            Map<String, String> map=new HashMap<>();
            map.put("id",id);
            map.put("test",test);


            Call<List<QuestionContact>> call = api_interface.getQuestionList(map);
            call.enqueue(new Callback<List<QuestionContact>>() {
                @Override
                public void onResponse(Call<List<QuestionContact>> call, Response<List<QuestionContact>> response) {
                    contacts = response.body();

                    if (contacts==null){
                        Dialogs.dataDialog(getActivity());

                    }else{



                        recyclerView.setVisibility(View.VISIBLE);

                        sucsess_question_adaptor=new SuccessQeustionAdaptor(contacts,getActivity(),answer);
                        recyclerView.setAdapter(sucsess_question_adaptor);



                        progressDoalog.dismiss();

                    }

                }


                @Override
                public void onFailure(Call<List<QuestionContact>> call, Throwable t) {

                    Dialogs.networkDialog(getActivity());
                    progressDoalog.dismiss();

                }
            });









        }





    }






}


