package com.gumilang.rezza.challengeapps.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gumilang.rezza.challengeapps.R;
import com.gumilang.rezza.challengeapps.adapter.LoveAdapter;
import com.gumilang.rezza.challengeapps.holder.KeyValueHolder;
import com.gumilang.rezza.challengeapps.holder.LoveHolder;
import com.gumilang.rezza.challengeapps.holder.UserHolder;
import com.gumilang.rezza.challengeapps.lib.ClientHandler;
import com.gumilang.rezza.challengeapps.lib.MasterActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoveListActivity extends MasterActivity {

    private TextView        txvw_title_11;
    private TextView        txvw_name_12;
    private TextView        txvw_email_13;
    private TextView        txvw_address_14;
    private CircleImageView imvw_profile_10;
    private RecyclerView    recycler_view_20;

    private List<LoveHolder>mList = new ArrayList<>();
    private LoveAdapter     mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love_list);
        registerLayout();

        mAdapter = new LoveAdapter(mList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view_20.setLayoutManager(mLayoutManager);
        recycler_view_20.setItemAnimator(new DefaultItemAnimator());
        recycler_view_20.setAdapter(mAdapter);



    }

    @Override
    protected void registerLayout() {
        txvw_title_11   =  (TextView)           findViewById(R.id.txvw_title_11);
        txvw_name_12    =  (TextView)           findViewById(R.id.txvw_name_12);
        txvw_email_13   =  (TextView)           findViewById(R.id.txvw_email_13);
        txvw_address_14 =  (TextView)           findViewById(R.id.txvw_address_14);
        imvw_profile_10 =  (CircleImageView)    findViewById(R.id.imvw_profile_10);
        recycler_view_20=  (RecyclerView)       findViewById(R.id.recycler_view_20);
    }


    @Override
    protected void messageFromInten(JSONObject message) {
        UserHolder user = new UserHolder().unpack(message);
        txvw_title_11.setText(user.getFullname());
        txvw_name_12.setText(user.getUsername());
        txvw_email_13.setText(user.getEmail());
        txvw_address_14.setText(user.getSubdistrict_name()+","+ user.getRegion_name() +","+ user.getProvince_name());
        Glide.with(LoveListActivity.this)
                .load(user.getUrlPic())
                .into(imvw_profile_10);
        try {
            request(message.getString("token"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void request(String token){
        ClientHandler clientHandler = new ClientHandler(this);
        clientHandler.setUrl("https://dev.prelo.id/api/me/lovelist");
        clientHandler.setMethode("GET");
        JSONObject parameter = new JSONObject();
        try {
            parameter.put("token",token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        clientHandler.setHeader(parameter);
        clientHandler.execute();
        clientHandler.setOnReceiveListener(new ClientHandler.onReceiveListener() {
            @Override
            public void onReceive(String obj) {
                Log.d(TAG,obj);
                // Dummy Data, karena ketika get data nilai nya selalu kosong
                {
                    LoveHolder lv = new LoveHolder();
                    lv.title = "Kucing Persia 1 Bulan";
                    lv.price = "Rp 1.000.000";
                    mList.add(lv);
                }{
                    LoveHolder lv = new LoveHolder();
                    lv.title = "Kucing Persia";
                    lv.price = "Rp 900.000";
                    mList.add(lv);
                }
                {
                    LoveHolder lv = new LoveHolder();
                    lv.title = "Pikasanta";
                    lv.price = "Rp 2.000.000";
                    mList.add(lv);
                }
                {
                    LoveHolder lv = new LoveHolder();
                    lv.title = "Poy";
                    lv.price = "Rp 26.000";
                    mList.add(lv);
                }
                {
                    LoveHolder lv = new LoveHolder();
                    lv.title = "Iphone 6s Like new";
                    lv.price = "Rp 4.600.000";
                    mList.add(lv);
                }


                mAdapter.notifyDataSetChanged();
            }
        });
    }
}
