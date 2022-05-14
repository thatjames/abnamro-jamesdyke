package xyz.slimjim.hungrytales.storage.service;

import org.springframework.stereotype.Component;
import xyz.slimjim.hungrytales.storage.item.ItemDO;

@Component
public interface ItemDOStorageService<I extends ItemDO> {

    int store(I itemDO);

    I get(int id);

    void update(I itemDO);

    void delete(int id);
}
