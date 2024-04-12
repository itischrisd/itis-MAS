package MPO1_exercise;

import MP1.model.ExtensiatedObject;

import java.util.Collections;
import java.util.List;

public class Tank extends ExtensiatedObject {

    private String name;
    private TankClass tankClass;
    private String nation;
    private String turret;
    private Integer turretTraverseSpeed;
    private String gunName;
    private int gunPenetration;
    private int gunDamage;
    private int armor;
    private Integer maxSpeed;
    private int maxDamage;
    private int damage;

    public Tank(String name, TankClass tankClass, String nation, String turret, Integer turretTraverseSpeed, String gunName, int gunPenetration, int gunDamage, int armor, Integer maxSpeed, int maxDamage, int damage) {
        super();
        setName(name);
        setTankClass(tankClass);
        setNation(nation);
        setTurret(turret);
        setTurretTraverseSpeed(turretTraverseSpeed);
        setGunName(gunName);
        setGunPenetration(gunPenetration);
        setGunDamage(gunDamage);
        setArmor(armor);
        setMaxSpeed(maxSpeed);
        setMaxDamage(maxDamage);
        setDamage(damage);
    }

    public Tank(String name, TankClass tankClass, String nation, String gunName, int gunPenetration, int gunDamage, int armor, int maxDamage, int damage) {
        this(name, tankClass, nation, null, null, gunName, gunPenetration, gunDamage, armor, null, maxDamage, damage);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public TankClass getTankClass() {
        return tankClass;
    }

    public void setTankClass(TankClass tankClass) {
        if (tankClass == null) {
            throw new IllegalArgumentException("Tank class cannot be null");
        }
        this.tankClass = tankClass;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        if (nation == null || nation.isBlank()) {
            throw new IllegalArgumentException("Nation cannot be null or empty");
        }
        this.nation = nation;
    }

    public String getTurret() {
        return turret;
    }

    public void setTurret(String turret) {
        if (turret.isBlank()) {
            throw new IllegalArgumentException("Turret cannot be empty");
        }
        this.turret = turret;
    }

    public Integer getTurretTraverseSpeed() {
        return turretTraverseSpeed;
    }

    public void setTurretTraverseSpeed(Integer turretTraverseSpeed) {
        if (turretTraverseSpeed <= 0) {
            throw new IllegalArgumentException("Turret traverse speed must be greater than zero");
        }
        this.turretTraverseSpeed = turretTraverseSpeed;
    }

    public String getGunName() {
        return gunName;
    }

    public void setGunName(String gunName) {
        if (gunName == null || gunName.isBlank()) {
            throw new IllegalArgumentException("Gun name cannot be null or empty");
        }
        this.gunName = gunName;
    }

    public int getGunPenetration() {
        return gunPenetration;
    }

    public void setGunPenetration(int gunPenetration) {
        if (gunPenetration <= 0) {
            throw new IllegalArgumentException("Gun penetration must be greater than zero");
        }
        this.gunPenetration = gunPenetration;
    }

    public int getGunDamage() {
        return gunDamage;
    }

    public void setGunDamage(int gunDamage) {
        if (gunDamage <= 0) {
            throw new IllegalArgumentException("Gun damage must be greater than zero");
        }
        this.gunDamage = gunDamage;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException("Armor cannot be negative");
        }
        this.armor = armor;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        if (maxSpeed == null || maxSpeed <= 0) {
            throw new IllegalArgumentException("Max speed must be greater than zero");
        }
        this.maxSpeed = maxSpeed;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        if (maxDamage <= 0) {
            throw new IllegalArgumentException("Max damage must be greater than zero");
        }
        this.maxDamage = maxDamage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }
        this.damage = damage;
    }

    public int getRemainDamage() {
        return maxDamage - damage;
    }

    public boolean takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }
        this.damage += damage;
        return this.damage >= maxDamage;
    }

    public void shoot(Tank target) {
        if (target == null) {
            throw new IllegalArgumentException("Target cannot be null");
        }
        boolean destroyed = target.takeDamage(gunDamage);
        System.out.println("Target " + (destroyed ? "destroyed" : "damaged"));
    }

    public static List<Tank> getTanks() {
        try {
            return (List<Tank>) Tank.getExtent(Tank.class);
        } catch (ClassNotFoundException e) {
            System.err.println("There are no tanks");
            return null;
        }
    }

    public static List<Tank> getTanks(int minRemainDamage) {
        List<Tank> tanks = getTanks();
        if (tanks == null) {
            return null;
        }
        tanks.removeIf(tank -> tank.getRemainDamage() < minRemainDamage);
        return Collections.unmodifiableList(tanks);
    }

    public static List<Tank> getDestroyedTanks() {
        List<Tank> tanks = getTanks();
        if (tanks == null) {
            return null;
        }
        tanks.removeIf(tank -> tank.getRemainDamage() > 0);
        return Collections.unmodifiableList(tanks);
    }

    /*
    Sprawdzić:
    - fail konstruktora po dodaniu do ekstensji
    - filtrowanie kolekcji ekstencji po pobraniu (czy modifiable)
     */
}
