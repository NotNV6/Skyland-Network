package rip.skyland.commons.module;

import lombok.Data;

@Data
public abstract class Module {

    private boolean enabled;
    private long lastTimeStarted;

    /**
     * Called upon start of the module
     */
    public void enable() {
        this.enabled = true;
        this.lastTimeStarted = System.currentTimeMillis();
    }

    /**
     * Called upon disable of the module
     */
    public void disable() {
        this.enabled = false;
    }

}
