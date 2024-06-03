package pro.sku.SQL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sku.SQL.service.InfoService;

@RestController
@RequestMapping ("/info")
public class InfoController {
    @Autowired
    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping ("/port")
    public String getPort () {
        return infoService.getCurrentPort ();
    }
    @GetMapping ("/sum")
    public ResponseEntity <Integer> getSum () {
        return ResponseEntity.ok(infoService.getSum());
    }
}