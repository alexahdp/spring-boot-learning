package com.alexahdp.spring.database.repository;

import com.alexahdp.spring.database.entity.Role;
import com.alexahdp.spring.database.entity.User;
import com.alexahdp.spring.dto.PersonalInfo;
import com.alexahdp.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {
    List<User> findAllByFilter(UserFilter filter);
    List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role);
    void updateCompanyAndRole(List<User> users);
    void updateCompanyAndRoleNamed(List<User> users);
}
