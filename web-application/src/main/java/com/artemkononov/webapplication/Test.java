package com.artemkononov.webapplication;

import com.artemkononov.webapplication.entities.security.Role;
import com.artemkononov.webapplication.entities.security.User;

import java.util.HashSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        Role user_role= new Role(1L,"ROLE_USER");
        Role admin_role=new Role(2L,"ROLE_ADMIN");

        Set<Role> set=new HashSet<>();

        set.add(user_role);
        set.add(admin_role);

        User user=new User(null,"Petr","$2a$12$yJq/35VKaBMQT.JMoaznpueJxCvgGvkziieT6sAG2EUCieJab.qU6","Petr@mail.ru",set);

        System.out.println(user.getAllRoles());
    }
}
