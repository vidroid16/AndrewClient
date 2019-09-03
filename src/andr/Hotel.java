package andr;

import java.util.ArrayList;
import java.util.Arrays;

public class Hotel {
    private String name;
    private String city;
    private ArrayList<Apartment> appartments;
    private ArrayList<Stuff> stuff;
    private Portmone budget;
    private Laundary laundary;
    private Kitchen kitchen;

    public Hotel(String name, String city, Apartment ... apps){
        this.name = name;
        this.city = city;
        this.budget = new Portmone(1000000);
        this.laundary = new Laundary(this);
        this.kitchen = new Kitchen(this);
        appartments = new ArrayList<>();
        appartments.addAll(Arrays.asList(apps));
        stuff = new ArrayList<>();
    }
    public void hireStuff(Stuff stuff){
        this.stuff.add(stuff);
        stuff.setWorkPlace(this);
    }
    public void fireStuff(Stuff stuff){
        if(this.stuff.contains(stuff))this.stuff.remove(stuff);
    }

    public Portmone getBudget() {
        return budget;
    }

    public void paySalary(){
        for(int i = 0; i<this.stuff.size();i++){
            try {
                this.getBudget().pay(this.stuff.get(i).getSalary(),this.stuff.get(i).getBudget());
            } catch (NoMoneyException e) {
                Main.ProgramPauser.pauseErr("Ошибка: на балансе отеля недостаточно средств!");
            }
        }
    }
    public ArrayList<Apartment> viewFreeAparts(){
        ArrayList<Apartment> freeApps = new ArrayList<>();
        //System.out.println(this.appartments.size());
        for(int i = 0; i<this.appartments.size();i++){
            if(this.appartments.get(i).getGuests().size()==0)freeApps.add(appartments.get(i));
            //System.out.println(this.appartments.size());
        }
        return freeApps;
    }

    public Laundary getLaundary() {
        return laundary;
    }

    public Kitchen getKitchen() {
        return kitchen;
    }

    public ArrayList<Stuff> getStuff() {
        return stuff;
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public int hashCode() {
        int kaef = 6;
        int result = 1;
        result = kaef * result +  name.length()+city.length()+budget.getBalance()+
                kitchen.hashCode()+laundary.hashCode()+appartments.size()+stuff.size();
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
        Hotel other = (Hotel) obj;
        if (name != other.name)
            return false;
        if (city != other.city)
            return false;
        if (budget != other.budget)
            return false;
        if (appartments != other.appartments)
            return false;
        if (stuff != other.stuff)
            return false;
        if (kitchen != other.kitchen)
            return false;
        if (laundary != other.laundary)
            return false;
        return true;

    }
}
