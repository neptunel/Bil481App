import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;


class AppTest {
    @Test void appHasAGreeting() {
        App testClass = new App();
        assertNotNull(testClass.info(), "app must have a info");
    }
  @Test
   public void testFound() {
      ArrayList<Integer> array = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
      assertTrue(App.search(array, 2,1,"searching"));
    }

     @Test
    public void testNull() {
      assertFalse(App.findMin(null, 0,0,"null"));
    }
     @Test
    public void testEmptyArray() {
      ArrayList<Integer> array = new ArrayList<>();
      assertFalse(App.search(array, 1,1,"emptyArray"));
    }
	@Test
    public void noMessage() {
      ArrayList<Integer> array = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
      assertFalse(App.search(array, 2,2,""));
    }



}