package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String phoneNumber;
    private String companyName;

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }

    private Address() {
    }

    public static Address.Builder builder() {
        return new Address.Builder();
    }

    public static final class Builder {
        private String street;
        private String city;
        private String state = "Arizona";
        private String postalCode = "85001";
        private String country = "United States";
        private String phoneNumber;
        private String companyName;


        public Address.Builder street(String street) {
            this.street = street;
            return this;
        }

        public  Address.Builder city(String city) {
            this.city = city;
            return this;
        }

        public  Address.Builder state(String state) {
            this.state = state;
            return this;
        }

        public  Address.Builder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public  Address.Builder country(String country) {
            this.country = country;
            return this;
        }

        public  Address.Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

         public  Address.Builder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }


        public  Address build() {
            if (street.isEmpty()) {
                throw new IllegalStateException("Address cannot be empty");
            }
            if (city.isEmpty()) {
                throw new IllegalStateException("City cannot be empty");
            }
            if (postalCode.isEmpty()) {
                throw new IllegalStateException("PostalCode cannot be empty");

            } if (phoneNumber.isEmpty()) {
                throw new IllegalStateException("PhoneNumber cannot be empty");
            }

            Address address= new Address();
            address.street = this.street;
            address.city = this.city;
            address.state = this.state;
            address.postalCode = this.postalCode;
            address.country = this.country;
            address.phoneNumber = this.phoneNumber;
            address.companyName = this.companyName;
            return address;
        }
    }





}
