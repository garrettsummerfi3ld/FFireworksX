package io.garrettsummerfi3ld.ffireworksx;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin implements Listener {
    public void onEnable() {
        getLogger().info("FlashingFireworks was enabled by console.");

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(this, (Plugin) this);
        pm.registerEvents(new EventsListener(this), (Plugin) this);
    }


    public void onDisable() {
        getLogger().info("FlashingFireworks was disabled by console.");
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("flashingfireworks") || cmd.getName().equalsIgnoreCase("ff")) {

            if (!(sender instanceof Player)) {

                sender.sendMessage(ChatColor.RED + "Only players can use FlashingFireworks commands!");
                return true;
            }

            Player player = (Player) sender;

            ArrayList<String> arglist = new ArrayList<>();
            String[] arrayOfString;
            int j = (arrayOfString = args).length;
            for (int i = 0; i < j; i++) {

                String s = arrayOfString[i];

                arglist.add(s);
            }

            if (args == null || arglist.isEmpty()) {

                sendHelp(player);
                return true;
            }

            if (args[0].equalsIgnoreCase("give")) {

                if (player.hasPermission("flashingfireworks.give")) {
                    /*     */
                    Player ptgt = player;
                    if (args.length >= 2 && args != null) {

                        if (Bukkit.getPlayer(args[1]) == null) {

                            player.sendMessage("§cSorry, but this player is not online at the moment.");
                            return true;
                        }

                        if (Bukkit.getPlayer(args[1]).isOnline()) {
                            ptgt = Bukkit.getPlayer(args[1]);
                        }
                    }

                    ItemStack is = new ItemStack(Material.SNOWBALL, 64);
                    ItemMeta im = is.getItemMeta();
                    im.setDisplayName("§bFlashing Firework Snowball");
                    is.setItemMeta(im);

                    player.getInventory().addItem(new ItemStack[]{is});
                    player.sendMessage("§7Given " + ptgt.getName() + " §ax64 Flashing Firework Snowballs §7for them to use.");
                    ptgt.sendMessage("§7Here is §ax64 Flashing Firework Snowballs §7for you to use.");
                    return true;
                }

                player.sendMessage("§cYou don't have enough permission to do that.");
                return true;
            }

            sendHelp(player);
            return true;
        }

        return true;
    }


    public void sendHelp(Player player) {
        String version = Bukkit.getServer().getPluginManager().getPlugin("FlashingFireworks").getDescription().getVersion();
        player.sendMessage("§b§lFlashingFireworks v" + version + " §aby Wulf Games.");
        player.sendMessage("§3/ff - §7View command help page.");
        player.sendMessage("§3/ff give [player]- §7Gives you or [player] a stack of flashing firework snowballs.");
    }
}