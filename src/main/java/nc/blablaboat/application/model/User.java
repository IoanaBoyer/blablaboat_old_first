package nc.blablaboat.application.model;

import nc.blablaboat.application.contract.UserInterface;

import java.util.Objects;
import java.util.UUID;

/**
 * Définition de la classe utilisateur
 */
public class User {

    /**
     * L'identifiant de l'utilisateur
     */
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
    private Boolean isDriver;

    /**
     * Constructeur vide
     */
    public User() {
    }

    /**
     * Constructeur avec initialisation des attributs
     * @param nickname le pseudo de l'utilisateur
     * @param lastname le nom de l'utilisateur
     * @param firstname le prénom de l'utilisateur
     * @param age l'âge de l'utilisateur
     * @param password le mot de passe de l'utilisateur
     * @param isDriver type de l'utilisateur
     */
    public User(UUID id, String nickname, String lastname, String firstname, int age, String password, Boolean isDriver) {
        setId(id);
        this.nickname = nickname;
        this.lastname = lastname;
        this.firstname = firstname;
        this.age = age;
        this.password = password;
        this.isDriver = isDriver;
    }
    public User(String id, String nickname, String lastname, String firstname, int age, String password, Boolean isDriver) {
        this(UUID.fromString(id), nickname, lastname, firstname, age, password, isDriver);
    }
    public User(String nickname, String lastname, String firstname, int age, String password, Boolean isDriver) {
        this(UUID.randomUUID(), nickname, lastname, firstname, age, password, isDriver);
    }

    /**
     * Récupère l'identifiant de l'utilisateur
     * @return id
     */
    public String getId() {
        return id.toString();
    }

    /**
     * Modifier l'identifiant de l'utilisateur
     * @param id le nouveau identifiant de l'utilisateur
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

    public Boolean getDriver() {
        return isDriver;
    }

    public void setDriver(Boolean driver) {
        isDriver = driver;
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
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }
}