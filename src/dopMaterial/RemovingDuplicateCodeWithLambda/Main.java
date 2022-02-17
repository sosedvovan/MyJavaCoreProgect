package dopMaterial.RemovingDuplicateCodeWithLambda;


/**
 * Теперь давайте напишем клиентский код, который использует приведенный выше класс:
 *
 * public class Main {
 *     public static void main(String arg[]) {
 *         EntityManagerFactory emf = null;
 *         try {
 *             emf = Persistence
 *                 .createEntityManagerFactory("a-persistence-name");
 *             ADataAccessObject dao = new ADataAccessObject(emf);
 *             //store anEntity ...
 *             dao.store(new AnEntity("aValuForTheProperty"));
 *             //update anEntity ...
 *             dao.update(1 l, "anotherValueForTheProperty");
 *             //retrieving ...
 *             AnEntity e = dao.byId(1 l);
 *         } finally {
 *             emf.close();
 *         }
 *     }
 *
 *     Что не очень хорошо выглядит из класса ADataAccessObject выше? Как вы, возможно, заметили, каждый метод кажется
 *     довольно похожим. Скелет try/catch/finally с созданием EntityManager, Транзакцией#фиксация, Транзакцией#откат и
 *     EntityManager#закрытиедублируется.
 *
 * Чтобы прояснить немного больше, ниже приведены предложения, уникальные для каждого метода:
 *
 * //from ADataAccessObject#store
 * em.persist(anEntity);
 *
 * //from ADataAccessObject#update
 * AnEntity anEntity = em.find(AnEntity.class, id);
 * anEntity.setAProperty(aNewPropertyValue);
 *
 * //from ADataAccessObject#byId
 * return em.find(AnEntity.class, id);
 *
 * Остальные строки кода дублируются в трех методах.
 *
 * Как мы можем удалить этот дублированный код? Есть несколько вариантов. Вы можете использовать динамические прокси или
 * AspectJ. Или включите такую структуру, как Spring, для обработки транзакций JPA для вас. Есть ли у нас более простой
 * подход? Я не хочу включать какие-либо рамки, и мне бы хотелось, чтобы сам язык обеспечивал синтаксические конструкции для этого.
 *
 * Что делать, если язык программирования позволяет передавать блок кода в качестве параметра метода? Это звучит здорово,
 * потому что я могу создать частный метод в своем классе ADataAccessObject со структурой try/catch/finally и получать
 * каждый уникальный блок предложений по параметру.
 *
 * До Java 8 это было возможно с помощью анонимных классов. Тогда давайте продолжим этот подход.
 *
 * Для реализации этого подхода мы должны создать интерфейс для указания подписи блока кода, который нам нужно передать
 * по параметру. Описано далее:
 *
 * public interface ABlockOfCode {
 *     AnEntity execute(EntityManager em);
 * }
 *
 * Этот шрифт выше - тип моего блока кода. Каждый блок кода будет реагировать на метод execute. Он должен получить
 * EntityManager по параметру и должен возвращать значение.
 *
 * Давайте пройдем вторую итерацию моего кода — шаг "Сделать его лучше". Я немного переработаю свой класс
 * ADataAccessObject, добавив несколько анонимных классов, чтобы исключить дублирование кода.
 *
 * public class ADataAccessObject {
 *
 *     private EntityManagerFactory emf;
 *
 *     public ADataAccessObject(EntityManagerFactory emf) {
 *         this.emf = emf;
 *     }
 *
 *      //в теле метода вызываем другой метод в аргументы которого передаем аноним. объект с реализицией
 *      //абстракта нашего интерфейса ABlockOfCode:
 *     public void store(AnEntity anEntity) {
 *         transactionExecute(new ABlockOfCode() {
 *             @Override
 *             public AnEntity execute(EntityManager em) {
 *                 em.persist(anEntity);
 *                 return null;
 *             }
 *         });
 *     }
 *
 *     public void update(long id, String aNewPropertyValue) {
 *         transactionExecute(new ABlockOfCode() {
 *             @Override
 *             public AnEntity execute(EntityManager em) {
 *                 AnEntity anEntity = em.find(AnEntity.class, id);
 *                 anEntity.setAProperty(aNewPropertyValue);
 *                 return null;
 *             }
 *         });
 *     }
 *
 *     public AnEntity byId(long id) {
 *         return transactionExecute(new ABlockOfCode() {
 *             @Override
 *             public AnEntity execute(EntityManager em) {
 *                 return em.find(AnEntity.class, id);
 *             }
 *         });
 *     }
 *
 *     private AnEntity transactionExecute(ABlockOfCode aBlockOfCode) {
 *         EntityManager em = this.emf.createEntityManager();
 *         EntityTransaction tx = null;
 *         try {
 *             tx = em.getTransaction();
 *             tx.begin();
 *
 *             AnEntity a = aBlockOfCode.execute(em);
 *
 *             tx.commit();
 *             return a;
 *         } catch (Exception e) {
 *             if (tx != null) {
 *                 tx.rollback();
 *             }
 *             throw new RuntimeException(e);
 *         } finally {
 *             em.close();
 *         }
 *     }
 * }
 * Как вы можете видеть, я создал частный метод под названием transactionExecute, который ожидает в качестве параметра
 * экземпляр ABlockOfCode. В каждом общедоступном методе я создаю эти экземпляры как анонимные классы, реализуя метод
 * execute кода ablockof с предложениями, уникальными для каждого метода, описанного ранее. Затем каждый метод вызывает
 * transactionExecute, передавая эти экземпляры ABlockOfCode по параметру. Наконец, обратите внимание, что внутри метода
 * transactionExecute есть вызов метода execute экземпляра ABlockOfCode внутри скелета try/catch/finally.
 *
 * Неплохо, правда? Давайте сделаем это еще лучше в моей третьей итерации разработки. Я собираюсь заменить анонимные
 * классы лямбдами. В Java 8 лямбды являются более красивым способом написания анонимных классов (не совсем так обстоит
 * дело с функциональными языками программирования, но это другой разговор). Они включают синтаксический сахар плюс
 * систему вывода типов, которые делают код более чистым и легким для чтения.
 *
 * Приведенный ниже код начинает перемещение частного метода transactionExecute в свой собственный класс.
 * public class TransactionTemplate {
 *     public EntityManagerFactory emf;
 *
 *     public TransactionTemplate(EntityManagerFactory emf) {
 *         this.emf = emf;
 *     }
 *
 *     public AnEntity execute(ABlockOfCode aBlockOfCode) {
 *         EntityManager em = this.emf.createEntityManager();
 *         EntityTransaction tx = null;
 *         try {
 *             tx = em.getTransaction();
 *             tx.begin();
 *
 *             AnEntity returnValue = aBlockOfCode.execute(em);
 *
 *             tx.commit();
 *             return returnValue;
 *         } catch (Exception e) {
 *             if (tx != null) {
 *                 tx.rollback();
 *             }
 *             throw new RuntimeException(e);
 *         } finally {
 *             em.close();
 *         }
 *     }
 * }
 *
 * public class ADataAccessObject {
 *
 *     private TransactionTemplate transaction;
 *
 *     public ADataAccessObject(TransactionTemplate transaction) {
 *         this.transaction = transaction;
 *     }
 *
 *     public void store(AnEntity anEntity) {
 *         transaction.execute(
 *             (em) - > {
 *                 em.persist(anEntity);
 *                 return null;
 *             });
 *     }
 *
 *     public void update(long id, String aNewPropertyValue) {
 *         transaction.execute(
 *             (em) - > {
 *                 AnEntity anEntity = em.find(AnEntity.class, id);
 *                 anEntity.setAProperty(aNewPropertyValue);
 *                 return null;
 *             });
 *     }
 *
 *     public AnEntity byId(long id) {
 *         return transaction.execute(
 *             (em) - > {
 *                 return em.find(AnEntity.class, id);
 *             });
 *     }
 * }
 * Лямбда-выражение (строки 40-42, 47-50 и 55-57) состоит из двух частей, разделенных лексическим маркером “->”:
 * (параметры) ->> { блок кода }. Нет необходимости указывать тип параметра в лямбда-выражении, он будет выведен
 * из интерфейса, в данном случае ABlockOfCode. Если вы посмотрите на этот интерфейс, вы заметите, что каждое
 * лямбда-выражение получает экземпляр EntityManager в качестве параметра (em) и должно возвращать экземпляр объекта.
 * Хороший, дублированный код удален и менее подробный код.
 *
 * В качестве заключительной итерации разработки я сделаю это более общим. Мой интерфейс ABlockOfCode и класс
 * TransactionTemplate должны поддерживать любой тип объекта, а не только объект. Итак, далее я изменю тип AnEntity
 * на универсальный тип T.
 *
 * Начиная с интерфейса ABlockOfCode:
 *
 * public interface ABlockOfCode< T >  {
 * T execute ( EntityManager em );
 * }
 * Я только что заменил сущность общим типом Tи объявил этот тип T как общий тип, используя синтаксис <T>> (в строке 1).
 *
 * Далее я сделаю метод TransactionTemplate#execute универсальным:
 *
 * public < T > T execute(ABlockOfCode < T > aBlockOfCode) {
 *     EntityManager em = this.emf.createEntityManager();
 *     EntityTransaction tx = null;
 *     try {
 *         tx = em.getTransaction();
 *         tx.begin();
 *
 *         T returnValue = aBlockOfCode.execute(em);
 *
 *         tx.commit();
 *         return returnValue;
 *     } catch (Exception e) {
 *         if (tx != null) {
 *             tx.rollback();
 *         }
 *         throw new RuntimeException(e);
 *     } finally {
 *         em.close();
 *     }
 * }
 *
 * В строке 1 я объявляю универсальный тип <T>, изменяю сигнатуру метода, возвращающего >T, и ожидаю ABlockOfCode<T>
 *     в качестве параметра. Обратите также внимание, что тип возврата предложения aBlockOfCode.execute(em) изменился
 *     с AnEntity на Tв строке 8.
 *
 * С этим последним изменением мы сделали метод TransactionTemplate#execute универсальным для использования любым
 * экземпляром ABlockOfCode, для которого требуется контекст транзакции JPA. Класс ADataAccessObject не нуждается
 * в изменении, потому что, как я объяснял ранее, лямбды выводят свой тип из интерфейса ABlockOfCode.
 *
 * Мы просто прошли несколько итераций разработки, чтобы удалить дублированный код. Природа дублированного кода,
 * показанного здесь, не может быть удалена с помощью простых методов рефакторинга, таких как метод извлечения
 * или класс извлечения. Для этого требуются более мощные языковые функции, позволяющие передавать предложения
 * или блоки кода по параметрам. Это было достигнуто сначала с помощью анонимных классов, а затем с помощью
 * лямбда-выражений начиная с Java 8.
 *
 *
 *
 *
 * ТО МЫ ПРИДУМАЛИ ТАКОЙ СВОЙ ИНТЕРФЕЙС К АБСТРАКТУ КОТОРОГО ПОДХОДЯТ УНИКАЛЬНЫЕ СТРОКИ КОДА, ИЗ ТРЕХ МЕТОДОВ
 * (ДРУГИЕ СТРОКИ КОДА ОДИНАКОВЫЕ).
 * ДАЛЕЕ МЫ СОЗДАЛИ ТРИ МЕТОДА, В ТЕЛЕ КОТОРЫХ ВЫЗЫВАЕТСЯ ЧЕТВЕРТЫЙ МЕТОД
 * (ЕГО АРГУМЕНЫ ПРИНИМАЮТ РЕАЛИЗАЦИЮ АБСТРАКТА)И ПРИ ВЫЗОВЕ ЭТОГО ЧЕТВЕРТОГО МЕТОДА (ИЗ ТЕЛ ТРЕХ ПЕРВЫХ)
 * ОН ПОЛУЧАЕТ В АРГУМЕНТЫ ОДНУ ИЗ УНИКАЛЬНЫХ РЕАЛИЗАЦИЙ(КУСОК УНИКАЛЬНОГО КОДА) И ДАЛЕЕ СРАБАТЫВАЮТ ОБЩИЕ СТРОКИ
 * (ОДИНАКОВЫЙ КОД В ТРЕХ МЕТОДАХ)
 * ДАЛЕЕ ИЗМЕНИМ АНОНИМНЫЕ КЛАССЫ НА ЛЯМБДЫ И ПОЛУЧИМ КОМПАКТНЫЙ КОД
 * ДАЛЕЕ ПЕРЕМЕСТИМ ЧЕТВЕРТЫЙ МЕТОД В СВОЙ СОБСТВЕННЫЙ КЛАСС, СДЕЛАВ ОСНОВНОЙ КЛАСС ЗАВИСИМЫМ ОТ НЕГО
 * ДАЛЕЕ ПАРАМЕТРИЗОВАЛИ ЧЕТВЕРТЫЙ МЕТОД, СДЕЛАВ ЕГО УНИВЕРСАЛЬНЫМ
 *
 * https://dzone.com/articles/removing-duplicate-code-with-lambda-expressions
 *
 */