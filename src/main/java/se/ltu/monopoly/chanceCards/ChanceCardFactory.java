package se.ltu.monopoly.chanceCards;

/**
 * Created by erikuusitalo on 28/10/16.
 */
public class ChanceCardFactory {

    ChanceCard chanceCard = null;

    public ChanceCard getChanceCard(String className) throws ClassNotFoundException{
        if(className.equals("Library")) {
            return new Library();
        }
        throw new ClassNotFoundException(className + " not fot a class");
    }


    private ChanceCard initChanceCard(String className) {
        try {
            chanceCard = (ChanceCard)Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return chanceCard;
    }
}
