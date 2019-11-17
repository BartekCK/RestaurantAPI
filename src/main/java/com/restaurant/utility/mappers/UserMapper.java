package com.restaurant.utility.mappers;

import com.restaurant.models.User;
import com.restaurant.views.UserPrincipal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserMapper {
    public static UserPrincipal mapUserToUserPrincipal(User user) {
        return UserPrincipal.build(user);
    }
}
