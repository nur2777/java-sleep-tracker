package ru.yandex.practicum.sleeptracker.analiticfunctions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

/**
 * Класс для подсчёта количества сессий сна
 */
public class SessionsCount implements Function<List<SleepingSession>, SleepAnalysisResult> {

    /**
     * Метод возвращает количество сессий сна за представленный период.
     *
     * @param sleepingSessions список сессий
     * @return количество сессий сна
     */
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        return new SleepAnalysisResult((long) sleepingSessions.size(),
                "Количество сессий сна за представленный период :" + sleepingSessions.size());
    }
}
