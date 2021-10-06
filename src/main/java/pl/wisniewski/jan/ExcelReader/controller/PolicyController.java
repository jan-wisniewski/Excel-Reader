package pl.wisniewski.jan.ExcelReader.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wisniewski.jan.ExcelReader.model.Policy;
import pl.wisniewski.jan.ExcelReader.service.PolicyService;

import java.util.List;

@RestController
@RequestMapping("/api/policy")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @RequestMapping("/upload")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Policy>> findAll() {
        return ResponseEntity.ok(policyService.findAll());
    }

}
