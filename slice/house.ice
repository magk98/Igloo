
#ifndef HOUSE_ICE
#define HOUSE_ICE

module House{
  sequence<string> houseDevices;

    struct myHouse{
        houseDevices devices;
        int temperature;
    }

    struct Cloth{
        string name;
        int timeToClean;
    };

    sequence<Cloth> Clothes;

    struct FridgeDevice{
        string name;
        int temperature;
    }

  interface Fridge{
    int cool(int temp);
    int heat(int temp);
    int showTemperature();
  };


  interface TempSensor{
    int measure();
  };

  interface SuperTempSensor{
    int measure();
    void heatHouse(int temp);
    void coolHouse(int temp);
    void changeTempUnit(string from, string to);
  };

  interface WashingMachine{
    void wash(string mode);
    Clothes takeWashedClothesAfterTime(int minutes);
    void dry(string mode);
  };

};

#endif
