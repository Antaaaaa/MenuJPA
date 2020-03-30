package service;

import entity.Menu;
import repository.Repos;
import util.Util;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class Service implements Repos {
    private final static Util utilRepos = new Util();

    @Override
    public List<Menu> findByPrice(long minPrice, long maxPrice) {
        List<Menu> resultList;
        TypedQuery<Menu> query = utilRepos.getEm().createQuery(
                "SELECT m from Menu m where m.price >= :min and m.price <= :max", Menu.class);
        query.setParameter("min", minPrice);
        query.setParameter("max", maxPrice);
        resultList = query.getResultList();
        return resultList;
    }
    @Override
    public List<Menu> findByDiscount() {
        List<Menu> resultList;
        TypedQuery<Menu> query = utilRepos.getEm().createQuery(
                "SELECT m from Menu m where m.discount = true", Menu.class);
        resultList = query.getResultList();
        return resultList;
    }
    @Override
    public List<Menu> getList() {
        List<Menu> resultList;
        TypedQuery<Menu> query = utilRepos.getEm().createQuery(
                "SELECT m from Menu m", Menu.class);
        resultList = query.getResultList();
        return resultList;
    }
    @Override
    public List<Menu> getMenuWithWeight(){
        List<Menu> resultList = getList();
        List<Menu> completeMenu = new ArrayList<>();
        Menu simpleDish;
        int randomDigit;
        double sum;
        while(true){
            randomDigit = (int)(Math.random()*resultList.size());
            if (resultList.size() > 0)
                simpleDish = resultList.get(randomDigit);
            else break;
            sum = completeMenu.stream().mapToDouble(Menu::getWeight).sum();
            if (sum >= 1000) break;
            else if  (simpleDish != null && ((simpleDish.getWeight()+sum) <= 1000))
               completeMenu.add(simpleDish);
           resultList.remove(simpleDish);
        }
        return completeMenu;
    }

    @Override
    public void insertDish(Menu menu) {
        EntityTransaction entityTransaction = utilRepos.getEm().getTransaction();
        try{
            entityTransaction.begin();
            utilRepos.getEm().persist(menu);
            entityTransaction.commit();
        } catch (Exception e){
            if (entityTransaction.isActive())
                entityTransaction.rollback();
        }
    }

    public void insertRandomDish(){
        this.insertDish(new Menu("Утка в сливочном соусе", 150L, 350L, false));
        this.insertDish(new Menu("Борщ", 100L, 300L, false));
        this.insertDish(new Menu("Вареники", 70L, 300L, true));
        this.insertDish(new Menu("Узвар", 40L, 250L, false));
        this.insertDish(new Menu("Скумбрия", 80L, 200L, false));
        this.insertDish(new Menu("Голубцы", 60L, 350L, true));
        this.insertDish(new Menu("Сок яблочный", 35L, 250L, false));
        this.insertDish(new Menu("Ризотто", 120L, 350L, false));
        this.insertDish(new Menu("Ребра в кислосладком соусе", 80L, 300L, true));
        this.insertDish(new Menu("Сырники", 70L, 200L, true));
        this.insertDish(new Menu("Тирамису", 60L, 200L, false));
    }
}
