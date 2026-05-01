package ru.yandex.practicum.sleeptracker;

import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

/**
 * Класс предназначен для загрузки лог-файла
 */
public class SleepLogFileLoader {

    private final String sleepLogFileName;

    private final Charset charset;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
    public SleepLogFileLoader(String sleepLogFileName, String charsetName) {
        this.sleepLogFileName = sleepLogFileName;
        this.charset = Charset.forName(charsetName);
    }

    public LinkedList<SleepingSession> loadFile() throws IOException {
        LinkedList<SleepingSession> sleepingSessions = new LinkedList<>();
        try (Reader fileReader = new FileReader(sleepLogFileName,charset)) {
            BufferedReader buffer = new BufferedReader(fileReader);
            while (buffer.ready()) {
                String row = buffer.readLine();
                sleepingSessions.add(convertToSleepSession(row));
            }
//            if (!dictionary.getWords().isEmpty()) {
//                logFile.println("Загрузка словаря успешно завершена. Загружено " + dictionary.size() + " слов");
//            } else {
//                throw new WordleGameExceptions("Ошибка при загрузке словаря. Словарь пуст, ни одно " +
//                        "слово не загружено.", logFile);
//            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Исходный файл словаря " + sleepLogFileName + " не найден. " +
                    "Загрузка прервана!");
        }
        return sleepingSessions;
    }

    private SleepingSession convertToSleepSession(String row) {
//        01.10.25 23:15;02.10.25 07:30;GOOD
//        System.out.println(row.substring(0, row.indexOf(";")));
//        System.out.println(row.substring(row.indexOf(";")+1, row.lastIndexOf(";")));
//        System.out.println(row.substring(row.lastIndexOf(";")+1));
        LocalDateTime startSleep = LocalDateTime.parse(row.substring(0, row.indexOf(";")), formatter);
        LocalDateTime endSleep = LocalDateTime.parse(row.substring(row.indexOf(";")+1, row.lastIndexOf(";"))
                , formatter);
        String quality = row.substring(row.lastIndexOf(";")+1);
        SleepQuality sleepQuality = null;
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
                System.out.println("ERROR"); //TODO
        }
        return new SleepingSession(startSleep, endSleep, sleepQuality);
    }
}
