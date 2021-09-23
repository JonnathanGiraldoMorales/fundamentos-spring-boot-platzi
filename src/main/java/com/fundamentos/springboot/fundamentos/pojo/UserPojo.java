package com.fundamentos.springboot.fundamentos.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding //para que una clase POJO se pueda inyectar como dependencia a partir de la propedades.
@ConfigurationProperties(prefix = "user") //indica que vamos a usar todos los valores que de nuestro archivo properties que tenga como prefijo “user”, asi, al declarar las variables con el mismo nombre despues del prefijo, estas tomaran el valor de la clase properties.
public class UserPojo {
    private String email;
    private String password;
    private int age;

    public UserPojo(String email, String password, int age) {
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
