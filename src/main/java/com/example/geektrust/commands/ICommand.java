package com.example.geektrust.commands;

import java.util.List;

/**
 * Contract for all commands to implement execute
 */
public interface ICommand {

    public void execute(List<String> data);
}
