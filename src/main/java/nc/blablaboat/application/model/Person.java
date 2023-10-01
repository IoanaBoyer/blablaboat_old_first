package nc.blablaboat.application.model;

// Modèle de données
public class Person {
    private int id;
    private String name;
    private int age;
    
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "{id: "+ this.id +", name: "+ this.name +", age: "+ this.age +"}";
    }

    // Constructeur, getters, setters, etc.
}