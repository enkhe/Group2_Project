/*
 * Author: Amrit Puri
 * Group 2 - TCSS 360A
 */

public class UnregisteredUser extends User implements Serializable {


    public UnregisteredUser () {

    }

    /*

     */
    public RegisteredUser register(String theFirstName, String theLastName, String theUserName, String theEmail,
                                   String thePassword) {
        RegisteredUser user = new RegisteredUser(theFirstName, theLastName, theUserName, theEmail, thePassword);
    }
}