package org.online.test.api.repository;

import java.util.List;

import org.online.test.api.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

  /**
   * Fetch User by email ID.
   *
   * @param email Email ID.
   * @return {@code User} User object.
   */
  User findByEmail(String email);

  /**
   * Fetch user by email and organization ID.
   *
   * @param email Email ID.
   * @param organizationId Organization ID.
   * @return {@cod User} User object.
   */
  User findByEmailAndOrganizationId(String email, String organizationId);

  /**
   * Fetch users by organization.
   *
   * @param organizationId Organization ID.
   * @param active User status indicator.
   * @return {@code List<User>} List of users.
   */
  List<User> findByOrOrganizationIdAndActive(String organizationId, boolean active);
}
