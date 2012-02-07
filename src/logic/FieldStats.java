package src.logic;

//import java.awt.Color;
import java.util.HashMap;

import src.logic.Counter;


/**
 * This class collects and provides some statistical data on the state 
 * of a field. It is flexible: it will create and maintain a counter 
 * for any class of object that is found within the field.
 * 
 * @author Ieme, Jermo, Yisong
 * @version 2012.01.29
 */
@SuppressWarnings("rawtypes")
public class FieldStats
{
    // Counters for each type of entity (fox, rabbit, etc.) in the simulation.
    
	private HashMap<Class, Counter> counters;
    // Whether the counters are currently up to date.
    private boolean countsValid;
    
<<<<<<< HEAD:view/FieldStats.java
    public static int foxCount = 0;
    public static int bearCount = 0;
    public static int rabbitCount = 0;
    public static int wolfCount = 0;
    public static int grassCount = 0;
    public static int hunterCount = 0;
=======
    public static int foxCount;
    public static int bearCount;
    public static int rabbitCount;
    public static int wolfCount;
    public static int grassCount;
>>>>>>> 01c116801d2f8ee6974248180fe33fec16f2865d:src/logic/FieldStats.java

    /**
     * Construct a FieldStats object.
     */
	public FieldStats()
    {
        // Set up a collection for counters for each type of animal that
        // we might find
        counters = new HashMap<Class, Counter>();
        countsValid = true;
    }

    /**
     * Get details of what is in the field.
     * @return A string describing what is in the field.
     */
    public String getPopulationDetails(Field field)
    {
        StringBuffer buffer = new StringBuffer();
        if(!countsValid) {
            generateCounts(field);
        }
        for(Class key : counters.keySet()) {
            Counter info = counters.get(key);
            int stringLength = info.getName().length();
            buffer.append(info.getName().substring(10,stringLength));	//	show info
            buffer.append(": ");
            buffer.append(info.getCount());
            buffer.append(' ');
            
            
            if(info.getName().equals("src.model.Fox")) {
            	foxCount = info.getCount();
            }
            
            if(info.getName().equals("src.model.Bear")) {
                bearCount = info.getCount(); }
            
            if(info.getName().equals("src.model.Rabbit")) {
                rabbitCount = info.getCount();
                // System.out.println(rabbitCount); rabbitCount wordt geupdate, Hunter neemt niet over.
                }
            
            if(info.getName().equals("src.model.Wolf")) {
                wolfCount = info.getCount(); }
            
<<<<<<< HEAD:view/FieldStats.java
            if(info.getName().equals("model.Grass")) {
                grassCount = info.getCount(); }
            
            if(info.getName().equals("model.Hunter")) {
                hunterCount = info.getCount(); }
            
            
=======
            if(info.getName().equals("src.model.Grass")) {
                grassCount = info.getCount(); }
>>>>>>> 01c116801d2f8ee6974248180fe33fec16f2865d:src/logic/FieldStats.java
        }
        return buffer.toString();
    }

    /**
     * Invalidate the current set of statistics; reset all 
     * counts to zero.
     */
    public void reset()
    {
        countsValid = false;
        for(Class key : counters.keySet()) {
            Counter count = counters.get(key);
            count.reset();
        }
    }

    /**
     * Increment the count for one class of animal.
     * @param animalClass The class of animal to increment.
     */
    public void incrementCount(Class animalClass)
    {
        Counter count = counters.get(animalClass);
        if(count == null) {
            // We do not have a counter for this species yet.
            // Create one.
            count = new Counter(animalClass.getName());
            counters.put(animalClass, count);
        }
        count.increment();
    }

    /**
     * Indicate that an animal count has been completed.
     */
    public void countFinished()
    {
        countsValid = true;
    }

    /**
     * Determine whether the simulation is still viable.
     * I.e., should it continue to run.
     * @return true If there is more than one species alive.
     */
    public boolean isViable(Field field)
    {
        // How many counts are non-zero.
        int nonZero = 0;
        if(!countsValid) {
            generateCounts(field);
        }
        for(Class key : counters.keySet()) {
            Counter info = counters.get(key);
            if(info.getCount() > 0) {
                nonZero++;
            }
        }
        return nonZero > 1;
    }
    
    /**
     * Retourneert de HashMap counter die de populatie per soort bijhoudt met class
     * @return counters populatie van een actor type
     */
    public HashMap<Class, Counter> getPopulation()
    {
    	return counters;
    }
    
//    /**
//     * Retourneert de Hashmap counter die de populatie per soort bijhoudt met kleur
//     * @return counters populatie van een actor type
//     */
//    public void HashMap<Color, Counter> getColorPopulation()
//    {
//    	HashMap<Class, Counter> stats
//    }
    
    /**
     * Generate counts of the number of foxes and rabbits.
     * These are not kept up to date as foxes and rabbits
     * are placed in the field, but only when a request
     * is made for the information.
     * @param field The field to generate the stats for.
     */
    void generateCounts(Field field)
    {
        reset();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                Object animal = field.getObjectAt(row, col);
                if(animal != null) {
                    incrementCount(animal.getClass());
                }
            }
        }
        countsValid = true;
    }
    
    /**
     * getter voor countsValid
     * @return countsValid
     */
    public boolean getCountsValid()
    {
    	return countsValid;
    }
}
