package com.alexahdp.spring.database.repository;

import com.alexahdp.spring.database.entity.Role;
import com.alexahdp.spring.database.entity.User;
import com.alexahdp.spring.database.querydsl.QPredicates;
import com.alexahdp.spring.dto.PersonalInfo;
import com.alexahdp.spring.dto.UserFilter;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.persistence.EntityManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

//import static com.alexahdp.spring.database.entity.User

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {
    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public static final String FIND_BY_COMPANY_AND_ROLE = """
            SELECT u.firstname, u.lastname, u.birth_date
            FROM users u
            WHERE u.company_id = ? AND u.role = ?
            """;

    public static final String UPDATE_COMPANY_AND_ROLE = """
            UPDATE users
            SET company_id = ?, role = ?
            WHERE id = ?
            """;

    public static final String UPDATE_COMPANY_AND_ROLE_NAMED = """
            UPDATE users
            SET company_id = :companyId, role = :role
            WHERE id = :id
            """;

    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        return List.of();
//        var predicate = QPredicates.builder()
//                .add(filter.firstName(), user.firstname::containsIgnoreCase)
//                .add(filter.lastName(), user.lastname::containsIgnoreCase)
//                .add(filter.birthDate(), user.birthDate::before)
//                .build();
//        return new JPAQuery<User>(entityManager)
//                .select(user)
//                .from(user)
//                .where(predicate)
//                .fetch();
    }

    public List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role) {
        return jdbcTemplate.query(FIND_BY_COMPANY_AND_ROLE,
            (rs, rowNum) -> new PersonalInfo(
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getDate("birth_date").toLocalDate()
            ), companyId, role.name());
    }

    @Override
    public void updateCompanyAndRole(List<User> users) {
        var args = users.stream().map(
                user -> new Object[] {user.getCompany().getId(), user.getRole().name(), user.getId()}
        ).toList();
        jdbcTemplate.batchUpdate(UPDATE_COMPANY_AND_ROLE, args);
    }

    @Override
    public void updateCompanyAndRoleNamed(List<User> users) {
        var args = users.stream()
            .map(user -> Map.of(
                    "companyId", user.getCompany().getId(),
                    "role", user.getRole().name(),
                    "id", user.getId()
            ))
            .map(MapSqlParameterSource::new)
            .toArray(MapSqlParameterSource[]::new);
        namedParameterJdbcTemplate.batchUpdate(UPDATE_COMPANY_AND_ROLE_NAMED, args);
    }
}
