package com.test.dmndemo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class DemoController {

    private final DemoRepository repository;

    @GetMapping("/transport-code/{transportCode}/declaration-type/{declarationType}")
    public Set<String> getDemoProfile(@PathVariable String transportCode, @PathVariable String declarationType, @RequestParam String type) {
        Map<String, String> stringMap = new HashMap<>();
        if (type.equals("ALL")) {
            DemoEntity demoEntity = repository.findDemoByTransportCode(transportCode, declarationType).get();
            stringMap.putAll(demoEntity.getContent().entrySet().stream().filter(i -> !i.getValue().isBlank())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        }
        else if(type.equals("M")) {
            DemoEntity demoEntity = repository.findDemoByTransportCode(transportCode, declarationType).get();
            stringMap.putAll(demoEntity.getContent().entrySet().stream().filter(i -> !i.getValue().isBlank() && i.getValue().equals("M"))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        }
        return stringMap.keySet();
    }

    @GetMapping("/cpc-codes")
    public Set<String> getcpcCodes(@RequestParam List<String> cpcCodes, @RequestParam String type) {
        Map<String, String> stringMap = new HashMap<>();

        if (type.equals("ALL")) {
            List<DemoEntity> demoEntities = repository.findDemoByCpcCodesIn(cpcCodes).get();
            demoEntities.forEach(i -> stringMap.putAll(i.getContent().entrySet().stream().filter(j -> !j.getValue().isBlank())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))));
        } else if (type.equals("M")) {
            List<DemoEntity> demoEntities = repository.findDemoByCpcCodesIn(cpcCodes).get();
            demoEntities.forEach(i -> stringMap.putAll(i.getContent().entrySet().parallelStream().filter(j -> j.getValue().equals("true"))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))));
        }
        return stringMap.keySet();
    }
}
