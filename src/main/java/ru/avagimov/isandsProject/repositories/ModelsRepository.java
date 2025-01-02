package ru.avagimov.isandsProject.repositories;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import ru.avagimov.isandsProject.models.Device;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelsRepository extends JpaRepository<Device, Integer>, JpaSpecificationExecutor<Device> {
    default List<Device> searchByField(String searchQuery, String fieldName) {
        return findAll((root, query, builder) -> {
            return builder.like(builder.lower(root.get(fieldName)), "%" + searchQuery.toLowerCase() + "%");
        });
    }
    List<Device> findAll(Sort sort);
}
