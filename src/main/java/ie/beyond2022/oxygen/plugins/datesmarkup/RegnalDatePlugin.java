package ie.beyond2022.oxygen.plugins.datesmarkup;

import ro.sync.exml.plugin.Plugin;
import ro.sync.exml.plugin.PluginDescriptor;

public class RegnalDatePlugin extends Plugin {

    public static RegnalDatePlugin instance = null;

    public RegnalDatePlugin(PluginDescriptor descriptor) {
        super(descriptor);

        if (instance != null) {
            throw new IllegalStateException("Plugin is already instantiated!");
        }

        instance = this;
    }

    public static RegnalDatePlugin getInstance() {
        return instance;
    }
}
