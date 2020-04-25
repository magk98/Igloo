
#ifndef HOUSE_ICE
#define HOUSE_ICE

module House{
  sequence<string> houseDevices;

    struct MyHouse{
        houseDevices devices;
        int temperature;
    };

    struct Cloth{
        string name;
        int timeToClean;
    };

    sequence<Cloth> Clothes;

    struct FridgeDevice{
        string name;
        int temperature;
        string category;
    }

  interface Fridge{
    int cool();
    int heat();
    int showTemperature();
  };


  interface TempSensor{
    int measure();
  };

  interface SuperTempSensor{
    int measure();
    int heatHouse(int temp);
    int coolHouse(int temp);
    void changeTempUnit();
    string showUnit();
  };

  interface WashingMachine{
    void wash(string mode);
    Clothes takeWashedClothesAfterTime(int minutes);
    void dry(string mode);
    bool isDone();
  };

};

#endif
