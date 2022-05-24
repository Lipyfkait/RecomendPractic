package ru.ithub.examination.services;

import ru.ithub.examination.domain.entity.ERole;
import ru.ithub.examination.domain.entity.Role;

public interface RoleService {
    Role get(ERole eRole);
}
