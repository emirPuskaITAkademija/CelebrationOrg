package ba.organizuj.celebrationorg.ejb.town.service;

import ba.organizuj.celebrationorg.ejb.service.AbstractService;
import ba.organizuj.celebrationorg.ejb.town.entity.Town;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Local
@Stateless
public class TownService extends AbstractService<Town> {
    @PersistenceContext(unitName = "birthdayPU")
    private EntityManager entityManager;

    public TownService() {
        super(Town.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
