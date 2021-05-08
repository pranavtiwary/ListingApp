package com.pranav.command.type;

import lombok.Data;

import java.util.Objects;

@Data
public abstract  class AbstractCommand implements ICommand {
    private String[] commandArray;

    @Override
    public boolean isValid(String[]  commandArray) {
        if(!Objects.isNull(commandArray)
                && commandArray.length == getCommandSize()
                && commandArray[0].equals(getName()))
            return true;
        return false;
    }
}
