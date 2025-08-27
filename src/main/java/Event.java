import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    public Event(String description, String start, String end) {
        super(TaskType.EVENT, description);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " +
                end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start.toString() + " | " + end.toString();
    }
}
