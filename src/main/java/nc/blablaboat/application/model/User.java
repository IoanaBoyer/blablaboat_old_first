package nc.blablaboat.application.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;
import java.util.UUID;

/**
 * Définition de la classe utilisateur
 */
public class User {

    /**
     * L'identifiant de l'utilisateur
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    /**
     * Le pseudo de l'utilisateur (unique)
     */
    private String nickname;

    /**
     * Le nom de l'utilisateur
     */
    private String lastname;

    /**
     * Le prénom de l'utilisateur
     */
    private String firstname;

    /**
     * L'âge de l'utilisateur
     */
    private int age;

    /**
     * Le mot de passe de l'utilisateur
     */
    private String password;

    /**
     * Type de l'utilisateur (pas héritage car un utilisateur peut être les 2 en mêmes temps)
     */
    private Boolean driver;

    /**
     * Constructeur vide
     */
    public User() {
    }

    /**
     * Constructeur avec initialisation des attributs
     * @param id l'identifiant de l'utilisateur de type UUID
     * @param nickname le pseudo de l'utilisateur
     * @param lastname le nom de l'utilisateur
     * @param firstname le prénom de l'utilisateur
     * @param age l'âge de l'utilisateur
     * @param password le mot de passe de l'utilisateur
     * @param driver type de l'utilisateur
     */
    public User(UUID id, String nickname, String lastname, String firstname, int age, String password, Boolean driver) {
        setId(id);
        this.nickname = nickname;
        this.lastname = lastname;
        this.firstname = firstname;
        this.age = age;
        this.password = password;
        this.driver = driver;
    }

    /**
     * Constructeur avec initialisation des attributs
     * @param id l'identifiant de l'utilisateur de type String
     * @param nickname le pseudo de l'utilisateur
     * @param lastname le nom de l'utilisateur
     * @param firstname le prénom de l'utilisateur
     * @param age l'âge de l'utilisateur
     * @param password le mot de passe de l'utilisateur
     * @param driver type de l'utilisateur
     */
    public User(String id, String nickname, String lastname, String firstname, int age, String password, Boolean driver) {
        this(UUID.fromString(id), nickname, lastname, firstname, age, password, driver);
    }

    /**
     * Constructeur avec initialisation des attributs sans fournir l'id
     * @param nickname le pseudo de l'utilisateur
     * @param lastname le nom de l'utilisateur
     * @param firstname le prénom de l'utilisateur
     * @param age l'âge de l'utilisateur
     * @param password le mot de passe de l'utilisateur
     * @param driver type de l'utilisateur
     */
    public User(String nickname, String lastname, String firstname, int age, String password, Boolean driver) {
        this(UUID.randomUUID(), nickname, lastname, firstname, age, password, driver);
    }

    public User(UUID conducteurId, String testConducteur, String driver, String mail, String password) {
    }

    /**
     * Récupère l'identifiant de l'utilisateur
     * @return id
     */
    public String getId() {
        return id.toString();
    }

    /**
     * Modifie l'identifiant de l'utilisateur
     * @param id le nouvel identifiant de l'utilisateur
     */
    public void setId(UUID id) {
        this.id = Objects.requireNonNullElseGet(id, UUID::randomUUID);
    }

    /**
     * Récupère le pseudo de l'utilisateur
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Modifie le pseudo de l'utilisateur
     * @param nickname le nouveau pseudo de l'utilisateur
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Récupère le nom de l'utilisateur
     * @return lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Modifie le nom de l'utilisateur
     * @param lastname le nouveau nom de l'utilisateur
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Récupère le prénom de l'utilisateur
     * @return firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Modifie le prénom de l'utilisateur
     * @param firstname le nouveau prénom de l'utilisateur
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Récupère l'âge de l'utilisateur
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * Modifie l'âge de l'utilisateur
     * @param age le nouvel âge de l'utilisateur
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Récupère le mot de passe de l'utilisateur
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Modifie le mot de passe de l'utilisateur
     * @param password le nouveau mot de passe de l'utilisateur
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Récupère le type de l'utilisateur
     * @return driver
     */
    public Boolean getDriver() {
        return this.driver;
    }

    /**
     * Modifie le type d'utilisateur
     * @param driver le nouveau type d'utilisateur
     */
    public void setDriver(Boolean driver) {
        this.driver = driver;
    }

    /**
     * Affiche les informations de l'utilisateur
     * @return la description de l'utilisateur
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", age=" + age + '\'' +
                ", driver=" + driver +
                '}';
    }
}