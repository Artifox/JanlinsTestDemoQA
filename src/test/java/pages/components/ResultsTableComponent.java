package pages.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultsTableComponent {

    public void checkTable(String columnName, String columnValue) {
        $(".table-responsive").$(byText(columnName))
                .shouldBe(visible)
                .parent().shouldHave(text(columnValue));
    }
}
