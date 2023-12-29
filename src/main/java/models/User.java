package models;

import lombok.Getter;

@Getter
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Gender gender;
    private String birthdate;


    @Override
    public String toString() {
        return "User{" +
                "gender=" + gender +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthdate='" + birthdate + '\'' +
                '}';
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    private User() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public enum Gender {
        MALE,
        FEMALE,
        OTHER;

    }

    public static final class Builder {
        private Gender gender;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String birthdate;


        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder birthdate(String birthdate) {
            this.birthdate = birthdate;
            return this;
        }


        public User build() {
            if (firstName.isEmpty()) {
                throw new IllegalStateException("Firstname cannot be empty");
            }
            if (lastName.isEmpty()) {
                throw new IllegalStateException("Lastname cannot be empty");
            }
            if (email.isEmpty()) {
                throw new IllegalStateException("Email cannot be empty");
            }
            if (password.isEmpty()) {
                throw new IllegalStateException("Password cannot be empty");
            }

            User user = new User();
            user.firstName = this.firstName;
            user.lastName = this.lastName;
            user.email = this.email;
            user.password = this.password;
            user.gender = this.gender;
            user.birthdate = this.birthdate;
            return user;
        }
    }

}

