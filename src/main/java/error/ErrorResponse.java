package error;

import error.exception.CustomException;
import io.vavr.control.Either;
import lombok.Data;

import java.util.Map;

@Data
public class ErrorResponse {

    private CustomException exception;

    public ErrorResponse(Error error, Map<String, String> parameters) {
        error.getException().setParameters(parameters);
        this.exception = error.getException();
    }

    public ErrorResponse(Error error) {
        this.exception = error.getException();
    }

    public static ErrorResponse generate(Error error, Map<String, String> parameters) {
        return new ErrorResponse(error, parameters);
    }

    public static ErrorResponse generate(Error error)  {
        return new ErrorResponse(error);
    }

    public static <T> Either<ErrorResponse, T> generateEither(Error error) {
        return Either.left(generate(error));
    }

    public static <T> Either<ErrorResponse, T> generateEither(Error error, Map<String, String> parameters) {
        return Either.left(generate(error, parameters));
    }
}
