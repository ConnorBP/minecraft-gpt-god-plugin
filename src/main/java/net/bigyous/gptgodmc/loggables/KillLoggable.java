package net.bigyous.gptgodmc.loggables;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
public class KillLoggable extends BaseLoggable{
    String killer;
    String victim;
    boolean isValid;
    public KillLoggable(EntityDeathEvent event){
        Entity k = event.getEntity().getKiller();
        Entity v = event.getEntity();
        this.isValid = k != null && (k instanceof Player || v instanceof Player);
        if(isValid){
            this.killer = k.getName();
            this.victim = v.getName();
        }
    }

    @Override
    public String getLog() {
        if(!isValid) return null;
        return String.format("%s was killed by %s", victim, killer);
    }
}
