package com.tamayodev.springboot.repository;

import com.tamayodev.springboot.dto.UserDto;
import com.tamayodev.springboot.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findByUserEmail(String email);

    @Query("SELECT u FROM User u WHERE u.name LIKE ?1%")
    List<User> findAndSort(String name, Sort sort);

    List<User> findByName(String name);

    Optional<User> findByNameAndEmail(String name, String email);

    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name, String email);

    List<User> findByBirthdateBetween(LocalDate begin, LocalDate end);

    List<User> findByNameLikeOrderByIdDesc(String name);

    List<User> findByNameContainingOrderByIdDesc(String name);

    @Query("SELECT new com.tamayodev.springboot.dto.UserDto(u.id, u.name, u.birthdate) " +
            "FROM User u " +
            "WHERE u.birthdate = :userBirthdate " +
            "AND u.email = :userEmail")
    Optional<UserDto> getAllByBirthDateAndEmail(@Param("userBirthdate") LocalDate date,
                                                @Param("userEmail") String email);

    List<User> findAll();
}
