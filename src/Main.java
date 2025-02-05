import java.util.Arrays;
import java.util.List;

public class Main {
    private static final List ALPHABET = Arrays.asList('а', 'б',
            'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у',
            'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»',
            ':', '!', '?', ' ');

    public static void main(String[] args) {
        CesarCipher cipher = new CesarCipher(ALPHABET);
        System.out.println(cipher.encrypt("привет, мир!", 3));
        System.out.println(cipher.decrypt("тумеих:впмуа", 3));
    }
}