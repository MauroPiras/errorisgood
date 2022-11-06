package error.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionMessage {
    private int code;
    private String message;
    private Map<String, String> parameters;
}
