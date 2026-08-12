package api.ahm.motogp.prediction.application.exception;

public class PredictionNotFoundException extends RuntimeException {
    public PredictionNotFoundException(String message) {
        super(message);
    }
}
