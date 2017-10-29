# Exercice 4/01

Cet exercice vous propose de vous familiariser avec les contextes Spring et l'injection de dépendances.

L'objectif est de réaliser deux assemblages de composants similaires d'une application de changement de mot de passe.  
Cet exercice est indépendant des autres, il redéfini une entité User et son DAO, sous la forme d'une interface, une classe abstraite pour gérer la vérification et la modification du mot de passe. Deux implémentation d'encodage de mot de passe seront à implémenter.

Nous allons voir deux manières de définir un contexte Spring :

* A l'aide d'annotations
* A l'aide d'un fichier xml de description de beans

## Utilisation des annotations Spring context

* Déclarez les sous-classes de PasswordChecker comme deux composants distincts.
* Implémentez au passage la méthode "encode" des deux composants, en utilisant un objet [MessageDigest](https://docs.oracle.com/javase/8/docs/api/java/security/MessageDigest.html) avec MD5 pour le premier, et un objet [Mac](https://docs.oracle.com/javase/8/docs/api/javax/crypto/Mac.html) avec l'algo HmacSHA1 pour le second. le retour se fera sous la forme d'une chaine de caractères encodé en base 64, vous pouvez utiliser java.util.Base64.
* Déclarez le DAO utilisant JPA comme un composant, idem pour ChangePwdApp
* Annotez les champs nécessaire en Autowired pour l'injection dans tous les composants
* On veillera à utiliser l'implémentation Hmac du PasswordChecker, tout en ayant l'autre implémentation disponible dans le contexte. Il vous faudra sans doute ajouter un Qualifier.
* Utilisez l'objet SpringConfig pour configurer le componentScan et pour exposer l'EntityManager dans le contexte spring.
* Complétez la méthode getAnnotationAppContext de ChangePwdApp pour construire un contexte Spring utilisant les annotations : AnnotationConfigApplicationContext.

Vous devriez être en mesure de lancer le programme et de choisir l'option 2

A noter : le provider JPA fourni par Hibernate va générer un pool de connexion qui écoute après les événements du contexte Spring. Lorsque le contexte s'arrête, les connexions seront fermée et l'EntityManagerFactory arrêté proprement.

## Utilisation des fichiers xml de contexte

L'objectif est de fournir un fichier créant un contexte avec l'implémentation JDBC du UserDAO et l'implémentation MD5 du PasswordChecker.

* Déclarez un bean pour le checker MD5
* Ajoutez un bean de connection SQL dans le contexte, créé à partir du DriverManager. Il vous faudra utiliser la notion de "factory-method" et utiliser "constructor-arg" pour passer des paramètres à cette méthode factory.
* Déclarez un bean pour le userDAO en utilisant l'implémentation JDBC, injectez-y les beans nécessaires.
* Déclarez un bean pour l'objet ChangePwdApp et injectez le DAO.
* Complétez la méthode getXmlAppContext pour utiliser un contexte chargeant un fichier xml dans le classpath : ClassPathXmlApplicationContext.
* Ajoutez une méthode dans le l'implémentation JDBC pour fermer proprement la connexion lorsque le contexte spring s'arrête. Configurez le bean correspondant pour appeler cette méthode, à l'aide de "destroy-method".

Vous devriez être en mesure de lancer le programme et de choisir l'option 1

A noter : cette manière de déclarer une connexion directement sous la forme d'un bean, et de la fermer dans un DAO n'est pas une bonne pratique et ne correspond pas à un véritable cas pratique d'application. Nous voulons simplement ici illustrer les usages de la factory-method et de destroy-method.

