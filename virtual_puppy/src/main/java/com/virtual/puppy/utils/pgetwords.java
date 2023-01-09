package com.virtual.puppy.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.virtual.puppy.Initiate;
import com.virtual.puppy.R;

import org.json.JSONObject;

public class pgetwords {

    public static Activity activity;
    public static pgetwords getLoadAsds;
    public static String mode = "";

    public static String app_VPN_Base_Carrier;
    public static String app_VPN_Access_Token;
    public static String app_VPN_Auth;

    public static Intent intent;

    public pgetwords(Activity activity1) {
        activity = activity1;
    }

    public static pgetwords getInstance(Activity activity1) {
        activity = activity1;
        if (getLoadAsds == null) {
            getLoadAsds = new pgetwords(activity);
        }
        return getLoadAsds;
    }

    public void sendRequest(Activity activity, Intent intent1, String Sword) {
        if (Connectivity.isConnected(activity)) {
            intent = intent1;
            try {
                mode = AESSUtils.Logw(activity.getString(R.string.ghtjdfl6790561) + Sword);
            } catch (Exception e) {
                e.printStackTrace();
            }
            RequestQueue queue = Volley.newRequestQueue(activity);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, mode, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getInt("success") == 1) {

                            app_VPN_Base_Carrier = jsonObject.getString("app_VPN_Base_Carrier");
                            app_VPN_Access_Token = jsonObject.getString("app_VPN_Access_Token");
                            app_VPN_Auth = jsonObject.getString("app_VPN_Auth");

                            Initiate.getInstance(activity).Initiate_call(activity, app_VPN_Access_Token, app_VPN_Auth, app_VPN_Base_Carrier, intent1);

                        } else {
                            Toast.makeText(activity, "Not Found Data!!!", Toast.LENGTH_LONG).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(activity, "Something went wrong!!!", Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(activity, "Error: Something went wrong!!!", Toast.LENGTH_LONG).show();

                }
            });
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(stringRequest);
        } else {
            Toast.makeText(activity, "Please Check Your Internet Connection!!!", Toast.LENGTH_LONG).show();
        }
    }
}
