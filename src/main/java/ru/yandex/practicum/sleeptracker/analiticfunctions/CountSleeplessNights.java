package ru.yandex.practicum.sleeptracker.analiticfunctions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class CountSleeplessNights implements Function<List<SleepingSession>, SleepAnalysisResult> {

    /** Метод возвращающий количество бессонных ночей
     * @param sleepingSessions список сессий сна
     * @return количество бессонных ночей
     */
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        int countSleeplessNights = 0;

        if (!sleepingSessions.isEmpty()) {
            LocalDate firstDayInList = sleepingSessions.getFirst().getStartSleep().toLocalDate();
            LocalDate lastDayInList = sleepingSessions.getLast().getEndSleep().toLocalDate();
            //количество ночей за весь период
            int countNights = Period.between(firstDayInList, lastDayInList).getDays();
            // если первая сессия сна началась до 12, то добавляем ещё одну ночь
            if (sleepingSessions.getFirst().getStartSleep().toLocalTime().isBefore(LocalTime.NOON)) {
                countNights++;
            }
            // если последняя сессия сна началась после 12, то добавляем ещё одну ночь
            if (sleepingSessions.getFirst().getStartSleep().toLocalTime().isAfter(LocalTime.NOON)) {
                countNights++;
            }
            Predicate<SleepingSession> isSleeplessNights = sleepingSession -> {
                // исключаем ночи которые начались в один день и закончились в другой
                if (sleepingSession.getStartSleep().toLocalDate().isBefore(sleepingSession.getEndSleep().toLocalDate())) {
                    return true;
                    // проверяем начался ли сон до 6 утра
                } else if (sleepingSession.getStartSleep().toLocalTime().isBefore(LocalTime.of(6, 0))) {
                    return true;
                } else {
                    return false;
                }
            };
            // создаём список правильных ночей
            List<SleepingSession> sleepyNights = sleepingSessions.stream()
                    .filter(isSleeplessNights)
                    .toList();
            // из общего количества ночей вычитаем количество правильных ночей
            countSleeplessNights = countNights - sleepyNights.size();
            return new SleepAnalysisResult((long) countSleeplessNights,
                    "Количество бессонных ночей :" + countSleeplessNights);
        } else {
            return new SleepAnalysisResult(-1L,
                    "Список сессий сна пуст. Подсчёт не выполнен.");
        }
    }

}
