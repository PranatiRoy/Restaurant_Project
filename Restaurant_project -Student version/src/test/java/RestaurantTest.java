import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    Restaurant restaurant,mockRestaurantClass;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void setUp(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        

    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant mockRestaurantClass=Mockito.mock(Restaurant.class);
        LocalTime fourOClock = LocalTime.parse("16:00:00");
        Mockito.when(mockRestaurantClass.getCurrentTime()).thenReturn(fourOClock);
        boolean currentOpenStatus=mockRestaurantClass.getCurrentTime().isAfter(restaurant.openingTime) && mockRestaurantClass.getCurrentTime().isBefore(restaurant.closingTime);
        assertEquals(true, currentOpenStatus);


    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant mockRestaurantClass=Mockito.mock(Restaurant.class);
        LocalTime elevenOClock = LocalTime.parse("23:00:00");
        Mockito.when(mockRestaurantClass.getCurrentTime()).thenReturn(elevenOClock);
        boolean currentOpenStatus=mockRestaurantClass.getCurrentTime().isAfter(restaurant.openingTime) && mockRestaurantClass.getCurrentTime().isBefore(restaurant.closingTime);
        assertEquals(false, currentOpenStatus);

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //Adding Feature Unit Test Cases
    @Test
    public void for_calculate_order_cost_selected_item_must_be_existed_in_menu_(){
        String itemName="Sweet corn soup";
        assertEquals("Sweet corn soup", restaurant.findItemByName(itemName));
    }
    @Test
    public void after_calculate_order_cost_result_will_always_be_non_negative_value(){
      String itemName="Sweet corn soup";
      assertEquals(119, restaurant.calculateOrderCost(itemName));
    }
    @Test
    public void for_calculate_order_cost_selected_item_not_existed_in_menu_should_throw_exception() throws itemNotFoundException {
        //WRITE UNIT TEST CASE HERE
        assertThrows(itemNotFoundException.class,()->restaurant.findItemByName("Some Invalid Name"));
        
    }

    
}