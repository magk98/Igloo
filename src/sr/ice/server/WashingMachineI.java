package sr.ice.server;

import House.Cloth;
import House.WashingMachine;
import com.zeroc.Ice.Current;

import java.util.LinkedList;

public class WashingMachineI implements WashingMachine {
    private String lastWashingMode;
    private Cloth[] clothes;

    WashingMachineI(){
        this.lastWashingMode = "";
        this.clothes = new Cloth[]{};
    }


    @Override
    public Cloth[] wash(String mode, Cloth[] clothes, Current current) {
        try{
            this.lastWashingMode = mode;
            int time = 0;
            LinkedList <Cloth> washedClothes = new LinkedList<>();
            switch(lastWashingMode){
                case "fast":
                    time = 10;
                    Thread.sleep(time * 100);
                    break;
                case "medium":
                    time = 30;
                    Thread.sleep(time * 100);
                    break;
                case "long":
                    time = 50;
                    Thread.sleep(time * 100);
                    break;
            }
            for (Cloth cloth : clothes) {
                if (cloth.timeToClean <= time)
                    washedClothes.add(cloth);
            }
            return washedClothes.toArray(new Cloth[0]);
        } catch (Exception e){
            e.printStackTrace();
        }
        return clothes;
    }

    @Override
    public String dry(String mode, Current current) {
        int time = 10;
        try{
            switch(mode){
                case "fast":
                    Thread.sleep(time * 100);
                    break;
                case "medium":
                    time = 20;
                    Thread.sleep(time * 100);
                    break;
                case "long":
                    time = 30;
                    Thread.sleep(time * 100);
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Suszono ubrania przez " + time + " minut";
    }

}
