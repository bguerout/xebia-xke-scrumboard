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
        //tips use parameter token '#'
        return null;
    }

    public void removeTasksBySize(Size taskSize) {
        //tips: remove tasks with update operation...
    }

    public List<String> generateTaskReport(Size taskSize) {
        /**
         You have to create documents as follow :
         {
         task : "task name",
         size : "task size",
         sprint : "sprint name",
         }
         and return them as JSON Strings
         tips: use new aggregation framework and map method...
         */
        return null;
    }
}
