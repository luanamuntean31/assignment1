package org.launchcode.data;

import org.launchcode.models.Year;
import org.springframework.data.repository.CrudRepository;

public interface YearRepository extends CrudRepository<Year, Integer> {
}