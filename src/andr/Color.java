package andr;

public enum Color {
    White{
        @Override
        public String toString() {
            return "Белый";
        }
    },
    Black{
        @Override
        public String toString() {
            return "Черный";
        }
    },
    Red{
        @Override
        public String toString() {
            return "Красный";
        }
    },
    Blue{
        @Override
        public String toString() {
            return "Синий";
        }
    },
    Brown{
        @Override
        public String toString() {
            return "Коричневый";
        }
    },
    Green{
        @Override
        public String toString() {
            return "Зеленый";
        }
    };
}
