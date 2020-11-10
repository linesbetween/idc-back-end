package org.utpe.freeopenuniversity.intelligentdocumentclassifier.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.utpe.freeopenuniversity.intelligentdocumentclassifier.beans.FileUploaded;
import org.utpe.freeopenuniversity.intelligentdocumentclassifier.beans.PredictionResult;

public interface FileDao extends MongoRepository<FileUploaded, String> {
}

