package andr;

public class Portmone {
    private int balance;
    public Portmone(int balance){
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }
    public void pay(int payment, Portmone budget) throws NoMoneyException{
        if(balance>=payment){
            balance -= payment;
            budget.balance += payment;
        }else{
            // TODO: 17.03.2019
            throw new NoMoneyException(getBalance());
        }
    }
    @Override
    public String toString() {
        return "Баланс кошелька в сантиках: "+balance;
    }

    @Override
    public int hashCode() {
        int kaef = 10;
        int result = 1;
        result = kaef * result + balance;
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
        Portmone other = (Portmone) obj;
        if ( balance!= other.balance)
            return false;
        return true;

    }
}
