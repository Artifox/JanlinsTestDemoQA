package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import pages.components.CalendarComponent;
import pages.components.ResultsTableComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationFromPage {

    CalendarComponent calendarComponent = new CalendarComponent();
    ResultsTableComponent resultsTableComponent = new ResultsTableComponent();

    SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderInput = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            subjectsInput = $("#subjectsInput"),
            hobbiesInput = $("#hobbiesWrapper"),
            uploadPictureInput = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateInput = $("#state"),
            cityInput = $("#city"),
            submitButton = $("#submit"),
            dateOfBirthInput = $("#dateOfBirthInput");

    public RegistrationFromPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        return this;
    }

    public RegistrationFromPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);

        return this;
    }

    public RegistrationFromPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);

        return this;
    }

    public RegistrationFromPage setEmail(String email) {
        userEmailInput.setValue(email);

        return this;
    }

    public RegistrationFromPage setGender(String gender) {
        genderInput.$(byText(gender)).click();

        return this;
    }

    public RegistrationFromPage setPhoneNumber(String phoneNumber) {
        userNumberInput.setValue(phoneNumber);

        return this;
    }

    public RegistrationFromPage setSubject(String subject) {
        subjectsInput.sendKeys(subject);      //sendKeys() is used as workaround for this buggy form
        subjectsInput.sendKeys(Keys.TAB);

        return this;
    }

    public RegistrationFromPage setHobby(String hobby) {
        hobbiesInput.$(byText(hobby)).click();

        return this;
    }

    public RegistrationFromPage uploadImage(String pictureClassPath) {
        uploadPictureInput.uploadFromClasspath(pictureClassPath);

        return this;
    }

    public RegistrationFromPage setAddress(String currentAddress) {
        addressInput.setValue(currentAddress);

        return this;
    }

    public RegistrationFromPage setState(String state) {
        stateInput.click();
        $(byText(state)).click();

        return this;
    }

    public RegistrationFromPage setCity(String city) {
        cityInput.click();
        $(byText(city)).click();

        return this;
    }

    public RegistrationFromPage submitForm() {
        submitButton.click();

        return this;
    }

    public RegistrationFromPage setDateOfBirth(String birthDay, String birthMonth, String birthYear) {
        dateOfBirthInput.click();
        calendarComponent.setDate(birthDay, birthMonth, birthYear);

        return this;
    }

    public RegistrationFromPage checkTable(String columnName, String columnValue) {
        resultsTableComponent.checkTable(columnName, columnValue);

        return this;
    }
}
