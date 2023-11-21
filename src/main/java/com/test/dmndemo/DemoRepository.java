package com.test.dmndemo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "demo", path = "demo1")
public interface DemoRepository extends JpaRepository<DemoEntity, Integer> {

    @Query(value = "SELECT * FROM demo\n" +
            "WHERE key->>'transportCode' = :transportCode AND key->>'declarationType' = :declarationType", nativeQuery = true)
    Optional<DemoEntity> findDemoByTransportCode(String transportCode, String declarationType);


    @Query(value = "SELECT * FROM demo\n" +
            "WHERE key->>'cpcCode' in (:cpcCodes)", nativeQuery = true)
    Optional<List<DemoEntity>> findDemoByCpcCodesIn(List<String> cpcCodes);
}
