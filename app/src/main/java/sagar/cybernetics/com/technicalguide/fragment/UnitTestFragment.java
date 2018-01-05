package sagar.cybernetics.com.technicalguide.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
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
import sagar.cybernetics.com.technicalguide.Communication.TestContact;
import sagar.cybernetics.com.technicalguide.Communication.UnitContact;
import sagar.cybernetics.com.technicalguide.R;
import sagar.cybernetics.com.technicalguide.Utils.Animations;
import sagar.cybernetics.com.technicalguide.Utils.Dialogs;
import sagar.cybernetics.com.technicalguide.adaptor.TestAdaptor;
import sagar.cybernetics.com.technicalguide.adaptor.UnitTestAdaptor;
import sagar.cybernetics.com.technicalguide.api_Client.api_Client;
import sagar.cybernetics.com.technicalguide.api_interface.Api_Interface;

public class UnitTestFragment extends Fragment {

    private RecyclerView recyclerView,TestRecyclerView;
    private RecyclerView.LayoutManager layoutManager,layoutManager1;
    private UnitTestAdaptor recycler_adaptor;
    private List<UnitContact> contacts;
    private  List<TestContact> test_contacts;
    private Api_Interface api_interface;
    private TestAdaptor test_adaptor;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_unit_test, container, false);





        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        TestRecyclerView=(RecyclerView)view.findViewById(R.id.recyclerView1);
        layoutManager1=new LinearLayoutManager(getActivity());
        TestRecyclerView.setLayoutManager(layoutManager1);
        TestRecyclerView.setHasFixedSize(true);
        // recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));


        Animations.animatorRecycler(recyclerView);
        Animations.animatorRecycler(TestRecyclerView);



        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        TestRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        api_interface= api_Client.Api_Client.getApi_Client().create(Api_Interface.class);





        Map<String, String> map=new HashMap<>();

        String id="";
        Bundle bundle = getArguments();
        if (bundle != null) {
            id=bundle.getString("id");
            //   Toast.makeText(getActivity(),""+id,Toast.LENGTH_LONG).show();



            map.put("id",id);

        }else {


            map.put("id","1");


        }



        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getActivity());
        progressDoalog.setMessage("Loading..");
        progressDoalog.setTitle("Getting Subjects.");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.setIndeterminate(true);
        progressDoalog.setCancelable(false);

        progressDoalog.show();


        Call<List<UnitContact>> call =api_interface.getSubject(map);

        call.enqueue(new Callback<List<UnitContact>>() {
            @Override
            public void onResponse(Call<List<UnitContact>> call, Response<List<UnitContact>> response) {

                contacts = response.body();

                recycler_adaptor = new UnitTestAdaptor(contacts, getActivity());
                if (contacts == null) {
                    Dialogs.dataDialog(getActivity());
                } else {
                    recyclerView.setAdapter(recycler_adaptor);
                    progressDoalog.dismiss();

                }


            }

            @Override
            public void onFailure(Call<List<UnitContact>> call, Throwable t) {


                Dialogs.networkDialog(getActivity());
                progressDoalog.dismiss();

            }

        });


        final ProgressDialog progressDoalog1;
        progressDoalog1 = new ProgressDialog(getActivity());

        progressDoalog1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog1.setIndeterminate(true);
        progressDoalog1.setCancelable(false);


        progressDoalog1.setMessage("Loading..");
        progressDoalog1.setTitle("Getting Tests..");
        progressDoalog1.show();


        Call<List<TestContact>> call1=api_interface.getTest(map);

        call1.enqueue(new Callback<List<TestContact>>() {
            @Override
            public void onResponse(Call<List<TestContact>> call, Response<List<TestContact>> response) {
                test_contacts=response.body();
                test_adaptor=new TestAdaptor(test_contacts,getActivity());
                //    Toast.makeText(getActivity(), "executing", Toast.LENGTH_LONG).show();

                if (test_contacts == null) {
                    Dialogs.dataDialog(getActivity());
                } else {
                    TestRecyclerView.setAdapter(test_adaptor);
                    progressDoalog1.dismiss();
                }

            }

            @Override
            public void onFailure(Call<List<TestContact>> call, Throwable t) {
                Dialogs.networkDialog(getActivity());
                progressDoalog1.dismiss();

            }
        });






        return view;

    }

}
