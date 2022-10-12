package com.jun.board.config;

import com.jun.board.User.Member;
import com.jun.board.User.UserRepository;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        String seq = (String) attributes.get("sub");
        Optional<Member> optionalUser = userRepository.findUserByEmail(email);
        Member member;
        System.out.println("oAuth2User = " + oAuth2User.getAttributes());
        System.out.println("userRequest = " + userRequest.getClientRegistration());
        if (optionalUser.isEmpty()) {
            member = Member.builder()
                    .email(email)
                    .name(name)
                    .build();

            userRepository.save(member);
        } else {
            member = optionalUser.get();
        }

        return new PrincipalDetails(member, oAuth2User.getAttributes());
    }

}
