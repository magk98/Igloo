package sr.ice.server;

import com.zeroc.Ice.Current;
import com.zeroc.Ice.Object;
import com.zeroc.Ice.ServantLocator;
import com.zeroc.Ice.UserException;

public class MyServantLocator implements ServantLocator {
    @Override
    public LocateResult locate(Current current) throws UserException {
        switch (current.id.category) {
            case "fridge":
                return new LocateResult(new FridgeI(5), null);
            case "wm":
                return new LocateResult(new WashingMachineI(), null);
            case "superTemp":
                return new LocateResult(new SuperTempSensorI(MyHouseSingleton.getInstance()), null);
            case "Temp":
                return new LocateResult(new TempSensorI(MyHouseSingleton.getInstance()), null);
            case "betterTemp":
                return new LocateResult(new BetterTempSensorI(MyHouseSingleton.getInstance()), null);
        }
        return new LocateResult();
    }

    @Override
    public void deactivate(String s) {

    }

    @Override
    public void finished(com.zeroc.Ice.Current current, com.zeroc.Ice.Object servant, java.lang.Object cookie) throws UserException {
        System.out.println(1);
    }
}
