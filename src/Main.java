import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
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
    private static double AVERAGE_WORD_LENGTH = 5.28;
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
            text.replaceAll(string -> cipher.encrypt(string, key));
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
            text.replaceAll(string -> cipher.decrypt(string, key));
            FileManager.writeToFile(text, outputFilePath);
            System.out.print("File has been successfully decrypted!");
        } catch (NumberFormatException e) {
            System.out.println("Key must be a number");
        } catch (IOException e) {
            System.out.println("Invalid file path");
        }
    }

    private static void BruteForce() {
        System.out.print("Enter encrypted file path: ");
        String inputFilePath = scanner.nextLine();
        try {
            List<String> text = FileManager.readFromFile(inputFilePath);
            String exampleLine = getExampleLine(text);
            for (int i = 1; i < ALPHABET.size(); i++) {
                System.out.println(i + ". | " + cipher.decrypt(exampleLine, i));
            }
            System.out.print("Enter line number with right decryption: ");
            int key = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter output file path: ");
            String outputFilePath = scanner.nextLine();
            text.replaceAll(string -> cipher.decrypt(string, key));
            FileManager.writeToFile(text, outputFilePath);
            System.out.print("File has been successfully decrypted with key " + key);
        } catch (NumberFormatException e) {
            System.out.println("Number excepted");
        } catch (IOException e) {
            System.out.println("Invalid file path");
        }
    }

    private static void SmartBruteForce() {
        System.out.print("Enter encrypted file path: ");
        String inputFilePath = scanner.nextLine();
        try {
            List<String> text = FileManager.readFromFile(inputFilePath);
            double[] wordLengths = new double[ALPHABET.size() + 1];
            for (int i = 0; i < ALPHABET.size(); i++) {
                text.replaceAll(string -> cipher.decrypt(string, 1));
                wordLengths[i + 1] = getAverageWordLength(text);
            }
            int key = getMinDeviationIndex(wordLengths, AVERAGE_WORD_LENGTH);
            System.out.print("Enter output file path: ");
            String outputFilePath = scanner.nextLine();
            text.replaceAll(string -> cipher.decrypt(string, key));
            FileManager.writeToFile(text, outputFilePath);
            System.out.print("File has been successfully decrypted with key " + key);
        } catch (NumberFormatException e) {
            System.out.println("Number excepted");
        } catch (IOException e) {
            System.out.println("Invalid file path");
        }
    }

    private static int getMinDeviationIndex(double[] array, double number) {
        int result = 0;
        double minDeviation = Double.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (Math.abs(array[i] - number) < minDeviation) {
                minDeviation = Math.abs(array[i] - number);
                result = i;
            }

        }
        return result;
    }

    private static String getExampleLine(List<String> text) {
        String exampleLine = "";
        for (String str : text) {
            if (str.length() > exampleLine.length()) {
                exampleLine = str;
            }
        }
        return exampleLine.substring(0, Math.min(exampleLine.length(), 60));
    }

    private static int getAverageWordLength(List<String> text) {
        int totalLength = 0;
        int wordCount = 0;
        for (String line : text) {
            String[] words = line.split("\\s+");
            for (String word : words) {
                if (!word.isEmpty()) {
                    totalLength += word.length();
                    wordCount++;
                }
            }
        }
        return wordCount == 0 ? 0 : totalLength / wordCount;
    }
}