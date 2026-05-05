package ru.yandex.practicum.sleeptracker.analiticfunctions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Класс для подсчета максимальной продолжительности сессии (в минутах)
 */
public class MaximumSessionDuration implements Function<List<SleepingSession>, SleepAnalysisResult> {

    /**
     * Метод вычисляет максимальную продолжительность сессии (в минутах).
     * @param sleepingSessions список сессий
     * @return максимальную продолжительность сессии (в минутах)
     */
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        Optional<Long> maxDurationInMinutes = sleepingSessions.stream()
                .map(sleepingSession -> Duration.between(sleepingSession.getStartSleep(),
                        sleepingSession.getEndSleep()))
                .map(Duration::toMinutes)
                .max(Long::compare);

        if (maxDurationInMinutes.isEmpty()) {
            return new SleepAnalysisResult(0L,
                    "Не удалось определить максимальную сессию!");
        } else {
            return new SleepAnalysisResult(maxDurationInMinutes.get(),
                    "Максимальная сессия сна за представленный период (в минутах) :" +
                            maxDurationInMinutes.get());
        }
    }
}
