package dopMaterial.RemovingDuplicateCodeWithLambda;
/**
 * НАЧИНАТЬ ЧИТАТЬ ОТСЮДА
 *
 * Предположим, нас просят написать некоторый код для простого сохранения, обновленияи извлечения определенного класса
 * сущностей с помощью JPA (подробнее об этом в copypasteisforword). Чтобы реализовать это, я напишу класс под названием
 * ADataAccessObject (просто чтобы как-то его назвать). Ниже вы найдете то, что я написал бы на своей первой итерации
 * разработки — шаг "Сделай так, чтобы это работало".
 *
public class ADataAccessObject {

    private EntityManagerFactory emf;

    public ADataAccessObject(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(AnEntity anEntity) {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            em.persist(anEntity);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    public void update(long id, String aNewPropertyValue) {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            AnEntity anEntity = em.find(AnEntity.class, id);
            anEntity.setAProperty(aNewPropertyValue);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    public AnEntity byId(long id) {
        EntityManager em = this.emf.createEntityManager();
        try {
            return em.find(AnEntity.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }
}

 */
