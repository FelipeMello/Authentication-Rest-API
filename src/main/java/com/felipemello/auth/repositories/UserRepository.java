package com.felipemello.auth.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.felipemello.auth.models.User;


@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByNickNameAndPassword(String nickName, String password);

    Optional<User> findByEmailOrNickName(String email, String nickName);

    Optional<User> findByEmail(String email);

    Optional<User> findByNickName(String nickName);

}
