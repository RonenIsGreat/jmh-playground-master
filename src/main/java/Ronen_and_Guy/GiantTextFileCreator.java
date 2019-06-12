package Ronen_and_Guy;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GiantTextFileCreator {
    static final String createdFileName = "GiantFile.txt";
    static final int charectersNumber = 1000000;

    public static void main(String... args) {
        try {
            long start = System.currentTimeMillis();
            char[] characters = new char[charectersNumber];
            Random random = new Random();

            for (int i = 0; i < charectersNumber; i++) {
                // a-z letters
                char c = (char)(random.nextInt(26) + 'a');
                characters[i] = c;
            }

            File file = new File(createdFileName);
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(characters);
            writer.flush();
            writer.close();
            long end = System.currentTimeMillis();
            System.out.println((end - start) / 1000f + " seconds");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
