package com.example.servington_from_ground_up.organization;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.servington_from_ground_up.R;
import com.example.servington_from_ground_up.utils.Const;
import com.example.servington_from_ground_up.utils.Singleton;
import com.example.servington_from_ground_up.volunteer.VolunteerHomeFragment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Fragment allowing Organization to change their account details.
 *
 * @author Connor Ferris
 */
public class OrganizationSettingsFragment extends Fragment {

    View view;
    String field;
    EditText oldField;
    EditText newField;
    TextView statusMessage;
    Button changeBtn;
    Button backBtn;
    Singleton data = Singleton.getInstance();
    public OrganizationSettingsFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     */
    public static OrganizationSettingsFragment newInstance() {return new OrganizationSettingsFragment();}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_organization_settings, container, false);

        //Old + New Field
        oldField = (EditText) view.findViewById(R.id.oldField2);
        newField = (EditText) view.findViewById(R.id.newField2);

        //Status message
        statusMessage = (TextView) view.findViewById(R.id.statusMessage4);

        //Change button
        changeBtn = (Button) view.findViewById(R.id.changeBtn2);
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    sendChange(newField.getText().toString());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //Back button
        backBtn = (Button) view.findViewById(R.id.backBtn3);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new VolunteerHomeFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        //Spinner
        Spinner spinner = (Spinner) view.findViewById(R.id.spinna2);
        String[] methods = new String[]{"Password", "Display Name", "Email", "Phone"};
        //TO BE changed: password, displayName, email, phone_number
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, methods);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String spinnerText = (String) adapterView.getItemAtPosition(i);
                if (spinnerText.equals("Password")){
                    field = "password";
                    oldField.setVisibility(View.VISIBLE);
                    setHint(oldField, "Old Password");
                    setHint(newField, "New Password");
                }
                else if (spinnerText.equals("Display Name")) {
                    field = "displayName";
                    oldField.setVisibility(View.GONE);
                    setHint(newField, "New Display Name");
                }
                else if (spinnerText.equals("Email")) {
                    field = "email";
                    oldField.setVisibility(View.GONE);
                    setHint(newField, "New Email");

                }
                else if (spinnerText.equals("Phone")) {
                    field = "phone_number";
                    oldField.setVisibility(View.GONE);
                    setHint(newField, "New Phone");
                }
                clearNewOld();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {field = "password";}
        });

        return view;
    }

    /**
     * Sets the hint of an EditText to described text.
     * @param edit EditText to be changed
     * @param hint text to be put for EditText's hint
     */
    private void setHint(EditText edit, String hint){edit.setHint(hint);}

    /**
     * Clears both EditTexts.
     */
    private void clearNewOld(){
        oldField.setText("");
        newField.setText("");
    }

    /**
     * Makes local changes to account on the server.
     * @param change what the field will be changed to
     * @throws JSONException uses (JSONobj).put
     */
    private void sendChange(String change) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JSONObject obj = new JSONObject();
        obj.put("id", data.getId());
        obj.put(field, change);

        System.out.println("ID:" + data.getId());
        System.out.println("Username:" + data.getUsername());
        System.out.println("Password:" + data.getPassword());
        System.out.println(field);
        System.out.println(oldField.getText().toString());

        //If "old password" does not equal current password
        if (field.equals("password") && !(oldField.getText().toString().equals(data.getPassword()))) {
            statusMessage.setBackgroundColor(Color.parseColor("#00FF00"));
            statusMessage.setText("Incorrect old password.");
            clearNewOld();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    statusMessage.setText("");
                }
            }, 5000);
            return;
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Const.SERVER + "/volunteerEditFields", obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        statusMessage.setBackgroundColor(Color.parseColor("#00FF00"));
                        statusMessage.setText(field + " changed!");
                        //5 second delay for text to go away
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                statusMessage.setText("");
                            }
                        }, 5000);
                        clearNewOld();
                        updateSingleton(field, change);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        statusMessage.setBackgroundColor(Color.parseColor("#00FF00"));
                        statusMessage.setText("Change failed.");
                        clearNewOld();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                statusMessage.setText("");
                            }
                        }, 5000);
                    }
                }
        );
        queue.add(request);

    }

    /**
     * Updates Singleton class with new changes.
     * @param field field to be changed (Singleton variable)
     * @param newContent what field is being changed to
     */
    private void updateSingleton(String field, String newContent) {
        if (field.equals("password")) {
            data.setPassword(newContent);
        }
        else if (field.equals("displayName")) {
            data.setDisplayName(newContent);
        }
        else if (field.equals("email")) {
            data.setEmail(newContent);
        }
        else if (field.equals("phone_number")) {
            data.setPhone(newContent);
        }
    }
}