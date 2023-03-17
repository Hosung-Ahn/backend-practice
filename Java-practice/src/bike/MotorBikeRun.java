package bike;

public class MotorBikeRun {

    public static void main(String[] args) {
        MotorBike motorBike = new MotorBike();
        motorBike.setSpeed(100);
        System.out.println("motorBike.getSpeed() = " + motorBike.getSpeed());
        motorBike.increase(100);
        System.out.println("motorBike.getSpeed() = " + motorBike.getSpeed());
        motorBike.decrease(100);
        System.out.println("motorBike.getSpeed() = " + motorBike.getSpeed());
        motorBike.decrease(200);
        System.out.println("motorBike.getSpeed() = " + motorBike.getSpeed());
    }



}
