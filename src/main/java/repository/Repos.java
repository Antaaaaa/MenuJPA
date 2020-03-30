package repository;
import java.util.List;
import entity.Menu;
public interface Repos {
    List<Menu> findByPrice(long minPrice, long maxPrice);
    List<Menu> findByDiscount();
    List<Menu> getList();
    List<Menu> getMenuWithWeight();
    void insertDish(Menu menu);
}
