import lambda.*;
import lambda.model.HeavyBox;
import annotation.deprecated.*;
import annotation.json.*;
import annotation.json.Person;

import java.util.function.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        //1.
        System.out.println("=== 1. Лямбда для Printable ===");
        Printable printable = () -> System.out.println("Привет из лямбда-выражения!");
        printable.print();
        //2
        System.out.println("\n=== 2. Проверка строки ===");
        Predicate<String> isNotNull = s -> s != null;
        Predicate<String> isNotEmpty = s -> !s.isEmpty();
        Predicate<String> isValid = isNotNull.and(isNotEmpty);
        //3
        System.out.println("'Hello': " + isValid.test("Hello"));
        System.out.println("null: " + isValid.test(null));
        System.out.println("'': " + isValid.test(""));
        //4
        System.out.println("\n=== 3. Проверка J/N и A ===");
        Predicate<String> startsWithJorN = s -> s != null && (s.startsWith("J") || s.startsWith("N"));
        Predicate<String> endsWithA = s -> s != null && s.endsWith("A");
        Predicate<String> combinedCheck = startsWithJorN.and(endsWithA);
        //5
        System.out.println("JavaA: " + combinedCheck.test("JavaA"));
        System.out.println("NimbleA: " + combinedCheck.test("NimbleA"));
        System.out.println("HelloA: " + combinedCheck.test("HelloA"));
        //6
        System.out.println("\n=== 4. HeavyBox с Consumer ===");
        Consumer<HeavyBox> ship = box ->
                System.out.println("Отправляем ящик с весом " + box.getWeight());
        Consumer<HeavyBox> deliver = box ->
                System.out.println("Отгрузили ящик с весом " + box.getWeight());
        ship.andThen(deliver).accept(new HeavyBox(150));
        //5
        System.out.println("\n=== 5. Проверка числа ===");
        Function<Integer, String> numberCheck = num -> {
            if (num > 0) return "Положительное число";
            else if (num < 0) return "Отрицательное число";
            else return "Ноль";
        };
        System.out.println("5: " + numberCheck.apply(5));
        System.out.println("-3: " + numberCheck.apply(-3));
        System.out.println("0: " + numberCheck.apply(0));
        //6
        System.out.println("\n=== 6. Случайное число ===");
        Supplier<Integer> randomSupplier = () -> new Random().nextInt(11);
        System.out.println("Случайное число: " + randomSupplier.get());
        System.out.println("Случайное число: " + randomSupplier.get());
        //7
        System.out.println("\n=== 7. Проверка @DeprecatedEx ===");
        DeprecatedHandler.checkClass(OldClass.class);
        //8
        System.out.println("\n=== 8. JSON сериализация ===");
        Person person = new Person();
        System.out.println("JSON: " + JsonSerializer.toJson(person));
    }
}