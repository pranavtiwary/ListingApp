package com.pranav.command.type;

import com.pranav.command.service.ICommandService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public abstract  class AbstractCommand implements ICommand {
    private ICommandService service;

    public AbstractCommand(ICommandService service){
        this.service = service;
    }

    @Override
    public boolean isValid(final List<String> commandArray) {
        if(!CollectionUtils.isEmpty(commandArray)
                && commandArray.size() == getCommandSize()
                && commandArray.get(0).equals(getName()))
            return true;
        return false;
    }


    @Override
    public void execute() {
        service.execute(this);
    }
}
