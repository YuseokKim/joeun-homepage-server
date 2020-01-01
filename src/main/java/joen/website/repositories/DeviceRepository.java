package joen.website.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import joen.website.domain.Device;

public interface DeviceRepository extends JpaRepository<Device, Serializable> {
	
	Device findByModel(String model);
	
	@Query(value="SELECT fcm_token FROM device WHERE fcm_token IS NOT NULL GROUP BY model;", nativeQuery=true)
	List<String> findFcmTokenList();

}
