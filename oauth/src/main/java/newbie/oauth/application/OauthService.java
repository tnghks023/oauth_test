package newbie.oauth.application;

import lombok.RequiredArgsConstructor;
import newbie.oauth.domain.OauthMember;
import newbie.oauth.domain.OauthMemberRepository;
import newbie.oauth.domain.OauthServerType;
import newbie.oauth.domain.authcode.AuthCodeRequestUrlProviderComposite;
import newbie.oauth.domain.client.OauthMemberClientComposite;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OauthService {

    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final OauthMemberRepository oauthMemberRepository;

    public String getAuthCodeRequestUrl (OauthServerType oauthServerType){
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }

    public Long login(OauthServerType oauthServerType, String authCode) {
        OauthMember oauthMember = oauthMemberClientComposite.fetch(oauthServerType, authCode);
        OauthMember saved = oauthMemberRepository.findByOauthId(oauthMember.oauthId())
                .orElseGet(()-> oauthMemberRepository.save(oauthMember));
        // JWT로 AccessToken을 생성하여반환할 수 있음
        return saved.id();
    }
}
