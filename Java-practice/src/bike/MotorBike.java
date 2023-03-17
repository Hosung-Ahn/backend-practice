package bike;

public class MotorBike {
    public static void main(String[] args) {

    }

    private int speed;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void run() {
        System.out.println("running~! on " + speed);
    }

    public void increase(int speed) {
        setSpeed(this.speed + speed);
    }

    public void decrease(int speed) {
        int newSpeed = Math.max(this.speed - speed, 0);
        setSpeed(newSpeed);
    }
}
