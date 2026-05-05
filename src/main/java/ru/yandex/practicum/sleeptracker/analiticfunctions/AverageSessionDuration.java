package ru.yandex.practicum.sleeptracker.analiticfunctions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Класс для подсчета средней продолжительность сессии (в минутах)
 */
public class AverageSessionDuration implements Function<List<SleepingSession>, SleepAnalysisResult> {

    /**
     * Метод вычисляет среднюю продолжительность сессии (в минутах).
     * @param sleepingSessions список сессий
     * @return средняя продолжительность сессии (в минутах)
     */
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        Double averageDurationInMinutes = sleepingSessions.stream()
                .map(sleepingSession -> Duration.between(sleepingSession.getStartSleep(),
                        sleepingSession.getEndSleep()))
                .map(Duration::toMinutes)
                .collect(Collectors.averagingDouble(Long::longValue));

            return new SleepAnalysisResult(averageDurationInMinutes.longValue(),
                    "Средняя продолжительность сессии за представленный период (в минутах) :" +
                            averageDurationInMinutes.longValue());
    }
}
