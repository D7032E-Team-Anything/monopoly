package se.ltu.monopoly.chanceCards;

/**
 * Created by erikuusitalo on 28/10/16.
 */
public class ChanceCardFactory {

    public ChanceCard getChanceCard(String className) throws ClassNotFoundException{
        if(className.equals("Library")) {
            return new Library();
        }
        throw new ClassNotFoundException(className + " is not a class");
    }


}
