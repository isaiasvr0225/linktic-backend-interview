package com.microservice.inventory.application;

import com.microservice.inventory.domain.Inventory;
import com.microservice.inventory.domain.exceptions.InventoryException;
import com.microservice.inventory.infrastructure.dto.InventoryAttributesDTO;
import com.microservice.inventory.infrastructure.dto.InventoryDTO;
import com.microservice.inventory.infrastructure.dto.JsonApiData;
import com.microservice.inventory.infrastructure.dto.JsonApiRequestDTO;
import com.microservice.inventory.infrastructure.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

class InventoryServiceImplTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldSaveInventoryAndReturnCreated() {
        UUID productId = UUID.randomUUID();
        int quantity = 100;

        InventoryAttributesDTO attributesDTO = InventoryAttributesDTO.builder()
                .productId(productId)
                .quantity(quantity)
                .build();

        JsonApiData<InventoryAttributesDTO> data = JsonApiData.<InventoryAttributesDTO>builder()
                .attributes(attributesDTO)
                .build();

        JsonApiRequestDTO<InventoryAttributesDTO> requestDTO = JsonApiRequestDTO.<InventoryAttributesDTO>builder()
                .data(data)
                .build();

        var result = inventoryService.save(requestDTO);

        assertEquals(CREATED, result);
        verify(inventoryRepository).save(any(Inventory.class));
    }

    @Test
    void findById_ShouldReturnInventoryDTO_WhenFound() {
        UUID id = UUID.randomUUID();
        Inventory inventory = Inventory.builder()
                .productId(id)
                .quantity(50)
                .build();

        when(inventoryRepository.findById(id)).thenReturn(Optional.of(inventory));

        InventoryDTO dto = inventoryService.findById(id);

        assertNotNull(dto);
        assertEquals(50, dto.quantity());
        assertEquals(id, dto.productId());
    }

    @Test
    void findById_ShouldThrowException_WhenNotFound() {
        UUID id = UUID.randomUUID();
        when(inventoryRepository.findById(id)).thenReturn(Optional.empty());

        InventoryException exception = assertThrows(InventoryException.class, () ->
                inventoryService.findById(id));

        assertEquals("Not found", exception.getMessage());
    }

    @Test
    void updateById_ShouldUpdateQuantity_WhenFound() {
        UUID id = UUID.randomUUID();
        Inventory inventory = Inventory.builder()
                .productId(id)
                .quantity(20)
                .build();

        when(inventoryRepository.findById(id)).thenReturn(Optional.of(inventory));

        var result = inventoryService.updateById(id, 80);

        assertEquals(OK, result);
        assertEquals(80, inventory.getQuantity());
        verify(inventoryRepository).save(inventory);
    }

    @Test
    void updateById_ShouldThrowException_WhenNotFound() {
        UUID id = UUID.randomUUID();
        when(inventoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(InventoryException.class, () -> inventoryService.updateById(id, 100));
    }

    @Test
    void deleteById_ShouldDeleteInventory_WhenFound() {
        UUID id = UUID.randomUUID();
        Inventory inventory = Inventory.builder()
                .productId(id)
                .quantity(10)
                .build();

        when(inventoryRepository.findById(id)).thenReturn(Optional.of(inventory));

        var result = inventoryService.deleteById(id);

        assertEquals(OK, result);
        verify(inventoryRepository).delete(inventory);
    }

    @Test
    void deleteById_ShouldThrowException_WhenNotFound() {
        UUID id = UUID.randomUUID();
        when(inventoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(InventoryException.class, () -> inventoryService.deleteById(id));
    }
}
