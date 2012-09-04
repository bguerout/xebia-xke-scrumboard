package com.xebia.scrumboard.data;

import com.mongodb.DBObject;
import com.xebia.scrumboard.representation.Sprint;

import java.util.List;

public class BackOffice {

    //Create a jongo instance

    public static List<Sprint> findSprintsWithXLTasks() {
        return null;
    }

    public static void removeXLTasks(String sprintId) {
        //tips: remove tasks with update operation...
    }

    public static List<String> generateTaskReport() {
        /**
         You have to create a document as follow :
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
