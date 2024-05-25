package newbie.oauth.infra.oauth.kakao;

import lombok.RequiredArgsConstructor;
import newbie.oauth.domain.OauthMember;
import newbie.oauth.domain.OauthServerType;
import newbie.oauth.domain.client.OauthMemberClient;
import newbie.oauth.infra.oauth.kakao.client.KakaoApiClient;
import newbie.oauth.infra.oauth.kakao.dto.KakaoMemberResponse;
import newbie.oauth.infra.oauth.kakao.dto.KakaoToken;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class KakaoMemberClient implements OauthMemberClient {

    private final KakaoApiClient kakaoApiClient;
    private final KakaoOauthConfig kakaoOauthConfig;


    @Override
    public OauthServerType supportServer() {
        return OauthServerType.KAKAO;
    }

    public OauthMember fetch(String authCode){
        KakaoToken tokenInfo = kakaoApiClient.fetchToken(tokenRequestParams(authCode)); // AccessToken 조회
        KakaoMemberResponse kakaoMemberResponse = kakaoApiClient.fetchMember("Bearer " + tokenInfo.accessToken());// AccessToken으로 회원 정보 받아옴
        return kakaoMemberResponse.toDomain(); // 회원 정보를 객체로 변환

    }

    private MultiValueMap<String, String> tokenRequestParams(String authCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoOauthConfig.clientId());
        params.add("redirect_uri", kakaoOauthConfig.redirectUri());
        params.add("code", authCode);
        params.add("client_secret", kakaoOauthConfig.clientSecret());
        return params;
    }
}
