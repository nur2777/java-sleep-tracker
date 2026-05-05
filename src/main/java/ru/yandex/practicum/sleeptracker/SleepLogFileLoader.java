package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.exceptions.SleepTrackerExceptions;

import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Класс предназначен для загрузки лог-файла с сессиями сна
 */
public class SleepLogFileLoader {
    /**
     * Имя лог-файла с данными
     */
    private final String sleepLogFileName;
    /**
     * Кодировка файла
     */
    private final Charset charset;
    /**
     * Формат даты и времени начала и конце сессии сна
     */
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public SleepLogFileLoader(String sleepLogFileName, String charsetName) {
        this.sleepLogFileName = sleepLogFileName;
        this.charset = Charset.forName(charsetName);
    }

    /** Метод загружает лог-файл с данными
     * @return возвращает список сессий
     */
    public LinkedList<SleepingSession> loadFile() throws IOException, SleepTrackerExceptions {
        LinkedList<SleepingSession> sleepingSessions;
        try (Reader fileReader = new FileReader(sleepLogFileName,charset)) {
            sleepingSessions = new BufferedReader(fileReader)
                    .lines()
                    .map(this::convertToSleepSession)
                    .collect(Collectors.toCollection(LinkedList::new));

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Исходный файл словаря " + sleepLogFileName + " не найден. " +
                    "Загрузка прервана!");
        }
        return sleepingSessions;
    }

    /** Метод конверирует строку из лог-файла в объект сессии сна
     * @param row строка из лог-файла
     * @return объект сессии сна
     */
    private SleepingSession convertToSleepSession(String row) throws SleepTrackerExceptions {
        LocalDateTime startSleep = LocalDateTime.parse(row.substring(0, row.indexOf(";")), formatter);
        LocalDateTime endSleep = LocalDateTime.parse(row.substring(row.indexOf(";") + 1, row.lastIndexOf(";")),
                formatter);
        String quality = row.substring(row.lastIndexOf(";") + 1);
        SleepQuality sleepQuality;

        switch (quality) {
            case "GOOD":
                sleepQuality = SleepQuality.GOOD;
                break;
            case "NORMAL":
                sleepQuality = SleepQuality.NORMAL;
                break;
            case "BAD":
                sleepQuality = SleepQuality.BAD;
                break;
            default:
                throw new SleepTrackerExceptions("В лог-файле сна для сессии c " + startSleep.toString()
                        + " по " + endSleep.toString() + " указан неверный тип качества сна");
        }
        return new SleepingSession(startSleep, endSleep, sleepQuality);
    }
}
