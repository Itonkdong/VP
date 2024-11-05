package mk.ukim.finki.wpaud.repository;

import mk.ukim.finki.wpaud.bootstrap.DataHolder;
import mk.ukim.finki.wpaud.model.Category;
import mk.ukim.finki.wpaud.model.exception.InstanceAlreadyExistsException;
import mk.ukim.finki.wpaud.model.exception.InstanceDoesNotExistException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryCategoryRepository
{
    public List<Category> findAll()
    {
        return DataHolder.categories;
    }

    public List<Category> findAllFilter(String name)
    {
        return DataHolder.
                categories
                .stream()
                .filter(category -> category.getName().contains(name))
                .collect(Collectors.toList());
    }

    public Optional<Category> find(long id)
    {
        return DataHolder
                .categories
                .stream()
                .filter(category -> category.getId() == id)
                .findFirst();
    }

    public Optional<Category> findByName(String name)
    {
        return DataHolder
                .categories
                .stream()
                .filter(category -> category.getName().equals(name))
                .findFirst();

    }

    public Optional<Category> save(Category category) throws InstanceAlreadyExistsException
    {
        if (DataHolder
                .categories
                .stream()
                .anyMatch(category1 -> category1.getName().equals(category.getName())))
        {
            throw new InstanceAlreadyExistsException("Category");
        }

        long _newEntryId = DataHolder.categoriesNextId++;
        category.setId(_newEntryId);

        DataHolder.categories.add(category);

        return Optional.of(category);
    }

    public Optional<Category> update(Category category) throws InstanceDoesNotExistException
    {
        Optional<Category> categoryInDb = this.find(category.getId());
        if (categoryInDb.isEmpty())
        {
            throw new InstanceDoesNotExistException("Category", category.getId());
        }

        Category categoryUpdated = categoryInDb.get();
        categoryUpdated.setName(category.getName());
        categoryUpdated.setDescription(category.getDescription());

        return Optional.of(categoryUpdated);
    }

    public Optional<Category> delete(long id) throws InstanceDoesNotExistException
    {
        Optional<Category> optionalCategory = this.find(id);
        if (optionalCategory.isEmpty())
        {
            throw new InstanceDoesNotExistException("Category", id);

        }
        DataHolder.categories.remove(optionalCategory.get());

        return optionalCategory;
    }
}
