public class activity1 {
public static void main(String[] args) {
    car Kia = new car();
    Kia.make = 2014;
    Kia.transmission = "Manual";
    Kia. color = "Black";

    //using methods from car class
    Kia.displayCharacteristics();
    Kia.accelerate();
    Kia.brake();
}
}
