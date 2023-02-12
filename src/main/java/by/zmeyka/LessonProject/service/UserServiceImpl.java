package by.zmeyka.LessonProject.service;

import by.zmeyka.LessonProject.model.User;
import by.zmeyka.LessonProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        Optional<User> optionalUser=userRepository.findById(id);
        User user=null;
        if(optionalUser.isPresent()){
            user=optionalUser.get();
        }else{
            System.out.println("User not found with id= "+id);
        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
        System.out.println("Saving user with id= "+ user.getId());

    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
        System.out.println("user with id = "+id+"has been deleted");

    }

    @Override
    public Page<User> pagination(int page, int size, String sortByField, String sortDir) {

        Sort sort=sortDir.equals("ASC")?Sort.by(sortByField).ascending():Sort.by(sortByField).descending();
        Pageable pageable= PageRequest.of(page-1,size,sort);
        return userRepository.findAll(pageable);
    }
}
