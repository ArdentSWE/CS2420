package assign03;
import java.util.Comparator;
public class PersonComparator implements Comparator<Person>{
    public int compare(Person a, Person b){
        return a.age-b.age;
    }
}