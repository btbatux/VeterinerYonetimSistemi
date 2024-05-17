package patika.vms.demo.CustomException;

public class DoctorNotAvailableException extends RuntimeException {

    public DoctorNotAvailableException(String message) {
        super(message);
    }
}
