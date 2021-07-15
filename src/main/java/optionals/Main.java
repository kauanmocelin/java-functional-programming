package optionals;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Object value1 = Optional.ofNullable("Hello")
            .orElseGet(() -> "default value");

        System.out.println(value1);

        Object value2 = Optional.ofNullable("Hello")
                .orElseThrow(() -> new IllegalStateException("exception test"));

        System.out.println(value2);

        Optional.ofNullable("kauan@gmail")
                .ifPresent(email -> System.out.println("Sending email to " + email));

        Optional.ofNullable(null)
                .ifPresentOrElse(
                        email -> System.out.println("Sending email to " + email),
                        () -> System.out.println("Cannot send email"));

    }
}
