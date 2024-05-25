package newbie.oauth.infra.oauth.kakao.client;

import newbie.oauth.infra.oauth.kakao.dto.KakaoMemberResponse;
import newbie.oauth.infra.oauth.kakao.dto.KakaoToken;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

//위는 Http Interface Client를 사용한 코드입니다.
//우선 Url에는 URL을 명시하였고, 이를 위한 ContentType과 요청 파라미터를 받아야 하므로 MultiValueMap을 파라미터로 설정했습니다.
//응답 값은 KakaoToken을 통해 객체로 바로 받아올 수 있도록 작성하였습니다.
//위처럼 인터페이스를 통해 바로 사용할 수 있지만, 한가지 추가 작업이 필요합니다.

public interface KakaoApiClient {

    @PostExchange(url = "https://kauth.kakao.com/oauth/token", contentType = APPLICATION_FORM_URLENCODED_VALUE)
    KakaoToken fetchToken(@RequestParam MultiValueMap<String, String> params);

    @GetExchange("https://kapi.kakao.com/v2/user/me")
    KakaoMemberResponse fetchMember(@RequestHeader(name = AUTHORIZATION) String bearerToken);
}
