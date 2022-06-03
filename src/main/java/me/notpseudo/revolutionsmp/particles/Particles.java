package me.notpseudo.revolutionsmp.particles;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

public class Particles {

    public static void dualSpiralUp(Particle particle, Entity target, double radius, double height, double seconds) {
        BukkitRunnable dualSpiralUp = new BukkitRunnable() {

            double degree = 0, degreeInc = 360 / (seconds * 20), y = 0, yInc = height / (seconds * 20);

            @Override
            public void run() {
                if(degree >= 360) {
                    this.cancel();
                    return;
                }
                double zChange = radius * Math.sin(degree * Math.PI / 90);
                double xChange = radius * Math.cos(degree * Math.PI / 90);
                target.getWorld().spawnParticle(particle,
                        target.getLocation().getX() + xChange,
                        target.getLocation().getY() + y,
                        target.getLocation().getZ() + zChange,
                        0, 0, 0, 0);
                target.getWorld().spawnParticle(particle,
                        target.getLocation().getX() - xChange,
                        target.getLocation().getY() + y,
                        target.getLocation().getZ() - zChange,
                        0, 0, 0, 0);
                degree += degreeInc;
                y += yInc;
            }
        };
        dualSpiralUp.runTaskTimer(RevolutionSMP.getPlugin(), 0, 1);
    }

}
