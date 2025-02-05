import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Character> ALPHABET = Arrays.asList('а', 'б',
            'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у',
            'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»',
            ':', '!', '?', ' ');
    private static final String MENU = """
            Welcome to cryptoanalyser
            1. Crypt file
            2. Decrypt file with a key
            3. Brute force
            4. Smart brute force""";
    private static final Scanner scanner = new Scanner(System.in);
    private static final CesarCipher cipher = new CesarCipher(ALPHABET);

    public static void main(String[] args) {
        System.out.println(MENU);
        while (!scanner.hasNextInt()) {
            System.out.println("It's not a number.");
            scanner.nextLine();
        }
        int task = scanner.nextInt();
        scanner.nextLine();
        switch (task) {
            case 1:
                encryptFile();
                break;
            case 2:
                decryptFile();
                break;
            case 3:
                BruteForce();
                break;
            case 4:
                SmartBruteForce();
                break;
            default:
                System.out.println("Number must be from 1 to 4");
        }
    }


    public static void encryptFile() {
        System.out.print("Enter input file path: ");
        String inputFilePath = scanner.nextLine();
        System.out.print("Enter output file path: ");
        String outputFilePath = scanner.nextLine();
        try {
            List<String> text = FileManager.readFromFile(inputFilePath);
            cipher.encrypt(text, 2);
            int o = 0;
            FileManager.writeToFile(text, outputFilePath);
        } catch (IOException e) {
            System.out.println("Invalid file path");
        }
    }

    private static void decryptFile() {
    }

    private static void BruteForce() {
    }

    private static void SmartBruteForce() {
    }
}