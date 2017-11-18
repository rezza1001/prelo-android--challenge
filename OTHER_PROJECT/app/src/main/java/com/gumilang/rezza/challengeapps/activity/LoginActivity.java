package com.gumilang.rezza.challengeapps.activity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gumilang.rezza.challengeapps.R;
import com.gumilang.rezza.challengeapps.component.MyEditext;
import com.gumilang.rezza.challengeapps.holder.KeyValueHolder;
import com.gumilang.rezza.challengeapps.holder.LoginUserHolder;
import com.gumilang.rezza.challengeapps.lib.ClientHandler;
import com.gumilang.rezza.challengeapps.lib.MasterActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends MasterActivity {

    private MyEditext   edwt_username_01;
    private MyEditext   edwt_password_02;
    private TextView    txvw_notif_00;
    private Button      bbtn_login_01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerLayout();

    }

    @Override
    protected void registerLayout(){
        edwt_username_01    = (MyEditext)   findViewById(R.id.edwt_username_01);
        edwt_password_02    = (MyEditext)   findViewById(R.id.edwt_password_02);
        txvw_notif_00       = (TextView)    findViewById(R.id.txvw_notif_00);
        bbtn_login_01       = (Button)      findViewById(R.id.bbtn_login_01);

        edwt_username_01.setTitle("Username/Email");
        edwt_password_02.setTitle("Password");
        edwt_password_02.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        txvw_notif_00.setVisibility(View.GONE);

        edwt_username_01.setValue("applicant");
        edwt_password_02.setValue("password");

        bbtn_login_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLogin();
            }
        });
    }

    @Override
    protected void messageFromInten(JSONObject message) {

    }

    private void onLogin(){
        if (validation()){
            String username = edwt_username_01.getValue();
            String password = edwt_password_02.getValue();
            // CALL API
            ClientHandler mClient = new ClientHandler(this);
            mClient.setUrl("https://dev.prelo.id/api/auth/login");
            mClient.addParam(new KeyValueHolder("username_or_email",username));
            mClient.addParam(new KeyValueHolder("password",password));
            mClient.execute();
            mClient.setOnReceiveListener(new ClientHandler.onReceiveListener() {
                @Override
                public void onReceive(String result) {
                    JSONObject jResponse = null;
                    try {
                        jResponse = new JSONObject(result);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    LoginUserHolder logindata = new LoginUserHolder().unpackDataFromServer(jResponse);

                    if (logindata.getMessage_login().equals("200 OK")){
                        changeActivity(LoveListActivity.class, logindata.pack());
                    }
                    else {
                        Toast.makeText(LoginActivity.this, logindata.getMessage_login(), Toast.LENGTH_LONG).show();
                    }

                }
            });



        }
    }

    private boolean validation(){
        if (edwt_username_01.getValue().isEmpty()){
            showNotif("Username required !");
          return false;
        }
        else if (edwt_username_01.getValue().length() < 4){
            showNotif("Your username must be at least 4 characters.");
            return false;
        }
        else if (edwt_password_02.getValue().isEmpty()){
            showNotif("Password required !");
            return false;
        }
        else if (edwt_password_02.getValue().length() < 6){
            showNotif("Your password must be at least 6 characters.");
            return false;
        }
        else {
            return true;
        }
    }

    private void showNotif(String pMessage){
        txvw_notif_00.setText(pMessage);
        txvw_notif_00.setVisibility(View.VISIBLE);
    }
}
