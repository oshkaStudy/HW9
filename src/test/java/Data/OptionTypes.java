package Data;

public enum OptionTypes {
    Coffee("Кофе", "Каталог кофе"),
    Tea("Чай", "Каталог чая");

    public final String name;
    public final String description;

    OptionTypes(String name, String description) {
        this.name = name;
        this.description = description;
    }
}