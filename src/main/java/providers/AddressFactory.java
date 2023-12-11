package providers;

import models.Address;

public class AddressFactory {

    private DataFaker dataFaker = new DataFaker();
    private TestDataProvider testDataProvider = new TestDataProvider();


    public Address getRandomAddress() {
        return Address.builder()
                .address(dataFaker.getFakeAddress())
                .city(dataFaker.getFakeCity())
                .postalCode(testDataProvider.getTestData("postalCode"))
                .phoneNumber(dataFaker.getFakePhoneNumber())
                .country(testDataProvider.getTestData("country"))
                .state(testDataProvider.getTestData("state"))
                .companyName(dataFaker.getFakeCompanyName())
                .build();
    }
}
