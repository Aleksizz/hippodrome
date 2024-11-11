import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {
    @Test
    @DisplayName("Check Exception with null  Arg")
    void NullArgumentIAEtest() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new Hippodrome(null);
                }
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    @DisplayName("Check Exception with Empty List in  Arg")
    void EmptyListArgumentIAEtest() {
        List<Horse> testHorses = new ArrayList<>();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new Hippodrome(testHorses);
                }
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    @DisplayName("Check get List of Horses")
    void checkGetHorses(){
        List <Horse> testListHorses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            String name = "Horse " + i;
            testListHorses.add(new Horse(name, i, i));
        }

        Hippodrome hippodrome = new Hippodrome(testListHorses);
        assertEquals(testListHorses, hippodrome.getHorses());
    }

    @Test
    @DisplayName("check using move()")
    void checkUsingMoveTest(){
        List<Horse> mockedList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
           mockedList.add(mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(mockedList);
        hippodrome.move();
        for (Horse horse : mockedList){
            verify(horse).move();
        }
    }

    @Test
    @DisplayName("check return the horse with the most result")
    void checkReturnHorseWithGreatestResult(){
        List <Horse> testListHorses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            String name = "Horse " + i;
            testListHorses.add(new Horse(name, i, i));
        }
        assertEquals(testListHorses.get(testListHorses.size()-1), new Hippodrome(testListHorses).getWinner());
    }
}
