package com.restaurant.utility.mappers;

import com.restaurant.commands.response.UserPrincipal;
import com.restaurant.models.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserMapper {
    public static UserPrincipal mapUserToUserPrincipal(User user) {
        return UserPrincipal.build(user);
    }
}
