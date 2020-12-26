package pl.jaskot.portalfordrivinginstructor.Backend.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.User;
import pl.jaskot.portalfordrivinginstructor.Backend.repository.UsersRepo;

import java.util.List;

@Service
public class UsersManager {

    @Autowired
    private UsersRepo usersRepo;

    private User mainUser;

    public UsersManager(UsersRepo usersRepo){
        this.usersRepo = usersRepo;
    }

    public void addUser(User user) {
        usersRepo.save(user);
    }

    public List<User> getUsers(){
        return (List<User>) usersRepo.findAll();
    }

    public void deleteUser(User user){
        usersRepo.delete(user);
    }

    public void putMainUser(User user){
        mainUser = user;
    }

    public void dropMainUser(){
        mainUser = null;
    }

    public User getMainUser(){
        return mainUser;
    }

    public boolean isAdmin(){
        if(mainUser == null){
            return false;
        }
        else {
            return mainUser.isAdmin();
        }
    }

    public boolean isActive(){
        if(mainUser == null){
            return false;
        }
        else {
            return true;
        }
    }
}
