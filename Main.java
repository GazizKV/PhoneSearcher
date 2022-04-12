package phonebook;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start searching...");
        long start = System.currentTimeMillis();
        File dictionaryFile = new File("C:\\Users\\valit\\Downloads\\directory.txt");
        File findFile = new File("C:\\Users\\valit\\Downloads\\find.txt");
        Map<String, String> dictionary = new HashMap<>();
        List<String> findNames = new ArrayList<>();

        try (
                BufferedReader dictionaryReader = new BufferedReader(new FileReader(dictionaryFile));
                BufferedReader findReader = new BufferedReader(new FileReader(findFile))
        ) {
            while (dictionaryReader.ready()) {
                String nextLine = dictionaryReader.readLine();
                int index = nextLine.indexOf(' ');
                dictionary.put(nextLine.substring(index + 1), nextLine.substring(0, index));
            }
            while (findReader.ready()) {
                findNames.add(findReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int counter = 0;
        Set<String> namesDictionary = dictionary.keySet();
        for (String name : findNames) {
            if (namesDictionary.contains(name)) counter++;
        }
        long end = System.currentTimeMillis();

        long timeMillis = end - start;

        long min = timeMillis / 1000 / 60;
        long sec = (timeMillis - min) / 1000;
        long ms = timeMillis - min * 1000 * 60 - sec * 1000;

        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.%n",
                counter, findNames.size(), min , sec, ms);
    }
}
