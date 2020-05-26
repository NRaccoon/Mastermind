package results;

import util.jpa.GenericJpaDao;

import javax.persistence.Persistence;
import java.util.List;

public class GameResultDao extends GenericJpaDao<GameResult> {

    private static GameResultDao instance;

    private GameResultDao() {
        super(GameResult.class);
    }

    public static GameResultDao getInstance() {
        if (instance == null) {
            instance = new GameResultDao();
            instance.setEntityManager(Persistence.createEntityManagerFactory("jpa-persistence-unit-1").createEntityManager());
        }
        return instance;
    }

    public List<GameResult> findBest(int n) {
        return entityManager.createQuery("SELECT r FROM GameResult r ORDER BY r.steps ASC, r.created DESC", GameResult.class)
                .setMaxResults(n)
                .getResultList();
    }

}
