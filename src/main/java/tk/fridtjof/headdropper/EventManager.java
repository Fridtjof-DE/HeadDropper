package tk.fridtjof.headdropper;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.org.apache.commons.codec.binary.Base64;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.entity.Player;
import tk.fridtjof.puddingapi.bukkit.items.PlayerHead;


import java.lang.reflect.Field;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class EventManager implements Listener {

    static HeadDropper plugin = HeadDropper.getInstance();

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        Player killer = ((LivingEntity) entity).getKiller();

        if(entity instanceof Player) {
            Player player = (Player) entity;
            doStuff(event, killer, "Player", player.getName());
        } /*else if(entity instanceof WitherSkeleton) {
            if(plugin.getConfig().getBoolean("force_drop_player_heads")) {
                doStuff(event, killer, "WitherSkeleton");
            } else {
                doStuff(event, killer, "WitherSkeleton", Material.WITHER_SKELETON_SKULL);
            }
        } else if(entity instanceof MushroomCow) {
            doStuff(event, killer, "MushroomCow");
        } else if(entity instanceof Sheep) {
            doStuff(event, killer, "Sheep");
        } else if(entity instanceof Blaze) {
            doStuff(event, killer, "Blaze");
        } else if(entity instanceof Squid) {
            doStuff(event, killer, "Squid");
        } else if(entity instanceof CaveSpider) {
            doStuff(event, killer, "CaveSpider");
        } else if(entity instanceof Chicken) {
            doStuff(event, killer, "Chicken");
        } else if(entity instanceof Cow) {
            doStuff(event, killer, "Cow");
        } else if(entity instanceof Creeper) {
            if(plugin.getConfig().getBoolean("force_drop_player_heads")) {
                doStuff(event, killer, "Creeper");
            } else {
                doStuff(event, killer, "Creeper", Material.CREEPER_HEAD);
            }
        } else if(entity instanceof Enderman) {
            doStuff(event, killer, "Enderman");
        } else if(entity instanceof Ghast) {
            doStuff(event, killer, "Ghast");
        } else if(entity instanceof IronGolem) {
            doStuff(event, killer, "IronGolem");
        } else if(entity instanceof MagmaCube) {
            doStuff(event, killer, "MagmaCube");
        } else if(entity instanceof Ocelot) {
            doStuff(event, killer, "Ocelot");
        } else if(entity instanceof Pig) {
            doStuff(event, killer, "Pig");
        } else if(entity instanceof PigZombie) {
            doStuff(event, killer, "PigZombie");
        } else if(entity instanceof Sheep) {
            doStuff(event, killer, "Sheep");
        } else if(entity instanceof Skeleton) {
            if(plugin.getConfig().getBoolean("force_drop_player_heads")) {
                doStuff(event, killer, "Skeleton");
            } else {
                doStuff(event, killer, "Skeleton", Material.SKELETON_SKULL);
            }
        } else if(entity instanceof Slime) {
            doStuff(event, killer, "Slime");
        } else if(entity instanceof Spider) {
            doStuff(event, killer, "Spider");
        } else if(entity instanceof Villager) {
            doStuff(event, killer, "Villager");
        }  else if(entity instanceof Zombie) {
            if(plugin.getConfig().getBoolean("force_drop_player_heads")) {
                doStuff(event, killer, "Zombie");
            } else {
                doStuff(event, killer, "Zombie", Material.ZOMBIE_HEAD);
            }
        } else if(entity instanceof Golem) {
            doStuff(event, killer, "SnowGolem");
        } else if(entity instanceof Panda) {
            //event.getDrops().add(getSkullFromWeb("https://textures.minecraft.net/texture/de360e2a1c6b0f48f44212575c7ad83b893803a6fd4c1cc3e0983abaa56af4b9"));
        }*/
    }

    private void doStuff(EntityDeathEvent event, Player killer, String id) {
        if (plugin.getConfig().getBoolean(id + ".drop")) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 100 + 1);
            if(randomNum <= plugin.getConfig().getInt(id + ".chance")) {
                if(plugin.getConfig().getBoolean(id + ".drop_only_if_killed_by_player")) {
                    if(killer != null) {
                        if(killer.hasPermission("headdropper.use")) {
                            event.getDrops().add(PlayerHead.getSkullFromOwner(plugin.getConfig().getString(id + ".skull_owner"), "§r§e" + (plugin.getConfig().getString(id + ".display_name"))));
                        }
                    }
                } else {
                    event.getDrops().add(PlayerHead.getSkullFromOwner(plugin.getConfig().getString(id + ".skull_owner"), "§r§e" + (plugin.getConfig().getString(id + ".display_name"))));
                }
            }
        }
    }

    private void doStuff(EntityDeathEvent event, Player killer, String id, String skullOwner) {
        if (plugin.getConfig().getBoolean(id + ".drop")) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 100 + 1);
            if(randomNum <= plugin.getConfig().getInt(id + ".chance")) {
                if(plugin.getConfig().getBoolean(id + ".drop_only_if_killed_by_player")) {
                    if(killer != null) {
                        if(killer.hasPermission("headdropper.use")) {
                            event.getDrops().add(PlayerHead.getSkullFromOwner(skullOwner, "§r§e" + (plugin.getConfig().getString(id + ".display_name"))));
                        }
                    }
                } else {
                    event.getDrops().add(PlayerHead.getSkullFromOwner(skullOwner, "§r§e" + (plugin.getConfig().getString(id + ".display_name"))));
                }
            }
        }
    }

    private void doStuff(EntityDeathEvent event, Player killer, String id, Material material) {
        if (plugin.getConfig().getBoolean(id + ".drop")) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 100 + 1);
            if(randomNum <= plugin.getConfig().getInt(id + ".chance")) {
                if(plugin.getConfig().getBoolean(id + ".drop_only_if_killed_by_player")) {
                    if(killer != null) {
                        if(killer.hasPermission("headdropper.use")) {
                            event.getDrops().add(getItem(material, "§r§e" + (plugin.getConfig().getString(id + ".display_name"))));
                        }
                    }
                } else {
                    event.getDrops().add(getItem(material, "§r§e" + (plugin.getConfig().getString(id + ".display_name"))));
                }
            }
        }
    }

    private ItemStack getItem(Material material, String displayName) {
        ItemStack itemStack = new ItemStack(material);
        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
        if(!displayName.equals("")) {
            meta.setDisplayName(displayName);
        }
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
