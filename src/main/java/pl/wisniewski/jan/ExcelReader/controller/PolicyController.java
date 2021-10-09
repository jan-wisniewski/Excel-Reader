package pl.wisniewski.jan.ExcelReader.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.wisniewski.jan.ExcelReader.model.PolicyDTO;
import pl.wisniewski.jan.ExcelReader.service.PolicyService;

import java.util.List;

@RestController
@RequestMapping("/api/policy")
@CrossOrigin(origins = "http://localhost:4200")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @RequestMapping("/upload")
    public ResponseEntity<List<PolicyDTO>> upload(MultipartFile file) {
        List<PolicyDTO> policyDTOS = policyService.readXlsxFile(file);
        return ResponseEntity.ok(policyDTOS);
    }

    @RequestMapping("/all")
    public ResponseEntity<List<PolicyDTO>> all() {
        return ResponseEntity.ok(policyService.findAll());
    }

}
