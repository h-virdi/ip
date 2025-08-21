public class Todo extends Task{
    protected String description;

    public Todo(String description) {
        super(TaskType.TODO, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
