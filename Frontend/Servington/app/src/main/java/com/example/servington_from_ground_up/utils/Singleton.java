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
    private String displayName;
    private String email;
    private String phone;
    private boolean isBanned;

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
    public void setData(JSONObject obj, String accountType) throws JSONException {

        id = obj.getInt("id");
        username = obj.getString("username");
        password = obj.getString("password");

        if (accountType.equals("ADMIN")) {
            return;
        }

        displayName = obj.getString("displayName");
        email = obj.getString("email");
        phone = obj.getString("phone_number");
        isBanned = obj.getBoolean("isBanned");
    }

    /**
     * Sets all fields of user data to null, used upon logout.
     */
    public void logOut() {
        id = 0;
        username = null;
        password = null;
        displayName = null;
        email = null;
        phone = null;
    }

    // get methods
    public int getId() {return id;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getDisplayName() {return displayName;}
    public String getEmail() {return email;}
    public String getPhone() {return phone;}
    public boolean getBanned() {return isBanned;}

    // set methods
    public void setID(int id) {this.id = id;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public void setDisplayName(String displayName) {this.displayName = displayName;}
    public void setEmail(String email) {this.email = email;}
    public void setPhone(String phone) {this.phone = phone;}
    public void setBanned(boolean b) {isBanned = b;}

}
