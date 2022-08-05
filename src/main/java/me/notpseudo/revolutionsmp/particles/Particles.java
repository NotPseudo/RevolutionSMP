package me.notpseudo.revolutionsmp.particles;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.ArrayList;

public class Particles {

    private static int id = 999999;

    public static void dualSpiralUp(Particle particle, Entity target, double radius, double height, double seconds, int count) {
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
                        count, 0, 0, 0);
                target.getWorld().spawnParticle(particle,
                        target.getLocation().getX() - xChange,
                        target.getLocation().getY() + y,
                        target.getLocation().getZ() - zChange,
                        count, 0, 0, 0);
                degree += degreeInc;
                y += yInc;
            }
        };
        dualSpiralUp.runTaskTimer(RevolutionSMP.getPlugin(), 0, 1);
    }

    public static void dualSpiralUp(Particle particle, Location location, double radius, double height, double seconds, int count) {
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
                location.getWorld().spawnParticle(particle,
                        location.getX() + xChange,
                        location.getY() + y,
                        location.getZ() + zChange,
                        count, 0, 0, 0, 0);
                location.getWorld().spawnParticle(particle,
                        location.getX() - xChange,
                        location.getY() + y,
                        location.getZ() - zChange,
                        count, 0, 0, 0, 0);
                degree += degreeInc;
                y += yInc;
            }
        };
        dualSpiralUp.runTaskTimer(RevolutionSMP.getPlugin(), 0, 1);
    }

    public static void explode(Location location) {
        location.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, location, 1, 0, 0, 0);
    }

    public static void hearts(Location location) {
        location.getWorld().spawnParticle(Particle.HEART, location, 1, 0, 0, 0);
    }

    public static void gravityStormParticles(Location center, double radius, Material[] materials, int count, double time) {
        Location particleCenter = center.clone().add(0, 1, 0);
        ArrayList<Matrix4f> matrices = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            Matrix4f matrix = new Matrix4f()
                    .rotate((float) (Math.PI * 2 * i / count), 0, 1, 0);
            matrices.add(matrix);
        }
        BukkitRunnable showPoints = new BukkitRunnable() {
            double t = Math.PI * 2, changeRadius = radius;
            @Override
            public void run() {
                double input = Math.PI / radius * t;
                double dx = Math.cos(input) * Math.pow(Math.E, input), dz = Math.sin(input) * Math.pow(Math.E, input);
                for(Matrix4f matrix : matrices) {
                    Vector3f particleVec = matrix.transformPosition(new Vector3f((float) dx, 0, (float) dz));
                    Location particleLocation = particleCenter.clone().add(particleVec.x, particleVec.y, particleVec.z);
                    center.getWorld().spawnParticle(Particle.SPELL_WITCH, particleLocation, 0, 0, 0, 0);
                }
                for (int i = 0; i < (int) (Math.random() * 7) + 4; i++) {
                    center.getWorld().spawnFallingBlock(center.clone().add(changeRadius * Math.sin(Math.toRadians(Math.random() * 360)), 0.2, changeRadius * Math.cos(Math.toRadians(Math.random() * 360))), materials[(int) (Math.random() * materials.length)].createBlockData()).remove();
                }
                changeRadius -= radius / (time * 20);
                t -= Math.PI / (time * 10);
                if(t <= 0) {
                    this.cancel();
                }
            }
        };
        showPoints.runTaskTimer(RevolutionSMP.getPlugin(), 0, 1);
    }

    public static void pinwheelOut(Particle particle, Location location, double radius, int count, double time) {
        ArrayList<Matrix4f> matrices = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            Matrix4f matrix = new Matrix4f()
                    .rotate((float) (Math.PI * 2 * i / count), 0, 1, 0);
            matrices.add(matrix);
        }
        BukkitRunnable showPoints = new BukkitRunnable() {
            double t = 0;
            @Override
            public void run() {
                double input = Math.PI / radius * t;
                double dx = Math.cos(input) * Math.pow(Math.E, input), dz = Math.sin(input) * Math.pow(Math.E, input);
                for(Matrix4f matrix : matrices) {
                    Vector3f particleVec = matrix.transformPosition(new Vector3f((float) dx, 0, (float) dz));
                    Location particleLocation = location.clone().add(particleVec.x, particleVec.y, particleVec.z);
                    location.getWorld().spawnParticle(Particle.SPELL_WITCH, particleLocation, 0, 0, 0, 0);
                }
                t += Math.PI / (time * 10);
                if(t >= Math.PI * 2) {
                    this.cancel();
                }
            }
        };
        showPoints.runTaskTimer(RevolutionSMP.getPlugin(), 0, 1);
    }

    public static void pinwheelIn(Particle particle, Location location, double radius, int count, double time) {
        ArrayList<Matrix4f> matrices = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            Matrix4f matrix = new Matrix4f()
                    .rotate((float) (Math.PI * 2 * i / count), 0, 1, 0);
            matrices.add(matrix);
        }
        BukkitRunnable showPoints = new BukkitRunnable() {
            double t = Math.PI * 2;
            @Override
            public void run() {
                double input = Math.PI / radius * t;
                double dx = Math.cos(input) * Math.pow(Math.E, input), dz = Math.sin(input) * Math.pow(Math.E, input);
                for(Matrix4f matrix : matrices) {
                    Vector3f particleVec = matrix.transformPosition(new Vector3f((float) dx, 0, (float) dz));
                    Location particleLocation = location.clone().add(particleVec.x, particleVec.y, particleVec.z);
                    location.getWorld().spawnParticle(Particle.SPELL_WITCH, particleLocation, 0, 0, 0, 0);
                }
                t -= Math.PI / (time * 10);
                if(t <= 0) {
                    this.cancel();
                }
            }
        };
        showPoints.runTaskTimer(RevolutionSMP.getPlugin(), 0, 1);
    }

    public static void polygon(Location location, int sides, double radius) {
        ArrayList<Matrix4f> matrices = new ArrayList<>();
        double sideLength = 10 * Math.tan(Math.PI / sides);
        for(int i = 0; i < sides; i++) {
            Matrix4f matrix = new Matrix4f()
                    .rotate((float) Math.toRadians(location.getYaw()), 0, 1, 0)
                    .translate(5F, 0, 0)
                    .rotate((float) (Math.PI * 2 * i / sides), 1, 0, 0)
                    .translate(0, 5F, 0)
                    .rotate((float) Math.toRadians(location.getYaw() + 90), 0, 1, 0);
                    //.rotate((float) (Math.PI * 3.0 / 2), 0, 0, 1);
            matrices.add(matrix);
        }
        BukkitRunnable draw = new BukkitRunnable() {
            double count = 0;
            @Override
            public void run() {
                for(Matrix4f matrix : matrices) {
                    Vector3f particleVec = matrix.transformPosition(new Vector3f((float) count, 0, 0));
                    Location particleLocation = location.clone().add(particleVec.x, particleVec.y, particleVec.z);
                    location.getWorld().spawnParticle(Particle.SPELL_WITCH, particleLocation, 0, 0, 0, 0);
                }
                count += (sideLength / 80);
                if(count >= sideLength) {
                    this.cancel();
                }
            }
        };
        draw.runTaskTimer(RevolutionSMP.getPlugin(), 0, 1);
    }

    private static int getNextId() {
        id++;
        return id - 1;
    }

}