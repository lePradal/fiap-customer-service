package br.com.fiap.postech.soat.techchallenge.adapter.out.mapper;

import br.com.fiap.postech.soat.techchallenge.adapter.out.persistence.ProductEntity;
import br.com.fiap.postech.soat.techchallenge.domain.models.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    default Product toDomain(ProductEntity entity) {
        if (entity == null) return null;
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getCategory(),
                entity.getDescription(),
                entity.isActive()
        );
    }

    default ProductEntity toEntity(Product product) {
        if (product == null) return null;

        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        entity.setCategory(product.getCategory());
        entity.setDescription(product.getDescription());
        entity.setActive(product.isActive());

        return entity;
    }
}
