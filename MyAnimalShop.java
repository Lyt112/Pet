import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop {
    static double balance = 5000;
    double profit = balance;
    boolean isOpen = true;
    int numbers = 5;
    Animal[] animals = new Animal[20];
    ArrayList<String> customerList = new ArrayList<>();
    ArrayList<Animal> animalArrayList = new ArrayList<>();

    public void animalList() {

        animals[0] = new Dog("旺财", 1, "公", 100, true);
        animals[1] = new Dog("花花", 2, "母", 100, true);
        animals[2] = new Dog("大黄", 1, "公", 100, false);
        animals[3] = new Cat("咪咪", 1, "母", 200);
        animals[4] = new Cat("猫猫", 2, "公", 200);
        for (int i = 0; i < animals.length && animals[i] != null; i++) {
            animalArrayList.add(animals[i]);
            System.out.println(animals[i]);
        }
    }

    @Override
    public void buyNewAnimals() {
        String key;
        System.out.println("是否要买入动物,需要输入1，不需要输入2");
        Scanner input = new Scanner(System.in);
        key = input.next();
        while (Objects.equals(key, "1")) {
            System.out.println("请输入购买的动物种类,购买猫输入1，购买狗输入2");
            key = input.next();
            if (Objects.equals(key, "1")) {
                balance -= 100;
                if (balance < 0) {
                    throw new InsufficientBalanceException("没钱买动物");
                }
                System.out.println("请输入猫的名字：");
                String catName = input.next();
                System.out.println("请输入猫的年龄");
                int catAge = input.nextInt();
                System.out.println("请输入猫的性别");
                String catSex = input.next();
                animals[numbers++] = new Cat(catName, catAge, catSex, 200);
                animalArrayList.add(animals[numbers - 1]);
            }
            if (Objects.equals(key, "2")) {
                balance -= 50;
                if (balance < 0) {
                    throw new InsufficientBalanceException("没钱买动物");
                }
                System.out.println("请输入狗的名字：");
                String dogName = input.next();
                System.out.println("请输入狗的年龄");
                int dogAge = input.nextInt();
                System.out.println("请输入狗的性别");
                String dogSex = input.next();
                System.out.println("请输入狗是否打过疫苗, 打了输入1，没打输入2");
                boolean dogIsVaccineInjected;
                key = input.next();
                dogIsVaccineInjected = Objects.equals(key, "1");
                animals[numbers++] = new Dog(dogName, dogAge, dogSex, 200, dogIsVaccineInjected);
                animalArrayList.add(animals[numbers - 1]);
            }

            System.out.println("是否要买入动物,需要输入1，不需要输入2");
            key = input.next();
        }
        System.out.println("当前动物列表为：");
        for (int i = 0; i < animalArrayList.size(); i++) {
            System.out.printf("%d: ", i + 1);
            System.out.println(animalArrayList.get(i));
        }
    }


    @Override
    public void treatCustomers() {


        System.out.println("是否需要添加顾客,需要输入1，不需要输入2");
        String key;
        Scanner input = new Scanner(System.in);
        key = input.next();
        int number = 0;
        while (Objects.equals(key, "1")) {
            number = number + 1;
            System.out.println("请输入顾客的姓名");
            customerList.add("顾客" + number + "姓名：" + input.next());
            System.out.println("请输入顾客到店次数");
            customerList.add(" 到店次数: " + input.next());
            LocalDate time;
            while (true) {
                System.out.println("请输入顾客最新到店时间,格式为yyyy-MM-dd");
                String date = input.next();
                try {
                    time = LocalDate.parse(date);
                    break;
                } catch (Exception e) {
                    System.out.println("您输入的日期可能有误，请重新输入：");
                }

            }
            customerList.add(" 最新到店时间: " + time);

            System.out.println("是否顾客需要购买动物，需要输入1，不需要输入2");
            key = input.next();
            if (animalArrayList.isEmpty() && Objects.equals(key, "1")) {
                throw new AnimalNotFoundException("当前动物列表为空");
            }
            while (Objects.equals(key, "1")) {
                System.out.println("请选择要购买的动物编号：");
                for (int i = 0; i < animalArrayList.size(); i++) {
                    System.out.printf("%d: ", i + 1);
                    System.out.println(animalArrayList.get(i));
                }
                int id;
                Scanner in = new Scanner(System.in);
                id = in.nextInt();
                balance = balance + animalArrayList.get(id - 1).price;
                animalArrayList.remove(id - 1);
                if (animalArrayList.isEmpty()) {
                    System.out.println("动物已经卖完了");
                } else {
                    System.out.println("当前动物：");
                    for (int i = 0; i < animalArrayList.size(); i++) {
                        System.out.printf("%d: ", i + 1);
                        System.out.println(animalArrayList.get(i));
                    }
                }
                System.out.println("是否顾客需要购买动物，需要输入1，不需要输入2");
                key = input.next();
                if (animalArrayList.isEmpty() && Objects.equals(key, "1")) {
                    throw new AnimalNotFoundException("当前动物列表为空");
                }
            }
            System.out.println("是否需要添加顾客,需要输入1，不需要输入2");
            key = input.next();
        }
    }

    @Override
    public void closure() {
        isOpen = false;
        showCustomer();
        profit =balance-profit;
        System.out.println("今天利润为：" + profit);
    }

    public void showCustomer() {
        if (customerList.isEmpty())
            System.out.println("今天没有顾客");
        else {
            System.out.println(customerList);
        }
    }


}
