package com.example.servington_from_ground_up.utils;

import com.example.servington_from_ground_up.R;

/**
 * Class for handling profile pictures across multiple activities.
 *
 * @author Connor Ferris
 */
public class ProfilePicture {

    public ProfilePicture(){}

    /**
     * Converts picture "id" to drawable int.
     * @param id Picture to load
     * @return
     */
    public int determinePicture(String id) {

        switch (id) {
            case ("spongebob"):
                return R.drawable.spongebob;
            case ("mitra"):
                return R.drawable.mitra_edited;
            case ("peter"):
                return R.drawable.peter;
            case ("ferris"):
                return R.drawable.ferris;
            case ("dog"):
                return R.drawable.dog;
            case ("derm"):
                return R.drawable.derm;
            case ("default"):
                return R.drawable.pfp_placeholder;
            case ("rigby"):
                return R.drawable.rigby;
            case ("jaguar"):
                return R.drawable.jaguar;
            default:
                return R.drawable.pfp_placeholder;
        }

    }
}
