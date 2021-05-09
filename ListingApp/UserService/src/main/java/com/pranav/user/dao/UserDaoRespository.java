package com.pranav.user.dao;


import com.pranav.user.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDaoRespository extends JpaRepository<User, String> {

}
