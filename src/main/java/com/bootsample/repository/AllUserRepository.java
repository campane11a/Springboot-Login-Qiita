package com.bootsample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bootsample.entity.MyUser;

@Repository
public interface AllUserRepository extends JpaRepository<MyUser,Object>{

}
