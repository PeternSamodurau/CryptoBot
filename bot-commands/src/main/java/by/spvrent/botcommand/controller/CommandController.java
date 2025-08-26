package by.spvrent.botcommand.controller;

import by.spvrent.botcommand.model.BotCommand;
import by.spvrent.botcommand.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/commands")
@RequiredArgsConstructor
public class CommandController {

    private final CommandService commandService;

    @GetMapping
    public List<BotCommand> getCommands() {
        return commandService.getCommands();
    }
}
