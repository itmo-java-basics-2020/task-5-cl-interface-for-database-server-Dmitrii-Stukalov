package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    static DatabaseCommandResult success(String result) {
        return new CommandResult(result, DatabaseCommandStatus.SUCCESS, null);
    }

    static DatabaseCommandResult error(String message) {
        return new CommandResult(null, DatabaseCommandStatus.FAILED, message);
    }

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    class CommandResult implements DatabaseCommandResult {
        private String result;
        private String message;
        private DatabaseCommandStatus status;

        private CommandResult(String result, DatabaseCommandStatus status, String message) {
            this.result = result;
            this.status = status;
            this.message = message;
        }

        @Override
        public Optional<String> getResult() {
            return Optional.ofNullable(this.result);
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return this.status;
        }

        @Override
        public boolean isSuccess() {
            return this.status.equals(DatabaseCommandStatus.SUCCESS);
        }

        @Override
        public String getErrorMessage() {
            return this.message;
        }
    }
}