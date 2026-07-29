package api.ahm.motogp.shared.category.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Integer id) {
        super("Category ID: " + id + " not found");
    }
}
