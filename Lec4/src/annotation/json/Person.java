package annotation.json;

public class Person {
    @JsonField(name = "person_name")
    private String name = "John";

    @JsonField(name = "person_age")
    private int age = 30;

    private String password = "secret";
}