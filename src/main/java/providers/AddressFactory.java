package providers;

import configuration.PropertiesFromYaml;
import models.Address;
import models.User;
import org.checkerframework.checker.units.qual.A;

import java.util.Map;

public class AddressFactory {

    private DataFaker dataFaker = new DataFaker();
    private TestDataProvider testDataProvider = new TestDataProvider();


    public Address getRandomAddress() {
        return Address.builder()
                .street(dataFaker.getFakeAddress())
                .city(dataFaker.getFakeCity())
                .phoneNumber(dataFaker.getFakePhoneNumber())
                .companyName(dataFaker.getFakeCompanyName())
                .build();
    }

    public Address getExistingAddress(String addressName) {
        Map<String, Object> existingAddress = getAddressDataFromYaml(addressName);
        return Address.builder()
                .street(existingAddress.get("street").toString())
                .city(existingAddress.get("city").toString())
                .postalCode(existingAddress.get("postalCode").toString())
                .phoneNumber(existingAddress.get("phoneNumber").toString())
                .country(existingAddress.get("country").toString())
                .state(existingAddress.get("state").toString())
                .companyName(existingAddress.get("companyName").toString())
                .build();

    }


    private Map<String, Object> getAddressDataFromYaml(String addressName){
        Map<String, Object> data = PropertiesFromYaml.config;
        Map<String, Object> addresses = (Map<String, Object>) data.get("addresses");
        return (Map<String, Object>) addresses.get(addressName);
    }


}
