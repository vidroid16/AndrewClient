package andr;

public class Pos{
        private HotelPlaces place;
        private Hotel hotel;
        //private Ho house;

        public Pos(Hotel hotel, HotelPlaces place){
            this.hotel = hotel;
            this.place = place;
        }

        public Pos(){
            hotel = new Hotel("def","def");
            place = null;
        }

        public Hotel getHotel() {
             return hotel;
        }
        public HotelPlaces getPlace() {
            return place;
        }

        public void setPlace(Hotel hotel,HotelPlaces place) {
            this.place = place;
            this.hotel = hotel;
        }

       // @Override
       // public String toString() {
     //     return "Room: " + room.toString() + ", House: " + house.toString();
      //  }

        @Override
        public int hashCode() {
            int kaef = 11;
            int result = 1;
            result = kaef * result + hotel.hashCode() + place.hashCode();
            return result;
        }
     @Override
     public String toString() {
         return "Позиция :"+hotel.toString() + " , "+ place.toString();
     }

     @Override
     public boolean equals(Object obj) {
         if (this == obj)
             return true;
         if (obj == null)
             return false;
         if (getClass() != obj.getClass())
             return false;
        Pos other = (Pos) obj;
         if ( place!= other.place)
             return false;
         if (hotel!= other.hotel)
             return false;

         return true;

     }

    }

