package com.example.servington_from_ground_up.multi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

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
import com.example.servington_from_ground_up.utils.ProfilePicture;
import com.example.servington_from_ground_up.utils.Singleton;
import com.example.servington_from_ground_up.volunteer.VolunteerHomeFragment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Fragment for choosing profile picture.
 */
public class ChoosePictureFragment extends Fragment {
    View view;
    ProfilePicture pfp;
    ImageButton current;
    ImageButton spongebob;
    ImageButton peter;
    ImageButton mitra;
    ImageButton ferris;
    ImageButton dog;
    ImageButton derm;
    ImageButton blank;
    ImageButton rigby;
    ImageButton jaguar;
    Singleton data = Singleton.getInstance();
    Button back;
    public ChoosePictureFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     */
    public static ChoosePictureFragment newInstance() {return new ChoosePictureFragment();}

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_choose_picture, container, false);
        pfp = new ProfilePicture();

        //Back Button
        back = view.findViewById(R.id.backPicBtn);
        back.setOnClickListener(new View.OnClickListener() {
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

        //Spongebob
        spongebob = view.findViewById(R.id.spongebobPic);
        spongebob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spongebob.setForeground(getResources().getDrawable(R.drawable.check));
                data.setProfilePicture("spongebob");
                try {
                    changePicture("spongebob");
                    removeCheck(current);
                    current = spongebob;
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //Peter
        peter = view.findViewById(R.id.peterPic);
        peter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                peter.setForeground(getResources().getDrawable(R.drawable.check));
                data.setProfilePicture("peter");
                try {
                    changePicture("peter");
                    removeCheck(current);
                    current = peter;
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //Mitra
        mitra = view.findViewById(R.id.mitraPic);
        mitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mitra.setForeground(getResources().getDrawable(R.drawable.check));
                data.setProfilePicture("mitra");
                try {
                    changePicture("mitra");
                    removeCheck(current);
                    current = mitra;
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //Ferris
        ferris = view.findViewById(R.id.ferrisPic);
        ferris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ferris.setForeground(getResources().getDrawable(R.drawable.check));
                data.setProfilePicture("ferris");
                try {
                    changePicture("ferris");
                    removeCheck(current);
                    current = ferris;
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //Dog
        dog = view.findViewById(R.id.dogPic);
        dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dog.setForeground(getResources().getDrawable(R.drawable.check));
                data.setProfilePicture("dog");
                try {
                    changePicture("dog");
                    removeCheck(current);
                    current = dog;
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //Derm
        derm = view.findViewById(R.id.dermPic);
        derm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                derm.setForeground(getResources().getDrawable(R.drawable.check));
                data.setProfilePicture("derm");
                try {
                    changePicture("derm");
                    removeCheck(current);
                    current = derm;
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //Default
        blank = view.findViewById(R.id.defaultPic);
        blank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blank.setForeground(getResources().getDrawable(R.drawable.check));
                data.setProfilePicture("default");
                try {
                    changePicture("default");
                    removeCheck(current);
                    current = blank;
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //Rigby
        rigby = view.findViewById(R.id.rigbyPic);
        rigby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rigby.setForeground(getResources().getDrawable(R.drawable.check));
                data.setProfilePicture("rigby");
                try {
                    changePicture("rigby");
                    removeCheck(current);
                    current = rigby;
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //Jaguar
        jaguar = view.findViewById(R.id.jaguarPic);
        jaguar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jaguar.setForeground(getResources().getDrawable(R.drawable.check));
                data.setProfilePicture("jaguar");
                try {
                    changePicture("jaguar");
                    removeCheck(current);
                    current = jaguar;
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //Set current check mark upon open
        if (data.getProfilePicture().equals("spongebob")) {
            spongebob.setForeground(getResources().getDrawable(R.drawable.check));
            current = spongebob;
        }
        else if(data.getProfilePicture().equals("peter")) {
            peter.setForeground(getResources().getDrawable(R.drawable.check));
            current = peter;
        }
        else if(data.getProfilePicture().equals("mitra")) {
            mitra.setForeground(getResources().getDrawable(R.drawable.check));
            current = mitra;
        }
        else if(data.getProfilePicture().equals("ferris")) {
            ferris.setForeground(getResources().getDrawable(R.drawable.check));
            current = ferris;
        }
        else if(data.getProfilePicture().equals("dog")) {
            dog.setForeground(getResources().getDrawable(R.drawable.check));
            current = dog;
        }
        else if(data.getProfilePicture().equals("derm")) {
            derm.setForeground(getResources().getDrawable(R.drawable.check));
            current = derm;
        }
        else if(data.getProfilePicture().equals("default")) {
            blank.setForeground(getResources().getDrawable(R.drawable.check));
            current = blank;
        }
        else if(data.getProfilePicture().equals("rigby")) {
            rigby.setForeground(getResources().getDrawable(R.drawable.check));
            current = rigby;
        }
        else if(data.getProfilePicture().equals("jaguar")) {
            jaguar.setForeground(getResources().getDrawable(R.drawable.check));
            current = jaguar;
        }

        return view;
    }

    /**
     * Removes check from ImageView.
     * @param v ImageView to remove check from
     */
    private void removeCheck(ImageView v) {
        v.setForeground(null);
    }

    /**
     * Sends POST request to the server changing account profile picture.
     * @param change profile picture to change to
     * @throws JSONException for handling JSON object
     */
    private void changePicture(String change) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JSONObject obj = new JSONObject();
        obj.put("id", data.getId());
        obj.put("profilePictureUrl", change);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Const.SERVER + "/volunteerEditFields", obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        queue.add(request);
    }
}