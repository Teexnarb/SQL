package pro.sku.SQL.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pro.sku.SQL.model.Avatar;
import pro.sku.SQL.repository.AvatarRepository;

import java.util.List;

@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final Logger logger = LoggerFactory.getLogger(AvatarService.class);
    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public List<Avatar> getPageOfAvatars(int pageNumber, int pageSize) {
        logger.info("Получение аватаров со страницы {} размером {}", pageNumber, pageSize);
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return avatarRepository.findAll(pageRequest).getContent();
    }
}