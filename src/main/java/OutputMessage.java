import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OutputMessage {
    
    private HttpStatus httpStatus;
    private String message;
    private String data;


    public static OutputMessage returnSuccessOutput(String message){
        return OutputMessage.builder().httpStatus(HttpStatus.OK).message(message).build();
    }

    public static OutputMessage returnSuccessOutput(String message, String data){
        return OutputMessage.builder().httpStatus(HttpStatus.OK).message(message).data(data).build();
    }

    public static OutputMessage returnFailureOutput(HttpStatus status, String message){
        return OutputMessage.builder().httpStatus(status).message(message).build();
    }

    public static OutputMessage returnFailureOutput(HttpStatus status){
        return OutputMessage.builder().httpStatus(status).message(MessageConstants.INTERNAL_ERROR).build();
    }

}
