package pullupmethod;

// Antes
/*
public class Unit {
    protected int health;
}

class Soldier extends Unit {
    int getHealth() {
        return health;
    }
}

class Tank extends Unit {
    int getHealth() {
        return health;
    }
}
*/

// Después
public class Unit {
    private int health;

    int getHealth() {
        return health;
    }
}

class Soldier extends Unit {

}

class Tank extends Unit {

}