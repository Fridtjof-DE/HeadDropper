package tk.fridtjof.headdropper;

import org.bukkit.plugin.java.JavaPlugin;
import tk.fridtjof.puddingapi.bukkit.utils.Metrics;
import tk.fridtjof.puddingapi.bukkit.utils.UpdateChecker;

public final class HeadDropper extends JavaPlugin {

    public static HeadDropper instance;

    public HeadDropper() {
        instance = this;
    }

    public static HeadDropper getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        new UpdateChecker(this, 1234, "headdropper.update");
        new Metrics(this, 123);
        Config.loadConfig();
        getServer().getPluginManager().registerEvents(new EventManager(), this);
    }

    @Override
    public void onDisable() {
    }
}
