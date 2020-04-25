package sr.ice.server;

import House.Fridge;
import com.zeroc.Ice.Current;

public class FridgeI implements Fridge {
    final int maxTemp = 15;
    final int minTemp = -15;
    int fridgeTemp;

    public FridgeI(int fridgeTemp){
        this.fridgeTemp = fridgeTemp;
    }


    @Override
    public int cool(Current current) {
        this.fridgeTemp--;
        return this.fridgeTemp;
    }

    @Override
    public int heat(Current current) {
        this.fridgeTemp++;
        return this.fridgeTemp;
    }

    @Override
    public int showTemperature( com.zeroc.Ice.Current current){
        return this.fridgeTemp;
    }

}
