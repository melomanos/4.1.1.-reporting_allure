package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Data
@RequiredArgsConstructor
public class DataGenerator {

    public static class Registration {
        private Registration() {}

        public static RegistrationInfo generate(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationInfo(
                generateCity(),
                generateMeetingDate(5),
                generateMeetingDate(7),
                faker.name().lastName() + " " + faker.name().firstName(),
                faker.phoneNumber().phoneNumber()
            );
        }

        public static String generateCity() {
            Random random = new Random();
            List<String> adminCenterCity = Arrays.asList("Майкоп", "Горно-Алтайск", "Уфа", "Улан-Удэ", "Махачкала",
                    "Магас", "Нальчик", "Элиста", "Черкесск", "Петрозаводск", "Сыктывкар", "Симферополь", "Йошкар-Ола",
                    "Саранск", "Якутск", "Владикавказ", "Казань", "Кызыл", "Ижевск", "Абакан", "Грозный", "Чебоксары",
                    "Барнаул", "Чита", "Петропавловск-Камчатский", "Краснодар", "Красноярск", "Пермь", "Владивосток",
                    "Ставрополь", "Хабаровск", "Благовещенск", "Архангельск", "Астрахань", "Белгород", "Брянск",
                    "Владимир", "Волгоград", "Вологда", "Воронеж", "Иваново", "Иркутск", "Калининград", "Калуга",
                    "Кемерово", "Киров", "Кострома", "Курган", "Курск", "Санкт-Петербург", "Липецк", "Магадан",
                    "Москва", "Мурманск", "Нижний Новгород", "Великий Новгород", "Новосибирск", "Омск", "Оренбург",
                    "Орёл", "Пенза", "Псков", "Ростов-на-Дону", "Рязань", "Самара", "Саратов", "Южно-Сахалинск",
                    "Екатеринбург", "Смоленск", "Тамбов", "Тверь", "Томск", "Тула", "Тюмень", "Ульяновск", "Челябинск",
                    "Ярославль", "Нарьян-Мар", "Ханты-Мансийск", "Анадырь", "Салехард", "Биробиджан");
            String city = adminCenterCity.get(random.nextInt(adminCenterCity.size()));
            return city;
        }

        public static String generateCityWrong() {
            Random random = new Random();
            List<String> wrongCity = Arrays.asList("Chicago", "Миллерово", "Кингисепп", "Toronto", "Dubai");
            String city = wrongCity.get(random.nextInt(wrongCity.size()));
            return city;
        }

        public static String generateMeetingDate(int plusDays) {
            String meetingDate = LocalDate.now().plusDays(plusDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

            return meetingDate;
        }

        public static String generateMeetingDateCalendarApp(int plusDays) {
            String meetingDateCalendarApp = LocalDate.now().plusDays(plusDays).plusMonths(2)
                    .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

            return meetingDateCalendarApp;
        }

        public static String generateMeetingDateCalendarAppCut(int plusDays) {
            StringBuilder stringBuilder = new StringBuilder(generateMeetingDate(5));
            StringBuilder meetingDateCut = stringBuilder.replace(2, 10, "");

            StringBuilder stringBuilderTill10 = new StringBuilder(generateMeetingDate(5));
            StringBuilder meetingDateCutTill10 = stringBuilderTill10.replace(2, 10, "").replace(0, 1, "");

            LocalDate date = LocalDate.now().plusDays(plusDays);
            int dayOfMonth = date.getDayOfMonth();

            if (dayOfMonth <= 9) {
                return meetingDateCutTill10.toString();
            } else {
                return meetingDateCut.toString();
            }
        }

        public static String generateMeetingDateWrong() {
            Random random = new Random();
            int dayOfMonth = random.nextInt(28);
            int month = random.nextInt(12);
            int year = random.nextInt(2019);
            String meetingDateWrong = LocalDate.of(year, month, dayOfMonth).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

            return meetingDateWrong;
        }

        public static String generateNameWrong() {
            Faker faker = new Faker(new Locale("en"));

            return faker.name().fullName();
        }

        public static String generatePhoneWrong() {
            Faker faker = new Faker(new Locale("en"));

            return faker.phoneNumber().cellPhone();
        }
    }
}
