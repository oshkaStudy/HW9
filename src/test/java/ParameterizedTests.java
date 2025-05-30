import Data.OptionTypes;
import PageComponents.GridComponent;
import PageComponents.NavigateBar;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;

@DisplayName("Кофейные тесты")
public class ParameterizedTests {

    @BeforeEach
    void setupConfig() {

        Configuration.baseUrl = "https://shop.tastycoffee.ru";
        Configuration.browserSize = "1920x1080";

        open("/");
    }

    NavigateBar navigateBar = new NavigateBar();
    GridComponent gridComponent = new GridComponent();

    @EnumSource(OptionTypes.class)
    @ParameterizedTest(name = "Проверка соответствия описания категории {0}")
    @Tag("Regression")
    void checkCategoryDescriptionByEnumSourceAnnotation(OptionTypes optionTypes) {
        navigateBar.HoverAndChooseOption(optionTypes.name);
        navigateBar.CheckOptionDescription(optionTypes.description);
    }

    @ValueSource(strings = {
            "для эспрессо", "для фильтра"
    })
    @ParameterizedTest(name = "Проверка наличия товаров в категории {0}")
    @Tag("Critical")
    void checkCategoryIsNotEmpty(String category) {
        navigateBar.openCategory(category);
        gridComponent.checkGridIsNotEmpty();
    }

    static Stream<Arguments> checkSubcategoryIsAvailable () {
        return Stream.of(
                Arguments.of("Чай", "Specialty tea"),
                Arguments.of("Аксессуары", "Фильтры")
        );

    }

    @MethodSource
    @ParameterizedTest(name = "Проверка наличия подкатегории {1} в категории {0}")
    @Tag("Critical")
    void checkSubcategoryIsAvailable(String category, String subcategoty) {

        navigateBar.HoverAndChooseOption(category);
        navigateBar.checkSubcategoryIsAvailable(subcategoty);
    }
}