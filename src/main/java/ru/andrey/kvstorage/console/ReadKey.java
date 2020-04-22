package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class ReadKey implements DatabaseCommand {
    private ExecutionEnvironment environment;
    private String tableName;
    private String databaseName;
    private String objectKey;

    public ReadKey(ExecutionEnvironment environment, String databaseName, String tableName, String objectKey) {
        this.environment = environment;
        this.tableName = tableName;
        this.databaseName = databaseName;
        this.objectKey = objectKey;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        Optional<Database> database = this.environment.getDatabase(this.databaseName);
        if (database.isEmpty()) {
            return DatabaseCommandResult.error("Database " + this.databaseName + " doesn't exist.");
        }
        try {
            String result = database.get().read(this.tableName, this.objectKey);
            return DatabaseCommandResult.success("Value of key " + this.objectKey + " is " + result);
        } catch (DatabaseException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
    }
}
