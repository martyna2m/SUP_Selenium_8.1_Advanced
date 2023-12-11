package providers;

import configuration.PropertiesFromYaml;
import models.User;

import java.util.Map;
import java.util.Random;

public class UserFactory {
    private DataFaker dataFaker = new DataFaker();


    public User getRandomUser() {
        return User.builder()
                .firstName(dataFaker.getFakeFirstName())
                .lastName(dataFaker.getFakeLastName())
                .email(dataFaker.getFakeEmail())
                .password(dataFaker.getFakePassword())
                .gender(getrandomGender())
                .birthdate(dataFaker.getFakeBirthdate())
                .build();
    }


    public User getExisitingUser(String userName) {
        Map<String, Object> existingUser = getUserDataFromYaml(userName);
        return User.builder()
                .firstName(existingUser.get("firstName").toString())
                .lastName(existingUser.get("lastName").toString())
                .email(existingUser.get("email").toString())
                .password(existingUser.get("password").toString())
                .gender(User.Gender.valueOf(existingUser.get("gender").toString().toUpperCase()))
                .birthdate(existingUser.get("birthdate").toString())
                .build();

    }

    private User.Gender getrandomGender() {
        User.Gender[] genders = User.Gender.values();
        Random random = new Random();
        return genders[random.nextInt(genders.length)];
    }


    private Map<String, Object> getUserDataFromYaml(String userName){
        Map<String, Object> data = PropertiesFromYaml.config;
        Map<String, Object> users = (Map<String, Object>) data.get("users");
        return (Map<String, Object>) users.get(userName);
    }


}

