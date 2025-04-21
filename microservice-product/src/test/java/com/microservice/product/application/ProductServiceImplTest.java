package com.microservice.product.application;

import com.microservice.product.domain.Product;
import com.microservice.product.domain.exceptions.ProductException;
import com.microservice.product.infrastructure.dto.*;
import com.microservice.product.infrastructure.http_client.InventoryHttpClient;
import com.microservice.product.infrastructure.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private InventoryHttpClient inventoryHttpClient;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnPageOfProducts() {
        Product product = Product.builder()
                .id(UUID.randomUUID())
                .name("Test Product")
                .price(BigDecimal.TEN)
                .build();

        Page<Product> page = new PageImpl<>(java.util.List.of(product));
        when(productRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<ProductResponseDTO> result = productService.findAll(PageRequest.of(0, 10));

        assertFalse(result.isEmpty());
        assertEquals("Test Product", result.getContent().get(0).name());
        verify(productRepository).findAll(any(Pageable.class));
    }

    @Test
    void findAll_ShouldThrowException_WhenNoProducts() {
        when(productRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());

        ProductException exception = assertThrows(ProductException.class, () ->
                productService.findAll(PageRequest.of(0, 10)));

        assertEquals("No hay Productos registrados", exception.getMessage());
    }

    @Test
    void save_ShouldCreateProductAndCallInventory() {
        SaveNewProductDTO dto = new SaveNewProductDTO("New Product", BigDecimal.valueOf(20), 10);
        when(inventoryHttpClient.save(any())).thenReturn(null);

        var status = productService.save(dto);

        assertEquals(CREATED, status);
        verify(productRepository).save(any(Product.class));
        verify(inventoryHttpClient).save(any());
    }

    @Test
    void findById_ShouldReturnProduct_WhenExists() {
        UUID id = UUID.randomUUID();
        Product product = Product.builder()
                .id(id)
                .name("Product")
                .price(BigDecimal.ONE)
                .build();

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        var response = productService.findById(id);

        assertNotNull(response);
        assertEquals("Product", response.data().attributes().name());
    }

    @Test
    void findById_ShouldThrowException_WhenNotFound() {
        UUID id = UUID.randomUUID();
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ProductException.class, () -> productService.findById(id));
    }

    @Test
    void updateById_ShouldUpdateProduct_WhenFound() {
        UUID id = UUID.randomUUID();
        Product product = Product.builder()
                .id(id)
                .name("Old")
                .price(BigDecimal.ONE)
                .build();

        SaveNewProductDTO dto = new SaveNewProductDTO("Updated", BigDecimal.TEN, 0);

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        var result = productService.updateById(id, dto);

        assertEquals(OK, result);
        verify(productRepository).save(product);
        assertEquals("Updated", product.getName());
    }

    @Test
    void updateById_ShouldThrowException_WhenNotFound() {
        UUID id = UUID.randomUUID();
        SaveNewProductDTO dto = new SaveNewProductDTO("Name", BigDecimal.TEN, 0);

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ProductException.class, () -> productService.updateById(id, dto));
    }

    @Test
    void deleteById_ShouldDeleteProduct_WhenFound() {
        UUID id = UUID.randomUUID();
        Product product = Product.builder().id(id).build();

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        var result = productService.deleteById(id);

        assertEquals(OK, result);
        verify(productRepository).delete(product);
    }

    @Test
    void deleteById_ShouldThrowException_WhenNotFound() {
        UUID id = UUID.randomUUID();
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ProductException.class, () -> productService.deleteById(id));
    }
}
