package org.tenmiles.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by comp17 on 5/8/15.
 */


public class SessionManager {


    private final static int PRIVATE_MODE = 0;

    private final static String PREFERENCE_NAME = "afriklight";
    private final static String USER_ID = "userId";
    private final static String USER_EMAIL = "userEmail";
    private final static String USER_NAME    = "userName";
    private final static String USER_PROFILE_PIC    = "profile_pic";
    private final static String USER_LOGGED_IN = "userLoggedIn";
    private final static String USER_PASSWORD = "userPassword";
    private final static String CITY_ID = "city_id";
    private final static String CITY_NAME= "city_name";
    private final static String CURRENT_CITY_NAME= "current_city_name";
    private final static String CURRENT_CITY_ID= "current_city_id";
    private final static String RADIO_ID= "current_city_id";
    private final static String PUSH_CHANNEL= "push_channel";
    private final static String SIP_PASS= "sip_pass";
    private Context context;


    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        editor = pref.edit();

    }


    public void setUserDetails(String userId, String userEmail,String userName,String sipid,String sipPass,String profilePic) {

        editor.putString(USER_ID, userId);
        editor.putString(USER_EMAIL, userEmail);
        editor.putString(USER_NAME, userName);
        editor.putString(RADIO_ID, sipid);
        editor.putString(SIP_PASS, sipPass);
        editor.putString(USER_PROFILE_PIC, profilePic);
        editor.commit();

    }

    public String getUserId() {
        String userid = pref.getString(USER_ID, "");
        return userid;
    }

    public String getRadioId() {
        String spId = pref.getString(RADIO_ID, "");
        return spId;
    }
    public String getSipPass() {
        String spPass = pref.getString(SIP_PASS, "");
        return spPass;
    }

    public String getUserName() {
        String userName = pref.getString(USER_NAME, "");
        return userName;
    }

    public void setUserProfilePic(String profilePic){
        editor.putString(USER_PROFILE_PIC, profilePic);
        editor.commit();
    }

    public String getUserProfilePic() {
        String profilePic = pref.getString(USER_PROFILE_PIC, "");
        return profilePic;
    }

    public String setPushChannel(String password){
        String pushChannel = pref.getString(PUSH_CHANNEL, ""+password);
        return pushChannel;
    }
    public String getPushChannel() {
        String pushChannel = pref.getString(PUSH_CHANNEL, "");
        return pushChannel;
    }


    public void setPassword(String password){
        editor.putString(USER_PASSWORD,password);
        editor.commit();
    }
    public String getPassword(){
        String password = pref.getString(USER_PASSWORD,"");
        return password;
    }

    public String getEmailId() {
        String userid = pref.getString(USER_EMAIL, "");
        return userid;
    }

    public void setUserLoggedIn(boolean userLoggedIn) {
        editor.putBoolean(USER_LOGGED_IN, userLoggedIn);
        editor.commit();
    }

    public boolean isUserLoggedIn() {
        return pref.getBoolean(USER_LOGGED_IN, false);
    }

    public void setCityId(String cityId){
        editor.putString(CITY_ID, cityId);
        editor.commit();
    }
    public String getCityId(){
        String cityId = pref.getString(CITY_ID,"");
        return cityId;
    }

    public void setCityName(String cityName){
        editor.putString(CITY_NAME, cityName);
        editor.commit();
    }
    public String getCityName(){
        String cityName = pref.getString(CITY_NAME,"");
        return cityName;
    }

    public void setCurrentCityName(String currentCityName){
        editor.putString(CURRENT_CITY_NAME,currentCityName);
        editor.commit();
    }
    public String getCurrentCityName(){
        String currentCityName = pref.getString(CURRENT_CITY_NAME,"");
        return currentCityName;
    }

    public void setCurrentCityId(String currentCityId){
        editor.putString(CURRENT_CITY_ID,currentCityId);
        editor.commit();
    }
    public String getCurrentCityId(){
        String currentCityId = pref.getString(CURRENT_CITY_ID,"");
        return currentCityId;
    }

}
