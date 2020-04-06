package rip.skyland.ranks.chat.format;

import lombok.Data;

@Data
public class FormatAddition {

    private final String addition;
    private final AdditionPosition position;

    public FormatAddition(String addition, AdditionPosition position) {
        this.addition = addition;
        this.position = position;
    }

    public enum AdditionPosition {BEFORE_PREFIX, AFTER_PREFIX, AFTER_NAME}
}