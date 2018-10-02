package pl.sebaszczen.services;

public class LoginExistException extends Throwable {
    public LoginExistException(final String message) {
        super(message);
    }
}
