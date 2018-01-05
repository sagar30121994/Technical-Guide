package sagar.cybernetics.com.technicalguide.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import sagar.cybernetics.com.technicalguide.Communication.PartContact;
import sagar.cybernetics.com.technicalguide.R;
import sagar.cybernetics.com.technicalguide.Utils.Animations;
import sagar.cybernetics.com.technicalguide.Utils.Dialogs;
import sagar.cybernetics.com.technicalguide.adaptor.PartAdaptor;
import sagar.cybernetics.com.technicalguide.api_Client.api_Client;
import sagar.cybernetics.com.technicalguide.api_interface.Api_Interface;

public class PartFragment extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PartAdaptor partAdaptor;
    private List<PartContact> contacts;
    private Api_Interface api_interface;
    public String subject="";
    public  String id="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_part, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        api_interface = api_Client.Api_Client.getApi_Client().create(Api_Interface.class);


        Animations.animatorRecycler(recyclerView);



        Map<String, String> map=new HashMap<>();


        Bundle bundle = getArguments();
        if (bundle != null) {
            id=bundle.getString("id");
            subject=bundle.getString("subjectName");
            //   Toast.makeText(getActivity(),""+id,Toast.LENGTH_LONG).show();



            map.put("id",id);

        }else {


            map.put("id","1");


        }


        // Set up progress before call
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getActivity());
        progressDoalog.setMessage("Loading..");
        progressDoalog.setTitle("Getting Subjects.");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.setIndeterminate(true);
        progressDoalog.setCancelable(false);
        // show it
        progressDoalog.show();




        Call<List<PartContact>> call = api_interface.getPartList(map);
        final String finalSubject = subject;
        call.enqueue(new Callback<List<PartContact>>() {
            @Override
            public void onResponse(Call<List<PartContact>> call, Response<List<PartContact>> response) {
                contacts=response.body();

                partAdaptor= new PartAdaptor(contacts, getActivity(), subject);
                if (contacts == null) {
                    Dialogs.dataDialog(getActivity());
                } else {

                    recyclerView.setAdapter(partAdaptor);
                    progressDoalog.dismiss();
                }



            }

            @Override
            public void onFailure(Call<List<PartContact>> call, Throwable t) {

                Dialogs.networkDialog(getActivity());

                progressDoalog.dismiss();


            }
        });





        return  view;
    }

}
