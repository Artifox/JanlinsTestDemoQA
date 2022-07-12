package utils;

import com.github.javafaker.Faker;

public class TestData {

    Faker faker = new Faker();

    public String firstName = faker.funnyName().name(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            gender = getGender(),
            phoneNumber = String.valueOf(faker.number().digits(10)),
            birthMonth = getBirthMonth(),
            birthYear = String.valueOf(faker.number().numberBetween(1992, 2022)),
            birthDay = String.valueOf(faker.number().numberBetween(1, 28)),
            subject = getSubject(),
            hobby = getHobby(),
            picturesRoot = "imgs/",
            pictureName = "img1.png",
            currentAddress = String.valueOf(faker.address().fullAddress()),
            state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan"),
            city = getCity(state);

    private String getCity(String state) {
        switch (state) {
            case "NCR":
                return faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh":
                return faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana":
                return faker.options().option("Karnal", "Panipat");
            case "Rajasthan":
                return faker.options().option("Jaipur", "Jaiselmer");
            default: return null;
        }

    }

    private String getBirthMonth() {
        return faker.options().option("January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December");
    }

    private String getGender() {
        return faker.options().option("Male", "Female", "Other");
    }

    private String getHobby() {
        return faker.options().option("Sports", "Reading", "Music");
    }

    private String getSubject() {
        return faker.options().option("Hindi", "English", "Maths", "Physics", "Chemistry",
                "Biology", "Computer Science", "Commerce", "Accounting", "Economics", "Arts", "Social Studies",
                "History", "Civics");
    }
}
