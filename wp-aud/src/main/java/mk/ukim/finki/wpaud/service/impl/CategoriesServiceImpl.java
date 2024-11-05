package mk.ukim.finki.wpaud.service.impl;

import mk.ukim.finki.wpaud.model.Category;
import mk.ukim.finki.wpaud.model.Result;
import mk.ukim.finki.wpaud.model.exception.InstanceAlreadyExistsException;
import mk.ukim.finki.wpaud.model.exception.InvalidArgumentsException;
import mk.ukim.finki.wpaud.model.helper.ErrorHandler;
import mk.ukim.finki.wpaud.repository.InMemoryCategoryRepository;
import mk.ukim.finki.wpaud.service.CategoriesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService
{
    private final InMemoryCategoryRepository categoryRepository;

    public CategoriesServiceImpl(InMemoryCategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> find(String categoryName)
    {
        if (categoryName == null)
        {
            return categoryRepository.findAll();
        }

        return categoryRepository.findAllFilter(categoryName);
    }

    @Override
    public Result tryCreate(String name, String description)
    {
        try
        {
            ErrorHandler.checkNull(name, description);
        } catch (InvalidArgumentsException e)
        {
            return new Result(e);
        }

        Category category = new Category(0L, name, description);
        try
        {
            categoryRepository.save(category);
        } catch (InstanceAlreadyExistsException e)
        {
            return new Result(e);
        }

        return Result.successfulResult();
    }
}
