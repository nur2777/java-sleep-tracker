package ru.yandex.practicum.sleeptracker.analiticfunctions;

import ru.yandex.practicum.sleeptracker.Chronotype;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Класс для классификации пользователя по хронотипу
 */
public class UserChronotypeAnalise implements Function<List<SleepingSession>, SleepAnalysisResult> {

    /**
     * Предикат для определения типа Сова
     */
    private final Predicate<SleepingSession> isOwl = sleepingSession -> {
        LocalTime startSleepTime = sleepingSession.getStartSleep().toLocalTime();
        LocalTime endSleepTime = sleepingSession.getEndSleep().toLocalTime();
        if ((startSleepTime.isAfter(LocalTime.of(23,0))
                && endSleepTime.isAfter(LocalTime.of(9,0))
                && sleepingSession.getStartSleep().toLocalDate().isBefore(sleepingSession.getEndSleep().toLocalDate()))
            ||
            (startSleepTime.isBefore(LocalTime.of(6,0))
                && endSleepTime.isAfter(LocalTime.of(9,0))
                && sleepingSession.getStartSleep().toLocalDate().isEqual(sleepingSession.getEndSleep().toLocalDate()))
        ) {
            return true;
        } else {
            return false;
        }
    };

    /**
     * Предикат для определения типа Жаворонок
     */
    private final Predicate<SleepingSession> isLark = sleepingSession -> {
        LocalTime startSleepTime = sleepingSession.getStartSleep().toLocalTime();
        LocalTime endSleepTime = sleepingSession.getEndSleep().toLocalTime();
        if (startSleepTime.isBefore(LocalTime.of(22,0))
            && endSleepTime.isBefore(LocalTime.of(7,0))
            && sleepingSession.getStartSleep().toLocalDate().isBefore(sleepingSession.getEndSleep().toLocalDate())
        ) {
            return true;
        } else {
            return false;
        }
    };

    /**
     * Предикат для определения типа голубь
     */
    private final Predicate<SleepingSession> isPigeon = sleepingSession -> {
        LocalTime startSleepTime = sleepingSession.getStartSleep().toLocalTime();
        LocalTime endSleepTime = sleepingSession.getEndSleep().toLocalTime();
        if ((startSleepTime.isBefore(LocalTime.of(23,0))
             && endSleepTime.isAfter(LocalTime.of(9,0))
             && sleepingSession.getStartSleep().toLocalDate().isBefore(sleepingSession.getEndSleep().toLocalDate()))
           ||
            (startSleepTime.isAfter(LocalTime.of(23,0))
             && endSleepTime.isBefore(LocalTime.of(9,0)))
           ||
            (startSleepTime.isBefore(LocalTime.of(22,0))
             && endSleepTime.isAfter(LocalTime.of(7,0))
             && sleepingSession.getStartSleep().toLocalDate().isBefore(sleepingSession.getEndSleep().toLocalDate()))
           ||
            (startSleepTime.isAfter(LocalTime.of(22,0))
             && endSleepTime.isBefore(LocalTime.of(7,0)))
        ) {
            return true;
        } else {
            return false;
        }
    };

    /** Метод определения хронотипа пользователя
     * @param sleepingSessions список сессий сна
     * @return хронотип пользователя
     */
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        Chronotype chronotype;
        if (!sleepingSessions.isEmpty()) {
            long isOwlNightCounts = sleepingSessions.stream()
                    .filter(isOwl)
                    .count();
            long isLarkNightCounts = sleepingSessions.stream()
                    .filter(isLark)
                    .count();
            long isPigeonCounts = sleepingSessions.stream()
                    .filter(isPigeon)
                    .count();
            if ((isOwlNightCounts > isLarkNightCounts) && (isOwlNightCounts > isPigeonCounts)) {
                chronotype = Chronotype.OWL;
            } else if ((isOwlNightCounts < isLarkNightCounts) && (isLarkNightCounts > isPigeonCounts)) {
                chronotype = Chronotype.LARK;
            } else {
                chronotype = Chronotype.PIGEON;
            }
            return new SleepAnalysisResult(chronotype);
        } else {
            return new SleepAnalysisResult(-1L,"Список сессий пуст, анализ невозможен!");
        }
    }
}
