package org.example.kafkaconsumerdatabase;

import org.example.kafkaconsumerdatabase.entity.WikimediaData;
import org.example.kafkaconsumerdatabase.repository.WikimediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
public class KafkaDatabaseConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    private final WikimediaDataRepository dataRepository;

    private static final int MAX_WIKI_EVENT_DATA_LENGTH = 10000;

    @Autowired
    public KafkaDatabaseConsumer(WikimediaDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @KafkaListener(topics = "wikimedia-project", groupId = "myGroup")
    public void consume(String eventMessage) {
        LOGGER.info(String.format("Message received -> %s", eventMessage));

        try {
            // Truncate the event message if it exceeds the maximum length
            if (eventMessage.length() > MAX_WIKI_EVENT_DATA_LENGTH) {
                eventMessage = eventMessage.substring(0, MAX_WIKI_EVENT_DATA_LENGTH);
            }

            // Save the data to the database
            WikimediaData wikimediaData = new WikimediaData();
            wikimediaData.setWikiEventData(eventMessage);
            dataRepository.save(wikimediaData);
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Error saving wikimedia data: " + e.getMessage());
            // Handle the exception as needed, e.g., log the error or take corrective action
        } catch (Exception ex) {
            LOGGER.error("Unexpected error occurred while saving wikimedia data: " + ex.getMessage());
            // Handle other unexpected exceptions
        }
    }
}
