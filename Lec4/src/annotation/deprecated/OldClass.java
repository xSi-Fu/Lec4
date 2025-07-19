package annotation.deprecated;

@DeprecatedEx(message = "Используйте NewClass вместо этого")
public class OldClass {
    @DeprecatedEx(message = "Используйте newMethod()")
    public void oldMethod() {}

    public void newMethod() {}
}