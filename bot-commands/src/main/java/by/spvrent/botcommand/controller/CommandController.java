package by.spvrent.botcommand.controller;

import by.spvrent.botcommand.model.BotCommand;
import by.spvrent.botcommand.service.CommandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/commands")
public class CommandController {

    private final CommandService commandService;

    public CommandController(CommandService commandService) {
        this.commandService = commandService;
    }

    @GetMapping
    public List<BotCommand> getCommands() {
        return commandService.getCommands();
    }
}
