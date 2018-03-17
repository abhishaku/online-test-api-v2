package org.online.test.api.service.user;

import org.online.test.api.annotation.LogExecutionTime;
import org.online.test.api.domain.User;
import org.online.test.api.repository.OrganizationRepository;
import org.online.test.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
      .getLogger(UserServiceImpl.class);

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private OrganizationRepository organizationRepository;

  @Override
  public void create(User user) {
    //TODO do not forget to implement me
  }

  @Override
  @LogExecutionTime
  public User getById(String id) {
    return userRepository.findOne(id);
  }
}
