package euteam.cleanmachine.model.enums;

public enum State {
    IDLE("Idle"), AUTHENTICATED("Authenticated"), OUTOFORDER("Out of order"), RUNNING("Running"), LOCKED("Locked");

    private String name;

    State(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
