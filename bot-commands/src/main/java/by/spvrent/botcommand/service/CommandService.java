package by.spvrent.botcommand.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommandService {

    START("/start", "Приветствие пользователя"),
    REGISTER("/register", "Регистрация пользователя в системе"),
    GET_PRICE("/get_price", "Получение текущего курса криптовалюты"),
    SUBSCRIBE("/subscribe", "Подписка на уведомления об изменении курса"),
    UNSUBSCRIBE("/unsubscribe", "Отписка от уведомлений"),
    MY_SUBSCRIPTIONS("/my_subscriptions", "Просмотр своих подписок");

    private final String command;
    private final String description;
}