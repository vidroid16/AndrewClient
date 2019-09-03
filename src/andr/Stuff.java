package andr;

public abstract class Stuff extends Human {
    private int rank;
    protected int salary;
    public Hotel workPlace;

    public Stuff(String name, Eyes eyes, Gender gender, Portmone budget, int salary, Passport passport){
        super(name, eyes, gender, budget, passport);
        this.salary = salary;
    }
    public int getSalary(){
        return salary;
    }

    public void setWorkPlace(Hotel hotel) {
        workPlace = hotel;
    }

    public Hotel getWorkPlace() {
        return workPlace;
    }
    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int hashCode() {
        int kaef = 7;
        int result = 1;
        result = kaef * result +getName().length() + getEyes().hashCode()+salary+workPlace.hashCode()
                 +getBudget().getBalance();
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
        Stuff other = (Stuff) obj;
        if (getName() != other.getName())
            return false;
        if (getBudget() != other.getBudget())
            return false;
        if (getEyes() != other.getEyes())
            return false;
        if(workPlace != other.workPlace)
            return false;
        if(salary != other.salary)
            return false;

        return true;

    }
}
