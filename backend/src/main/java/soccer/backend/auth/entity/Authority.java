package soccer.backend.auth.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import soccer.backend.global.entity.EnumType;

@RequiredArgsConstructor
public enum Authority implements EnumType {
    ROLE_USER("일반 사용자"),
    ROLE_ADMIN("관리자"),
    ROLE_DEVELOPER("개발자");

    private final String description;

    @Override
    public String getName() {
        return name();
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}