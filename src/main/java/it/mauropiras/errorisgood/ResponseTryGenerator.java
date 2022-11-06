package it.mauropiras.errorisgood;

import it.mauropiras.errorisgood.error.ErrorResponse;
import it.mauropiras.errorisgood.exception.CustomException;
import io.vavr.CheckedFunction1;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.function.Function;

public class ResponseTryGenerator {

    public static <S, R> ResponseEntity<R> generateResponse(
            Either<ErrorResponse, S> either,
            CheckedFunction1<S, R> function1) throws CustomException {
        return ResponseEntity.ok(
                either.map(Try::success)
                        .getOrElseThrow(ErrorResponse::getException)
                        .mapTry(function1)
                        .get());
    }

    public static ResponseEntity<OutputMessage> generateResponse(
            Either<ErrorResponse, OutputMessage> either) throws CustomException {

        return either
                .map(outputMessage ->
                        ResponseEntity.status(outputMessage.getHttpStatus()).body(outputMessage))
                .getOrElseThrow(ErrorResponse::getException);
    }

    public static <R> ResponseEntity<R> generateSimpleResponse(
            Either<ErrorResponse, R> either) throws CustomException {
        return either.map(ResponseEntity::ok).getOrElseThrow(ErrorResponse::getException);
    }

    public static <S, R> ResponseEntity<Page<R>> generatePageResponse(Page<S> page, Function<S, R> function) {
        return ResponseEntity.ok(page.map(function));
    }

    public static ResponseEntity<OutputMessage> generateOutputMessage(OutputMessage outputMessage) {
        return ResponseEntity.status(outputMessage.getHttpStatus()).body(outputMessage);
    }
}
