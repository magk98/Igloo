package sr.ice.server;

public class MyHouseSingleton {
    // static variable single_instance of type Singleton
    private static MyHouseSingleton single_instance = null;

    public String[] devices;
    public int temperature;
    public String unit;

    // private constructor restricted to this class itself
    private MyHouseSingleton() {
        this.devices = new String[]{"fridge", "basic temperature sensor", "super temperature sensor"};
        this.temperature = 22;
        this.unit = "C";
    }

    // static method to create instance of Singleton class
    public static MyHouseSingleton getInstance()
    {
        if (single_instance == null)
            single_instance = new MyHouseSingleton();

        return single_instance;
    }
}
