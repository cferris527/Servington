package com.example.servington_from_ground_up;

import org.json.JSONException;
import org.json.JSONObject;

public class Singleton {

    private static Singleton userData = new Singleton();

    private int id;
    private String username;
    private String password;
    private String displayName;
    private String email;
    private int phone;
    private Singleton() {}

    public static Singleton getInstance() {
        return userData;
    }

    /**
     * Sets all fields of user data with JSON object, used upon login.
     * @param obj
     * @throws JSONException
     */
    public void setData(JSONObject obj) throws JSONException {
        id = obj.getInt("id");
        username = obj.getString("username");
        password = obj.getString("password");
        displayName = obj.getString("displayName");
        email = obj.getString("email");
        phone = obj.getInt("phone");
    }

    public String getDisplayName() {return displayName;}

}
