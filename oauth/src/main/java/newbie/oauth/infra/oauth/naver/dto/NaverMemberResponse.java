package newbie.oauth.infra.oauth.naver.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import newbie.oauth.domain.OauthId;
import newbie.oauth.domain.OauthMember;
import newbie.oauth.domain.OauthServerType;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record NaverMemberResponse(
    String resultcode,
    String message,
    Response response
) {

    public OauthMember toDomain() {
        return OauthMember.builder()
                .oauthId(new OauthId(String.valueOf(response.id), OauthServerType.NAVER))
                .nickname(response.nickname)
                .profileImageUrl(response.profile_image)
                .build();
    }


    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record Response(
            String id,
            String nickname,
            String name,
            String email,
            String gender,
            String age,
            String birthday,
            String profile_image,
            String birthyear,
            String mobile
    ) {

    }

}
