package replaceconditionalwithpolymorphism;

// Antes
/*
public class Bird {
    enum birdType {
        EUROPEAN, AFRICAN, NORWEGIAN_BLUE
    }
    birdType type;
    int voltage;
    boolean isNailed;
    int numberOfCoconuts;
    double getSpeed() {
        switch (type) {
            case EUROPEAN:
                return getBaseSpeed();
            case AFRICAN:
                return getBaseSpeed() - getLoadFactor() * numberOfCoconuts;
            case NORWEGIAN_BLUE:
                return (isNailed) ? 0 : getBaseSpeed(voltage);
        }
        throw new RuntimeException("Should be unreachable");
    }
    double getBaseSpeed(){
        return 100;
    }
    double getLoadFactor(){
        return 0.8;
    }
    double getBaseSpeed(int voltage) {
        return 100 + voltage;
    }
}
 */

// Después
abstract class Bird {
    int voltage;
    boolean isNailed;
    int numberOfCoconuts;
    abstract double getSpeed();
    double getBaseSpeed(){
        return 100;
    }
    double getLoadFactor(){
        return 0.8;
    }
    double getBaseSpeed(int voltage) {
        return 100 + voltage;
    }
}

class European extends Bird {
    @Override
    double getSpeed() {
        return getBaseSpeed();
    }
}
class African extends Bird {
    @Override
    double getSpeed() {
        return getBaseSpeed() - getLoadFactor() * numberOfCoconuts;
    }
}
class NorwegianBlue extends Bird {
    @Override
    double getSpeed() {
        return (isNailed) ? 0 : getBaseSpeed(voltage);
    }
}
