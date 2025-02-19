package com.ploy.bubble_server_v3.domain.user.domain.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role
{
    USER("ROLE_USER"),

    ADMIN("ROLE_ADMIN");

    private final String role;
}
