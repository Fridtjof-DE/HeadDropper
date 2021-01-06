package tk.fridtjof.headdropper;

public class Config {

    static HeadDropper plugin = HeadDropper.getInstance();

    public static void loadConfig() {

        plugin.reloadConfig();
        plugin.getConfig().options().header("This is the main configuration file - Read the wiki for help!");

        plugin.getConfig().addDefault("force_drop_player_heads", true);

        doStuff("Panda", "IGNORE_THIS");

        doStuff("Player", "IGNORE_THIS");
        doStuff("Squid", "MHF_Squid");
        doStuff("Blaze", "MHF_Blaze");
        doStuff("CaveSpider", "MHF_CaveSpider");
        doStuff("Chicken", "MHF_Chicken");
        doStuff("Cow", "MHF_Cow");
        doStuff("Creeper", "MHF_Creeper");
        doStuff("Enderman", "MHF_Enderman");
        doStuff("Ghast", "MHF_Ghast");
        doStuff("IronGolem", "MHF_Golem");
        doStuff("SnowGolem", "MHF_Pumpkin");
        doStuff("MagmaSlime", "MHF_LavaSlime");
        doStuff("MushroomCow", "MHF_MushroomCow");
        doStuff("Ocelot", "MHF_Ocelot");
        doStuff("Pig", "MHF_Pig");
        doStuff("PigZombie", "MHF_PigZombie");
        doStuff("Sheep", "MHF_Sheep");
        doStuff("Skeleton", "MHF_Skeleton");
        doStuff("Slime", "MHF_Slime");
        doStuff("Spider", "MHF_Spider");
        doStuff("Villager", "MHF_Villager");
        doStuff("WitherSkeleton", "MHF_WSkeleton");
        doStuff("Zombie", "MHF_Zombie");

        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
        Log.info("Successfully (re)loaded config.yml");
    }

    private static void doStuff(String id, String skullOwner) {
        plugin.getConfig().addDefault(id + ".drop", true);
        plugin.getConfig().addDefault(id + ".drop_only_if_killed_by_player", true);
        plugin.getConfig().addDefault(id + ".chance", 100);
        plugin.getConfig().addDefault(id + ".skull_owner", skullOwner);
        if(id.equalsIgnoreCase("Player")) {
            plugin.getConfig().addDefault(id + ".display_name", "§e%player%'s Head");
        } else {
            plugin.getConfig().addDefault(id + ".display_name", id + " Head");
        }
    }
}
