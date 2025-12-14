package ComplexTask.task1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashStrategy implements ShorteningStrategy {
    @Override
    public String shorten(String originalUrl) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(originalUrl.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                sb.append(String.format("%02x", hash[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 недоступен", e);
        }
    }

}
