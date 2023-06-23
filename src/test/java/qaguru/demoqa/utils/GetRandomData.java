package qaguru.demoqa.utils;

import com.github.javafaker.Faker;

public class GetRandomData {

    Faker faker = new Faker();

    public String getFirstName() {
        return faker.name().firstName();
    }

    public String getLastName() {
        return faker.name().lastName();
    }

    public String getEmail() {
        return faker.internet().emailAddress();
    }

    public String getGender() {
        String[] genders = {"Male", "Female", "Other"};
        return faker.options().option(genders);
    }

    public String getPhoneNumber() {
        return faker.number().digits(10);
    }

    public String getDay() {
        return String.format("%02d", faker.number().numberBetween(1, 28));
    }

    public String getMonth() {
        String[] months = {"January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October", "November",
                "December"};
        return faker.options().option(months);
    }

    public String getYear() {
        return String.valueOf(faker.number().numberBetween(1900, 2100));
    }

    public String getSubjects() {
        String[] subjects = {"Math", "English", "Chemistry", "Civics",
                "Computer Science", "Arts", "Physics", "Economics"};
        return faker.options().option(subjects);
    }

    public String getHobbies() {
        String[] hobbies = {"Sports", "Reading", "Music"};
        return faker.options().option(hobbies);
    }

    public String getAddress() {
        return faker.address().fullAddress();
    }

    public String getState() {
        String[] state = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        return faker.options().option(state);
    }

    public String getCity(String state) {
        switch (state) {
            case "NCR": {
                String[] city = {"Delhi", "Gurgaon", "Noida"};
                return faker.options().option(city);
            }
            case "Uttar Pradesh": {
                String[] city = {"Agra", "Lucknow", "Merrut"};
                return faker.options().option(city);
            }
            case "Haryana": {
                String[] city = {"Karnal", "Panipat"};
                return faker.options().option(city);
            }
            case "Rajasthan": {
                String[] city = {"Jaipur", "Jaiselmer"};
                return faker.options().option(city);
            }
        }
        return null;
    }



}
