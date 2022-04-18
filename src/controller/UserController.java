package controller;

import model.User;
import service.staffService.UserServiceIPLM;

import java.util.List;

public class UserController {
    public void showInfo(){
        System.out.println(new UserServiceIPLM().showInfo());
    }

    public void editInfo(User user){
        new UserServiceIPLM().editInfo(user);
    }

    public void showList(){
        System.out.println(new UserServiceIPLM().findAll());
    }

    public void deleteById(int id){
        new UserServiceIPLM().deleteById(id);
    }

    public void editById(User user){
        new UserServiceIPLM().editById(user);
    }
}
