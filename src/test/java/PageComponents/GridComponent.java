package PageComponents;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$;

public class GridComponent {

    public ElementsCollection gridElements = $$("div.product-list div.product");

    public void checkGridIsNotEmpty() {

        gridElements.shouldBe(sizeGreaterThan(0));
    }

}
