import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class Tests {

    @Test
    public void testSameDayCompletion() {
        DueDate test1 = new DueDate(LocalDateTime.of(2024, 11, 14, 9, 0),4);
        LocalDateTime expectedDueDate = LocalDateTime.of(2024, 11, 14, 13, 0);

        assertEquals(expectedDueDate,test1.calculate());
    }

    @Test
    public void testSameDayExactMinuteCompletion() {
        DueDate test1 = new DueDate(LocalDateTime.of(2024, 11, 14, 9, 14),4);
        LocalDateTime expectedDueDate = LocalDateTime.of(2024, 11, 14, 13, 14);

        assertEquals(expectedDueDate, test1.calculate());
    }

    @Test
    public void testNextDayCompletion() {
        DueDate test1 = new DueDate(LocalDateTime.of(2024, 11, 14, 9, 0),12);
        LocalDateTime expectedDueDate = LocalDateTime.of(2024, 11, 15, 13, 0);

        assertEquals(expectedDueDate, test1.calculate());
    }

    @Test
    public void testTwoDayCompletion() {
        DueDate test1 = new DueDate(LocalDateTime.of(2024, 11, 12, 9, 0),17);
        LocalDateTime expectedDueDate = LocalDateTime.of(2024, 11, 14, 10, 0);

        assertEquals(expectedDueDate, test1.calculate());
    }

    @Test
    public void testWeekendSkip() {
        DueDate test1 = new DueDate(LocalDateTime.of(2024, 11, 14, 9, 0),20);
        LocalDateTime expectedDueDate = LocalDateTime.of(2024, 11, 18, 13, 0);

        assertEquals(expectedDueDate, test1.calculate());
    }

    @Test
    public void testMultipleDayWithWeekendSkip() {
        DueDate test1 = new DueDate(LocalDateTime.of(2024, 11, 14, 10, 0),18);
        LocalDateTime expectedDueDate = LocalDateTime.of(2024, 11, 18, 12, 0);

        assertEquals(expectedDueDate, test1.calculate());
    }

    @Test
    public void testExactEndOfDayCompletion() {
        DueDate test1 = new DueDate(LocalDateTime.of(2024, 11, 14, 14, 0),3);
        LocalDateTime expectedDueDate = LocalDateTime.of(2024, 11, 14, 17, 0);

        assertEquals(expectedDueDate, test1.calculate());
    }

}
