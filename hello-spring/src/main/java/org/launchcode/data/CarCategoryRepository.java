package org.launchcode.data;

import org.launchcode.models.CarCategory;
import org.springframework.data.repository.CrudRepository;

public interface CarCategoryRepository extends CrudRepository<CarCategory, Integer> {
}
