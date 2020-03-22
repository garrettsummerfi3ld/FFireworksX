
package io.github.garrettsummerfield.ffireworksx;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.Random;
public class EventsListener implements Listener { Main configGetter;

    public EventsListener(Main main) {

        this.configGetter = main;

    }

    @EventHandler
    public void onSnowBallThrow(PlayerInteractEvent e) {

        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (p.hasPermission("flashingfireworks.throw")) {
                if (p.getItemInHand().getType() == Material.SNOWBALL)
                {
                    if (p.getItemInHand().getItemMeta().hasDisplayName()) {
                        if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§bFlashing Firework Snowball")) {

                            Projectile projectile = p.launchProjectile(Snowball.class);

                            Throw(0, (Entity) projectile);

                            e.setCancelled(true);

                            if (p.getGameMode() != GameMode.CREATIVE) {

                                ItemStack is = new ItemStack(Material.SNOWBALL, 1);

                                ItemMeta im = is.getItemMeta();

                                im.setDisplayName("§bFlashing Firework Snowball");

                                is.setItemMeta(im);

                                p.getInventory().removeItem(new ItemStack[]{is});

                                p.updateInventory();

                            }

                        }

                    }

                }

            }

        }

    }


    public void Throw(int i, final Entity l) {

        for (int b = 0; b <= 250; b++) {

            i = b;

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.configGetter, new Runnable() {
                public void run() {

                    if (!l.isDead())
                    {
                        Random r = new Random();
                        Color c = Color.fromBGR(r.nextInt(256), r.nextInt(256), r.nextInt(256));

                        Random r1 = new Random();

                        Color c1 = Color.fromBGR(r1.nextInt(256), r1.nextInt(256), r1.nextInt(256));

                        FireworkEffect fireworkEffect = FireworkEffect.builder().flicker(false).trail(true).with(FireworkEffect.Type.BURST).withColor(c).withFade(c1).build();

                    }

                }

            }(long)(i * 2.5D));

        }

    }

}
