package com.fundamentos.springboot.fundamentos.caseuse;

import com.fundamentos.springboot.fundamentos.entity.User;

import java.util.List;

/**
 * Clase encargada de retribuir la lista de los usuarios
 */
public interface GetUser {
    List<User> getAll();
}
