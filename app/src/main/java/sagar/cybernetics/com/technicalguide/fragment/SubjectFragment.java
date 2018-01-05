package sagar.cybernetics.com.technicalguide.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sagar.cybernetics.com.technicalguide.Communication.Subject_Contact;
import sagar.cybernetics.com.technicalguide.R;
import sagar.cybernetics.com.technicalguide.Utils.Animations;
import sagar.cybernetics.com.technicalguide.Utils.Dialogs;
import sagar.cybernetics.com.technicalguide.adaptor.Subject_Adaptor;
import sagar.cybernetics.com.technicalguide.api_Client.api_Client;
import sagar.cybernetics.com.technicalguide.api_interface.Api_Interface;

public class SubjectFragment extends Fragment {

    getCLickListener getcLicklistener;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Subject_Adaptor recycler_adaptor;
    private List<Subject_Contact> contacts;
    private Api_Interface api_interface;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_subject, container, false);



        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewSubject);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        api_interface = api_Client.Api_Client.getApi_Client().create(Api_Interface.class);


        Animations.animatorRecycler(recyclerView);


        String account,accountCopmlete, Year,University,Branch;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());

        //SharedPreferences sharedPreferences=ListHome.this.getSharedPreferences("Profile",MODE_PRIVATE);
        account = sharedPreferences.getString("profile", "");
        accountCopmlete = sharedPreferences.getString("profileComplete", "");


        Year=sharedPreferences.getString("Year","");
        University=sharedPreferences.getString("University","");
        Branch=sharedPreferences.getString("Branch","");





        Map<String, String> map=new HashMap<>();
        map.put("year",Year);
        map.put("branch",Branch);












        if(!Year.equals("")){

            loadData(map);


        }else{

            Dialogs.accountDialog(getActivity());

        }

/*
        Call<List<Subject_Contact>> call = api_interface.getSubjectName(map);

        call.enqueue(new Callback<List<Subject_Contact>>() {


            @Override
            public void onResponse(Call<List<Subject_Contact>> call, Response<List<Subject_Contact>> response) {

                contacts = response.body();

                recycler_adaptor = new Subject_Adaptor(contacts, getActivity());
                if (contacts == null) {
                    Dialogs.dataDialog(getActivity());


                } else {

                    recyclerView.setAdapter(recycler_adaptor);
               showProgressBar(false);

                }


            }

            @Override
            public void onFailure(Call<List<Subject_Contact>> call, Throwable t) {

                Dialogs.networkDialog(getActivity());

                showProgressBar(false);

            }


        });*/

            return view;

    }

    public void getClicked(String id){


        getcLicklistener.clickedId(id);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            getcLicklistener = (getCLickListener) activity;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public interface getCLickListener{

        public void clickedId(String id);
    }

public void showProgressBar(Boolean status) {







    }



    public void loadData(Map<String, String> map){


        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getActivity());
        progressDoalog.setMessage("Loading..");
        progressDoalog.setTitle("Getting Subjects.");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.setIndeterminate(true);
        progressDoalog.setCancelable(true);

        progressDoalog.show();



        Call<List<Subject_Contact>> call = api_interface.getSubjectName(map);

        call.enqueue(new Callback<List<Subject_Contact>>() {


            @Override
            public void onResponse(Call<List<Subject_Contact>> call, Response<List<Subject_Contact>> response) {

                contacts = response.body();

                recycler_adaptor = new Subject_Adaptor(contacts, getActivity());
                if (contacts == null) {
                    Dialogs.dataDialog(getActivity());


                } else {

                    recyclerView.setAdapter(recycler_adaptor);
                    progressDoalog.dismiss();

                }


            }

            @Override
            public void onFailure(Call<List<Subject_Contact>> call, Throwable t) {

                Dialogs.networkDialog(getActivity());
                progressDoalog.dismiss();
            }


        });

    }



}

