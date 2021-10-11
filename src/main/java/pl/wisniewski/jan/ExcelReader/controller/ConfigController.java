package pl.wisniewski.jan.ExcelReader.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wisniewski.jan.ExcelReader.config.SystemConfig;

import java.util.List;

@RestController
@RequestMapping("/api/config")
@CrossOrigin(origins = "http://localhost:4200")
public class ConfigController {

    private final SystemConfig systemConfig;

    public ConfigController(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }

    @RequestMapping("/columns")
    public ResponseEntity<List<String>> getColumns() {
        return ResponseEntity.ok(systemConfig.getExcelColumnsStructure());
    }
}
