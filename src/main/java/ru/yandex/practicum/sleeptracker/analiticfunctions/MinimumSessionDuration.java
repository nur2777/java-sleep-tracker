package ru.yandex.practicum.sleeptracker.analiticfunctions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Класс для подсчета минимальной продолжительности сессии (в минутах)
 */
public class MinimumSessionDuration implements Function<List<SleepingSession>, SleepAnalysisResult> {

    /**
     * Метод вычисляет минимальную продолжительность сессии (в минутах).
     * @param sleepingSessions список сессий
     * @return минимальную продолжительность сессии (в минутах)
     */
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {

        Optional<Long> minDurationInMinutes = sleepingSessions.stream()
                .map(sleepingSession -> Duration.between(sleepingSession.getStartSleep(),
                        sleepingSession.getEndSleep()))
                .map(Duration::toMinutes)
                .min(Long::compare);

        if (minDurationInMinutes.isEmpty()) {
            return new SleepAnalysisResult(null,
                    "Не удалось определить минимальную сессию!");
        } else {
            return new SleepAnalysisResult(minDurationInMinutes.get(),
                    "Минимальная сессия сна за представленный период (в минутах) :" +
                            minDurationInMinutes.get());
        }
    }


}
