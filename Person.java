
/**
 * @author Mohammad Mahdi Malmasi
 * @version 0.0.1
 */
public class Person
{

            /* Feilds */
    
    // the first name of the person
    private String firstName;

    // the last name of the person
    private String lastName;

    // the age of the person
    private int age;
    





    
          /* Constructor */
    
    public Person(String firstName, String lastName, int age)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }







            /* Methods */



    // * setter methods *

    /**
     * set the first name of the person
     * @param firstName : the first name of the person
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * set the last name of the person
     * @param lastName : the last name of the person
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * set the age of the person
     * @param age : the age of the person
     */
    public void setAge(int age) {
        this.age = age;
    }
    

    // * getter methods *

    /**
     * @return the first name of the person
     */
    public String getFirstName() 
    {
        return firstName;
    }
    /**
     * @return the last name of the person
     */
    public String getLastName() 
    {
        return lastName;
    }
    /**
     * @return the age of the person
     */
    public int getAge() 
    {
        return age;
    }
    

    @Override
    public String toString()
    {
        return "Person first name: " + firstName + ",  "
                +
               "Person last name: " + lastName + ",  "
                +
               "Person age: " + age + ".\n";
    }

    public void print()
    {
        System.out.println(this.toString());
    }
}

