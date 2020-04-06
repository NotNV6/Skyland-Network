package rip.skyland.commons.module;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class ModuleRegistrar {

    private List<Module> modules = new ArrayList<>();

    /**
     * Constructor for registering a new ModuleRegistrar class
     *
     * @param modules the modules which needs to get registered
     */
    public ModuleRegistrar(Module... modules) {
        this.modules.addAll(Arrays.asList(modules));
        this.modules.forEach(Module::enable);
    }

    /**
     * Find a Module by a Class
     *
     * @param clazz the class
     * @param <T> the type of the module
     * @return the module
     */
    public <T> T findModule(Class<T> clazz) {
        return clazz.cast(this.modules.stream()
                .filter(module -> module.getClass().equals(clazz))
                .findFirst().orElse(null));
    }

    public void registerModule(Module module) {
        this.modules.add(module);

        module.enable();
    }

}
