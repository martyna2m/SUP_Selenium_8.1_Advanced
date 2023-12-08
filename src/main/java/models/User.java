package models;

public class User {

    private String gender;

    private String firstName;

    private String lastName;

    private String email;
    private String password;
    private String birthDay;


    public User(String gender, String firstName, String lastName, String email, String password, String birthDay) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDay = birthDay;
    }


    public User() {
    }
}
