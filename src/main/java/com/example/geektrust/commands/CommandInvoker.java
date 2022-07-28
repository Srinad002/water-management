package com.example.geektrust.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandInvoker {

    private final Map<String, ICommand> commandMap;

    public CommandInvoker() {
        commandMap = new HashMap<>();
    }

    public void registerCommands(String commandName, ICommand command){
        commandMap.put(commandName, command);
    }

    public ICommand getCommand(String commandName) {
        return commandMap.get(commandName);
    }

    public void execute(List<String> data) {
        ICommand command = getCommand(data.get(0));
        if (command != null) {
            command.execute(data);
        }
    }

    public Map<String, ICommand> getCommandMap() {
        return this.commandMap;
    }
}
