public class Dog extends Animal {
    boolean isVaccineInjected;

    public Dog(String name, int age, String sex, double price, boolean isVaccineInjected) {
        super(name, age, sex, price);
        super.price = 100;
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "Dog" +
                "  name='" + name + ' ' +
                ", age=" + age +
                ", sex='" + sex + ' ' +
                ", price=" + price +
                ", isVaccineInjected=" + isVaccineInjected;

    }
}
