package com.pharmacoldtrack.platform.shipping.interfaces.rest.transform;

import com.pharmacoldtrack.platform.shipping.domain.model.commands.DeliveryShipmentCommand;
import com.pharmacoldtrack.platform.shipping.interfaces.rest.dto.request.DeliveryShipmentResource;

public class DeliveryShipmentCommandFromResourceAssembler {
    public static DeliveryShipmentCommand toCommandFromResource(Long id, DeliveryShipmentResource resource) {
        return new DeliveryShipmentCommand(id, resource.recipientSignature(), resource.notes());
    }
}