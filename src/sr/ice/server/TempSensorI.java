package sr.ice.server;

import House.MyHouse;
import House.TempSensor;
import com.zeroc.Ice.Current;

public class TempSensorI implements TempSensor {
    public MyHouse myHouse;

    public TempSensorI(MyHouse myHouse){
        this.myHouse = myHouse;
    }

    @Override
    public int measure(Current current) {
        return myHouse.temperature;
    }
}
