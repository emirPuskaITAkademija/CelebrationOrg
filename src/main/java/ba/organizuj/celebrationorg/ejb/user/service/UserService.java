package ba.organizuj.celebrationorg.ejb.user.service;

import ba.organizuj.celebrationorg.controller.register.RegisterModel;
import ba.organizuj.celebrationorg.ejb.service.AbstractService;
import ba.organizuj.celebrationorg.ejb.town.entity.Town;
import ba.organizuj.celebrationorg.ejb.town.service.TownService;
import ba.organizuj.celebrationorg.ejb.user.entity.User;
import ba.organizuj.celebrationorg.ejb.user.privilege.entity.Privilege;
import ba.organizuj.celebrationorg.ejb.user.privilege.service.PrivilegeService;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * <li>1. User</li>
 * <li>2. UserService extends AbstractService<User>
 * <li>2.1 Local Stateless SessionBean</li>
 * <li>2.2 findAll</li>
 * </li>
 *
 * <li>findAll -> UserService userService
 * userService.findAll();
 * </li>
 */
@Local
@Stateless
public class UserService extends AbstractService<User> {

    @PersistenceContext(unitName = "birthdayPU")
    private EntityManager entityManager;

    @Inject
    private Pbkdf2PasswordHash pbkdf2PasswordHash;

    @Inject
    private TownService townService;

    @Inject
    private PrivilegeService privilegeService;

    public UserService() {
        super(User.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * @param username
     * @param plainPassword
     * @return user or null(in case of incorrect username or plainPassword)
     */
    public User login(String username, String plainPassword) {
        User user = findByUsernameOrNull(username);
        //plainPassword -> plainPassword
        //hashedPassword -> user.getPassword();
        if (user != null && pbkdf2PasswordHash.verify(plainPassword.toCharArray(), user.getPassword())) {
            return user;
        }
        return null;
    }

    public User findByUsernameOrNull(String username){
        Query query = getEntityManager().createNamedQuery("User.findByUsername");
        query.setParameter("username", username);
        try {
            return (User) query.getSingleResult();
        } catch (NonUniqueResultException | NoResultException e) {
            Logger.getLogger(getClass().getName()).info("Not exist user or not unique: " + e.getMessage());
            return null;
        }
    }

    public User register(RegisterModel registerModel) {
        User user = new User();
        user.setName(registerModel.getName());
        user.setSurname(registerModel.getSurname());
        user.setUsername(registerModel.getUsername());
        String hashedPass = pbkdf2PasswordHash.generate(registerModel.getPlainPassword().toCharArray());
        user.setPassword(hashedPass);
        Town town = townService.find(registerModel.getTownId());
        user.setTown(town);
        Privilege clientPrivilege = privilegeService.find(Privilege.CLIENT_PRIVILEGE_ID);
        user.setPrivilege(clientPrivilege);
        user.setStatus(User.STATUS.ACTIVE.name().toLowerCase());
        user.setContact(registerModel.getContact());
        user.setEmail(registerModel.getEmail());
        create(user);//getEntityManager().persist(user);
        return user;
    }
}
