public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    public Task(TaskType type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
    }

    public TaskType getType() {
        return type;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDesc() {
        return this.description;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}


