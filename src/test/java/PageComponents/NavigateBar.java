package PageComponents;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;

public class NavigateBar {

    //Панель навигации

    private final SelenideElement ChooseTypeButton = $("div.justify-between");

    public void HoverAndChooseOption(String optionType) {

        ChooseTypeButton.hover();
        ChooseTypeButton
                .parent().parent()
                .$$("a").find(text(optionType)).click();
    }

    public void CheckOptionDescription (String optionDescription) {

        $("h1").shouldHave(text(optionDescription));
    }

    //Основные категории

    public void openCategory(String category) {

        $(byTagAndText("b", category)).click();
    }

    //Подкатегории

    private final SelenideElement subcategory = $("span.coffee-type-item__name");

    public void checkSubcategoryIsAvailable (String subcategoryName) {

        subcategory.shouldHave(text(subcategoryName));
    }
}
