package zad3;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LoremIpsumGenerator {


    public static String generate(int wordCount) {
        List<String> words = getLoremImpsumWords();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < wordCount; i++) {
            sb.append(words.get(random.nextInt(words.size())));
            sb.append(" ");
        }

        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        sb.setLength(sb.length() - 1);
        sb.append(".");

        return sb.toString();
    }

    private static List<String> getLoremImpsumWords() {
        String words = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        String[] wordArray = words.replace('.', ' ').replace(',', ' ').toLowerCase().split(" ");
        Set<String> uniqueWords = new HashSet<>(List.of(wordArray));
        return uniqueWords.stream().toList();
    }

    public static void main(String[] args) {
        int loremLength = 25;
        String loremIpsum = generate(loremLength);
        System.out.println(loremIpsum);
    }
}
