package by.spvrent.botinit.configuration;

import by.spvrent.botcommand.model.BotCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class CryptoBot extends TelegramLongPollingBot {

    private final BotProperties botProperties;
    private final RestTemplate restTemplate;

    public CryptoBot(BotProperties botProperties, RestTemplate restTemplate) {
        this.botProperties = botProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public String getBotUsername() {
        return botProperties.getBotName();
    }

    @Override
    public String getBotToken() {
        return botProperties.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageText.equals("/start")) {
                startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
            } else {
                sendMessage(chatId, "Sorry, command was not recognized", null);
            }
        }
    }

    private void startCommandReceived(long chatId, String name) {
        String answer = "Hi, " + name + ", nice to meet you!";
        log.info("Replied to user " + name);

        List<BotCommand> commands = new ArrayList<>();
        try {
            // Пытаемся получить команды через HTTP-запрос
            BotCommand[] commandsArray = restTemplate.getForObject("http://api-gateway:8083/api/commands", BotCommand[].class);
            
            if (commandsArray != null) {
                commands = Arrays.asList(commandsArray);
                log.info("Successfully loaded " + commands.size() + " commands.");
            } else {
                log.warn("Received null response from API-GATEWAY for commands.");
            }
        } catch (Exception e) {
            log.error("Error while getting commands from API-GATEWAY: " + e.getMessage());
        }

        // Если команды не загрузились, отправляем сообщение без кнопок
        if (commands.isEmpty()) {
            sendMessage(chatId, answer, null);
            return;
        }

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        
        // НОВАЯ ЛОГИКА ПОСТРОЕНИЯ КЛАВИАТУРЫ
        final int buttonsPerRow = 2; // Поставим по 2 кнопки в ряд
        KeyboardRow currentRow = new KeyboardRow();

        for (BotCommand command : commands) {
            KeyboardButton button = new KeyboardButton();
            String commandText = command.command();
            log.info("Adding button with text: [" + commandText + "]");
            button.setText(commandText);
            
            currentRow.add(button);

            // Если ряд заполнился, добавляем его в список и создаем новый
            if (currentRow.size() >= buttonsPerRow) {
                keyboardRows.add(currentRow);
                currentRow = new KeyboardRow();
            }
        }

        // Добавляем последний ряд, если он не пустой
        if (!currentRow.isEmpty()) {
            keyboardRows.add(currentRow);
        }

        keyboardMarkup.setKeyboard(keyboardRows);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);

        log.info("Final keyboard object: {}", keyboardMarkup);

        sendMessage(chatId, answer, keyboardMarkup);
    }

    private void sendMessage(long chatId, String textToSend, ReplyKeyboardMarkup keyboardMarkup) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        if (keyboardMarkup != null) {
            message.setReplyMarkup(keyboardMarkup);
        }

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }
}
