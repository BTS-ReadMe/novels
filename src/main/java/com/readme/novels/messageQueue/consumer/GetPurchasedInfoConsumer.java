package com.readme.novels.messageQueue.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.readme.novels.messageQueue.producer.GetPurchasedInfoProducer;
import com.readme.novels.messageQueue.dto.EpisodeNovelDto;
import com.readme.novels.messageQueue.dto.GetPurchasedInfoDto;
import com.readme.novels.novels.repository.INovelsRepository;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetPurchasedInfoConsumer {

    private final GetPurchasedInfoProducer getPurchasedInfoProducer;
    private final INovelsRepository novelsRepository;

    @KafkaListener(id = "getPurchasedInfo", topics = "getPurchasedInfo")
    public void listen(String kafkaMessage) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        GetPurchasedInfoDto getPurchasedInfoDto = mapper.readValue(kafkaMessage,
            GetPurchasedInfoDto.class);

        List<EpisodeNovelDto> result = novelsRepository.findEpisodeNovelInfoByIds(
            getPurchasedInfoDto.getEpisodeIds());

        getPurchasedInfoProducer.sendGetPurchasedInfoResult(getPurchasedInfoDto.getId(), result);
    }
}
