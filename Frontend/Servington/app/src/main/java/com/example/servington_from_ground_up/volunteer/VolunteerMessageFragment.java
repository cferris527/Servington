package com.example.servington_from_ground_up.volunteer;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.servington_from_ground_up.R;
import com.example.servington_from_ground_up.utils.Const;
import com.example.servington_from_ground_up.utils.Singleton;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Global chat for all users.
 *
 * @author Connor Ferris
 */
public class VolunteerMessageFragment extends Fragment {

    View view;
    LinearLayout messageLayout;
    Button sendBtn;
    EditText messageTxt;
    private WebSocketClient cc;

    public VolunteerMessageFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     */
    public static VolunteerMessageFragment newInstance() {return new VolunteerMessageFragment();}

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_volunteer_message, container, false);

        messageLayout = view.findViewById(R.id.messageLayout);
        sendBtn = view.findViewById(R.id.sendBtn);
        messageTxt = view.findViewById(R.id.messageTxt);

        Draft[] drafts = {
                new Draft_6455()
        };

        Singleton data = Singleton.getInstance();

        String w = Const.SERVER + "/chat" + "/" + data.getUsername() ;

        //initial connection to websocket
        try {
            Log.d("Socket:", "Trying socket");

            cc = new WebSocketClient(new URI(w), (Draft) drafts[0]) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    Log.d("OPEN", "run() returned: " + "is connecting");
                }

                @Override
                public void onMessage(String s) {
                    Log.d("", "run() returned: " + s);
                    //String s = t1.getText().toString();
                    //t1.setText(s + "\nServer:" + message);
                    generateMessage(s);
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    Log.d("CLOSE", "onClose() returned: " + s);
                }

                @Override
                public void onError(Exception e) {
                    Log.d("Exception:", e.toString());
                }
            };
        } catch (URISyntaxException e) {
            Log.d("Exception:", e.getMessage().toString());
            e.printStackTrace();
        }
        cc.connect();

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    cc.send(messageTxt.getText().toString());
                    //messageTxt.setText("");
                } catch (Exception e) {
                    Log.d("ExceptionSendMessage:", e.getMessage().toString());
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

    private void generateMessage(String m) {
        TextView t = new TextView(getContext());
        t.setText(m);
        t.setBackgroundColor(Color.parseColor("#fcfcfc"));
        t.setTextSize(25);
        messageLayout.addView(t);
    }


}