package newbie.oauth.domain;

import java.util.Locale;

public enum OauthServerType {

    KAKAO,
    NAVER,
    ;

    public static OauthServerType fromName(String type) {
        return OauthServerType.valueOf(type.toUpperCase(Locale.ENGLISH));
    }
}
