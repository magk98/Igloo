package sr.ice.server;

import com.zeroc.Ice.Current;
import com.zeroc.Ice.Object;
import com.zeroc.Ice.ServantLocator;
import com.zeroc.Ice.UserException;

public class MyServantLocator implements ServantLocator {
    @Override
    public LocateResult locate(Current current) throws UserException {
        // Get the object identity. (We use the name member as the database key.)
        String name = current.id.name;

        // We have the state, instantiate a servant and return it.
        //
        return new ServantLocator.LocateResult(new FridgeI(10), null);
    }

    @Override
    public void deactivate(String s) {

    }

    @Override
    public void finished(com.zeroc.Ice.Current current, com.zeroc.Ice.Object servant, java.lang.Object cookie) throws UserException {
        System.out.println(1);
    }
}
