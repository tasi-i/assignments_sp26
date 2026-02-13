import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Wrapper class to run all JUnit tests for the DynamicArray project.
 * 
 * This class executes the DynamicArrayTest test suite and prints results
 * in a readable format for Gradescope or command-line grading.
 */
public class RunTests {

    /**
     * Main method that runs the DynamicArrayTest JUnit tests.
     *
     * @param args command-line arguments (ignored)
     */
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(DynamicArrayTest.class);

        System.out.println("Ran " + result.getRunCount() + " tests, " +
                "Failures: " + result.getFailureCount());

        if (!result.wasSuccessful()) {
            System.out.println("Failure details:");
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        } else {
            System.out.println("All tests passed successfully!");
        }
    }
}