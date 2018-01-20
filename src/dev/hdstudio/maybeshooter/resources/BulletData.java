package dev.hdstudio.maybeshooter.resources;

public class BulletData {
    public int damage;
    public double speed;
    public double lifeTime;

    public Price price;

    public BulletData(int damage, double speed, int lifeTime) {
        this.damage = damage;
        this.speed = speed;
        this.lifeTime = lifeTime;
        this.price = new Price();
        //printData();
    }

/*    public void printData() {
        System.out.println(damage);
        System.out.println(speed);
        System.out.println(lifeTime);
    }*/
}
