package andr;

public class Guest extends Human{
    private Apartment room;

    public Guest(String name, Eyes eyes, Gender gender, Portmone budget, Passport passport){
        super(name, eyes, gender, budget, passport);
    }
    public void chooseAparts(Hotel hotel, Luxority AppLuxority, Guest... friends){
        for(int i = 0; i < hotel.viewFreeAparts().size();i++){
            if(hotel.viewFreeAparts().get(i).getLuxority()==AppLuxority){
                Main.ProgramPauser.pause("Гости:");
                for (int j =0;j<friends.length; j++) {
                    Main.ProgramPauser.pause(friends[j].getName());
                }Main.ProgramPauser.pause("заселились в " + hotel.viewFreeAparts().get(i).toString() + " в отеле " + hotel.toString());
                hotel.viewFreeAparts().get(i).settleGuests(friends);
                break;
            }
        }
    }
    public Apartment getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int hashCode() {
        int kaef = 5;
        int result = 1;
        result = kaef * result + room.hashCode()+getName().length() + getEyes().hashCode()+getBudget().getBalance();
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
        Guest other = (Guest) obj;
        if (getName() != other.getName())
            return false;
        if (getBudget() != other.getBudget())
            return false;
        if (getEyes() != other.getEyes())
            return false;
        return true;

    }
}

