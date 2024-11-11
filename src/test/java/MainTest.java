import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

class MainTest {

   @Disabled
    @Test
    @DisplayName("Проверка времени выполнения метода main за установленное время 22 сек")
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    void mainDuration22SecTest() throws Exception {
        Main.main(new String[0]);
    }
}
