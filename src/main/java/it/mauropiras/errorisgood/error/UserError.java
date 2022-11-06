package it.mauropiras.errorisgood.error;

import it.mauropiras.errorisgood.exception.CustomException;
import it.mauropiras.errorisgood.exception.NotFoundException;
import it.mauropiras.errorisgood.exception.AlreadyExistsException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserError implements Error {

    USER_EMAIL_NOT_FOUND(new NotFoundException("User with email: {email} not found")),
    USER_USERNAME_NOT_FOUND(new NotFoundException("User with username: {username} not found")),
    USER_UUID_NOT_FOUND(new NotFoundException("User with uuid: {uuid} not found")),
    USER_EMAIL_ALREADY_EXISTS(new AlreadyExistsException("User with this email already exists!")),
    USER_USERNAME_ALREADY_EXISTS(new AlreadyExistsException("User with this username already exists!")),
    ;

    @Getter
    private final CustomException exception;
}
