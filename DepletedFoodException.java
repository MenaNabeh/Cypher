//Custom exception used when a food source is empty.
public class DepletedFoodException extends Exception {
    public DepletedFoodException(String message) {
        super(message);
    }
}