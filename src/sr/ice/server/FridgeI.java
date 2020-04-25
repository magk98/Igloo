package sr.ice.server;

import House.Fridge;
import com.zeroc.Ice.Current;

public class FridgeI implements Fridge {
    private int fridgeTemp;

    FridgeI(int fridgeTemp){
        this.fridgeTemp = fridgeTemp;
    }


    @Override
    public int cool(Current current) {
        int minTemp = -15;
        if (this.fridgeTemp - 1 > minTemp)
            this.fridgeTemp--;
        else
            System.out.println("Cannot decrease fridge temperature more");
        return this.fridgeTemp;
    }

    @Override
    public int heat(Current current) {
        int maxTemp = 15;
        if (this.fridgeTemp + 1 < maxTemp)
            this.fridgeTemp++;
        else
            System.out.println("Cannot increase fridge temperature more");
        return this.fridgeTemp;
    }

    @Override
    public int showTemperature(com.zeroc.Ice.Current current){
        return this.fridgeTemp;
    }

}
