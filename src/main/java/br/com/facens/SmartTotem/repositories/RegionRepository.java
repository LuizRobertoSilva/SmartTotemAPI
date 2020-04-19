package br.com.facens.SmartTotem.repositories;

import br.com.facens.SmartTotem.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

    Region findByName(String name);
}
