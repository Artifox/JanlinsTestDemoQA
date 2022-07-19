package tests;

import org.junit.jupiter.api.Test;
import utils.TestData;

import static io.qameta.allure.Allure.step;

public class FormTests extends BaseTest {

    TestData testData = new TestData();


    @Test
    public void fillFormTest() {
        step("Open main page", () -> {
            registrationFromPage.openPage();
            System.out.println(config.login());
            System.out.println(config.password());
        });
        step("Fill in the form", () -> {
            registrationFromPage
                    .setFirstName(testData.firstName)
                    .setLastName(testData.lastName)
                    .setEmail(testData.email)
                    .setGender(testData.gender)
                    .setPhoneNumber(testData.phoneNumber)
                    .setDateOfBirth(testData.birthDay, testData.birthMonth, testData.birthYear)
                    .setSubject(testData.subject)
                    .setHobby(testData.hobby)
                    .uploadImage(testData.picturesRoot + testData.pictureName)
                    .setAddress(testData.currentAddress)
                    .setState(testData.state)
                    .setCity(testData.city);
        });
        step("Submit form", () -> {
            registrationFromPage.submitForm();
        });
        step("Check the results", () -> {
            registrationFromPage.checkTable("Student Name", testData.firstName + " " + testData.lastName)
                    .checkTable("Student Email", testData.email)
                    .checkTable("Gender", testData.gender)
                    .checkTable("Mobile", testData.phoneNumber)
                    .checkTable("Date of Birth", testData.birthDay + " " + testData.birthMonth + "," + testData.birthYear)
                    .checkTable("Subjects", testData.subject)
                    .checkTable("Hobbies", testData.hobby)
                    .checkTable("Picture", testData.pictureName)
                    .checkTable("Address", testData.currentAddress)
                    .checkTable("State and City", testData.state + " " + testData.city);
        });
    }
}
