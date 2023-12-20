package providers;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DataFaker {
    TestDataProvider testDataProvider = new TestDataProvider();
    private Faker faker = new Faker(new Locale("en-US"));
    private FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"), new RandomService());

    public String getFakeFirstName() {
        return faker.name().firstName();
    }

    public String getFakeLastName() {
        return faker.name().lastName();
    }

    public String getFakeAddress() {
        return faker.address().streetAddress() + " " + faker.address().buildingNumber();
    }

    public String getFakeCity() {
        return faker.address().city();
    }

    public String getFakePhoneNumber() {
        return faker.numerify("###-###-###");
    }


    public String getFakeCompanyName() {
        return faker.company().name();
    }

    public String getFakeEmail() {
        return fakeValuesService.bothify("??????##@example.com");
    }


    public String getFakePassword() {
        return fakeValuesService.regexify("[a-z1-9]{8}");
    }

    ;

    public String getFakeBirthdate() {
        return generateBirthdate();
    }


    public String generateBirthdate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(testDataProvider.getTestData("dateFormat"));
        return dateFormat.format(faker.date().birthday());

    }

}
