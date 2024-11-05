package mk.ukim.finki.wpaud.service;

import mk.ukim.finki.wpaud.model.Category;
import mk.ukim.finki.wpaud.model.Result;

import java.util.List;

public interface CategoriesService
{
    List<Category> find(String categoryName);
    Result tryCreate(String name, String description);
}
