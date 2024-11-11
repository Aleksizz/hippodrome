import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

class HorseTest {

    @Test
    @DisplayName("Check Exception with null first Arg")
    void firstNullArgumentIAEtest() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                   new Horse(null, 0.1, 0.1);
                }
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Check Exception with empty or blank first Arg")
    @ValueSource(strings = {" ", "", "\t"})
    void firstNullOrBlankArgumentIAEParamsTest(String string){
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new Horse(string, 0.1, 0.1);
                }
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    @DisplayName("Check Exception with negative second Arg")
    void secondNegativeArgTest(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("TestHorse", -1.0 * Math.random(), 0.1)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("Check Exception with negative second Arg")
    void thirdNegativeArgTest(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("TestHorse", 0.1, -1.0 * Math.random())
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("Check return first Arg")
    void getNameTest(){
        assertEquals("TestHorse", new Horse("TestHorse", 0.1, 0.1).getName());
    }

    @Test
    @DisplayName("Check return second Arg")
    void getSpeedTest(){
        assertEquals(0.1, new Horse("TestHorse", 0.1, 0.2).getSpeed());
    }

    @Test
    @DisplayName("Check return third Arg")
    void getDistanceTest(){
        assertEquals(0.2, new Horse("TestHorse", 0.1, 0.2).getDistance());
    }

    @Test
    @DisplayName("Check return zero without third Arg")
    void getZeroDistanceTest(){
        assertEquals(0, new Horse("TestHorse", 0.1).getDistance());
    }

    @DisplayName("Check getRandonDouble in move()")
    @ParameterizedTest
    @ValueSource(doubles = {1.3, 0.7, 0.9})
    void checkGetRandomDoubleIntoMoveTest(double randomMockValue){
        try(MockedStatic<Horse> mocked = mockStatic(Horse.class)){
            mocked.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomMockValue);
            Horse testHorse = new Horse("TestHorse", 0.2, 0.1);
            testHorse.move();
            mocked.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            assertEquals(0.1 + 0.2*randomMockValue, testHorse.getDistance());
        }
    }


}
