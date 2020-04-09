package rip.skyland.commons.module;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import rip.skyland.commons.CommonsPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
     * @param <T>   the type of the module
     * @return the module
     */
    public <T> T findModule(Class<T> clazz) {
        return clazz.cast(this.modules.stream()
                .filter(module -> module.getClass().equals(clazz))
                .findFirst().orElse(null));
    }

    /**
     * Register a module
     *
     * @param module the module to get registered
     */
    public void registerModule(Module module) {
        this.modules.add(module);

        module.enable();
    }

    /**
     * Register a new listener
     * This method automatically makes a new instance of the listener class.
     *
     * @param listenerClass the listener class
     */
    @SneakyThrows
    public void registerListener(Class<? extends Listener> listenerClass) {
        if (Objects.requireNonNull(Arrays.stream(listenerClass.getConstructors()).findFirst().orElse(null)).getParameterCount() > 1) {
            throw new IllegalArgumentException("that listener requires more parameters than 1");
        }

        this.registerListener(listenerClass.newInstance());
    }

    /**
     * Register a new listener
     *
     * @param listener the listener
     */
    public void registerListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, CommonsPlugin.getInstance());
    }
}