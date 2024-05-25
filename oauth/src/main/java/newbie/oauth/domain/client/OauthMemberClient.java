package newbie.oauth.domain.client;

import newbie.oauth.domain.OauthMember;
import newbie.oauth.domain.OauthServerType;

public interface OauthMemberClient {

    OauthServerType supportServer();
    OauthMember fetch(String code);
}
