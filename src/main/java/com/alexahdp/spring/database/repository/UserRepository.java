package com.alexahdp.spring.database.repository;

import com.alexahdp.spring.database.entity.Role;
import com.alexahdp.spring.database.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.firstname like %:firstname% AND u.lastname like %:lastname%")
    List<User> findAllBy(String firstname, String lastname);

    @Query(
            value = "SELECT * FROM users u WHERE u.username like :username",
            nativeQuery = true
    )
    List<User> findAllByUsername(String username);

    @Lock(LockModeType.OPTIMISTIC)
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update User u set u.role = :role where u.id in (:ids)")
    int updateRole(Role role, Long... ids);

    Optional<User> findTopByOrderByIdDesc();

    List<User> findTop3ByBirthDateBefore(LocalDate birthDate, Sort sort);

    @Query(
            value = "select u from User u",
            countQuery = "select count(distinct u.firstname) from User u"
    )
    @EntityGraph(attributePaths = {"company", "company.locales"})
    Page<User> findAllBy(Pageable pageable);
}


//public class UserRepository {
//    @Value("${spring.datasource.url}")
//    private final String dbUrl;
//
////    public Optional<User> findById(Long id) {
////        return Optional.of(new User(
////                id,
////                "username",
////                LocalDate.of(1990, 1, 1),
////                "John",
////                "Doe",
////                Role.ADMIN
////        ));
////    }
//
//    public List<User> findAll() {
////        var u = new User.builder()
////                .id(1L)
////                .username("username")
////                .birthDate(LocalDate.of(1990, 1, 1))
////                .firstname("John")
////                .lastname("Doe")
////                .role(Role.ADMIN)
////                .build();
//        var u = new User(
//                1L,
//                "username",
//                LocalDate.of(1990, 1, 1),
//                "John",
//                "Doe",
//                Role.ADMIN,
//                null,
//                null
//        );
//        return List.of(u);
//    }
//
//    public Optional<User> findById(Long id) {
//        var u = new User(
//                id,
//                "username",
//                LocalDate.of(1990, 1, 1),
//                "John",
//                "Doe",
//                Role.ADMIN,
//                null,
//                null
//        );
//        return Optional.of(u);
//    }
//
//    public User create() {
//        var u = new User(
//                1L,
//                "username",
//                LocalDate.of(1990, 1, 1),
//                "John",
//                "Doe",
//                Role.ADMIN,
//                null,
//                null
//        );
//        return u;
//    }
//}
