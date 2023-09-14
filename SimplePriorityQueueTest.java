package assign03;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
/*
 * @Author: Adarsh Sreeram & Stewart Russell
 * @Version: 09/14/2023
 * @Description: Unit tests for SimplePriorityQueue class
 */
class SimplePriorityQueueTest {

    private static int size = 10;
    /*
     * testPrims static Integer array
     */
    private static Integer[] testPrims = {
            0,
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
    };
    /*
     * testStrings static String array
     */
    private static String[] testStrings = {
            "Add",
            "Apple",
            "Kerplunk",
            "Meanie",
            "Nah",
            "Shdonk",
            "Shop",
            "Tuvnicker",
            "Vindicate",
            "Zombie",
    };
    /*
     * testSubjects static Person array
     */
    private static Person[] testSubjects = {
            new Person(40, "Bruce Wane"),
            new Person(26, "Barry Allen"),
            new Person(40, "Clark Kent"),
            new Person(18, "Peter Parker"),
            new Person(40, "Tony Stark"),
            new Person(40, "Bruce Banner"),
            new Person(1500, "Thor Odinson"),
            new Person(5000, "Diana Prince"),
            new Person(30, "Wanda Maximoff"),
            new Person(53, "Clint Barton"),
            new Person(179, "Steve Rodgers"),
    };
    /*
     * Static variables for SimplePriorityQueue
     */
    private static SimplePriorityQueue<Integer> queuePrim;
    private static SimplePriorityQueue<String> queueString;
    private static SimplePriorityQueue<Person> queueObj;
    private static SimplePriorityQueue<Person> queueCom;

    @BeforeEach
    void setUp()
    {
        // initialize all queues
        queuePrim = new SimplePriorityQueue<>();
        queueString = new SimplePriorityQueue<>();
        queueObj = new SimplePriorityQueue<>();
        queueCom = new SimplePriorityQueue<>((a, b) -> a.name.compareTo(b.name));

        // insert test variables
        queuePrim.insertAll(arrayToList(testPrims));
        queueString.insertAll(arrayToList(testStrings));
        queueObj.insertAll(arrayToList(testSubjects));
        queueCom.insertAll(arrayToList(testSubjects));
    }

    // helper method
    static <T> List<T> arrayToList(T[] array)
    {
        List<T> output = new ArrayList<>();
        Collections.addAll(output, array);
        return output;
    }
    /*
     * size test method that checks the size of the queue, which has primitive variables
     * Leveraging assertEquals to test the accuracy
     */
    @Test
    void size() {
        assertEquals(size, queuePrim.size());
    }
    //isEmpty test method that checks if the queue is empty, which has primitive variables
    @Test
    void isEmpty()
    {
        assertFalse(queuePrim.isEmpty());
        assertTrue(new SimplePriorityQueue<Integer>().isEmpty());
    }
    // clear test method that checks if all elements in the queue are cleared when the user elects to do so.
    @Test
    void clear()
    {
        SimplePriorityQueue<Integer> clearTest = new SimplePriorityQueue<>();
        clearTest.insertAll(Arrays.asList(1, 3, 4, 7, 8));
        clearTest.clear();
        assertTrue(clearTest.isEmpty());
    }
    //getFromEmpty test method that checks if an exception is thrown, when the queue is empty, which has primitive variables
    @Test
    void getFromEmpty()
    {
        SimplePriorityQueue<Integer> queue = new SimplePriorityQueue<>();

        assertThrows(NoSuchElementException.class, queue::findMax);
        assertThrows(NoSuchElementException.class, queue::deleteMax);
    }

    //findMaxPrim test method that checks whether the maxmium element has been returned from a queue of primitive variables.
    @Test
    void findMaxPrim() {
        int test = 9;
        assertEquals(test, queuePrim.findMax());
    }
    //deleteMaxPrim test method that checks whether the maxmium element is deleted  from the queue, which has primitive variables
    void deleteMaxPrim() {
        int test = 9;
        int finalTest = 8;
        assertEquals(test, queuePrim.deleteMax());
        assertEquals(finalTest, queuePrim.findMax());  // The next maximum should be 8 after 9 is deleted
    }
    //insertPrim test method that checks whether an element was inserted into the queue, which has primitive variables
    @Test
    void insertPrim() {
        queuePrim.insert(10);
        int test = 10;
        assertEquals(test, queuePrim.findMax());  // Now, 10 should be the maximum
    }
    //insertAllPrim test method that checks whether  multiple elements were inserted into the queue, which has primitive variables.
    @Test
    void insertAllPrim() {
        List<Integer> newList = Arrays.asList(11, 12, 13);
        queuePrim.insertAll(newList);
        assertEquals(Integer.valueOf(13), queuePrim.findMax());  // The maximum should now be 13
    }
    //containsPrim test method that checks if an element is in the queue, which has primitive variables
    @Test
    void containsPrim() {
        assertTrue(queuePrim.contains(3));
        assertFalse(queuePrim.contains(10));
    }

    // with Strings

    //findMaxString test method that checks -> returns the maximum element in the queue, which has String variables
    @Test
    void findMaxString() {
        assertEquals("Zombie", queueString.findMax());
    }
    //deleteMaxString test method that checks-> deletes the maximum element in the queue, which has String variables
    @Test
    void deleteMaxString() {
        assertEquals("Zombie", queueString.deleteMax());
        assertEquals("Vindicate",queueString.findMax());
    }
    //insertString test method that checks if a new string has been inserted into the queue,
    // which has string variables
    @Test
    void insertString() {
        queueString.insert("Zute");
        assertEquals("Zute",queueString.findMax());
    }
    //insertAllString test method that checks if multiple strings have been inserted into the queue
    @Test
    void insertAllString() {
        List<String> newString = Arrays.asList("Alpha","Beta");
        queueString.insertAll(newString);
        assertTrue(queueString.contains("Alpha"));
        assertTrue(queueString.contains("Beta"));
        assertEquals("Zombie", queueString.findMax());
    }
    // containsString test method that checks if a string exists in the queue
    @Test
    void containsString() {
        //False test case for queueString
        assertFalse(queueString.contains("Omega"));
        //True test case for queueString
        assertTrue(queueString.contains("Zombie"));
    }

    // with objects
    // default
    //findMaxObj test method that checks if the maximum element is returned from the queue, which has object variables
    @Test
    void findMaxObj() {
        assertEquals(new Person(5000, "Diana Prince"), queueObj.findMax());
        assertNotEquals(new Person(18, "Peter Parker"), queueObj.findMax());
    }
    //deleteMaxObj test method that checks if the maximum element is deleted from the queue, which has object variables
    @Test
    void deleteMaxObj() {
        assertEquals(new Person(5000, "Diana Prince"), queueObj.deleteMax());
        assertEquals(new Person(1500, "Thor Odinson"), queueObj.findMax());
    }
    //insertObj test method that checks if an element is inserted into the queue, which has object variables
    @Test
    void insertObj() {
        queueObj.insert(new Person(35, "Jack White"));
        assertTrue(queueObj.contains(new Person(35, "Jack White")));
    }
    //insertAllObj test method that checks if multiple elements have been inserted into the queue, which has object variables.
    @Test
    void insertAllObj() {
        List<Person> subjectList = Arrays.asList(new Person(62, "Eduardo Dorrance"), new Person(56, "Norman Osborn"), new Person(1000, "Thanos"));
        queueObj.insertAll(subjectList);
        assertTrue(queueObj.contains(new Person(62, "Eduardo Dorrance")));
        assertTrue(queueObj.contains(new Person(56, "Norman Osborn")));
        assertTrue(queueObj.contains(new Person(1000, "Thanos")));
    }
    //containsObj test method that returns a boolean, while checking if an element exists in the queue, which has object variables.
    @Test
    void containsObj() {
        assertTrue(queueObj.contains(new Person(179, "Steve Rodgers")));
    }

    // comparator

    //findMaxCom test method that checks if the maximum element is returned from the queue, which has comparator variables
    @Test
    void findMaxCom() {
        assertEquals(new Person(30, "Wanda Maximoff"), queueCom.findMax());
        assertNotEquals(new Person(1500, "Thor Odinson"), queueCom.findMax());
    }
    //deleteMaxCom test method that checks if the maximum element is deleted from the queue, which has comparator variables
    @Test
    void deleteMaxCom() {
        assertEquals(new Person(30, "Wanda Maximoff"), queueCom.deleteMax());
        assertEquals(new Person(40, "Tony Stark"), queueCom.findMax());
    }
    //insertCom test method that checks if an element is inserted into the queue, which has comparator variables
    @Test
    void insertCom() {
        queueCom.insert(new Person(35, "Jack White"));
        assertTrue(queueCom.contains(new Person(35, "Jack White")));
    }
    //insertAllCom test method that checks if multiple elements have been inserted into the queue, which has comparator
    @Test
    void insertAllCom() {
        List<Person> subjectList = Arrays.asList(new Person(62, "Eduardo Dorrance"), new Person(56, "Norman Osborn"), new Person(1000, "Thanos"));
        queueCom.insertAll(subjectList);
        assertTrue(queueCom.contains(new Person(62, "Eduardo Dorrance")));
        assertTrue(queueCom.contains(new Person(56, "Norman Osborn")));
        assertTrue(queueCom.contains(new Person(1000, "Thanos")));
    }
    // containsCom test method that returns a boolean, while checking if an element exists in the queue, which has comparator variables.
    @Test
    void containsCom() {
        assertTrue(queueCom.contains(new Person(179, "Steve Rodgers")));
    }
    // Static Person class that implements Java's comparator tool
    static class Person implements Comparable<Person> {

        public int age;
        public String name;
        /*
         * Person constructor
         * @param: int age, String name
         * @return: void
         */
        public Person(int age, String name)
        {
            this.age = age;
            this.name = name;
        }
        /*
         * Comparable constructor that returns a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
         * @return: int age difference
         */
        @Override
        public int compareTo(Person o) {
            return this.age - o.age;
        }
        /*
         * equals method that returns a boolean value based on the comparison of two objects.
         * @param: Object o
         * @return: boolean
         */
        @Override
        public boolean equals(Object o)
        {
            if(!(o instanceof Person)) return false;

            Person other = (Person) o;

            return other.age == this.age && this.name.equals(other.name);
        }
        /*
         * toString method that returns a string representation of this object.
         * @return: String
         * @param: None
         */
        @Override
        public String toString()
        {
            return this.name + ", " + this.age;
        }
    }
}