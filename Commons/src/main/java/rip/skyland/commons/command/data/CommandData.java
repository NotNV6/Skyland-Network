package rip.skyland.commons.command.data;

import lombok.Data;
import rip.skyland.commons.command.annotation.Command;
import rip.skyland.commons.command.annotation.Subcommand;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Data
public class CommandData {

    private Method method;

    private Object commandObject;
    private Command command;
    private List<SubcommandData> subcommands = new ArrayList<>();

    public CommandData(Object commandObject, Method method) {
        this.commandObject = commandObject;
        this.method = method;
        this.command = method.getAnnotation(Command.class);
    }

    public boolean isParentOfSubCommand(Subcommand subcommand) {
        return subcommand.parentLabel().equalsIgnoreCase(command.label());
    }

}
