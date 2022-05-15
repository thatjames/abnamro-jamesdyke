package xyz.slimjim.hungrytales.storage.service;

import org.springframework.stereotype.Component;
import xyz.slimjim.hungrytales.common.item.Item;

import java.util.List;

@Component
public interface ItemStorageService<I extends Item> {

    int store(I itemDO);

    I get(int id);

    void update(I itemDO);

    void delete(int id);

    List<I> listAll();
}
