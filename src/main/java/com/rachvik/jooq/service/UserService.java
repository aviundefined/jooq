package com.rachvik.jooq.service;

import static com.rachvik.jooq.sample.model.tables.Users.USERS;

import com.rachvik.jooq.sample.model.Tables;
import com.rachvik.jooq.sample.model.tables.pojos.Users;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserService {
  private final DSLContext context;

  public Users create(Users user) {
    val rowsInserted =
        context
            .insertInto(
                USERS, USERS.FIRSTNAME, USERS.LASTNAME, USERS.USERNAME, USERS.ROLES, USERS.PASSWORD)
            .values(
                user.getFirstname(),
                user.getLastname(),
                user.getUsername(),
                user.getRoles(),
                user.getPassword())
            .execute();
    log.info("Created user: {}, inserted rows: {}", user.getUsername(), rowsInserted);
    return user;
  }

  public Optional<Users> get(String username) {
    return context
        .selectFrom(Tables.USERS)
        .where(Tables.USERS.USERNAME.eq(username))
        .limit(1)
        .fetchInto(Users.class)
        .stream()
        .findFirst();
  }

  public List<Users> getAll() {
    return context.selectFrom(Tables.USERS).fetchInto(Users.class);
  }
}
