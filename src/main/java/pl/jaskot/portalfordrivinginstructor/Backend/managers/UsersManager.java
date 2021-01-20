package pl.jaskot.portalfordrivinginstructor.Backend.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jaskot.portalfordrivinginstructor.Backend.configuration.AppSettings;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.ExamScore;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.User;
import pl.jaskot.portalfordrivinginstructor.Backend.repository.ExamScoreRepo;
import pl.jaskot.portalfordrivinginstructor.Backend.repository.UsersRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersManager {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private ExamScoreRepo examScoreRepo;

    private User mainUser;

    public UsersManager(UsersRepo usersRepo, ExamScoreRepo examScoreRepo){
        this.usersRepo = usersRepo;
        this.examScoreRepo = examScoreRepo;
    }

    public boolean addUser(User user) {
        usersRepo.save(user);
        return true;
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

    public boolean addMainUserReservation(){
        if(mainUser.getLessonsLimit() >= AppSettings.getReservationLimit()){
            return false;
        }else {
            mainUser.setLessonsLimit(mainUser.getLessonsLimit() + 1);
            usersRepo.save(mainUser);
            return true;
        }
    }

    public void minusMainUserReservation(){
        mainUser.setLessonsLimit(mainUser.getLessonsLimit() - 1);
        usersRepo.save(mainUser);
    }

    public void minusUserReservationLimit(Long id){
        getUsers().forEach(user -> minusReservation(user,id));
    }

    private void minusReservation(User user, Long id){
        if(user.getId() == id){
            user.setLessonsLimit(user.getLessonsLimit() - 1);
            usersRepo.save(user);
        }
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

    public boolean checkUserData(String email, String password){
        User user;
        try{
            user =  usersRepo.findByEmail(email);
            if(user.getPassword().equals(password)){
                mainUser = user;
                return true;
            }else {
                return false;
            }
        } catch (Exception e){
            return false;
        }
    }

    public void saveExamScore(ExamScore examScore){
        examScoreRepo.save(examScore);
        List<ExamScore> scores;
        try{
            scores = mainUser.getExamScores();
        }catch (Exception e){
            scores = new ArrayList<ExamScore>();
        }
        if(examScore.isPassed()){
            mainUser.setExamPassed(true);
        }
        scores.add(examScore);
        mainUser.setExamScores(scores);
        usersRepo.save(mainUser);
    }

    public List<ExamScore> getExamScore(){
        return mainUser.getExamScores();
    }
}
