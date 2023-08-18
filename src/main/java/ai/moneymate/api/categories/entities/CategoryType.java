package ai.moneymate.api.categories.entities;

public enum CategoryType {
    FOOD("FOOD"),
    TRANSPORTATION("TRANSPORTATION"),
    UTILITIES("UTILITIES"),
    PERSONAL_SPENDING("PERSONAL_SPENDING"),
    ENTERTAINMENT("ENTERTAINMENT"),
    SAVING("SAVING");

    private final String name;

    CategoryType(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
