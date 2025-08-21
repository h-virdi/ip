public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(TaskType.EVENT, description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end;
    }
}
