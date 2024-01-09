import org.owasp.esapi.ESAPI;

public class XSSProtection {

    public static String sanitizeUserInput(String input) {
        return ESAPI.encoder().encodeForHTML(input);
    }

    public static void main(String[] args) {
        String unsafeInput = "<script>alert('xss');</script>";
        String safeInput = sanitizeUserInput(unsafeInput);
        System.out.println("Sanitized Input: " + safeInput);
    }
}