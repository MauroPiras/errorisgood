package it.mauropiras.errorisgood.error;

import it.mauropiras.errorisgood.exception.CustomException;

public interface Error {
    CustomException getException();
}
