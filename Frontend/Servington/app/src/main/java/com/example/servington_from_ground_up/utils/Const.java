package com.example.servington_from_ground_up.utils;

/**
 * Class containing URL String constants. Created for
 * ease of changing links all in one place.
 *
 * @author Connor Ferris
 */
public class Const {

    /**
     * Initial URL to be used before every "/" and string.
     */
    public static final String SERVER = "http://coms-309-029.class.las.iastate.edu:8080";

    /**
     * URL for pushing "Login" button on MainActivity. Sends POST with:
     * { "username" , "password" }
     */
    public static final String URL_LOGIN_POST
            = "http://coms-309-029.class.las.iastate.edu:8080/users/";


    /**
     * URL for pushing "Login" button on MainActivity, after POST has
     * been executed. GET request grabs JSON obj with:
     * { "username" , "isValid"(boolean) , "accountType" }
     */
    public static final String URL_LOGIN_GET
            = "https://a601cc78-61cd-46e0-aca3-100920b95d12.mock.pstmn.io/doingget";


    /**
     * URL for creating an account/pushing the "Create Account" button.
     * Sends POST with:
     * { "username" , "password" , "accountType" }
     */
    public static final String URL_CREATE_ACCOUNT_POST
            = "http://coms-309-029.class.las.iastate.edu:8080/users";


    /**
     * URL for creating an Admin account on the Adminpage.
     *      * Sends POST with:
     * { "username", "password" , "accountType" }
     */
    public static final String URL_CREATE_ADMIN_POST
            = "https://a601cc78-61cd-46e0-aca3-100920b95d12.mock.pstmn.io/doingpost";


    /**
     * URL for deleting a post.
     * Sends DELETE with { "postTitle" }
     */
    public static final String URL_DELETE_POST
            = "http://coms-309-029.class.las.iastate.edu:8080/postDelete";


    /**
     * URL for deleting a user.
     * Sends DELETE with { "accountID" }
     */
    public static final String URL_DELETE_ACCOUNT
            = "http://coms-309-029.class.las.iastate.edu:8080/usersDelete";

    /**
     * URL for creating a post
     * Sends POST with { "createPost" + {"id"}}
     */
    public static final String URL_CREATE_POST
            = "http://coms-309-029.class.las.iastate.edu:8080/createPost/";

    /**
     * URL for creating a post
     * Sends Get with {"getPostsByOrg" + {"id"}}
     */
    public static final String URL_VIEW_POST_BYID
            = "http://coms-309-029.class.las.iastate.edu:8080/getPostsByOrg/";

    public static final String URL_View_All_Posts
            = "http://coms-309-029.class.las.iastate.edu:8080/allPosts";

    public static final String URL_SEARCH_POST_TITLE
            = "http://coms-309-029.class.las.iastate.edu:8080/postTitleKeyword/";

    public static final String URL_ADD_VOLUNTEER
            = "http://coms-309-029.class.las.iastate.edu:8080/addVolunteer";

    public static final String URL_CREATE_REPORT
            = "http://coms-309-029.class.las.iastate.edu:808/createReport/";
}
