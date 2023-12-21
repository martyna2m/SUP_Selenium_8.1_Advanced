package pages.checkout;

import models.Address;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import providers.DataFaker;


public class AddressesSectionFormPage extends BasePage {


    @FindBy(css = "[name='address1']")
    private WebElement addressInput;

    @FindBy(css = "[name='firstname']")
    private WebElement firstNameInput;

    @FindBy(css = "[name='lastname']")
    private WebElement lastNameInput;

    @FindBy(css = "[name='company']")
    private WebElement companyNameInput;

    @FindBy(css = "[name= 'id_state']")
    private WebElement stateSelect;

    @FindBy(css = "[name='postcode']")
    private WebElement postalCodeInput;

    @FindBy(css = "[name='city']")
    private WebElement cityInput;

    @FindBy(css = "[name='id_country']")
    private WebElement countrySelect;

    @FindBy(css = "[name='phone']")
    private WebElement phoneInput;

    @FindBy(css = "[name='confirm-addresses']")
    private WebElement continueBtn;

    @FindBy(xpath = "//a[contains(text(),'Cancel')]")
    private WebElement cancelBtn;

    @FindBy(css = "#use_same_address")
    private WebElement useSameAddressCheckbox;

    public AddressesSectionFormPage(WebDriver driver) {
        super(driver);
    }


    public void fillTheAddressForm(Address address) {
        DataFaker dataFaker = new DataFaker();
        fillName(dataFaker.getFakeFirstName(), dataFaker.getFakeLastName())
                .fillAddress(address.getStreet())
                .fillCompanyName(address.getCompanyName())
                .fillCity(address.getCity())
                .fillPostalCode(address.getPostalCode())
                .fillPhoneNumber(address.getPhoneNumber())
                .chooseCountry(address.getCountry())
                .chooseState(address.getState());
        clickContinue();

    }


    public void clickContinue() {
        click(continueBtn);
    }

    public AddressesSectionFormPage fillAddress(String address) {
        sendKeys(addressInput, address);
        return this;
    }

    public AddressesSectionFormPage fillCompanyName(String company) {
        sendKeys(companyNameInput, company);
        return this;
    }


    public AddressesSectionFormPage fillCity(String city) {
        sendKeys(cityInput, city);
        return this;
    }

    public AddressesSectionFormPage fillPostalCode(String postalCode) {
        sendKeys(postalCodeInput, postalCode);
        return this;
    }

    public AddressesSectionFormPage fillName(String firstName, String lastName) {
        if (isNameEmpty()) {
            sendKeys(firstNameInput, firstName);
            sendKeys(lastNameInput, lastName);
        }
        return this;
    }

    public AddressesSectionFormPage fillPhoneNumber(String phoneNumber) {
        sendKeys(phoneInput, phoneNumber);
        return this;
    }

    public AddressesSectionFormPage chooseCountry(String country) {
        if (isCountryEmpty()) {
            click(countrySelect);
            selectByVisibleText(countrySelect, country);
        }
        return this;
    }

    public AddressesSectionFormPage chooseState(String state) {
        click(stateSelect);
        selectByVisibleText(stateSelect, state);
        return this;
    }


    private boolean isCountryEmpty() {
        return countrySelect.getAttribute("value").isEmpty();
    }


    private boolean isNameEmpty() {
        return (firstNameInput.getAttribute("value").isEmpty()) || (lastNameInput.getAttribute("value").isEmpty());

    }
}
