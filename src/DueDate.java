import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class DueDate {

    private LocalDateTime submitDate;
    private int turnAround;

    private LocalTime WORKDAY_START = LocalTime.of(9, 0);
    private LocalTime WORKDAY_END = LocalTime.of(17, 0);

    public DueDate(LocalDateTime submitDate, int turnAround){
        this.submitDate = submitDate;
        this.turnAround = turnAround;
    }

    /**
     * Calculates the time (year/month/day|hour:minute) by which the task must be completed
     * @return Returns the date (year/month/day|hour:minute)
     */

    public LocalDateTime calculate(){

        LocalDateTime current_date = submitDate;
        int remaining_minutes = turnAround*60;

        while(remaining_minutes >0){
            if(isWorkingDay(current_date)){
                LocalDateTime endOfDay = current_date.with(WORKDAY_END);
                if(current_date.isBefore(endOfDay)){

                    long minutesLeft = Math.min(ChronoUnit.MINUTES.between(current_date, endOfDay), remaining_minutes);
                    current_date = current_date.plusMinutes(minutesLeft);
                    remaining_minutes -= minutesLeft;
                }

                if (remaining_minutes > 0) {
                    current_date = goToNextWorkingDay(current_date);
                    current_date = current_date.with(WORKDAY_START);
                }
            }else{
                current_date = goToNextWorkingDay(current_date);
                current_date = current_date.with(WORKDAY_START);
            }
        }
        return current_date;
    }

    /**
     * Checks if whether it's a working day or not
     * @param dateTime - The time when the problem has been submitted
     * @return Return a true or false value which defines whether it's a working day or not
     */

    private boolean isWorkingDay(LocalDateTime dateTime) {
        int dayOfWeek = dateTime.getDayOfWeek().getValue();
        return dayOfWeek >= 1 && dayOfWeek <= 5;
    }

    /**
     * Checks if the next working day is the next day or monday.
     * @param dateTime - Defines the current day
     * @return Returns the next day or monday
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