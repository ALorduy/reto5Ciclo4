package com.retoCinco.service;

import com.retoCinco.model.Order;
import com.retoCinco.repository.OrderRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alberto
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    public Optional<Order> getOrder(int id) {
        return orderRepository.getOrder(id);
    }

    public Order create(Order order) {

        //Maximo id existente en la coleccion
        Optional<Order> orderIdMaxima = orderRepository.lastUserId();

        //Si el id es nulo, entonces valida el maximo id existente en base de datos
        if (order.getId() == null) {
            //
            if (orderIdMaxima.isEmpty()) {
                order.setId(1);
            } 
            else {
                order.setId(orderIdMaxima.get().getId() + 1);
            }
        }

        Optional<Order> e = orderRepository.getOrder(order.getId());
        if (e.isEmpty()) {
            return orderRepository.create(order);
        } else {
            return order;
        }
    }

    public Order update(Order order) {

        if (order.getId() != null) {
            Optional<Order> orderDb = orderRepository.getOrder(order.getId());
            if (!orderDb.isEmpty()) {
                if (order.getStatus() != null) {
                    orderDb.get().setStatus(order.getStatus());
                }
                orderRepository.update(orderDb.get());
                return orderDb.get();
            } else {
                return order;
            }
        } else {
            return order;
        }
    }

    public boolean delete(int id) {
        Boolean aBoolean = getOrder(id).map(order -> {
            orderRepository.delete(order);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    //Listar a los asesores de una zona
    public List<Order> findByZone(String zona) {
        return orderRepository.findByZone(zona);
    }
   
    //Ordenes de un asesor
    public List<Order> ordersSalesManByID(Integer id){
        return orderRepository.ordersSalesManByID(id);
    }
    //Ordenes de un asesor x Estado
    public List<Order> ordersSalesManByState(String state, Integer id){
        return orderRepository.ordersSalesManByState(state, id);
    }

    //Ordenes de un asesor by fecha
    public List<Order> ordersSalesManByDate(String dateStr, Integer id) {
        return orderRepository.ordersSalesManByDate(dateStr,id);
    }
   //Reto 5: Ordenes de un asesor by descripcion
    public List<Order> ordersSalesManByDescription(String description, Integer id){
        return orderRepository.ordersSalesManByDescription(description, id);
    }
}
