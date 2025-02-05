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

    public static void main(String[] args) throws IOException {
        FileManager.writeToFile(FileManager.readFromFile("C:\\Users\\pavel\\Desktop\\test.txt"), "C:\\Users\\pavel\\Desktop\\out.txt");
        System.out.println(MENU);
//        while (!scanner.hasNextInt()) {
//            System.out.println("It's not a number.");
//            scanner.nextLine();
//        }
//        switch (scanner.nextInt()) {
//            case 1:
//                scanner.nextLine();
//                System.out.print("Enter input file path: ");
//                String inputFilePath = scanner.nextLine();
//                System.out.print("Enter output file path: ");
//                String outputFilePath = scanner.nextLine();
//                break;
//            case 2:
//                break;
//            case 3:
//                break;
//            case 4:
//                break;
//            default:
//                System.out.println("Number must be from 1 to 4");
//        }
    }
}