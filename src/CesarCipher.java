import java.util.ArrayList;
import java.util.List;

public class CesarCipher {
    private final List<Character> alphabet;

    public CesarCipher(List<Character> alphabet) {
        this.alphabet = alphabet;
    }

    public String encrypt(String text, int shift) {
        if (shift <= 0) {
            throw new IllegalArgumentException("Shift must be more than zero");
        }
        return doShift(text, shift);
    }

    public String decrypt(String encryptedText, int shift) {
        if (shift <= 0) {
            throw new IllegalArgumentException("Shift must be more than zero");
        }
        return doShift(encryptedText, -shift);
    }

    private String doShift(String text, int shift) {
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
        for (char c : text.toCharArray()) {
            if (alphabet.contains(c)) {
                result.append(newAlphabet.get(alphabet.indexOf(c)));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
