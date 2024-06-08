package pro.sku.SQL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sku.SQL.service.InfoService;

@RestController
@RequestMapping("/port")
public class InfoController {
    private final InfoService infoService;
    @Autowired
    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping
    Integer getPort() {
        return infoService.getPort();
    }
}