package org.example.javafxdeneme2.User;

public class AdminUser extends User{
    public AdminUser(String email, String password){
        super(email,password);
    }

    @Override
    public boolean password_validate(){
        return getPassword().length()>=10;
    }

    // adminler için minimum 10 karakterli şifre

}
