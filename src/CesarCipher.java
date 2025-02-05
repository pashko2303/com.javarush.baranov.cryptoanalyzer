import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CesarCipher {
    private final List<Character> alphabet;

    public CesarCipher(List<Character> alphabet) {
        this.alphabet = alphabet;
    }

    public void encrypt(List<String> text, int shift) {
        if (shift <= 0) {
            throw new IllegalArgumentException("Shift must be more than zero");
        }
        text.replaceAll(string -> doShift(string, shift));
    }

    public void decrypt(List<String> text, int shift) {
        if (shift <= 0) {
            throw new IllegalArgumentException("Shift must be more than zero");
        }
        text.replaceAll(string -> doShift(string, -shift));
    }

    private String doShift(String string, int shift) {
        List<Character> newAlphabet = new ArrayList<>(alphabet.size());
        shift = shift % alphabet.size();
        for (int i = 0; i < alphabet.size(); i++) {
            if (i + shift >= 0) {
                newAlphabet.add(alphabet.get((i + shift) % alphabet.size()));
            } else {
                newAlphabet.add(alphabet.get(alphabet.size() + (i + shift)));
            }
        }

        StringBuilder result = new StringBuilder();
        for (char c : string.toCharArray()) {
            if (alphabet.contains(c)) {
                result.append(newAlphabet.get(alphabet.indexOf(c)));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
