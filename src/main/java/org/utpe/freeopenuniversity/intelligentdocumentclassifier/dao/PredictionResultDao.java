package org.utpe.freeopenuniversity.intelligentdocumentclassifier.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.utpe.freeopenuniversity.intelligentdocumentclassifier.beans.PredictionResult;

import java.util.List;

public interface PredictionResultDao extends MongoRepository<PredictionResult, String> {
}
