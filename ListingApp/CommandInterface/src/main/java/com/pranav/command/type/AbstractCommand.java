package com.pranav.command.type;

import com.pranav.command.service.ICommandService;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public abstract  class AbstractCommand implements ICommand {
    private String[] commandArray;
    private ICommandService service;

    public AbstractCommand(ICommandService service){
        this.service = service;
    }
    @Override
    public boolean isValid(String[]  commandArray) {
        if(!Objects.isNull(commandArray)
                && commandArray.length == getCommandSize()
                && commandArray[0].equals(getName()))
            return true;
        return false;
    }


    @Override
    public void execute() {
        service.execute(this);
    }
}
