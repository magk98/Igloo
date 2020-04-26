package sr.ice.server;

import House.BetterTempSensor;
import House.MyHouse;
import com.zeroc.Ice.Current;

public class BetterTempSensorI implements BetterTempSensor {
    private MyHouseSingleton myHouse;

    BetterTempSensorI(MyHouseSingleton myHouse){
        this.myHouse = myHouse;
    }

    @Override
    public String measure(Current current) {
        String result = "";
        int temp;
        if(myHouse.unit.equals("C")){
            result += "Temperatura wynosi: " + myHouse.temperature + " C, ";
            temp = 2 * this.myHouse.temperature + 32;
            result += temp + " F";
        }
        if(myHouse.unit.equals("F")){
            result += "Temperatura wynosi: " + myHouse.temperature + " F, ";
            temp = (this.myHouse.temperature - 32) / 2;
            result += temp + " C";
        }
        return result;
    }

}
