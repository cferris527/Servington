package com.example.servington_from_ground_up.utils;

/**
 * Class storing volley GET/POST methods for reference in other code.
 *
 * @author Connor Ferris
 */
public class RequestsReference {

//    private void postRequest() {
//        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
//
//        // create a new JSON object
//        JSONObject body = new JSONObject();
//
//        // grabs Strings from Username and Password fields.
//        String user_name = username.getText().toString();
//        String pass_word = password.getText().toString();
//
//        // adds fields to JSON object
//        try {
//            body.put("username", user_name);
//            body.put("password", pass_word);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Const.URL_LOGIN_POST, body,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(MainActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
//                    }
//                }
//        );
//        queue.add(request); // send request
//    }

//    private void getRequest() {
//        // Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(this);
//
//        JSONObject body = new JSONObject();
//
//        // grabs Strings from Username and Password fields.
//        String user_name = username.getText().toString();
//        String pass_word = password.getText().toString();
//
//        // adds fields to JSON object
//        try {
//            body.put("username", user_name);
//            body.put("password", pass_word);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // Request a string response from the provided URL.
//        StringRequest request = new StringRequest(Request.Method.GET, Const.URL_LOGIN_GET,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        // String response can be converted to JSONObject via
//                        try {
//                            JSONObject object = new JSONObject(response);
//                            if(object.has("accountType")) {
//
//                                String type = object.optString("accountType");
//
//                                if(type.equals("USER")) {
//                                    Intent intent = new Intent(MainActivity.this, UserActivity.class);
//                                    startActivity(intent);
//                                }
//                                else if(type.equals("ORGANIZATION")) {
//                                    Intent intent = new Intent(MainActivity.this, OrganizationActivity.class);
//                                    startActivity(intent);
//                                }
//                                else if(type.equals("ADMIN")) {
//                                    Intent intent = new Intent(MainActivity.this, AdminActivity.class);
//                                    startActivity(intent);
//                                }
//
//                            }
//
//                        } catch (Exception e) {
//                            throw new RuntimeException(e);
//                        }
//                        //tvResponse.setText("Response is: "+ response);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //tvResponse.setText("That didn't work!" + error.toString());
//                    }
//                });
//
//        // Add the request to the RequestQueue.
//        queue.add(request);
//        //Toast.makeText(getApplicationContext(),method+" request sent!",Toast.LENGTH_SHORT).show();
//    }
}
