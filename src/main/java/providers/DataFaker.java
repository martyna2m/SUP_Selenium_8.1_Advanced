package providers;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DataFaker {
    private Faker faker =  new Faker(new Locale("en-US"));
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

    public String getFakeCity(){
       return faker.address().city();
    }

     public String getFakePhoneNumber(){
       return fakeValuesService.numerify("[1-9]{9}");
    }



    public String getFakeCompanyName(){
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
        return generateBirthdate(faker, 1950,2000);
    }


    private String generateBirthdate(Faker faker, int startYear, int endYear) {

        Calendar calendar = Calendar.getInstance();

        int year = faker.number().numberBetween(startYear, endYear);
        int month = faker.number().numberBetween(1, 13);

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int day = faker.number().numberBetween(1, maxDay + 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(date);

    }
}
