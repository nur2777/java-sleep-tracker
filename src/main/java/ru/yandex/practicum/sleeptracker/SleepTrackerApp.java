package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.analiticfunctions.*;
import ru.yandex.practicum.sleeptracker.exceptions.SleepTrackerExceptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {
    /**
     * Кодировка лог-файла сна
     */
    private static final String charset = "UTF8";

    /**
     * Список сессий сна
     */
    private static List<SleepingSession> sleepingSessionList = new LinkedList<>();

    /**
     * Список функций для выполнения
     */
    private static final List<Function<List<SleepingSession>, SleepAnalysisResult>> functions = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в Sleep Tracker Analyzer!");
        if (!checkArgs(args)) {
            System.out.println("Приложение завершило работу!");
            return;
        }
        final String sleepLogFile = args[0];
        SleepLogFileLoader sleepLogFileLoader = new SleepLogFileLoader(sleepLogFile,charset);

        try {
            sleepingSessionList = sleepLogFileLoader.loadFile();
            if (sleepingSessionList.isEmpty()) {
                System.out.println("Не удалось получить данные, список сессий пуст. Дальнейший анализ невозможен.");
            } else {
                functions.add(new SessionsCount());
                functions.add(new MinimumSessionDuration());
                functions.add(new MaximumSessionDuration());
                functions.add(new AverageSessionDuration());
                functions.add(new BadSessionCount());
                functions.add(new CountSleeplessNights());
                functions.add(new UserChronotypeAnalise());

                List<SleepAnalysisResult> results = functions.stream()
                        .map(func -> func.apply(sleepingSessionList))
                        .peek(sleepAnalysisResult ->
                                System.out.println(sleepAnalysisResult.getResultDescription()))
                        .toList();
            }
        } catch (IOException | SleepTrackerExceptions e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Программа завершена.");
        }

    }

    /** Метод проверяет входной параметр приложения
     * @param args массив входных аргументов
     * @return true - если аргументы корректны, false - если неправильные
     */
    private static boolean checkArgs(String[] args) {
        if (args.length == 0) {
            System.out.println("Внимание! Не заполнен обязательный параметр. " +
                    "В параметре запуска приложения нужно указать путь к файлу с логом сна!");
            return false;
        } else if (args[0].isEmpty() || args[0].isBlank()) {
            System.out.println("Внимание! Не верное значение обязательного параметра. " +
                    "В параметре запуска приложения нужно указать путь к файлу с логом сна!");
            return false;
        } else {
            return true;
        }

    }
}