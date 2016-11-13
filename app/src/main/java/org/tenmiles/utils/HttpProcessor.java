package org.tenmiles.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.view.View;


import java.io.IOException;


/**
 * Created by comp17 on 5/8/15.
 */
public class HttpProcessor {


//    private Context context;
//    private RequestBody valueMap;
//    private String url;
//    private String method;
//    private boolean isShowDialog;
//    private AsyncTask httpRequest;
//
//    private SmoothProgressBar progressBar;
//    ProgressDialog progressDialog;
//
//    public HttpProcessor(Context context, boolean showDialog, SmoothProgressBar progressBar, String url, String method, RequestBody valueMap) {
//        this.context = context;
//        this.url = url;
//        this.valueMap = valueMap;
//        this.isShowDialog = showDialog;
//        this.progressBar = progressBar;
//        this.method = method;
//    }
//
//
//    public AsyncTask executeRequest(String TAG) {
//        try{
//            if (isConnected()) {
//                httpRequest = new HttpRequest(TAG, valueMap).execute();
//            } else {
//                if (null != httpResponserListener) {
//                    httpResponserListener.responseResult("", TAG, false);
//                }
//            }
//        }catch (OutOfMemoryError e){
//            e.printStackTrace();
//            if (null != httpResponserListener) {
//                httpResponserListener.responseResult("", TAG, false);
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            if (null != httpResponserListener) {
//                httpResponserListener.responseResult("", TAG, false);
//            }
//        }
//
//        return httpRequest;
//    }
//
//
//    public class HttpRequest extends AsyncTask<String, String, String> {
//
//        ProgressDialog progressDialog;
//        String TAG = "";
//        RequestBody valueMap;
//
//        public HttpRequest(String TAG, RequestBody valueMap) {
//            this.TAG = TAG;
//            this.valueMap = valueMap;
//        }
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            if (isShowDialog) {
//
//                if (progressBar == null) {
//                    progressDialog = new ProgressDialog(context);
//                    progressDialog.setIndeterminate(true);
//                    progressDialog.setCanceledOnTouchOutside(false);
//                    progressDialog.show();
//                } else {
//                    progressBar.setVisibility(View.VISIBLE);
//                    progressBar.progressiveStart();
//                }
//
//            }
//        }
//
//        @Override
//        protected String doInBackground(String... hashMaps) {
//            String result = "";
//
//            OkHttpClient client = new OkHttpClient();
//
//            Request request = null;
//
//            if (method.equals("PUT")) {
//
//                request = new Request.Builder()
//                        .url(API.BASE_URL + url)
//                        .put(valueMap)
//                        .build();
////                .put(valueMap)
//            } else if (method.equals("POST")) {
//                request = new Request.Builder()
//                        .url(API.BASE_URL + url)
//                        .post(valueMap)
//                        .build();
//            } else if (method.equals("GET")) {
//                request = new Request.Builder()
//                        .url(url)
//                        .build();
//            }else if (method.equals("SENTHIL")) {
//                request = new Request.Builder()
//                        .url("http://106.51.252.229/thunderbird/buzzinga/api/UserRegistration")
//                        .post(valueMap)
//                        .build();
//            }
//
//            try {
//                Response response = null;
//                response = client.newCall(request).execute();
//
//                result = response.body().string();
//                return result;
//
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }  catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            return result;
//        }
//
//        @Override
//        protected void onCancelled() {
//            super.onCancelled();
//            if (null != progressDialog && progressDialog.isShowing()) {
//                progressDialog.dismiss();
//            }
//        }
//
//        @Override
//        protected void onCancelled(String s) {
//            super.onCancelled(s);
//        }
//
//
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            if (null != result && null != httpResponserListener) {
//
//                try {
////                    JSONObject jsonObject = new JSONObject(result);
//                    httpResponserListener.responseResult(result, TAG, true);
//                    if (null != progressBar && progressBar.isShown()) {
////                        progressDialog.dismiss();
//                        progressBar.progressiveStop();
//                        progressBar.setVisibility(View.INVISIBLE);
//                    } else if (null != progressDialog && progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//                } catch (NullPointerException je) {
//                    je.printStackTrace();
//                    if (null != progressBar && progressBar.isShown()) {
////                        progressDialog.dismiss();
//                        progressBar.progressiveStop();
//                        progressBar.setVisibility(View.INVISIBLE);
//                    } else if (null != progressDialog && progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    if (null != progressBar && progressBar.isShown()) {
////                        progressDialog.dismiss();
//                        progressBar.progressiveStop();
//                        progressBar.setVisibility(View.INVISIBLE);
//                    }
//                }
//            }
//        }
//    }
//
//    // check network connection
//    public boolean isConnected() {
//        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//        if (networkInfo != null && networkInfo.isConnected())
//            return true;
//        else
//            return false;
//    }
//
//    public HttpResponser httpResponserListener;
//
//    public void setHttpResponserListener(HttpResponser httpResponserListener) {
//        this.httpResponserListener = httpResponserListener;
//    }
//
//    public interface HttpResponser {
//
//        public void responseResult(String result, String TAG, boolean networkConncectivity);
//    }
}


