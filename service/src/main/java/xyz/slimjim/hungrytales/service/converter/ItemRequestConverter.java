package xyz.slimjim.hungrytales.service.converter;

import org.springframework.stereotype.Component;
import xyz.slimjim.hungrytales.common.item.Item;
import xyz.slimjim.hungrytales.common.request.BaseRequest;

@Component
public abstract class ItemRequestConverter<I extends Item> {

    public abstract <R extends BaseRequest> I convertRequest(R request);
}
