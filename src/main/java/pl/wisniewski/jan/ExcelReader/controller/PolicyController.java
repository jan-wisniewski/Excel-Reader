package pl.wisniewski.jan.ExcelReader.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wisniewski.jan.ExcelReader.model.Policy;
import pl.wisniewski.jan.ExcelReader.service.PolicyService;

import java.util.List;

@RestController
@RequestMapping("/api/policy")
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService policyService;

    @RequestMapping("/upload")
    public ResponseEntity<List<Policy>> findAll() {
        return ResponseEntity.ok(policyService.findAll());
    }

}
