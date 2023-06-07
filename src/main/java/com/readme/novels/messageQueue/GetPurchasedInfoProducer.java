package com.readme.novels.messageQueue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.readme.novels.messageQueue.dto.EpisodeNovelDto;
import com.readme.novels.messageQueue.dto.GetPurchasedInfoResultDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetPurchasedInfoProducer {

    private final KafkaTemplate<String, Object> getPurchasedInfoResultDtoKafkaTemplate;

    public void sendGetPurchasedInfoResult(String id, List<EpisodeNovelDto> info) {

        ObjectMapper mapper = new ObjectMapper();
        GetPurchasedInfoResultDto getPurchasedInfoResultDto = new GetPurchasedInfoResultDto();
        getPurchasedInfoResultDto.setId(id);
        getPurchasedInfoResultDto.setPurchased(info);

        try {
            String data = mapper.writeValueAsString(getPurchasedInfoResultDto);
            getPurchasedInfoResultDtoKafkaTemplate.send("getPurchasedInfoResult", data);
        } catch (Exception e){
            log.info(e.toString());
        }
    }
}
