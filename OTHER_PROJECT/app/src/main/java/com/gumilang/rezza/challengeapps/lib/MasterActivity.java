package com.gumilang.rezza.challengeapps.lib;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rezza on 17/11/17.
 */

public abstract class MasterActivity extends Activity {
    protected static final String TAG = "MasterActivity";

    protected abstract void registerLayout();

    protected void changeActivity(Class pNextActivity, String data){
        Intent intent = new Intent(MasterActivity.this, pNextActivity);
        intent.putExtra("DATA", data);
        startActivity(intent);
        this.finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String data = intent.getStringExtra("DATA");

        try {
            Log.d(TAG, data);
            messageFromInten(new JSONObject(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void messageFromInten(JSONObject message);
}
