package model;

import java.util.List;
import java.util.Random;



import logic.Field;
import logic.Location;
import logic.Randomizer;


/**
 * A class representing shared characteristics of animals.
 * 
 * @author Ieme, Jermo, Yisong
 * @version 2012.01.29
 */
public abstract class Animal implements Actor, RabbitSickness
{
    // Whether the animal is alive or not.
    private boolean alive;
    // determine if the animal is infected or not
    private boolean infected;
    // The animal's field.
    private Field field;
    // The animal's position in the field.
    private Location location;
    // An animal's age.
    private int age;
    // An animal's food level, which is increased by eating.
    private int foodLevel;

    // The food value of a single animal. In effect, this is the
    // number of steps an animal can go before it has to eat again.
    public static final int WOLFS_FOOD_VALUE = 27;
    public static final int FOX_FOOD_VALUE = 18;
    public static final int RABBIT_FOOD_VALUE = 9;
    public static final int GRASS_FOOD_VALUE = 4;
    // A shared random number generator to control breeding.
    protected static final Random rand = Randomizer.getRandom();
    
    /**
     * Create a new animal at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Animal(Field field, Location location)
    {
        alive = true;
        this.field = field;
        setLocation(location);
    }
    
    /**
     * Make this animal act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to receive newly born animals.
     */
    public abstract void act(List<Actor> newAnimals);
    
    /**
     * Zorgt er voor dat er geen nakomeling worden geboren als er te weinig voesel zijn.
     * @return true als genoeg voedsel zijn
     * @return false als niet genoeg voedsel zijn
     */
    public abstract boolean survivalInstinct();
    
	/**
	 * @return true if infected
	 * @return false if not infected
	 */
	public abstract boolean isInfected();
	
	/**
	 * setter voor infected
	 * @param infected
	 */
	public void setInfected(boolean infected)
	{
		this.infected = infected;
	}
	
    /**
     * Check whether the animal is alive or not.
     * @return true if the animal is still alive.
     */
    public boolean isAlive()
    {
        return alive;
    }

    /**
     * Indicate that the animal is no longer alive.
     * It is removed from the field.
     */
    protected void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    /**
     * Increase the age. This could result in the animal's death.
     */
    protected void incrementAge()
    {
        setAge(getAge() + 1);
        if(getAge() > getMaxAge()) {
            setDead();
        }
    }
    
    /**
     * returns the maximum age of an animal type can live
     * @return agemaximum age of an animal type can live
     */
    protected abstract int getMaxAge();
    
    /**
     * Return the animal's location.
     * @return The animal's location.
     */
    protected Location getLocation()
    {
        return location;
    }
    
    /**
     * Place the animal at the new location in the given field.
     * @param newLocation The animal's new location.
     */
    public void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
    
    /**
     * Return the animal's field.
     * @return The animal's field.
     */
    protected Field getField()
    {
        return field;
    }
    
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    protected int breed()
    {
        int births = 0;
        if(survivalInstinct() && canBreed() && getRandom().nextDouble() <= getBreedingProbability()) {
            births = getRandom().nextInt(getMaxLitterSize()) + 1;
        }
        return births;
    }
    
    /**
     * An animal can breed if it has reached the breeding age.
     * @return true if the animal can breed, false otherwise.
     */
    protected abstract boolean canBreed();
    
    /**
     * Returns an animals current age
     * @return age int the current age of an animal
     */
	protected int getAge() 
	{
		return age;
	}
	
    /**
     * set an animals current age
     * @param age int set the current age
     */
	protected void setAge(int age) 
	{
		this.age = age;
	}
	
    /**
     * Returns an animals current foodlevel
     * @return foodLevel int current foodlevel
     */
	protected int getFoodLevel() 
	{
		return foodLevel;
	}
	
    /**
     * set an animals current foodLevel
     * @param foodLevel int set the current foodLevel
     */
	protected void setFoodLevel(int foodLevel) 
	{
		this.foodLevel = foodLevel;
	}
	
	/**
	 * Getter om Rand op te halen
	 * @return rand random generator number
	 */
	protected Random getRandom()
	{
		return rand;
	}
	
	/**
	 * getter om infected op te halen
	 * @return infected boolean
	 */
	protected boolean getInfected()
	{
		return infected;
	}
	
    /**
     * Getter om MAX_LITTER_SIZE op te halen
     * @return MAX_LITTER_SIZE maximum litter
     */
    protected abstract int getMaxLitterSize();
    
    /**
     * Getter om BREEDING_PROBABILITY op te halen
     * @return BREEDING_PROBABILITY breeding kansen
     */
    protected abstract double getBreedingProbability();
}