package models;

import lombok.Getter;

@Getter
public class Address {
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String phoneNumber;
    private String companyName;


    private Address() {
    }

    public static Address.Builder builder() {
        return new Address.Builder();
    }

    public static final class Builder {
        private String address;
        private String city;
        private String state;
        private String postalCode;
        private String country;
        private String phoneNumber;
        private String companyName;


        public Address.Builder address(String address) {
            this.address = address;
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
            if (address.isEmpty()) {
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
            address.address = this.address;
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
