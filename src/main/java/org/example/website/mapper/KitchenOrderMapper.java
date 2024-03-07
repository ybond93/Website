package org.example.website.mapper;

import org.example.website.dto.*;
import org.example.website.entities.KitchenViewEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import static java.util.stream.Nodes.collect;

public class KitchenOrderMapper {

    public static List<KitchenOrderDTO> mapListToDTO(List<KitchenViewEntity> entityList) {
        List<KitchenOrderDTO> dtoList = new ArrayList<>();
        Map<Integer, KitchenOrderDTO> orderMap = new HashMap<>();

        for (KitchenViewEntity entity : entityList) {
            if (!orderMap.containsKey(entity.getOrderId())) {
                orderMap.put(entity.getOrderId(), mapToDTO(entity));
            } else {
                KitchenOrderDTO dto = orderMap.get(entity.getOrderId());
                KitchenOrderDTO.OrderDetails orderDetails = new KitchenOrderDTO.OrderDetails();
                orderDetails.setMenuItemId(entity.getMenuItemId());
                orderDetails.setName(entity.getName());
                orderDetails.setAmount(entity.getAmount());
                orderDetails.setCategory(entity.getCategory());
                dto.getOrderDetails().add(orderDetails);
            }
        }

        for (Integer key : orderMap.keySet()) {
            dtoList.add(orderMap.get(key));
        }

        return dtoList;
    }

    public static KitchenOrderDTO mapToDTO(KitchenViewEntity entity) {
        KitchenOrderDTO dto = new KitchenOrderDTO();
        dto.setOrderId(entity.getOrderId());
        dto.setTableNum(entity.getTableNum());
        dto.setStatusOrder(entity.getStatusOrder());
        dto.setStatusMains(entity.getStatusMains());
        dto.setStatusStart(entity.getStatusStart());

        // Map order details
        KitchenOrderDTO.OrderDetails orderDetails = new KitchenOrderDTO.OrderDetails();
        orderDetails.setMenuItemId(entity.getMenuItemId());
        orderDetails.setName(entity.getName());
        orderDetails.setAmount(entity.getAmount());
        orderDetails.setCategory(entity.getCategory());

        List<KitchenOrderDTO.OrderDetails> orderDetailsList = new ArrayList<>();
        orderDetailsList.add(orderDetails);
        dto.setOrderDetails(orderDetailsList);

        return dto;
    }
}
