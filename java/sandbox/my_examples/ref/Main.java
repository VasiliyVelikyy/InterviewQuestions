package sandbox.my_examples.ref;

//“Передача по ссылке” или “передача по значению”?
public class Main {
    public static void main(String[] args) {
        int number = 10;
        System.out.println("Before: " + number); // Вывод: Before: 10
        modifyNumber(number);
        System.out.println("After: " + number); // Вывод: After: 10


        Point point = new Point(2, 3);
        System.out.println("Before: " + point); // Вывод: Before: (2, 3)
        modifyPoint(point);
        System.out.println("After: " + point); // Вывод: After: (20, 30)
    }

    public static void modifyNumber(int value) { //ПРИМИТИВЫ ПЕРЕДАЮТЬСЯ ПО ЗНАЧЕНИЮ
        value = value * 2;
        System.out.println("Modified: " + value); // Вывод: Modified: 20
    }

    /*В данном примере, когда мы передаем объект point в метод modifyPoint(),
    передается только копия значения ссылки на объект (адрес памяти).
    Исходный объект модифицируется в методе, в результате чего изменение отражается в выходных данных.
     Однако важно понимать, что сама ссылка на объект передается по значению, а это означает, что адрес памяти объекта не может
     быть изменен.*/
    public static void modifyPoint(Point point) {
        point.setX(point.getX() * 10);
        point.setY(point.getY() * 10);
        System.out.println("Modified: " + point); // Вывод: Modified: (20, 30)
    }


    static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}
