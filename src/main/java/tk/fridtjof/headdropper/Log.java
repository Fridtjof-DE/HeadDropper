package tk.fridtjof.headdropper;

import org.bukkit.Bukkit;

import java.util.logging.Logger;

public class Log {

    static HeadDropper plugin = HeadDropper.getInstance();

    public static Logger log = Bukkit.getLogger();

    public static void info(String msg) {
        log.info("[" + plugin.getName() + "] " + msg);
    }

    public static void warning(String msg) {
        log.warning("[Yggdrasil] " + msg);
    }
}