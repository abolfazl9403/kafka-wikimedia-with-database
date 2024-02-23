package org.example.kafkaconsumerdatabase.repository;

import org.example.kafkaconsumerdatabase.entity.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  WikimediaDataRepository extends JpaRepository<WikimediaData, Long> {

}
