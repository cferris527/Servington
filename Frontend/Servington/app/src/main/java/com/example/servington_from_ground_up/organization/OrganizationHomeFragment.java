package com.example.servington_from_ground_up.organization;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.servington_from_ground_up.MainActivity;
import com.example.servington_from_ground_up.R;
import com.example.servington_from_ground_up.utils.Singleton;
import com.squareup.picasso.Picasso;

/**
 * Fragment that acts as a home page for Organization. This fragment is the first one
 * loaded upon login. Displays Organization details and option to log out/change settings.
 *
 * @author Connor Ferris
 */
public class OrganizationHomeFragment extends Fragment {
    View view;
    Singleton data = Singleton.getInstance();
    TextView welcomeText;
    TextView idText;
    Button logoutBtn;
    Button settingsBtn;
    ImageView profilePicture;
    public OrganizationHomeFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     */
    public static OrganizationHomeFragment newInstance() {return new OrganizationHomeFragment();}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_organization_home, container, false);

        //Welcome Text
        welcomeText = (TextView) view.findViewById(R.id.welcomeOrgText);
        welcomeText.setText("Welcome, " + data.getUsername() + "!");

        //ID text
        idText = (TextView) view.findViewById(R.id.idOrgText);
        idText.setText("ID: " + data.getId());

        //Logout Button
        logoutBtn = (Button) view.findViewById(R.id.logoutOrgBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
                data.logOut();
            }
        });

        //Settings Button
        settingsBtn = (Button) view.findViewById(R.id.settingsOrgBtn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            //Changes to Settings Fragment
            public void onClick(View view) {
                Fragment fragment = new OrganizationDmFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        profilePicture = view.findViewById(R.id.profilePictureOrg);

        String url = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAALQAAAC0CAMAAAAKE/YAAAAANlBMVEXHICqsDxavDxerDhXJHyrIIizFHiiyERnFISq5FR6+GiOuERjHHSi0ExuqDxXBHSbFJS25GSFP2qm/AAADdklEQVR4nO2Z246dOhBEMeALNsbx//9sqgzszE4y5/JEb6mWIo2GycOi1a62zTQJIYQQQgghhBBCCCGEEEIIIYQQQgghhBBCCCGE+F+s68ofYT9/8vcXeBwelfuOy9Wf6pO/LMO+r485/SuXM2S9n0J6gWfj+bN2/wCbwE+p5a3GmcRaG98Cr/G027dQOrUeYbzMN3FrybI0+wI1hrBzQ3gh1A5222MKeZtp7JbjLnQpeImabVaaSy3kCuMC5VJ6bi33OB8HreP2tN9fWXf/o6MpIBljnl7xvFUo4y02NDZX6dOeb6xT6IyMMsceGNtnPoeG6qOxHV7EHqtvZ8zVjLCm8ci/yacW4Yy+bgYzJHANLvN2BsXI5tMxZdQfiZLtJUjILPRS4RxQ6oBwPisbfKpsG/zpacc/SFhwbokZ689Puda6NQbKyn1IHtKzva5G52Ki9ETnjSNlZl6cez92+3HM9mKvz0uBNHZ5aG5XjnlUdmeI+LTd0rutvkawOaxCLMFWOcRR6l/DG6+B/IjJljKl3SWd40uaf+CjbV5MSkdKM9XYHZDGv8boeJN+WvJ35kva39JLZKE/TfocM96y9Nf2WMZuOo8z2GhjPnIGpV8LccqnNBTXSzpw9+Hmam4h/oq8W5qjxVM6BA4eSJs73ma2sas/1imdu73OZvABM5FdvhxHzGE1ts9LqGYp2Hvc0tiJYhh6tEjgjrossT3t+CfbOIFDup2Hw4QZjiqjrTNbutybVlO0jWOw+ZDHOKx+SO8jA0d25MmctPep1xkDhY7LCA9KY7fEjbZjbPO/2epp6rWcxz6UkwQ19yv20jwjnoNnxLYt6X3HYmTCtVFXVzrvlUb5eTpwKDRj29Z8QffyULiypSm94BQDeENG6RLY41OwJf3i6o7CO5uB432evaPWV5jSiIrK+kL8wHEGR/RmLjjeQEtjivd2XkTeF5D20u6NPqKipzRSY1z1ZtNXvaRykHCG83I9996Mfm55I1475/szxrWjtt0e49xd/XR/MDL9oeimUHq7r2mm687XMNfGg8cu26JfCdfVaW2fJ43A8/vTLv+ZSzoan3/vXNKb9x+QzTeUdqPQBk9W3zGk+WnL+jD5ypCuCSfwT+oPfoL7KN2LT9gfCSGEEEIIIYQQQgghhBBCCCGEEEIIIYQQQgghhBDv/ASRoBn8uVD0mwAAAABJRU5ErkJggg==";
        Picasso.get().load(url).into(profilePicture);



        return view;
    }
}