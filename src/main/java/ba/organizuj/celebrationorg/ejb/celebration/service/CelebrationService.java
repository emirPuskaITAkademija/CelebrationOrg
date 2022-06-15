package ba.organizuj.celebrationorg.ejb.celebration.service;

import ba.organizuj.celebrationorg.ejb.celebration.entity.Celebration;
import ba.organizuj.celebrationorg.ejb.service.AbstractService;
import ba.organizuj.celebrationorg.ejb.user.entity.User;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Local
@Stateless
public class CelebrationService extends AbstractService<Celebration> {

    @PersistenceContext(unitName = "birthdayPU")
    private EntityManager entityManager;

    public CelebrationService() {
        super(Celebration.class);
    }

    public List<Celebration> findByUserCreator(User userCreator) {
        Query query = getEntityManager().createNamedQuery("Celebration.findByUserCreator");
        query.setParameter("userCreator", userCreator);
        try {
            return query.getResultList();
        }catch (Exception e){
            return Collections.emptyList();
        }
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
