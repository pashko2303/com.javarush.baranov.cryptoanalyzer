import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileManager {
    public static List<String> readFromFile(String filePath) throws IOException {
        Path path = Path.of(filePath);
        return Files.readAllLines(path);
    }

    public static void writeToFile(List<String> text, String filePath) throws IOException {
        Path path = Path.of(filePath);
        Files.write(path, text);
    }
}
