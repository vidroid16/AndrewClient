package andr;

public abstract class Human {
    private String name;
    private Eyes eyes;
    private Gender gender;
    private Pos pos;
    private Passport passport;
    private Portmone budget;
    public Human(String name, Eyes eyes, Gender gender, Portmone budget, Passport passport){
        if(name.length() == 0){
            try {
                throw new NameException(this);
            }catch(NameException e){
                name = "Aндрюха";
                Main.ProgramPauser.pauseErr("Ошибка: У человека нет имени, поэтому он будет назван Андрюхой");
            }
        }
        this.name = name;
        this.pos = new Pos();
        this.budget = budget;
        this.eyes = eyes;
        this.gender = gender;
        Main.ProgramPauser.pause("Человек с именем "+this.toString()+" был создан");
        this.passport = passport;
    }
    public void damageEyes(EyeDamager damager){
        int dmg = damager.getDmg();
        Color color = damager.getColor();

        this.getEyes().setColor(color);
        this.getEyes().doDamage(dmg);

        Main.ProgramPauser.pause("Глазам "+this.toString()+" был нанесен урон "+dmg+" и они окрасились в "+color);
    }
    public void go(Hotel hotel, HotelPlaces place){
        this.pos.setPlace(hotel,place);
    }

    public String getName() {
        return name;
    }

    public Pos getPos() {
        return pos;
    }

    public Portmone getBudget() {
        return budget;
    }

    public Eyes getEyes() {
        return eyes;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int hashCode() {
        int kaef = 7;
        int result = 1;
        result = kaef * result +getName().length() + getEyes().hashCode()+getBudget().getBalance();
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
        Human other = (Human) obj;
        if (getName() != other.getName())
            return false;
        if (getBudget() != other.getBudget())
            return false;
        if (getEyes() != other.getEyes())
            return false;
        return true;

    }
}
