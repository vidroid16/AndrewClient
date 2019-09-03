package andr;

public class Laundary implements HotelPlaces {
    private Hotel hotel;

    public Laundary(Hotel hotel){
        this.hotel = hotel;
    }
    public void ironThings(int amount, Guest guest){
        Main.ProgramPauser.pause("Гость "+ guest.getName() + " хочет погладить " + amount + " вещей");
        if(this.isHere(guest)){
            try {
                guest.getBudget().pay(amount*10, hotel.getBudget());
                Main.ProgramPauser.pause(amount + " вещей гостя "+guest.toString()+" были успешно поглажены");
                Main.ProgramPauser.pause("Текущий баланс его счета составляет "+ guest.getBudget().getBalance()+" сантиков");
            } catch (NoMoneyException e) {
                Main.ProgramPauser.pauseErr("Ошибка: На балансе недостаточно средств!");
            }
        }
        else{
            try {
            guest.getBudget().pay(amount*10*2, hotel.getBudget());
            } catch (NoMoneyException e) {
                Main.ProgramPauser.pauseErr("Ошибка: На балансе недостаточно средств!");
            }
        }
    }
    public void washThings(int amount, Guest guest){
        if(this.isHere(guest)){
            try{
            guest.getBudget().pay(amount*7, hotel.getBudget());
        } catch (NoMoneyException e) {
                Main.ProgramPauser.pauseErr("Ошибка: На балансе недостаточно средств!");
        }
        }
        else{
            try{
                guest.getBudget().pay(amount*7*2, hotel.getBudget());
            } catch (NoMoneyException e) {
                Main.ProgramPauser.pauseErr("Ошибка: На балансе недостаточно средств!");
            }
        }
        Main.ProgramPauser.pause(amount + " вещей гостя "+guest.toString()+" были успешно поcтираны ");
        Main.ProgramPauser.pause("Текущий баланс его счета составляет "+ guest.getBudget().getBalance()+" сантиков");
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
        return "Прачечная в отеле"+hotel.toString();
    }

    @Override
    public int hashCode() {
        int kaef = 9;
        int result = 1;
        result = kaef * result + hotel.hashCode();
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
        Laundary other = (Laundary) obj;
        if (hotel!= other.hotel)
            return false;

        return true;

    }


}
