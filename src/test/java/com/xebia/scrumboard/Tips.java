package com.xebia.scrumboard;

public enum Tips {

    /**
     * You should share Mongo instance.
     *
     * The Java MongoDB driver is thread safe.
     * If you are using in a web serving environment, for example, you should create a single Mongo instance, and you can use it in every request.
     * <p/>
     * The Mongo object maintains an internal pool of connections to the database (default pool size of 10).
     * For every request to the DB (find, insert, etc) the java thread will obtain a connection from the pool, execute the operation, and release the connection.
     * This means the connection (socket) used may be different each time.
     *
     * @see http://www.mongodb.org/display/DOCS/Java+Driver+Concurrency
     */
    SHARE_MONGO_INSTANCE,

    /**
     * With Jongo/Jackson, you can map immutable Pojo with constructor injection using @JsonCreator
     *
     * @see com.xebia.scrumboard.representation.Task
     */
    IMMUTABLE_POJO

}
