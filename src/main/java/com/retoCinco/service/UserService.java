package com.retoCinco.service;

import com.retoCinco.model.User;
import com.retoCinco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Alberto
 */
@Service
public class UserService {
    
    /**
     * Se instancia a UserRepository 
     */
    @Autowired
    private UserRepository userRepository;

    /**
     *
     * @return Listado de usuarios
     */
    public List<User> getAll() {
        return userRepository.getAll();
    }

    /**
     *
     * @param id
     * @return Obtener usuario por id
     */
    public Optional<User> getUser(int id) {
        
        return userRepository.getUser(id);
    }

    /**
     *
     * @param user
     * @return
     */
    public User create(User user) {
        
        //Obtiene el maximo id existente
        Optional<User> userIdMaximo = userRepository.lastUserId();
        
        //Si el id  es nulo, entonces valida el maximo id existente en base de datos
        if (user.getId() == null) {
            //Valida el maximo id generado
            if (userIdMaximo.isEmpty())
                user.setId(1);
            //si retorna informacion suma 
            else
                user.setId(userIdMaximo.get().getId() + 1);
        }
        /**
         * optional para comprobar si el usuario existe
         * 
         */
        Optional<User> e = userRepository.getUser(user.getId());
        if (e.isEmpty()) {
            if (emailExists(user.getEmail())==false){
                return userRepository.create(user);
            }else{
                return user;
            }
        }else{
            return user;
        }
        
    }

    /**
     *
     * @param user
     * @return 
     */
    public User update(User user) {

        if (user.getId() != null) {
            Optional<User> userDb = userRepository.getUser(user.getId());
            if (!userDb.isEmpty()) {
                if (user.getIdentification() != null) {
                    userDb.get().setIdentification(user.getIdentification());
                }
                if (user.getName() != null) {
                    userDb.get().setName(user.getName());
                }
                if (user.getAddress() != null) {
                    userDb.get().setAddress(user.getAddress());
                }
                if (user.getCellPhone() != null) {
                    userDb.get().setCellPhone(user.getCellPhone());
                }
                if (user.getEmail() != null) {
                    userDb.get().setEmail(user.getEmail());
                }
                if (user.getPassword() != null) {
                    userDb.get().setPassword(user.getPassword());
                }
                if (user.getZone() != null) {
                    userDb.get().setZone(user.getZone());
                }
                
                userRepository.update(userDb.get());
                return userDb.get();
            } else {
                return user;
            }
        } else {
            return user;
        }
    }

    /**
     *
     * @param userId
     * @return Retorna true or false y si es True elimina usuario por id
     */
    public boolean delete(int userId) {
        Boolean aBoolean = getUser(userId).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    /**
     *
     * @param email
     * @return Retorna true or false de validación de existencia de usuario
     */
    public boolean emailExists(String email) {
        return userRepository.emailExists(email);
    }

    /**
     *
     * @param email
     * @param password
     * @return Usuario autenticado
     */
    public User authenticateUser(String email, String password) {
        Optional<User> usuario = userRepository.authenticateUser(email, password);

        if (usuario.isEmpty()) {
            return new User();
        } else {
            return usuario.get();
        }
    }


    public List<User> listBirthtDayMonth(String month){
        return userRepository.listBirthtDayMonth(month);
    }
}
