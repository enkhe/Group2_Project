/*
 * Author: Amrit Puri
 * Group 2 - TCSS 360A
 */

public interface RegisteredUser extends User implements Serializeable {

    private String myFirstName;
    private String myLastName;
    private String myUserName;
    private String myEmail;
    private String myPassword;
    private int myID;


    public RegisteredUser (String theFirstName, String theLastName, String theUserName, String theEmail,
                           String thePassword) {
        this.myFirstName = theFirstName;
        this.myLastName = theLastName;
        this.myUserName = theUserName;
        this.myEmail = theEmail;
        this.myPassword = thePassword;

    /*

     */
    public int getID() {
        return myID;
    }

    /*

     */
    public Manuscript getManuscript() {

    }

    /*

     */
    public String getFirstName() {
        return myFirstName;
    }

    /*

     */
    public String getLastName() {
        return myLastName;
    }

    /*

     */
    public String getUserName() {
        return myUserName;
    }

    public String getEmail() {
        return myEmail;
    }
}