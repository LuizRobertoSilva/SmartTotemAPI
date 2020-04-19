package br.com.facens.SmartTotem.repositories;

import br.com.facens.SmartTotem.domain.DeviceInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceInformationRepository extends JpaRepository<DeviceInformation, Integer> {
}
