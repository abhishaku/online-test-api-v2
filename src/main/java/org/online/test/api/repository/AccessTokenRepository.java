package org.online.test.api.repository;

import java.util.List;

import org.online.test.api.domain.security.AccessToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessTokenRepository extends MongoRepository<AccessToken, String> {

  AccessToken findByTokenId(String tokenId);

  AccessToken findByRefreshToken(String refreshToken);

  AccessToken findByAuthenticationId(String authenticationId);

  List<AccessToken> findByClientId(String clientId);

  List<AccessToken> findByClientIdAndUserName(String clientId, String userName);
}