package com.gumilang.rezza.challengeapps.lib;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.gumilang.rezza.challengeapps.holder.KeyValueHolder;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.util.EntityUtils;

/**
 * Created by Rezza on 8/22/17.
 */

public class ClientHandler extends AsyncTask<String, Void, String> {

    private static final String TAG = "LongRunningGetIO";
    private String url = "";
    private String method = "";
    private ArrayList<KeyValueHolder> kvh = new ArrayList<>();
    private Context context;
    private ProgressDialog loading;
    private JSONObject mData = new JSONObject();

    public ClientHandler(Context mContext){
        context = mContext;
        loading = new ProgressDialog(mContext);
        loading.setMessage("Please Wait...");
        loading.show();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public ArrayList<KeyValueHolder> getKeyValue() {
        return kvh;
    }

    public void addParam(KeyValueHolder kv){
        kvh.add(kv);
    }

    public void setHeader(JSONObject pData){
        mData = pData;
    }

    public void setMethode(String pmethode){
        method = pmethode;
    }

    protected String doInBackground(String... arg0) {

        if (method.equals("GET")){
            Log.d(TAG,"GET METHOD");try {
                URL url = new URL(this.url); //Enter URL here
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("GET"); // here you are telling that it is a POST request, which can be changed into "PUT", "GET", "DELETE" etc.
                httpURLConnection.setRequestProperty("Content-Type", "application/json"); // here you are setting the `Content-Type` for the data you are sending which is `application/json`
                httpURLConnection.connect();

                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                Log.d(TAG,"SEND : "+ mData.toString());
                wr.writeBytes(mData.toString());
                wr.flush();
                wr.close();

                int responseCode = httpURLConnection.getResponseCode();
                Log.d(TAG,"RESPONSE CODE : "+ responseCode);
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Request time out";
            } catch (IOException e) {
                e.printStackTrace();
                return "Request time out";
            }

        }
        else {
            Log.d(TAG,"POST METHOD");
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(this.url);
            String text = null;
            List<NameValuePair> nameValuePair = new ArrayList<>(getKeyValue().size());
            Log.d(TAG,"Post To "+httpPost.getURI().toString());
            try {
                for (int i=0; i< getKeyValue().size(); i++){
                    Log.d(TAG,"PARAM TO SEND "+ "Key "+ getKeyValue().get(i).getKey()+" | Value "+getKeyValue().get(i).getValue() );
                    nameValuePair.add(new BasicNameValuePair(getKeyValue().get(i).getKey(), getKeyValue().get(i).getValue()));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
            } catch (Exception e) {
                return e.getLocalizedMessage();
            }

            try {
                HttpResponse response = httpClient.execute(httpPost);
                HttpEntity ent = response.getEntity();
                text = EntityUtils.toString(ent);
                return text;
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                loading.dismiss();
                return "Request time out";
            } catch (IOException e) {
                loading.dismiss();
                e.printStackTrace();
                return "Request time out";
            }
        }



    }


    protected void onPostExecute(String results) {
        loading.dismiss();
        if (results!=null) {
            Log.d(TAG,"TEXT RESPONSE "+ results.toString());
        }
        if (mReceiveListener != null){
            if (results != null){
                if (results.equals("Request time out")){
                    Toast.makeText(context,"Request time out", Toast.LENGTH_SHORT).show();
                }
                mReceiveListener.onReceive(results);
            }
        }

    }

    private onReceiveListener mReceiveListener;
    public void setOnReceiveListener(onReceiveListener mReceiveListener){
        this.mReceiveListener = mReceiveListener;
    }
    public interface onReceiveListener{
        public void onReceive(String obj);
    }
}
