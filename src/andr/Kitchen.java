package andr;

import java.util.ArrayList;

public class Kitchen implements HotelPlaces {
    private Hotel hotel;
    private ArrayList<Food> food;

    public Kitchen(Hotel hotel){
        Food baltos0 = new Food("Балтика0",12);
        Food baltos9 = new Food("Балтика9",18);
        Food burger = new Food("Бургер",40);
        Food food1 = new Food("Кола",11);
        Food food2 = new Food("Форель под соусом тар-тар",4000);
        Food food3 = new Food("Роллы Филадельфия",21);
        Food food4 = new Food("Тирамису",22);
        this.hotel = hotel;
        this.food = new ArrayList<>();
        this.food.add(baltos0);
        this.food.add(baltos9);
        this.food.add(food1);
        this.food.add(food2);
        this.food.add(food3);
        this.food.add(food4);
    }
    public void eatFood(Guest guest, Food myfood) {
        if (food.contains(myfood)) {
            try{
                guest.getBudget().pay(myfood.getPrice(), hotel.getBudget());
            } catch (NoMoneyException e) {
                Main.ProgramPauser.pauseErr("Ошибка: На балансе недостаточно средств!");
            }
            if(guest.getBudget().getBalance()>=myfood.getPrice()){
                Main.ProgramPauser.pause(myfood.getName() + " приготовили специально для гостя " + guest.toString());
                Main.ProgramPauser.pause("Текущий баланс его счета составляет "+ guest.getBudget().getBalance()+" сантиков");
            }
        }else{
            Main.ProgramPauser.pause("Извините, но "+ myfood.getName() +" нет в меню");
        }
    }

    public ArrayList<Food> getFood() {
        return food;
    }

    @Override
    public Pos getPos(){
        Pos position = new Pos(hotel,this);
        return position;
    }
    @Override
    public boolean isHere(Human human){
        if(human.getPos().equals(this.getPos()))return true;
        else return false;
    }
    @Override
    public String toString() {
        return "Кухня в отеле"+hotel.toString();
    }

    @Override
    public int hashCode() {
        int kaef = 8;
        int result = 1;
        result = kaef * result + hotel.hashCode() + food.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Kitchen other = (Kitchen) obj;
        if ( food!= other.food)
            return false;
        if (hotel!= other.hotel)
            return false;

        return true;

    }
}
