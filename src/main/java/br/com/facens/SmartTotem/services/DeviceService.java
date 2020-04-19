package br.com.facens.SmartTotem.services;

import br.com.facens.SmartTotem.domain.Device;
import br.com.facens.SmartTotem.domain.DeviceInformation;
import br.com.facens.SmartTotem.domain.Region;
import br.com.facens.SmartTotem.repositories.DeviceInformationRepository;
import br.com.facens.SmartTotem.repositories.DeviceRepository;
import br.com.facens.SmartTotem.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private DeviceInformationRepository deviceInformationRepository;
    @Autowired
    private RegionRepository regionRepository;

    public DeviceService() {
    }

    public DeviceService(DeviceRepository deviceRepository, DeviceInformationRepository deviceInformationRepository, RegionRepository regionRepository) {
        this.deviceInformationRepository = deviceInformationRepository;
        this.deviceRepository = deviceRepository;
        this.regionRepository = regionRepository;
    }

    public Device createDevice(Device device) {
        device.setId(null);

        if (device.getRegion() != null) {
            Region region;
            if (device.getRegion().getId() != null) {
                Optional<Region> regionOptional = regionRepository.findById(device.getRegion().getId());
                region = regionOptional.orElse(null);
            } else {
                region = regionRepository.findByName(device.getRegion().getName());
            }
            if (region == null) {
                region = new Region();
                region.setName(device.getRegion().getName());
                region = regionRepository.save(region);
            }
            device.setRegion(region);
        }
        device = deviceRepository.save(device);
        return device;
    }


    public Device updateCurrentDevice(Device device) {
        Optional<Device> obj = deviceRepository.findById(device.getId());
        Device deviceFound = obj.orElse(null);
        if (deviceFound == null) {
            return null;
        }
        deviceFound.setCurrentCO2(device.getCurrentCO2());
        deviceFound.setCurrentTemperature(device.getCurrentTemperature());
        deviceFound.setCurrentHumidity(device.getCurrentHumidity());

        DeviceInformation deviceInformation = new DeviceInformation(deviceFound);

        deviceInformationRepository.save(deviceInformation);

        return deviceRepository.save(deviceFound);
    }

    public List<DeviceInformation> getHistoryByDevice(Integer id) {
        Optional<Device> deviceOptional = deviceRepository.findById(id);
        Device device = deviceOptional.orElse(null);
        if (device == null) {
            return new ArrayList<>();
        }
        return device.getDeviceInformationList();
    }

    public List<DeviceInformation> getFullHistory() {
        return deviceInformationRepository.findAll();
    }
    public List<Device> getDevices() {
        return deviceRepository.findAll();
    }
}
