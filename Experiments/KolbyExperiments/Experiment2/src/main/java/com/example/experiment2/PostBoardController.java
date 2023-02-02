package com.example.experiment2;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class PostBoardController {

    public ArrayList<PostBoard> postBoardList = new ArrayList<PostBoard>();

    //returns the current postBoard
    @GetMapping({"/postBoard"})
    @ResponseBody
    public String printBoard(){
        String s = "";
        int i;
        for (i = 0; i < postBoardList.size(); i++) {
            PostBoard p = postBoardList.get(i);
            s = s + p.getPostMessage() + "\n";
        }

        return s;
    }

    //create an account
    @PostMapping({"/createPost"})
    @ResponseBody
    public String createPost(@RequestBody PostBoard p) {
        postBoardList.add(p);
        return "New post from " + p.getUser() + " Added!";
    }

    //delete an account
    @DeleteMapping({"/remove/{message}"})
    @ResponseBody
    public String removeUser(@PathVariable String message) {

        for(int i = 0; i < postBoardList.size(); i++) {
            PostBoard p = postBoardList.get(i);
            if (message.compareTo(p.getPostMessage()) == 0) {
                postBoardList.remove(i);
                return "User removed successfully";
            }
        }

        return "User not found";
    }


}
