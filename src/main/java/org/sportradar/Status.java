package org.sportradar;

public enum Status {
    GREEN("Green"),
    YELLOW("Yellow"),
    BLACK("Black");

    private final String label;

    Status(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
