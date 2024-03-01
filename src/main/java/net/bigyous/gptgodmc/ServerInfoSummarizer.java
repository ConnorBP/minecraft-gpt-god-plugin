package net.bigyous.gptgodmc;

import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.kyori.adventure.text.TextComponent;
public class ServerInfoSummarizer {
    public static String getInventoryInfo(Player player) {
        StringBuilder sb = new StringBuilder();
        // Armor Items
        StringBuilder armorString = new StringBuilder();
        for(ItemStack armor: player.getInventory().getArmorContents()){
            if(armor!=null){
                armorString.append(formatItemStack(armor) + ", ");
            }
        }
        if(!armorString.isEmpty()){
            sb.append("Armor: " + armorString.toString() + "\n");
        }

        // Inventory Items
        // sb.append("Inventory: ");
        // for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
        //     ItemStack stack = player.getInventory().getItem(i);
        //     if (!stack.isEmpty()) {
        //         sb.append(formatItemStack(stack) + ", ");
        //     }
        // }
        // sb.append("\n");

        // Equipped Item (Main Hand)
        ItemStack main = player.getInventory().getItemInMainHand();
        ItemStack off = player.getInventory().getItemInOffHand();
        sb.append(String.format("Main Hand: %s, Off Hand %s\n", formatItemStack(main), formatItemStack(off)));
        return sb.toString();
    }

    private static String formatItemStack(ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return "None";
        }
        //stack.getItem()
        return stack.getType().toString();
    }
    private static String getPlayerHealth(Player player){
        double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
        double health = player.getHealth();
        double healthRatio = health/maxHealth;
        if(healthRatio > 0.50){
            return "Healthy";
        }
        if(healthRatio <= 0.5 && healthRatio > 0.3 ){
            return "Injured";
        }
        if(healthRatio <= 0.3){
            return "Gravely Wounded";
        }
        return "Unknown";
    }
    public static String getStatusSummary() {
        StringBuilder sb = new StringBuilder("=== Server Status ===\n");

        for (Player player : GPTGOD.SERVER.getOnlinePlayers()) {
            //player.getP
            String name = player.getName();
            boolean isDead = player.isDead() || player.getGameMode().equals(GameMode.SPECTATOR);
            String health = isDead? "Dead" : getPlayerHealth(player);
            boolean isSleeping = player.isSleeping();
            //player.getInventory()
            String inventoryInfo = getInventoryInfo(player);

            sb.append("Status of Player " + name + ":\n");
            sb.append("Health: " + health + '\n');
            // sb.append("\tDead? " + isDead + "\n");
            // sb.append("\tInventory: " + inventoryInfo + "\n");
            // sb.append(isDead? "Dead\n" : "Alive\n");
            sb.append(isSleeping? "Asleep\n" : "");
            sb.append(inventoryInfo + "\n");
        }

        sb.append("==================");
        return sb.toString();
    }
}