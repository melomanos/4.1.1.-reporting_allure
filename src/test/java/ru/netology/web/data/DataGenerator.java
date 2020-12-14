package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Locale;

@Data
@RequiredArgsConstructor
public class DataGenerator {

    public static class Registration {
        private Registration() {}

        public static RegistrationInfo generate(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationInfo(
                faker.name().firstName() + " " + faker.name().lastName(),
                faker.address().city(),
                faker.phoneNumber().phoneNumber()
            );
        }
    }
}
