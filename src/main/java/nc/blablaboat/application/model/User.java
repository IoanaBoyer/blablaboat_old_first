package nc.blablaboat.application.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Définition de la classe User
 */
public class User {

    /**
     * Nom de l'utilisateur
     */
    public static AtomicInteger genId = new AtomicInteger();

    /**
     * L'identifiant de la Userne
     */
    private int id;

    /**
     * Nom de la Userne
     */
    private String lastname;

    /**
     * Prénom de la Userne
     */
    private String firstname;

    /**
     * L'âge de la Userne
     */
    private int age;

    /**
     * Mot de passe de la Userne
     */
    private String password;

    public User(String lastname, String firstname, int age, String password) {
        this.id = genId.getAndIncrement();
        this.lastname = lastname;
        this.firstname = firstname;
        this.age = age;
        this.password = password;
    }

    /**
     * Récupère l'identifiant de la Userne
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Récupère le nom de la Userne
     * @return lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Modifie le nom de la Userne
     * @param lastname le nouveau nom de la Userne
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Récupère le prénom de la Userne
     * @return firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Modifie le prénom de la Userne
     * @param firstname le nouveau prénom de la Userne
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Récupère l'âge de la Userne
     * @return  age
     */
    public int getAge() {
        return age;
    }

    /**
     * Modifie l'âge de la Userne
     * @param age le nouvel age de la Userne
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Récupère le mot de passe de la Userne
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Modifie le mot de passe de la Userne
     * @param password le nouveau mot de passe
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Affiche les informations de la Userne
     * @return la description de la Userne
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }
}