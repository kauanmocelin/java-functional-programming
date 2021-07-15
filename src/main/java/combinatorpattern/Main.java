package combinatorpattern;

import java.time.LocalDate;

import static combinatorpattern.CustomerRegistrationValidator.*;
import static combinatorpattern.CustomerRegistrationValidator.isEmailValid;

public class Main {
    public static void main(String[] args) {
        Customer alice = new Customer(
                "Alice",
                "alice@gmail.com",
                "+0332656598",
                LocalDate.of(2000, 1, 1)
        );

//        CustomerValidatorService validatorService = new CustomerValidatorService();
//        System.out.println(validatorService.isValid(alice));

        // Using combinator pattern
        ValidationResult result = isEmailValid()
                .and(isPhoneNumberValid())
                .and(isAdult())
                .apply(alice);

        System.out.println(result);

        if(result != ValidationResult.SUCCESS) {
            throw new IllegalStateException(result.name());
        }
    }
}
