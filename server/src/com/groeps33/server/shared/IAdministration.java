package com.groeps33.server.shared;

import java.util.List;

/**
 * Created by Bram on 5/29/2016.
 *
 * @author Bram Hoendervangers
 */
public interface IAdministration<T> {


    void register(T t);

    void remove(T t);

    List<T> getAll();

    T getById(String id);
}
