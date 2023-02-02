package userinyerface.waits;

import java.time.Duration;
import java.util.concurrent.TimeoutException;
import java.util.function.BooleanSupplier;

import static aquality.selenium.browser.AqualityServices.getConditionalWait;
import static aquality.selenium.browser.AqualityServices.getLogger;

public class ConditionalWaits {
    private ConditionalWaits() {}

    public static boolean waitForTrue(BooleanSupplier booleanSupplier, WaitDuration waitDuration, String description) {

        Duration secondsDuration = Duration.ofSeconds(waitDuration.getSeconds());
        Duration millisDuration = Duration.ofMillis(waitDuration.getMillis());

        String infoMessage = "conditional wait(" + waitDuration.getSeconds() + "s" + waitDuration.getMillis() + "ms)";
        if (description != null) {
            infoMessage = String.join("", infoMessage, ": ", description);
        }

        getLogger().info(infoMessage);

        try {
            getConditionalWait().waitForTrue(booleanSupplier, secondsDuration, millisDuration, description);
        } catch (TimeoutException e) {
            String timeoutInfoMessage = "failed conditional wait";
            if (description != null) {
                timeoutInfoMessage = String.join("", timeoutInfoMessage, ": ", description);
            }
            getLogger().info(timeoutInfoMessage);

            return false;
        }
        return true;
    }


}
