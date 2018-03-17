package org.online.test.api.repository;

import org.online.test.api.domain.security.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {

  RefreshToken findByTokenId(String tokenId);
}