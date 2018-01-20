package dev.hdstudio.maybeshooter.resources;

public class Stats {
    //public static boolean alive = true;

    public static int score = 0;
    public static int money = 1000;
    public static int wave = 1;

    public static BulletData simpleBulletData;
    public static BulletData hardBulletData;
    public static BulletData tripleBulletData;
    public static BulletData pentaBulletData;
    public static BulletData octaBulletData;

    public static int health = 100;
    public static int maxHealth = 100;
    public static double minSpeed = 0.1;
    public static double maxSpeed = 1;

    public static void initBulletData() {
        simpleBulletData = new BulletData(10, 5, 7);
        hardBulletData = new BulletData(20, 7, 3);
        tripleBulletData= new BulletData(10, 3, 3);
        pentaBulletData = new BulletData(5, 3, 3);
        octaBulletData = new BulletData(20, 1, 30);

    }

}
