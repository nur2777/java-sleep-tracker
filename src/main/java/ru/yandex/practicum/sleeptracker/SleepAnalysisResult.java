package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult {

    private Integer result;
    private String resultDescription;

    public SleepAnalysisResult(Integer result, String resultDescription) {
        this.result = result;
        this.resultDescription = resultDescription;
    }

    public String getResultDescription() {
        return resultDescription;
    }
}
