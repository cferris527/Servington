package com.example.experiment1.Post;

import com.example.experiment1.Admin.AdminRepository;
import com.example.experiment1.Message;
import com.example.experiment1.Organization.Organization;
import com.example.experiment1.Volunteer.Volunteer;
import com.example.experiment1.Volunteer.VolunteerRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.experiment1.Organization.Organization;
import com.example.experiment1.Organization.OrganizationRepository;


import java.util.List;
import java.util.ArrayList;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    VolunteerRepository volunteerRepository;



    //This function returns a list of all posts in the database. This works on frontend.
    @Operation(summary = "List all posts", description = "Return a JSON array of all of the posts in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned all posts.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Post.class)))
    })
    @GetMapping(path = "/allPosts")
    List<Post> getAllPosts(){
        return postRepository.findAll();
    }



    //Returns the post with the given title. Has not been implemented on frontend.
    @Operation(summary = "Return post with given title", description = "Returns a JSON object of the post with the given title." +
            "Example url: /getPostByTitle/Title --> The post with title: Title will be returned")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found the post", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Post.class)))
    })
    @GetMapping(path = "/getPostByTitle/{title}")
    Post getPostByTitle( @PathVariable String title){
        return postRepository.findByTitle(title);
    }


    //Returns a list of posts containing the given keyword in the description. Has not been implemented on frontend.
    @Operation(summary = "Return post with given keyword in Description", description = "Returns a JSON array of all posts in the database that contain the given keyword in the description." +
            "Example url: /postDescriptionKeyword/help --> all posts containing help in the description will be returned.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned posts", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Post.class)))
    })
    @GetMapping(path = "/postDescriptionKeyword/{keyword}")
    public List<Post> getPostsByKeywordInDescription(@PathVariable String keyword){
        return postRepository.findByDescriptionContaining(keyword);
    }


    //Returns a list of posts containing the given keyword in the Title. Has not been implemented on frontend.
    @Operation(summary = "Return post with given keyword in Title", description = "Returns a JSON array of all posts in the database that contain the given keyword in the title.  " +
            "Example url: /postTitleKeyword/help --> all posts containing help in the title will be returned.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned posts", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Post.class)))
    })
    @GetMapping(path = "/postTitleKeyword/{keyword}")
    public List<Post> getPostsByKeywordinTitle(@PathVariable String keyword){
        return postRepository.findByTitleContaining(keyword);
    }





    //Creates a new post and adds it to the database. Not used on frontend yet.
    @Operation(summary = "Create a post", description = "A post is created for the organization whose ID is included in the url and added to the database. A JSON array of the post's fields is included in the response body. " +
            "Example url: /createPost/2 --> A post containing the attributes from the response body is added to the database under the organization with id 2")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created a post", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Post.class)))
    })
    @PostMapping(path = "/createPost/{id}")
    Message createPost(@RequestBody Post post, @PathVariable int id){
        Message m = new Message();

        if (post == null) {
            m.message = "failure";
            return m;
        }
        Organization o = organizationRepository.findById(id);
        post.setOrg(o);
        postRepository.save(post);
        m.message = "success";
        return m;
    }


    //Given the organization id in the url, this method returns a list of every post that organization has made
    @Operation(summary = "Return organization's posts", description = "Returns all of the posts a given organization has made in a JSON array. They are stored in the database under the org's ID.  " +
            "Example url: /getPostsByOrg/4 --> this returns all the posts org with ID 4 has made.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned the posts", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Post.class)))
    })
    @GetMapping(path = "/getPostsByOrg/{id}")
    public List<Post> getPostsByOrganization(@PathVariable int id){
        List<Post> all = postRepository.findAll();
        List<Post> selected = new ArrayList<>();
        for (Post post:all) {
            if (post.getOrg().getId() == id){
                selected.add(post);
            }
        }

        return selected;
    }




    //This function uses the post mapping method to delete a post. Works with front end.
    @Operation(summary = "Delete's a post from the database", description = "A JSON object containing a post ID is sent in the request body. The post with the given ID in the database is deleted.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted a post", content = @Content(mediaType = "application/json"))
    })
    @PostMapping(path = "/deletePost")
    Message deletePost(@RequestBody Post post){
        String title = post.getTitle();
        postRepository.deleteByTitle(title);

        Message m = new Message();
        m.message = "success";
        return m;
    }



    @Operation(summary = "Volunteer signs up for a post", description = "A volunteer can register for a given post. The volunteer ID and post title are included in the url. The relation is added to the database and the post's volunteer_count field is incremented. " +
            "Example url: addVolunteer/Opportunity/2 -->  volunteer with ID 2 signs up for post with title Opportunity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully registered for a post", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Post.class)))
    })
    @PostMapping(path = "/addVolunteer/{postTitle}/{volunteerId}")
    Message addVolunteer(@PathVariable String postTitle, @PathVariable int volunteerId){
        Message m = new Message();
        Post p = postRepository.findByTitle(postTitle);
        Volunteer v = volunteerRepository.findById(volunteerId);

        List<Volunteer> volunteers = p.getVolunteers();

        for(Volunteer vol : volunteers){
            if(vol.getId() == volunteerId){
                m.message = "You have already signed up for this post";
                return m;
            }
        }

        p.addVolunteer(v);
        v.addEvent(p);

        p.incrementCount();

        postRepository.save(p);
        volunteerRepository.save(v);


        m.message = "success";
        return m;
    }




    @Operation(summary = "Volunteer unregisters for a post", description = "A volunteer can unregister for a given post. The volunteer ID and post title are included in the url. The relation is removed from the database and the post's volunteer_count field is decremented. " +
            "Example url: addVolunteer/Opportunity/2 -->  volunteer with ID 2 unregisters for post with title Opportunity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully unregistered for a post", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Post.class)))
    })
    @PostMapping(path = "/removeVolunteer/{postTitle}/{volunteerId}")
    Message removeVolunteer(@PathVariable String postTitle, @PathVariable int volunteerId){
        Message m = new Message();
        Post p = postRepository.findByTitle(postTitle);
        Volunteer v = volunteerRepository.findById(volunteerId);


        int found = p.removeVolunteer(v);

        if(found == 0){
            m.message = "You have not signed up for this post";
            return m;
        }

        v.removeEvent(p);

        p.decrementCount();

        postRepository.save(p);
        volunteerRepository.save(v);

        m.message = "success";
        return m;
    }



    @GetMapping(path = "/orgViewSignUps/{postTitle}")
    public List<Volunteer> orgViewSignUps(@PathVariable String postTitle){
        Post p = postRepository.findByTitle(postTitle);
        List<Volunteer> vols = p.getVolunteers();



        return vols;
    }



}



