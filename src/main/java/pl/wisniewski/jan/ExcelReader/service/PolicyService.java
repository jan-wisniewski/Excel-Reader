package pl.wisniewski.jan.ExcelReader.service;

import org.springframework.stereotype.Service;
import pl.wisniewski.jan.ExcelReader.model.Policy;

import java.util.List;

@Service
public class PolicyService {

    public List<Policy> findAll() {
        return List.of(new Policy());
    }

}
