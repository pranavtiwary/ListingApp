package com.pranav.command.service;

import com.pranav.command.type.AbstractCommand;
import com.pranav.command.type.ICommand;

public interface ICommandService {
    void execute(ICommand abstractCommand);
}
