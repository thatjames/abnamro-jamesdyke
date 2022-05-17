package xyz.slimjim.hungrytales.web.converter;

import xyz.slimjim.hungrytales.common.dataobject.DataObject;
import xyz.slimjim.hungrytales.web.dto.BaseDTO;

public interface DataObjectDTOConverter<I extends DataObject, D extends BaseDTO> {

    D fromItemToDTO(I item);

    I fromDTOToItem(D dto);

}
