package sandbox.my_examples.innerclasses;

public class Building {
    Building() {
    }

    private static class Master  {
        public void build(){
            System.out.println("Building Master...");
        }

        public static void main(String[] args) {
            Building.Master master=new Master();
            master.build();
        }
    }
}
