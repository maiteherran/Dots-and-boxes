package TPE.Timbiriche.Model.Exceptions;

public class edgeAlreadyTaken extends Exception{
    private static final String MESSAGE = "Edge already taken";
    public edgeAlreadyTaken(){
        super(MESSAGE);
    }
}

