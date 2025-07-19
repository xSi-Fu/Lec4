package annotation.json;

import java.lang.reflect.Field;

public class JsonSerializer {
    public static String toJson(Object object) throws IllegalAccessException {
        StringBuilder json = new StringBuilder("{");
        boolean first = true;

        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonField.class)) {
                JsonField annotation = field.getAnnotation(JsonField.class);
                field.setAccessible(true);

                if (!first) json.append(", ");
                first = false;

                json.append("\"").append(annotation.name()).append("\": ");

                Object value = field.get(object);
                if (value instanceof String) {
                    json.append("\"").append(value).append("\"");
                } else {
                    json.append(value);
                }
            }
        }
        json.append("}");
        return json.toString();
    }
}