package com.example.contentmanagementsystem.service;

import com.example.contentmanagementsystem.dto.FileContentDto;
import com.example.contentmanagementsystem.entity.FileContent;
import com.example.contentmanagementsystem.entity.FileInfo;
import com.example.contentmanagementsystem.entity.UserHistory;
import com.example.contentmanagementsystem.repo.FileAuditRepo;
import com.example.contentmanagementsystem.repo.FileContentRepo;
import com.example.contentmanagementsystem.utils.AppUtils;
import com.example.contentmanagementsystem.utils.file.GetMime;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.diff.Diff;
import org.javers.core.json.JsonConverter;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.core.metamodel.object.CdoSnapshotState;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileContentRepo fileContentsRepo;

    private final Javers javers;

    private final FileAuditRepo fileAuditRepo;

    @Override
    @Transactional
    public FileContentDto saveFileContents(FilePart filePart) throws IOException {
        FileContent fileContentDto = getFileContent(filePart);
        return AppUtils.entityToDto(fileAuditRepo.save(fileContentDto));
    }

    private static FileContent getFileContent(FilePart filePart) throws IOException {
        FileContent fileContent = new FileContent();
        fileContent.setFileName(filePart.filename());
        FileInfo fileInfo = getFileInfo(filePart);
        fileContent.setFileInfo(fileInfo);
        fileContent.setActual_data(GetMime.getMime(filePart));
        return fileContent;
    }

    private static FileInfo getFileInfo(FilePart filePart) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(filePart.filename());
        fileInfo.setContent_type(String.valueOf(filePart.headers().getContentType()));
        fileInfo.setSize(filePart.headers().size());
        fileInfo.setPath("test/this/is/test");
        fileInfo.setUserHistory(new UserHistory());
        return fileInfo;
    }

    @Override
    public FileContentDto updateFileContents(FilePart filePart, String id) throws IOException {

        FileContent fileContent= getFileContent(filePart);
        fileContent.setId(id);
        return AppUtils.entityToDto(fileAuditRepo.save(fileContent));
    }

    public  FileContentDto rollbackToSnapshot(String entityId, int snapshotVersion) {
        QueryBuilder jqlQuery = QueryBuilder.byInstanceId(entityId, FileContent.class).withVersion(snapshotVersion);
        AtomicReference<FileContent> fileContent= new AtomicReference<>(new FileContent());

        javers.findSnapshots(jqlQuery.build()).forEach(snapshot -> {
            CdoSnapshotState cdoSnapshotState = snapshot.getState();
            String json = javers.getJsonConverter().toJson(cdoSnapshotState);

            // Convert JSON to object using Gson or any other JSON library
            Gson gson = new Gson();
            fileContent.set(gson.fromJson(json, FileContent.class));

        });

        return AppUtils.entityToDto(fileAuditRepo.save(fileContent.get()));
    }

    @Override
    public Mono<String> getFileContentChanges(String id) {

        QueryBuilder jqlQuery = QueryBuilder.byInstanceId(id, FileContent.class);

        Changes changes = javers.findChanges(jqlQuery.build());
        String formattedChanges = "<pre>" + changes.prettyPrint() + "</pre>";
        return Mono.just(formattedChanges);
    }

    @Override
    public Mono<String> getFileContentChanges() {
        QueryBuilder jqlQuery = QueryBuilder.byClass(FileContent.class);
        Changes changes = javers.findChanges(jqlQuery.build());
        String formattedChanges = "<pre>" + changes.prettyPrint() + "</pre>";
        return Mono.just(formattedChanges);
    }

    @Override
    public Mono<String> getFileContentStates() {
        QueryBuilder jqlQuery = QueryBuilder.byClass(FileContent.class);

        List<CdoSnapshot> changes = new ArrayList<>(javers.findSnapshots(jqlQuery.build()));

        changes.sort((o1, o2) -> -1 * o1.getCommitMetadata().getCommitDate().compareTo(o2.getCommitMetadata().getCommitDate()));

        JsonConverter jsonConverter = javers.getJsonConverter();

        return Mono.just(jsonConverter.toJson(changes));
    }

    @Override
    public Mono<String> getFileContentStates(String login) {

        QueryBuilder jqlQuery = QueryBuilder.byInstanceId(login, FileContent.class);

        List<CdoSnapshot> changes = javers.findSnapshots(jqlQuery.build());

        changes.sort((o1, o2) -> -1 * o1.getCommitMetadata().getCommitDate().compareTo(o2.getCommitMetadata().getCommitDate()));

        JsonConverter jsonConverter = javers.getJsonConverter();

        return Mono.just(jsonConverter.toJson(changes));

    }


    public Mono<String> getFileContentStates(String id, String id1) {
        FileContent fileContent1 = fileContentsRepo.findById(id).block();
        FileContent fileContent2 = fileContentsRepo.findById(id1).block();


        Diff diff = javers.compare(fileContent1, fileContent2);

        JsonConverter jsonConverter = javers.getJsonConverter();

        return Mono.just(jsonConverter.toJson(diff.getChanges()));
    }


}
