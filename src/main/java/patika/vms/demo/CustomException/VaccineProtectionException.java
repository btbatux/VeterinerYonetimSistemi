package patika.vms.demo.CustomException;

public class VaccineProtectionException extends RuntimeException {
    public VaccineProtectionException(String message) {
        super(message);
    }
}
