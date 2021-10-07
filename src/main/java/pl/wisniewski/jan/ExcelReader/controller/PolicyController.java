package pl.wisniewski.jan.ExcelReader.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.wisniewski.jan.ExcelReader.mappers.PolicyMapper;
import pl.wisniewski.jan.ExcelReader.model.Policy;
import pl.wisniewski.jan.ExcelReader.model.PolicyDTO;
import pl.wisniewski.jan.ExcelReader.service.PolicyService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/policy")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @RequestMapping("/upload")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Policy>> upload(MultipartFile file) {
        policyService.readXlsxFile(file);
        return ResponseEntity.ok(policyService.findAll().stream()
                .map(PolicyMapper.INSTANCE::policyDTOToPolicy)
                .collect(Collectors.toList()));
    }

}
