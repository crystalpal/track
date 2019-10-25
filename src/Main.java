import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");

        Item model = new Item("testje", false, 51.00);
        validateGadget(model).forEach(e -> System.out.println(e.getMessage()));

    }

    public static List<ItemError> validateGadget(Item order) {
      return order.track()
              .next(Main::isLargerThan10, () -> createError("the name is too long!"))
              .next(Item::easilyLost, () -> createError("item can be found!"))
              .next(Main::isCheap, () -> createError("gadgets must be cheap!"))
              .getErrors();
    }

    public static boolean isLargerThan10(Item model) {
        return model.getName().length() > 10;
    }

    public static boolean isCheap(Item order) {
        return order.getPrice() < 50;
    }

    public static ItemError createError(String message){
        return new ItemError(message);
    }
}
