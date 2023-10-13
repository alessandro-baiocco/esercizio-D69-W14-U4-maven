package files;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FilesMain {
    public static void main(String[] args) {
        File file = new File("src/output.txt");
        try {


            FileUtils.writeStringToFile(file, "CIAO" + System.lineSeparator(), StandardCharsets.UTF_8, true);

            String contenuto = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            System.out.println("Nel file ho trovato: " + contenuto);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}