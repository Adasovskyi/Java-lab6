import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Translator {
    private final HashMap<String, String> dictionary;

    public Translator() {
        dictionary = new HashMap<>();
    }

    public void addWordPair(String englishWord, String ukrainianWord) {
        dictionary.put(englishWord.toLowerCase(), ukrainianWord.toLowerCase());
    }

    public String translatePhrase(String phrase) {
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher(phrase);
        StringBuilder translatedPhrase = new StringBuilder();

        while (matcher.find()) {
            String word = matcher.group();
            String lowerWord = word.toLowerCase();

            String translation = dictionary.getOrDefault(lowerWord, "[" + lowerWord + "]");

            matcher.appendReplacement(translatedPhrase, translation);
        }

        matcher.appendTail(translatedPhrase);

        return translatedPhrase.toString();
    }
}

public class Main {
    static void main() {
        Translator translator = new Translator();
        Scanner scanner = new Scanner(System.in);

        translator.addWordPair("hello", "привіт");
        translator.addWordPair("world", "світ");
        translator.addWordPair("simple", "просте");
        translator.addWordPair("task", "завдання");
        translator.addWordPair("this", "це");
        translator.addWordPair("is", "є");
        System.out.println("Базовий словник завантажено. Бажаєте додати нові слова? (так/ні)");
        String choice = scanner.nextLine();

        while (choice.equalsIgnoreCase("так")) {
            System.out.print("Введіть слово англійською: ");
            String eng = scanner.nextLine();
            System.out.print("Введіть переклад українською: ");
            String ukr = scanner.nextLine();

            translator.addWordPair(eng, ukr);

            System.out.print("Додати ще одне слово? (так/ні): ");
            choice = scanner.nextLine();
        }

        System.out.println("\nВведіть фразу англійською мовою для перекладу:");
        String phrase = scanner.nextLine();

        String translation = translator.translatePhrase(phrase);
        System.out.println("Переклад українською: " + translation);

        scanner.close();
    }
}
