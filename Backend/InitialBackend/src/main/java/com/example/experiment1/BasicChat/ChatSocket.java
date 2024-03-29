package com.example.experiment1.BasicChat;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.example.experiment1.Team.TeamRepository;
import com.example.experiment1.Volunteer.Volunteer;
import jakarta.transaction.Transactional;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
@ServerEndpoint(value = "/chat/{username}")
public class ChatSocket {



    private static TeamRepository teamRepository;

    private static ChatMessageRepository msgRepo;


    @Autowired
    public void setMessageRepository(ChatMessageRepository repo) {
        msgRepo = repo;  // we are setting the static variable
    }

    @Autowired
    public void setMessageRepository(TeamRepository repo) {
        teamRepository = repo;  // we are setting the static variable
    }

    



   
    private static Map<Session, String> sessionUsernameMap = new Hashtable<>();
    private static Map<String, Session> usernameSessionMap = new Hashtable<>();

    private final Logger logger = LoggerFactory.getLogger(ChatSocket.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username)
            throws IOException {

        logger.info("Entered into Open");

        // store connecting user information
        sessionUsernameMap.put(session, username);
        usernameSessionMap.put(username, session);

        //Send chat history to the newly connected user
        sendMessageToPArticularUser(username, getChatHistory());

        // broadcast that new user joined
        String message = "User:" + username + " has Joined the Chat";
        broadcast(message);
    }


    @OnMessage
    public void onMessage(Session session, String message) throws IOException {

        // Handle new messages
        logger.info("Entered into Message: Got Message:" + message);
        String username = sessionUsernameMap.get(session);

        // Direct message to a user using the format "@username <message>"
        if (message.startsWith("@")) {
            String destUsername = message.split(" ")[0].substring(1);

            // send the message to the sender and receiver
            sendMessageToPArticularUser(destUsername, "[DM] " + username + ": " + message);
            sendMessageToPArticularUser(username, "[DM] " + username + ": " + message);

        }
        else if (message.startsWith("#")){
            String destTeam = message.split(" ")[0].substring(1);

            sendMessageToParticularTeam(destTeam, "[TEAM] " + username + ": " + message);
            sendMessageToPArticularUser(username, "[TEAM] " + username + ": " + message);

        }
        else { // broadcast
            broadcast(username + ": " + message);
        }

        // Saving chat history to repository
        msgRepo.save(new ChatMessage(username, message));
    }


    @OnClose
    public void onClose(Session session) throws IOException {
        logger.info("Entered into Close");

        // remove the user connection information
        String username = sessionUsernameMap.get(session);
        sessionUsernameMap.remove(session);
        usernameSessionMap.remove(username);

        // broadcase that the user disconnected
        String message = username + " disconnected";
        broadcast(message);
    }


    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
        logger.info("Entered into Error");
        throwable.printStackTrace();
    }


    private void sendMessageToPArticularUser(String username, String message) {
        try {
            usernameSessionMap.get(username).getBasicRemote().sendText(message);
        }
        catch (IOException e) {
            logger.info("Exception: " + e.getMessage().toString());
            e.printStackTrace();
        }
    }

    //@Transactional
    private void sendMessageToParticularTeam(String teamName, String message){
        System.out.print(teamName);
        List<Volunteer> volunteersInTeam = teamRepository.findByName(teamName).getVolunteers();


        for(int i = 0; i < volunteersInTeam.size(); i++) {
            sendMessageToPArticularUser(volunteersInTeam.get(i).getUsername(), message);
        }
    }


    private void broadcast(String message) {
        sessionUsernameMap.forEach((session, username) -> {
            try {
                session.getBasicRemote().sendText(message);
            }
            catch (IOException e) {
                logger.info("Exception: " + e.getMessage().toString());
                e.printStackTrace();
            }

        });

    }


    // Gets the Chat history from the repository
    private String getChatHistory() {
        List<ChatMessage> messages = msgRepo.findAll();

        // convert the list to a string
        StringBuilder sb = new StringBuilder();
        if(messages != null && messages.size() != 0) {
            for (ChatMessage message : messages) {
                sb.append(message.getUserName() + ": " + message.getContent() + "\n");
            }
        }
        return sb.toString();
    }

}

