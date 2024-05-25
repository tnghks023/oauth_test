package newbie.oauth.infra.oauth.naver;

import lombok.RequiredArgsConstructor;
import newbie.oauth.domain.OauthMember;
import newbie.oauth.domain.OauthServerType;
import newbie.oauth.domain.client.OauthMemberClient;
import newbie.oauth.infra.oauth.naver.client.NaverApiClient;
import newbie.oauth.infra.oauth.naver.dto.NaverMemberResponse;
import newbie.oauth.infra.oauth.naver.dto.NaverToken;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class NaverMemberClient implements OauthMemberClient {

    final static String AUTHORIZATION_CODE = "authorization_code";

    private final NaverApiClient naverApiClient;
    private final NaverOauthConfig naverOauthConfig;

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.NAVER;
    }

    @Override
    public OauthMember fetch(String code) {
        NaverToken tokenInfo = naverApiClient.fetchToken(tokenRequestParams(code));
        NaverMemberResponse naverMemberResponse =  naverApiClient.fetchMember("Bearer " + tokenInfo.accessToken());
        return naverMemberResponse.toDomain();
    }

    private MultiValueMap<String, String> tokenRequestParams(String authCode){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", AUTHORIZATION_CODE);
        params.add("client_id", naverOauthConfig.clientId());
        params.add("client_secret", naverOauthConfig.clientSecret());
        params.add("code", authCode);
        params.add("state", "samplestate");
        return params;
    }
}
