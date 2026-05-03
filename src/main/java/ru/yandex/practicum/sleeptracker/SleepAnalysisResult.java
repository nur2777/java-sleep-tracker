package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult {

    private Long result;
    private String resultDescription;

    public SleepAnalysisResult(Long result, String resultDescription) {
        this.result = result;
        this.resultDescription = resultDescription;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public Long getResult() {
        return result;
    }
}
