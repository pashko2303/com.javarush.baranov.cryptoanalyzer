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
            ==========================
            Welcome to cryptoanalyser
            1. Crypt file
            2. Decrypt file with a key
            3. Brute force
            4. Smart brute force
            0. exit
            ==========================""";
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
        System.out.print("Enter key: ");
        String keyStr = scanner.nextLine();
        System.out.print("Enter input file path: ");
        String inputFilePath = scanner.nextLine();
        System.out.print("Enter output file path: ");
        String outputFilePath = scanner.nextLine();
        try {
            int key = Integer.parseInt(keyStr);
            if (key <= 0) {
                System.out.println("Key must be more than zero");
            }
            List<String> text = FileManager.readFromFile(inputFilePath);
            cipher.encrypt(text, key);
            FileManager.writeToFile(text, outputFilePath);
            System.out.print("File has been successfully encrypted!");
        } catch (NumberFormatException e) {
            System.out.println("Key must be a number");
        } catch (IOException e) {
            System.out.println("Invalid file path");
        }
    }

    private static void decryptFile() {
        System.out.print("Enter key: ");
        String keyStr = scanner.nextLine();
        System.out.print("Enter encrypted file path: ");
        String inputFilePath = scanner.nextLine();
        System.out.print("Enter output file path: ");
        String outputFilePath = scanner.nextLine();
        try {
            int key = Integer.parseInt(keyStr);
            if (key <= 0) {
                System.out.println("Key must be more than zero");
            }
            List<String> text = FileManager.readFromFile(inputFilePath);
            cipher.decrypt(text, key);
            FileManager.writeToFile(text, outputFilePath);
            System.out.print("File has been successfully encrypted!");
        } catch (NumberFormatException e) {
            System.out.println("Key must be a number");
        } catch (IOException e) {
            System.out.println("Invalid file path");
        }
    }

    private static void BruteForce() {
    }

    private static void SmartBruteForce() {
    }
}