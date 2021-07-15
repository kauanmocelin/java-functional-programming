package functionalinterface;

import java.util.function.BiFunction;
import java.util.function.Function;

public class _Function {

    public static void main(String[] args) {
        // Function
        int incrementMethod = incrementByOne(1);
        System.out.println(incrementMethod);

        Integer incrementFunction = incrementByOneFunction.apply(1);
        System.out.println(incrementFunction);

        Integer multiply = multipleByTenFunction.apply(incrementFunction);
        System.out.println(multiply);

        // chaining functions
        Function<Integer, Integer> addByOneAndThenMultiplyByTen = incrementByOneFunction.andThen(multipleByTenFunction);
        System.out.println(addByOneAndThenMultiplyByTen.apply(1));

        // BiFunction
        System.out.println(incrementByOneAndMultiply(4, 100));
        System.out.println(incrementByOneAndMultiplyBiFunction.apply(4, 100));
    }

    // Function
    static int incrementByOne(int number) {
        return number + 1;
    }

    static Function<Integer, Integer> incrementByOneFunction = number -> number + 1;

    static Function<Integer, Integer> multipleByTenFunction = number -> number * 10;





    // Bi Function
    static int incrementByOneAndMultiply(int number, int numToMultiplyBy) {
        return (number + 1) * numToMultiplyBy;
    }

    static BiFunction<Integer, Integer, Integer> incrementByOneAndMultiplyBiFunction =
            (numberToIncrementByOne, numberToMultiplyBy )
                    -> (numberToIncrementByOne + 1) * numberToMultiplyBy;
}
