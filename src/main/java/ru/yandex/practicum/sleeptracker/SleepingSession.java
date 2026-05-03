package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;

public class SleepingSession {

    private LocalDateTime startSleep;
    private LocalDateTime endSleep;


    private SleepQuality quality;

    public SleepingSession(LocalDateTime startSleep, LocalDateTime endSleep, SleepQuality quality) {
        this.startSleep = startSleep;
        this.endSleep = endSleep;
        this.quality = quality;
    }

    public LocalDateTime getStartSleep() {
        return startSleep;
    }

    public LocalDateTime getEndSleep() {
        return endSleep;
    }

    public SleepQuality getQuality() {
        return quality;
    }

    @Override
    public String toString() {
        return "SleepingSession{" +
                "startSleep=" + startSleep +
                ", endSleep=" + endSleep +
                ", quality=" + quality +
                '}';
    }
}
