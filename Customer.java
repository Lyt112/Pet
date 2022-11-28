
import java.time.LocalDate;
public class Customer {

    private String name;
    private int arriveTimes;
    private LocalDate date = LocalDate.now();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArriveTimes() {
        return arriveTimes;
    }

    public void setArriveTimes(int arriveTimes) {
        this.arriveTimes = arriveTimes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", arriveTimes=" + arriveTimes +
                ", date=" + date +
                '}';
    }

    public Customer() {

    }
}
