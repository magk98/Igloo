package sr.ice.server;

import House.Fridge;
import com.zeroc.Ice.Current;

public class FridgeI implements Fridge {
    int maxTemp = 15;
    int minTemp = -15;

    @Override
    public int cool(int temp, Current current) {
        return temp - 1;
    }

    @Override
    public int heat(int temp, Current current) {
        temp++;
        return temp;
    }

    @Override
    public int showTemperature( com.zeroc.Ice.Current current){
        return 10;
    }

}
