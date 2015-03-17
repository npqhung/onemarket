package com.onesys.onemarket.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;

/**
 * This is an application scope class that acts like a singleton class
 * @author Hung
 *
 */
public class OneMarketApplication extends Application{

    public static String DEFAULT_SERVER = "www.agilelogic.com.au/fleet";
    public static String DEFAULT_CLIENT = "new-client";
    
    public static final String SETTING_SERVER = "setting_server";
    public static final String SETTING_CLIENT_NAME = "setting_client_name";

    @Override
	public void onCreate() {
		super.onCreate();
		initSettings();
	}

	private void initSettings() {

	}

	private String getRestURL() {
   	 	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(OneMarketApplication.this);
   	 	String clientName = prefs.getString(SETTING_CLIENT_NAME, DEFAULT_CLIENT);
	   	//String restURL = "http://"+prefs.getString(SETTING_SERVER, DEFAULT_SERVER);
	   	//restURL += UPLOAD_TRAFFIC_DATA;
	   	//return restURL;

   	 	return "http://"+DEFAULT_SERVER ;
	}


}
