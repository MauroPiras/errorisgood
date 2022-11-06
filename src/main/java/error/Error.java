package error;

import error.exception.CustomException;

public interface Error {
    CustomException getException();
}
