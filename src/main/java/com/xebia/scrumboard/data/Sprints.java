package com.xebia.scrumboard.data;

import static com.google.common.collect.Lists.newArrayList;

import java.net.UnknownHostException;
import java.util.List;

import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.xebia.scrumboard.representation.Sprint;

public class Sprints {

    static MongoCollection sprints;

    static {
        Mongo mongo;
        try {
            mongo = new Mongo("127.0.0.1", 27017);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (MongoException e) {
            throw new RuntimeException(e);
        }

        Jongo jongo = new Jongo(mongo.getDB("xebia"));
        sprints = jongo.getCollection("sprints");
    }

    public static List<Sprint> get() {
        return newArrayList(sprints.find().as(Sprint.class));
    }

    public static Sprint get(String id) {
        if (ObjectId.isValid(id))
            return sprints.findOne(new ObjectId(id)).as(Sprint.class);
        else
            return null;
    }

    public static Sprint put(Sprint sprint) {
        sprints.save(sprint);
        return sprint;
    }

    public static void delete(String id) {
        sprints.remove(new ObjectId(id));
    }
}
