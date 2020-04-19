package br.com.facens.SmartTotem.controllers;

import br.com.facens.SmartTotem.domain.Device;
import br.com.facens.SmartTotem.domain.DeviceInformation;
import br.com.facens.SmartTotem.services.DeviceService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    public DeviceController() {
    }

    public DeviceController(DeviceService deviceService) {
        deviceService = this.deviceService;
    }

    @RequestMapping(value = "/devices", method = RequestMethod.POST)
    public ResponseEntity<Device> createDevice(@RequestBody Device device) {
        Device newDevice = deviceService.createDevice(device);
        return ResponseEntity.ok(newDevice);
    }
    @RequestMapping(value = "/devices", method = RequestMethod.PUT)
    public ResponseEntity<Device> updateDevice(@RequestBody Device device) {
        Device newDevice = deviceService.updateCurrentDevice(device);
        return ResponseEntity.ok(newDevice);
    }

    @RequestMapping(value = "/devices/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<DeviceInformation>> getHistoryByDevice(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(deviceService.getHistoryByDevice(id));
    }

    @RequestMapping(value = "/devices", method = RequestMethod.GET)
    public ResponseEntity<List<Device>> getDevices() {
        return ResponseEntity.ok(deviceService.getDevices());
    }
    @RequestMapping(value = "/devices/all", method = RequestMethod.GET)
    public ResponseEntity<List<DeviceInformation>> getFullHistory() {
        return ResponseEntity.ok(deviceService.getFullHistory());
    }

}
