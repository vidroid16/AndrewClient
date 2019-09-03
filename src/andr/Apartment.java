package andr;

import java.util.ArrayList;

public class Apartment implements HotelPlaces {
    private int number;
    private Hotel hotel;
    private Luxority luxority;
    private ArrayList<Guest> guests;
    private int price;
    private ControlCenter pult;
    private boolean isFree;

    public Apartment(Luxority luxority, int number){
        this.number = number;
        guests = new ArrayList<>();
        switch (luxority){
            case Econom1:
                price = 100;
                this.luxority= Luxority.Econom1;
                break;
            case Econom2:
                price = 200;
                this.luxority= Luxority.Econom2;
                break;
            case Lux:
                price = 500;
                this.luxority= Luxority.Lux;
                break;
            case Lux2:
                price = 1000;
                this.luxority= Luxority.Lux2;
                break;
            case Presidium:
                price = 5000;
                this.luxority= Luxority.Presidium;
                break;
        }
        isFree = true;
        pult = new ControlCenter(this);
    }
    public void settleGuests(Guest... guests){
        for(int i =0; i<guests.length; i++) {
            Guest guest = guests[i];
            this.guests.add(guest);
        }
    }
    public void evictGuests(){
        this.guests.clear();
    }
    public ControlCenter usePult(){
        return pult;
    }
    public ArrayList<Guest> getGuests(){
        return guests;
    }

    public Luxority getLuxority() {
        return luxority;
    }

    public boolean isFree(){
        if(guests.size()==0)return true;
        else return false;
    }

    public Hotel getHotel() {
        return hotel;
    }

    @Override
    public Pos getPos() {
        return new Pos(hotel, this);
    }

    @Override
    public boolean isHere(Human human) {
        if(human.getPos().equals(this.getPos()))return true;
        else return false;
    }

    @Override
    public String toString() {
        return "комнате номер " + number;
    }

    @Override
    public int hashCode() {
        int kaef = 1;
        int result = 1;
        int isfree = isFree ? 1 : 0;
        result = kaef * result + number + hotel.hashCode() + luxority.toString().length() +
                guests.size() + price + isfree;
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
        Apartment other = (Apartment) obj;
        if (number != other.number)
            return false;
        if (luxority != other.luxority)
            return false;
        if (isFree != other.isFree)
            return false;
        if (price != other.price)
            return false;
        if (!hotel.equals(other.hotel))
            return false;
        if (!guests.equals(other.guests))
            return false;

        return true;

    }
}

