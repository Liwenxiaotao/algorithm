package tao.com;

import org.junit.Test;
import tao.com.arrayList.Person;
import tao.com.arrayList.ArrayList;

public class ArrayListTest {
    @Test
    public void arrayListTest() {
        ArrayList<Person> array = new ArrayList<Person>();
        for (int i = 0; i < 5; i++) {
            Person person = new Person(i, "tao" + 1);
            array.add(person);
        }
        System.out.println(array.toString());
    }
}
