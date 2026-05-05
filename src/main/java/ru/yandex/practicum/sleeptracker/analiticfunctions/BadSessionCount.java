package ru.yandex.practicum.sleeptracker.analiticfunctions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

/**
 * Класс для подсчета количества сессий сна с плохим качеством сна
 */
public class BadSessionCount implements Function<List<SleepingSession>, SleepAnalysisResult> {
    /**
     * Метод возвращает количество сессий сна с плохим качеством сна.
     * @param sleepingSessions список сессий
     * @return количество сессий сна с плохим качеством
     */
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long badSessionCount = sleepingSessions.stream()
                .filter(session -> session.getQuality() == SleepQuality.BAD)
                .count();
        return new SleepAnalysisResult(badSessionCount,
                "Количество сессий сна с плохим качеством сна :" + badSessionCount);
    }
}
