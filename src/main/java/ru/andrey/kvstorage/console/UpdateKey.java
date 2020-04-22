package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class UpdateKey implements DatabaseCommand {
    private ExecutionEnvironment environment;
    private String tableName;
    private String databaseName;
    private String objectKey;
    private String objectValue;

    public UpdateKey(ExecutionEnvironment environment, String databaseName, String tableName, String objectKey, String objectValue) {
        this.environment = environment;
        this.tableName = tableName;
        this.databaseName = databaseName;
        this.objectKey = objectKey;
        this.objectValue = objectValue;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        Optional<Database> database = this.environment.getDatabase(this.databaseName);
        if (database.isEmpty()) {
            return DatabaseCommandResult.error("Database " + this.databaseName + " doesn't exist.");
        }
        try {
            database.get().write(this.tableName, this.objectKey, this.objectValue);
            return DatabaseCommandResult.success("Key " + this.objectKey + " was updated with value " + this.objectValue);
        } catch (DatabaseException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
    }
}
