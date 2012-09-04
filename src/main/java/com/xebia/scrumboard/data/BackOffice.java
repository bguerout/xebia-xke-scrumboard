package com.xebia.scrumboard.data;

import com.google.common.collect.Lists;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;
import com.xebia.scrumboard.representation.Size;
import com.xebia.scrumboard.representation.Sprint;
import org.jongo.MongoCollection;
import org.jongo.ResultMapper;

import java.util.List;

public class BackOffice {

    private final MongoCollection collection;

    public BackOffice(MongoCollection collection) {
        this.collection = collection;
    }

    public List<Sprint> findSprintsByTaskSize(Size size) {
        Iterable<Sprint> sprints = collection.find("{tasks.size:#}", size).as(Sprint.class);
        return Lists.newArrayList(sprints);
    }

    public void removeTasksBySize(Size taskSize) {
        collection.update("{}")
                .multi()
                .concern(WriteConcern.SAFE)
                .with("{$pull:{tasks:{size:#}}}", taskSize);
    }

    public List<String> generateTaskReport(Size taskSize) {
        return collection.aggregate("{ $unwind : '$tasks' }")
                .and("{ $match:{ tasks.size:# }}", taskSize)
                .and("{ $project : {sprint:'$name', task :'$tasks.name', size:'$tasks.size'}}")
                .map(new ResultMapper<String>() {
                    public String map(DBObject result) {
                        return result.toString();
                    }
                });
    }
}
