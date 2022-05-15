package xyz.slimjim.hungrytales.web.converter;

import xyz.slimjim.hungrytales.common.item.Item;
import xyz.slimjim.hungrytales.web.dto.BaseDTO;

public interface ItemDTOConverter<I extends Item, D extends BaseDTO> {

    D fromItemToDTO(I item);

    I fromDTOToItem(D dto);

}
