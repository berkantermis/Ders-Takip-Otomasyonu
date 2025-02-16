package org.example.javafxdeneme2.User;

public class RegularUser extends User{

    public RegularUser(String email, String password){
        super(email, password);
    }

    @Override
    public boolean password_validate(){
        return getPassword().length()>=6;
    }

    // regular kullanıcılar için minimum 6 karakterli şifre

}
