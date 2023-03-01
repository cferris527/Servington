package com.example.servington_from_ground_up.utils;

/**
 * Class containing URL String constants. Created for
 * ease of changing links all in one place.
 *
 * @author Connor Ferris
 */
public class Const {

    /**
     * URL for pushing "Login" button on MainActivity. Sends POST with:
     * { "username" , "password" }
     */
    public static final String URL_LOGIN_POST
            = "https://a601cc78-61cd-46e0-aca3-100920b95d12.mock.pstmn.io/doingpost";


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
            = "https://a601cc78-61cd-46e0-aca3-100920b95d12.mock.pstmn.io/doingpost";


    /**
     * URL for creating an Admin account on the Admin page.
     * Sends POST with:
     * { "username", "password" , "accountType" }
     */
    public static final String URL_CREATE_ADMIN_POST
            = "https://a601cc78-61cd-46e0-aca3-100920b95d12.mock.pstmn.io/doingpost";


    /**
     * URL for
     */
    public static final String URL_DELETE_POST
            = "https://a601cc78-61cd-46e0-aca3-100920b95d12.mock.pstmn.io/doingdelete";


    public static final String URL_DELETE_ACCOUNT
            = "https://a601cc78-61cd-46e0-aca3-100920b95d12.mock.pstmn.io/doingdelete";
}
