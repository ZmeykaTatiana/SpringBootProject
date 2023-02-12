package by.zmeyka.LessonProject.service;

import by.zmeyka.LessonProject.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(long id);
    void saveUser(User user);
    void deleteById(long id);
    Page<User> pagination(int page, int size, String SortByField, String SortDir);
}
