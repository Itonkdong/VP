package mk.ukim.finki.wpaud.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wpaud.model.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder
{
    public static List<Category> categories = null;
    public static long categoriesNextId;

    @PostConstruct
    public void init()
    {
        categories = new ArrayList<>();
        categories.add(new Category(0L, "Food", "Delicious Food"));
        categories.add(new Category(1L, "Clothes", "Beautiful Clothes"));
        categories.add(new Category(2L, "Electronics", "Future Electronics"));
        categoriesNextId = categories.size();
    }

}
