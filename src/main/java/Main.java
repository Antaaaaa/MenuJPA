import service.Service;

public class Main {
    public static void main(String[] args){
        Service service = new Service();
        service.insertRandomDish();
        service.getList().stream().forEach(i -> System.out.println(i.toString()));
        service.findByDiscount().forEach(i -> System.out.println(i.toString()));
        service.findByPrice(100, 300).forEach(i -> System.out.println(i.toString()));
        service.getMenuWithWeight().forEach(i -> System.out.println(i.toString()));
    }
}
