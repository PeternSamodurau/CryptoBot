package by.spvrent.botcommand.controller;

import by.spvrent.botcommand.model.BotCommand;
import by.spvrent.botcommand.service.CommandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/commands")
public class CommandController {

    @GetMapping
    public List<BotCommand> getCommands() {
        return Arrays.stream(CommandService.values())
                .map(commandEnum -> new BotCommand(commandEnum.getCommand(), commandEnum.getDescription()))
                .collect(Collectors.toList());
    }
}
