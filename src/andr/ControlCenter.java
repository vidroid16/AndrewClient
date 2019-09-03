package andr;

public class ControlCenter {
    private Apartment app;

    ControlCenter(Apartment app){
        this.app = app;
    }

    public void Wash(int amount, Guest guest, Stuff st){
        st.go(st.getWorkPlace(), st.getWorkPlace().getLaundary());
        st.getWorkPlace().getLaundary().washThings(amount, guest);
        try {
            guest.getBudget().pay(1, st.getBudget());
        } catch (NoMoneyException e) {
            Main.ProgramPauser.pauseErr("Ошибка: На балансе не достаточно средств!");
        }
    }

    public void Iron(int amount, Guest guest, Stuff st){
        st.go(st.getWorkPlace(), st.getWorkPlace().getLaundary());
        st.getWorkPlace().getLaundary().ironThings(amount, guest);
        try {
            guest.getBudget().pay(1, st.getBudget());
        } catch (NoMoneyException e) {
            Main.ProgramPauser.pauseErr("Ошибка: на балансе недостаточно средств!");
        }
    }

    public void Eat(Food food, Guest guest, Stuff st){
        Main.ProgramPauser.pause(guest.toString() + " заказывает "+ food.getName());
        st.go(st.getWorkPlace(), st.getWorkPlace().getKitchen());
        st.getWorkPlace().getKitchen().eatFood(guest,food);
        try {
            guest.getBudget().pay(1, st.getBudget());
        } catch (NoMoneyException e) {
            Main.ProgramPauser.pauseErr("Ошибка: на балансе недостаточно средств!");
        }
    }

    @Override
    public String toString() {
        return "пульт управления в" +app.toString();
    }

    @Override
    public int hashCode() {
        int kaef = 2;
        int result = 1;
        result = kaef * result + app.hashCode();
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
        ControlCenter other = (ControlCenter) obj;
        if (app != other.app)
            return false;
        return true;

    }
}
