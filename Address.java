
package AlmogAsiaDolfinVarshev;

public class Address {
    private String street, city, country;
    private int houseNumber;

    public Address(String country, String city, String street, int houseNumber) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) throws Exception{
        if (houseNumber < 0) {
            throw new Exception("House number cannot be negative.");
        }
        else{
            this.houseNumber = houseNumber;
        }
    }

    public boolean equals(Object other) {
        if (!(other instanceof Address)) {
            return false;
        }

        if (!(super.equals(other))) {
            return false;
        }

        Address temp = (Address) other;
        return this.country.equals(temp.getCountry())
                && this.city.equals(temp.getCity())
                && this.street.equals(temp.getCity())
                && this.houseNumber == temp.houseNumber;
    }

    public String toString() {
        return "[country - " +
                country + ", city - " +
                city + ", street - " +
                street + ", house number - " +
                houseNumber + "]";
    }
}
