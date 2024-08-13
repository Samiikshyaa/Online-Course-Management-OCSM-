package com.infodevelopers.ocsm.service;

import java.util.List;

public interface GenericService<D> {
    D create(D d) throws Exception;
    void deleteById(Integer id);
    List<D> findAll();
    D update(D d);
}
