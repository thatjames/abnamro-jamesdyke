package xyz.slimjim.hungrytales.storage.service;

import org.springframework.stereotype.Component;
import xyz.slimjim.hungrytales.common.dataobject.DataObject;

import java.util.List;

@Component
public interface DataObjectDAO<I extends DataObject> {

    int store(I itemDO);

    I get(int id);

    void update(I itemDO);

    void delete(int id);

    List<I> listAll();
}
