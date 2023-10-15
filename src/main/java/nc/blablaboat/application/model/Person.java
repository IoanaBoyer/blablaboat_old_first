package nc.blablaboat.application.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Définition de la classe Person
 */
public class Person {

    /**
     * Générateur de l'identifiant
     */
    public static AtomicInteger genId = new AtomicInteger();

    /**
     * L'identifiant de la personne
     */
    private int id;

    /**
     * Nom de la personne
     */
    private String lastname;

    /**
     * Prénom de la personne
     */
    private String firstname;

    /**
     * L'âge de la personne
     */
    private int age;

    /**
     * Mot de passe de la personne
     */
    private String password;

    public Person(String lastname, String firstname, int age, String password) {
        this.id = genId.getAndIncrement();
        this.lastname = lastname;
        this.firstname = firstname;
        this.age = age;
        this.password = password;
    }

    /**
     * Récupère l'identifiant de la personne
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Récupère le nom de la personne
     * @return lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Modifie le nom de la personne
     * @param lastname le nouveau nom de la personne
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Récupère le prénom de la personne
     * @return firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Modifie le prénom de la personne
     * @param firstname le nouveau prénom de la personne
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Récupère l'âge de la personne
     * @return  age
     */
    public int getAge() {
        return age;
    }

    /**
     * Modifie l'âge de la personne
     * @param age le nouvel age de la personne
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Récupère le mot de passe de la personne
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Modifie le mot de passe de la personne
     * @param password le nouveau mot de passe
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Affiche les informations de la personne
     * @return la description de la personne
     */
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }
}