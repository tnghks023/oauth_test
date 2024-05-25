package newbie.oauth.infra.oauth.naver.authcode;

import lombok.RequiredArgsConstructor;
import newbie.oauth.domain.OauthServerType;
import newbie.oauth.domain.authcode.AuthCodeRequestUrlProvider;
import newbie.oauth.infra.oauth.naver.NaverOauthConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class NaverAuthCodeRequestUrlProvider implements AuthCodeRequestUrlProvider {

    private final NaverOauthConfig naverOauthConfig;

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.NAVER;
    }

    @Override
    public String provide() {
        return UriComponentsBuilder
                .fromUriString("https://nid.naver.com/oauth2.0/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", naverOauthConfig.clientId())
                .queryParam("redirect_uri", naverOauthConfig.redirectUri())
                .queryParam("state", "samplestate")
                .build()
                .toUriString();

    }
}
