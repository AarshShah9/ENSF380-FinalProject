package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * The ScheduleItemTest class is a JUnit test class that tests the ScheduleItem
 * clas
 * 
 * @author Sarim
 * @version 1.0
 * @date 2023-04-04
 */
public class ScheduleItemTest {
    // Test the ScheduleItem constructor with valid data
    @Test
    public void testScheduleItemConstructorValidData() {
        ArrayList<String> names = new ArrayList<>();

        String test = "Test";
        names.add(test);

        ScheduleItem temp = new ScheduleItem(names, 0, test, 0, 0, 0, 0);
        assertEquals(names, temp.getName());
        assertEquals(0, temp.getQuantity());
        assertEquals(test, temp.getDescription());
        assertEquals(0, temp.getStartHour());
        assertEquals(0, temp.getMaxWindow());
        assertEquals(0, temp.getDuration());
        assertEquals(0, temp.getPrepTime());
    }

    // Test the ScheduleItem constructor with invalid data
    @Test(expected = IllegalArgumentException.class)
    public void testTaskConstructorBadData() {
        ArrayList<String> test = new ArrayList<>();
        ScheduleItem temp = new ScheduleItem(test, 0, "test", 0, 0, 0, 0);
    }

    // Test the getName function
    @Test
    public void testGetName() {
        ArrayList<String> names = new ArrayList<>();

        String testName = "Test";
        names.add(testName);

        ScheduleItem temp = new ScheduleItem(names, 0, testName, 0, 0, 0, 0);
        ArrayList<String> result = temp.getName();
        System.out.println("getName");
        assertEquals("getName() value was incorrect: ", names, result);
    }

    // Test the getQuantity function
    @Test
    public void testGetQuantity() {
        ArrayList<String> names = new ArrayList<>();

        String testName = "Test";
        names.add(testName);

        ScheduleItem temp = new ScheduleItem(names, 0, testName, 0, 0, 0, 0);
        int result = temp.getQuantity();
        System.out.println("getQuantity");
        assertEquals("getQuantity() value was incorrect: ", 0, result);
    }

    // Test the getDescription function
    @Test
    public void testGetDescription() {
        ArrayList<String> names = new ArrayList<>();

        String testName = "Test";
        names.add(testName);

        ScheduleItem temp = new ScheduleItem(names, 0, testName, 0, 0, 0, 0);
        String result = temp.getDescription();
        System.out.println("getDescription");
        assertEquals("getDescription() value was incorrect: ", testName, result);
    }

    // Test the getStartHour function
    @Test
    public void testGetStartHour() {
        ArrayList<String> names = new ArrayList<>();

        String testName = "Test";
        names.add(testName);

        ScheduleItem temp = new ScheduleItem(names, 0, testName, 0, 0, 0, 0);
        int result = temp.getStartHour();
        System.out.println("getStartHour");
        assertEquals("getStartHour() value was incorrect: ", 0, result);
    }

    // Test the getMaxWindow function
    @Test
    public void testGetMaxWindow() {
        ArrayList<String> names = new ArrayList<>();

        String testName = "Test";
        names.add(testName);

        ScheduleItem temp = new ScheduleItem(names, 0, testName, 0, 0, 0, 0);
        int result = temp.getMaxWindow();
        System.out.println("getMaxWindow");
        assertEquals("getMaxWindow() value was incorrect: ", 0, result);
    }

    // Test the getDuration function
    @Test
    public void testGetDuration() {
        ArrayList<String> names = new ArrayList<>();

        String testName = "Test";
        names.add(testName);

        ScheduleItem temp = new ScheduleItem(names, 0, testName, 0, 0, 0, 0);
        int result = temp.getDuration();
        System.out.println("getDuration");
        assertEquals("getDuration() value was incorrect: ", 0, result);
    }

    // Test the getPrepTime function
    @Test
    public void testGetPrepTime() {
        ArrayList<String> names = new ArrayList<>();

        String testName = "Test";
        names.add(testName);

        ScheduleItem temp = new ScheduleItem(names, 0, testName, 0, 0, 0, 0);
        int result = temp.getPrepTime();
        System.out.println("getPrepTime");
        assertEquals("getPrepTime() value was incorrect: ", 0, result);
    }

    // Test the setName function
    @Test
    public void testSetName() {
        ArrayList<String> names = new ArrayList<>();

        String testName = "Test";
        names.add(testName);

        ScheduleItem temp = new ScheduleItem(names, 0, testName, 0, 0, 0, 0);

        String test = "Test2";
        names.set(0, test);
        temp.setName(names);
        ArrayList<String> result = temp.getName();

        System.out.println("setName");
        assertEquals("setName() value was incorrect: ", names, result);
    }

    // Test the setQuantity function
    @Test
    public void testSetQuantity() {
        ArrayList<String> names = new ArrayList<>();

        String testName = "Test";
        names.add(testName);

        ScheduleItem temp = new ScheduleItem(names, 0, testName, 0, 0, 0, 0);

        int test = 1;
        temp.setQuantity(test);
        int result = temp.getQuantity();

        System.out.println("setQuantity");
        assertEquals("setQuantity() value was incorrect: ", test, result);
    }

    // Test the setStartHour function
    @Test
    public void testSetStartHour() {
        ArrayList<String> names = new ArrayList<>();

        String testName = "Test";
        names.add(testName);

        ScheduleItem temp = new ScheduleItem(names, 0, testName, 0, 0, 0, 0);

        int test = 1;
        temp.setStartHour(test);
        int result = temp.getStartHour();

        System.out.println("setStartHour");
        assertEquals("setStartHour() value was incorrect: ", test, result);
    }

    // Test the setDuration function
    @Test
    public void testSetDuration() {
        ArrayList<String> names = new ArrayList<>();

        String testName = "Test";
        names.add(testName);

        ScheduleItem temp = new ScheduleItem(names, 0, testName, 0, 0, 0, 0);

        int test = 1;
        temp.setDuration(test);
        int result = temp.getDuration();

        System.out.println("setDuration");
        assertEquals("setDuration() value was incorrect: ", test, result);
    }

    // Test the setPrepTime function
    @Test
    public void testSetPrepTime() {
        ArrayList<String> names = new ArrayList<>();

        String testName = "Test";
        names.add(testName);

        ScheduleItem temp = new ScheduleItem(names, 0, testName, 0, 0, 0, 0);

        int test = 1;
        temp.setPrepTime(test);
        int result = temp.getPrepTime();

        System.out.println("setPrepTime");
        assertEquals("setPrepTime() value was incorrect: ", test, result);
    }

    // Test the addName function
    @Test
    public void testAddName() {
        ArrayList<String> names = new ArrayList<>();
        names.add("Test");
        ScheduleItem temp = new ScheduleItem(names, 0, "Test", 0, 0, 0, 0);

        ArrayList<String> testArr = new ArrayList<>();
        testArr.add("Test");
        testArr.add("Test2");
        ArrayList<String> tempArr = new ArrayList<>();
        tempArr.add("Test2");
        temp.addName(tempArr);

        System.out.println("setPrepTime");
        assertEquals("setPrepTime() value was incorrect: ", testArr, temp.getName());
    }

    // Test the addQuantity function
    @Test
    public void testAddQuantity() {
        ArrayList<String> names = new ArrayList<>();

        String testName = "Test";
        names.add(testName);

        ScheduleItem temp = new ScheduleItem(names, 0, testName, 0, 0, 0, 0);

        int test = 1;
        temp.addQuantity(test);
        int result = temp.getQuantity();

        System.out.println("addQuantity");
        assertEquals("addQuantity() value was incorrect: ", test, result);
    }

    // Test the addDuration function
    @Test
    public void testAddDuration() {
        ArrayList<String> names = new ArrayList<>();

        String testName = "Test";
        names.add(testName);

        ScheduleItem temp = new ScheduleItem(names, 0, testName, 0, 0, 0, 0);

        int test = 1;
        temp.addDuration(test);
        int result = temp.getDuration();

        System.out.println("addDuration");
        assertEquals("addDuration() value was incorrect: ", test, result);
    }
}
