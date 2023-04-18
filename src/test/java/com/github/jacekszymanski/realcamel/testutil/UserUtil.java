package com.github.jacekszymanski.realcamel.testutil;

import com.github.jacekszymanski.realcamel.entity.User;
import com.github.jacekszymanski.realcamel.model.CreateUserRequest;
import com.github.jacekszymanski.realcamel.model.NewUser;
import org.apache.commons.codec.digest.DigestUtils;

public class UserUtil {
  public static final String DEFAULT_EMAIL = "abcde@abcde.com";
  public static final String DEFAULT_PASSWORD = "password";
  public static final String DEFAULT_USERNAME = "abcde";

  public static CreateUserRequest defaultUserRequest() {
    final NewUser newUser = new NewUser();

    newUser.email(DEFAULT_EMAIL);
    newUser.password(DEFAULT_PASSWORD);
    newUser.username(DEFAULT_USERNAME);

    final CreateUserRequest req = new CreateUserRequest();
    req.user(newUser);

    return req;
  }

  public static User defaultUserEntity() {
    final User user = new User();

    user.setEmail(DEFAULT_EMAIL);
    user.setPassword(DigestUtils.sha256Hex(DEFAULT_PASSWORD));
    user.setUsername(DEFAULT_USERNAME);

    return user;
  }
}
