package pl.sebaszczen.domain;

public class InactiveException extends Throwable {
    public InactiveException(){
        super("Please avtivate your account first");
    }
}
