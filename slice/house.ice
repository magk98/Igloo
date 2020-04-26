
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

  interface BetterTempSensor{
    int measure();
    string showFormula();
  }

  interface SuperTempSensor{
    int measure();
    int heatHouse(int temp);
    int coolHouse(int temp);
    void changeTempUnit();
    string showUnit();
  };

  interface WashingMachine{
    Clothes wash(string mode, Clothes clothes);
    string dry(string mode);
  };

};

#endif
