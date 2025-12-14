package ComplexTask.task1;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Base62Strategy implements ShorteningStrategy {
    private static final String BASE62_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int SHORT_URL_LENGTH = 7;

    @Override
    public String shorten(String originalUrl) {

        return generateShortCode(originalUrl);
    }

    public String generateShortCode(String url) {

        try {


            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(url.getBytes());
            BigInteger value = new BigInteger(1, digest);
            StringBuilder sb = new StringBuilder();
            BigInteger base = BigInteger.valueOf(62);

            while (value.compareTo(BigInteger.ZERO) > 0 && sb.length() < SHORT_URL_LENGTH) {
                BigInteger[] divmod = value.divideAndRemainder(base);
                int remainder = divmod[1].intValue();
                sb.append(BASE62_CHARS.charAt(remainder));
                value = divmod[0];
            }
            while (sb.length() < SHORT_URL_LENGTH) {
                sb.append('0');
            }
            return sb.reverse().toString();
        } catch (
                NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 не доступен", e);
        }
    }
}