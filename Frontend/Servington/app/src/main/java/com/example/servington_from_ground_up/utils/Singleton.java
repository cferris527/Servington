package com.example.servington_from_ground_up.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Singleton class for storing user data.
 * @author Connor Ferris
 */
public class Singleton {
    private static Singleton userData = new Singleton();
    private static int id;
    private String username;
    private String password;
    //private String displayName;
   // private String email;
    //private int phone;

    private Singleton() {}

    /**
     * Method used to instantiate the Singleton within activities.
     * @return userData
     */
    public static Singleton getInstance() {
        return userData;
    }

    /**
     * Initialize all fields of user data with JSON object, used upon login.
     * @param obj
     * @throws JSONException
     */
    public void setData(JSONObject obj) throws JSONException {
        id = obj.getInt("id");
        username = obj.getString("username");
        password = obj.getString("password");
        //displayName = obj.getString("displayName");
        //email = obj.getString("email");
        //phone = obj.getInt("phone");
    }

    // get methods

    public static int getId() {return id;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    //public String getDisplayName() {return displayName;}
    //public String getEmail() {return email;}
    //public int getPhone() {return phone;}

    // set methods

    public void setID(int id) {this.id = id;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    //public void setDisplayName(String displayName) {this.displayName = displayName;}
    //public void setEmail(String email) {this.email = email;}
    //public void setPhone(int phone) {this.phone = phone;}

}
