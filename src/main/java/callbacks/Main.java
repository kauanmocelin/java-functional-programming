package callbacks;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        helloCallback("John", null, value -> System.out.println("no lastname provided"));
        helloRunnable("John", null, () -> System.out.println("no lastname provided"));
    }

    static void helloCallback(String firstName, String lastName, Consumer<String> callback) {
        System.out.println(firstName);
        if(lastName != null) {
            System.out.println(lastName);
            return;
        }
        callback.accept(firstName);
    }

    static void helloRunnable(String firstName, String lastName, Runnable callback) {
        System.out.println(firstName);
        if(lastName != null) {
            System.out.println(lastName);
            return;
        }
        callback.run();
    }
}
