package org.launchcode.data;

import org.launchcode.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
    User findByPassword(String password);
}
