import java.util.ArrayList;
import java.util.List;

public class ModifyPrice {
    private Command command;

    public ModifyPrice() {
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void invoke(){
        this.command.executeCommand();
    }
}
