package by.spvrent.botcommand.service;

import by.spvrent.botcommand.model.BotCommand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandService {

    public List<BotCommand> getCommands() {
        return List.of(
                new BotCommand("/start", "Приветствие пользователя"),
                new BotCommand("/register", "Регистрация пользователя в системе"),
                new BotCommand("/get_price", "Получение текущего курса криптовалюты"),
                new BotCommand("/subscribe", "Подписка на уведомления об изменении курса"),
                new BotCommand("/unsubscribe", "Отписка от уведомлений"),
                new BotCommand("/my_subscriptions", "Просмотр своих подписок")
        );
    }
}
