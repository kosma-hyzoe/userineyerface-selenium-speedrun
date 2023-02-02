package userinyerface.waits;

import lombok.Getter;
import static userinyerface.tests.BaseTest.testData;

public enum WaitDuration {
    LONG("long"),
    MEDIUM("medium"),
    SHORT("short");

    @Getter
    private final int seconds;
    @Getter
    private final int millis;

    WaitDuration(String testDataKey) {
        this.seconds = testData.get("waitDurations").get(testDataKey).get("seconds").asInt();
        this.millis = testData.get("waitDurations").get(testDataKey).get("millis").asInt();
    }
}

