package sandbox.my_examples.inner_classes;

public abstract class Building {
    private String name, address, type;

    Building(String name, String address) {
        this.name = name;
        this.address = address;
    }

    void setType(String type) {
        this.type = type;
    }


    public static void main(String[] args) {
        Platform platform = new Platform("plat1", "Pockrovskya street");
        System.out.println(platform);
    }


    public static class Platform extends Building {
        public Platform(String name, String address) {
            super(name, address);
            setType("Platform");
        }

        @Override
        public String toString() {
            return "Platform{" +
                    "address='" + super.address + '\'' +
                    ", name='" + super.name + '\'' +
                    ", type='" + super.type + '\'' +
                    '}';
        }

        // some additional logic
    }

    public static class House extends Building {
        public House(String name, String address) {
            super(name, address);
            setType("House");
        }

        // some additional logic
    }

    public static class Shop extends Building {
        public Shop(String name, String address) {
            super(name, address);
            setType("Shop");
        }

        // some additional logic
    }


}