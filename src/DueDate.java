

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class DueDate {

    private LocalDateTime date;
    private int turnAround;

    private final LocalTime WORKDAY_START = LocalTime.of(9, 0);
    private final LocalTime WORKDAY_END = LocalTime.of(17, 0);

    public DueDate(LocalDateTime submitDate, int turnAround) {
        date = submitDate;
        this.turnAround = turnAround;
    }

    /**
     * Kiszámítja azt az időpontot (év/hónap/nap|óra:perc), amire végezni kell az adott task-kal
     * @return Visszadja a dátumot (év/hónap/nap|óra:perc)
     */
    public LocalDateTime calculate(){

        LocalDateTime current = date;
        int remaining_minutes = turnAround*60;

        while(remaining_minutes >0){
            if(isWorkingDay(current)){
                LocalDateTime endOfDay = current.with(WORKDAY_END);
                if(current.isBefore(endOfDay)){

                    long minutesLeft = Math.min(ChronoUnit.MINUTES.between(current, endOfDay), remaining_minutes);
                    current = current.plusMinutes(minutesLeft);
                    remaining_minutes -= minutesLeft;
                }

                if (remaining_minutes > 0) {
                    current = goToNextWorkingDay(current);
                    current = current.with(WORKDAY_START);
                }
            }else{
                current = goToNextWorkingDay(current);
                current = current.with(WORKDAY_START);
            }
        }
        return current;
    }

    /**
     *
     * @param dateTime - The time when the problem has been submitted
     * @return Return a true or false value which defines if it is a working day or not
     */

    private boolean isWorkingDay(LocalDateTime dateTime) {
        int dayOfWeek = dateTime.getDayOfWeek().getValue();
        return dayOfWeek >= 1 && dayOfWeek <= 5;
    }

    /**
     *
     * @param dateTime - The time when
     * @return
     */
    private LocalDateTime goToNextWorkingDay(LocalDateTime dateTime) {
        int dayOfWeek = dateTime.getDayOfWeek().getValue();

        if (dayOfWeek == 5) {
            return dateTime.plusDays(3);
        } else {
            return dateTime.plusDays(1);
        }
    }

}